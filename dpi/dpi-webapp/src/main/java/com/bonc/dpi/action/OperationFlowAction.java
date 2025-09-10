package com.bonc.dpi.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.bonc.dpi.dao.entity.OperationFlow;
import com.bonc.dpi.dao.entity.OperationFlowExportReturn;
import com.bonc.dpi.dao.entity.OperationFlowImportReturn;
import com.bonc.dpi.service.i.OperationFlowServiceI;
import com.bonc.dpi.utils.DpiUtils;
import com.bonc.dpi.utils.ExcelUtils;
import com.bonc.dpi.utils.FileUtils;

@Controller
@RequestMapping(value = "/operationflow")
public class OperationFlowAction implements ApplicationContextAware {

	private ApplicationContext applicationContext;
	
	private static final String maxDataNumForExcle = DpiUtils.MAX_DATANUM_FOR_EXCLE;//导入excle的最大数
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * 下载模板
	 * 
	 * @param request
	 * @param name
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/download")
	public void doDownload(HttpServletRequest request, HttpServletResponse response, String templateName)
			throws Exception {
		// 获取模板文件
		File file = new File(request.getServletContext()
				.getRealPath(File.separator + "templatecodetable" + File.separator + templateName));
		try (InputStream is = new FileInputStream(file)) {
			byte[] body = new byte[is.available()];
			// 将输入流写入byte[]数组中
			is.read(body);
			String name = file.getName();
			// 浏览器下载
			buildResponseEntity(response, body, name);
		}
	}

	/**
	 * 导入
	 * 
	 * @param file
	 * @param entityName
	 * @param beanName
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/import")
	@ResponseBody
	public OperationFlowImportReturn doImport(MultipartFile file, String entityName, String beanName, String type,
			String fieldNums) throws Exception {
		
		HashMap mapparam = DpiUtils.editDoImportParam(entityName,beanName,type,fieldNums);//编辑批量导入的参数
		entityName =(String)mapparam.get("entityName");
		beanName =(String)mapparam.get("beanName");
		fieldNums =(String)mapparam.get("fieldNums");
		
		InputStream inputStream = file.getInputStream();
		List<List<String>> list = null;
		String[] nums = fieldNums.split(",");
		if ("1".equals(type)) {
			// 导入excel流
			String fileName = file.getOriginalFilename();
			// excel每行数据转成List<String>
			list = ExcelUtils.getListByRow(inputStream, fileName);
			
			Integer maxNum = Integer.parseInt(maxDataNumForExcle);//导入excle的最大数
			if(list.size()>maxNum){
				OperationFlowImportReturn ofr = new OperationFlowImportReturn();
				ofr.setFalseType("3");//falseType=3是excle导入数据超出最大数
				ofr.setMaxDataNumForExcle(maxDataNumForExcle);//用于页面展示最大数
				return ofr;
			}
			
		} else if ("2".equals(type)) {
			// txt每行数据转成List<String>
			list = FileUtils.createList(inputStream, "~");
		}
		
		// 获取反射实体类的class
		Class<?> cls = Class.forName(entityName);
		List<OperationFlow> oList = new ArrayList<OperationFlow>();
		
		for (int j = 0; j < list.size(); j++) {
			List<String> strList = list.get(j);
			// 创建实体类
			Object o = cls.newInstance();
			Field[] fs = cls.getDeclaredFields();
			for (int i = 0; i < nums.length; i++) {
				Field f = fs[Integer.valueOf(nums[i])];
				f.setAccessible(true);
				f.set(o, StringUtils.trim(strList.get(i)));
			}
			OperationFlow of = (OperationFlow) o;
			// set值List<String>
			of.setList(strList);
			// set对应excel行号
			of.setRowNum((j + 1) + "");
			oList.add(of);
		}
		OperationFlowServiceI ofi = (OperationFlowServiceI) applicationContext.getBean(beanName);
		OperationFlowImportReturn ofb = ofi.doImport(oList,type);
		if (ofb == null) {
			return new OperationFlowImportReturn();
		}
		return ofb;
	}
	
	/**
	 * 导出
	 * 
	 * @param exportParameter
	 * @param entityName
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/export")
	public void doExport(
			HttpServletRequest request, 
			HttpServletResponse response, 
			String exportParameter,
			String entityName, 
			String beanName,
			String exportType,
			String templateName,
			String fieldNums,
			String textFieldNums) throws Exception {
		HashMap mapparam = DpiUtils.editDoExportParam(entityName, beanName, templateName, fieldNums,textFieldNums);//编辑批量导出的参数
		entityName =(String)mapparam.get("entityName");
		beanName =(String)mapparam.get("beanName");
		templateName =(String)mapparam.get("templateName");
		fieldNums =(String)mapparam.get("fieldNums");
		textFieldNums =(String)mapparam.get("textFieldNums");
		// 将查询参数转为json
		JSONObject jo = (JSONObject) JSONObject.parse(exportParameter);
		jo = DpiUtils.editJo(jo);//xuxl修改，如果含有产品名称，则对产品名称加密
		// 获取json所有key
		Set<String> set = jo.keySet();
		// 创建class用于反射
		Class<?> cls = Class.forName(entityName);
		// 创建参数对象
		OperationFlow o = (OperationFlow) cls.newInstance();
		// 获取所有字段
		Field[] fs = cls.getDeclaredFields();
		// 循环便利字段，反射到对象中
		for (String key : set) {
			for (int i = 0; i < fs.length; i++) {
				Field f = fs[i];
				f.setAccessible(true);
				if (f.getName().equals(key)) {
					f.set(o, jo.get(key));
					break;
				}
			}
		}
		// 根绝beanName调用导出查询方法
		OperationFlowServiceI ofi = (OperationFlowServiceI) applicationContext.getBean(beanName);
		if(exportType.equals("1")){
			o.setExportTypeProdExcel("1");  //将Excel进行解密  
		}
		OperationFlowExportReturn ofer = ofi.doExport(o);
		// 获得查询结果
		if (exportType.equals("1")) {
			doExportExcel(request, response, templateName, fieldNums, ofer);
		} else {
			doExportText(response, fieldNums, ofer,textFieldNums);
		}
	}

	public static void doExportText(HttpServletResponse response, String num, OperationFlowExportReturn ofer,String textFieldNums)
			throws IllegalAccessException, IOException {
		String[] nums = num.split(",");
		List<? extends OperationFlow> list = ofer.getList();
		// 根据文件名称，查询返回值，需要获取的字段位数，获取txt文件
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			Object o = list.get(i);
			Field[] fs = o.getClass().getDeclaredFields();
			for (int j = 0; j < nums.length; j++) {
				Field f = fs[Integer.valueOf(nums[j])];
				f.setAccessible(true);
				Object s = f.get(o);
				if (s == null) {
                } else {
					sb.append(s);
				}
				if (j != nums.length - 1) {
					sb.append("~");
				}
			}
			sb.append("\r\n");
		}
		byte[] b1 = sb.toString().getBytes();
		if(!"null".equals(textFieldNums)){
			//输出压缩文件
			sb.setLength(0);
			nums = textFieldNums.split(",");
			// 根据文件名称，查询返回值，需要获取的字段位数，获取txt文件
			for (int i = 0; i < list.size(); i++) {
				Object o = list.get(i);
				Field[] fs = o.getClass().getDeclaredFields();
				for (int j = 0; j < nums.length; j++) {
					Field f = fs[Integer.valueOf(nums[j])];
					f.setAccessible(true);
					Object s = f.get(o);
					if (s == null) {
                    } else {
						sb.append(s);
					}
					if (j != nums.length - 1) {
						sb.append("~");
					}
				}
				sb.append("\r\n");
			}
			byte[] b2=sb.toString().getBytes();
			buildResponseZip(response,b1,ofer.getTxtName(),b2,ofer.getNewTxtName());
		}else{
			// 浏览器下载
			buildResponseEntity(response, b1, ofer.getTxtName());
		}
	}

	public static void doExportExcel(
			HttpServletRequest request, 
			HttpServletResponse response, 
			String templateName, 
			String num,
			OperationFlowExportReturn ofer) throws IOException, IllegalAccessException {
		String[] nums = num.split(",");
		List<? extends OperationFlow> list = ofer.getList();
		// 获取excel模板
		File file = new File(request.getServletContext().getRealPath(File.separator + "templatecodetable" + File.separator + templateName));
		InputStream inputStream = new FileInputStream(file);
		// 根据excel模板将list写入到excel中
		byte[] b = null;
		if (list == null || list.size() == 0) {
			b = new byte[inputStream.available()];
			inputStream.read(b);
		} else {
			Workbook workbook = ExcelUtils.getWorkBook(inputStream, templateName);
			b = ExcelUtils.getExcel(workbook, list, nums);
		}
		// 浏览器下载
		buildResponseEntity(response, b, ofer.getExcelName());
	}

	/**
	 * 构建下载类
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	private static void buildResponseEntity(HttpServletResponse response, byte[] body, String fileName) throws IOException {
		response.setContentType("application/octet-stream;");
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		response.setCharacterEncoding("utf-8");
		OutputStream os = response.getOutputStream();
		os.write(body);
		os.flush();
		os.close();
	}
	
	private static void buildResponseZip(HttpServletResponse response, byte[] body1,String fileName1,byte[] body2,String fileName2) throws IOException {
		//设置压缩包名
		String filename=fileName2.split("\\.")[0]+".zip";
		//设置下载的方式
		response.setContentType("application/force-download");
		response.setHeader("Content-Disposition", "attachment;fileName=" + filename);
		//设置文件编码
		response.setCharacterEncoding("utf-8");
		//创建zip文件输出流
		ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
		zos.setEncoding("utf-8");
		//将文件写入zip内，即将文件进行打包
		zos.putNextEntry(new ZipEntry(fileName1));
		zos.write(body1);
		//关闭单个文件的输出流
		zos.closeEntry();

		zos.putNextEntry(new ZipEntry(fileName2));
		zos.write(body2);
		zos.closeEntry();

		//关闭整个压缩文件的输出流
		zos.close();
	}


	/**
	 * 检查导出文件的数量
	 * @param exportParameter
	 * @param entityName
	 * @param beanName
	 * @param exportType
	 * @param templateName
	 * @param fieldNums
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/checkExportFileDataNum")
	@ResponseBody
	public String checkExportFileDataNum(
			String exportParameter,
			String entityName, 
			String beanName,
			String exportType,
			String templateName,
			String fieldNums) throws Exception {
		
		HashMap mapparam = DpiUtils.editDoExportParam(entityName, beanName, templateName, fieldNums,"");//编辑批量导出的参数
		entityName =(String)mapparam.get("entityName");
		beanName =(String)mapparam.get("beanName");
		templateName =(String)mapparam.get("templateName");
		fieldNums =(String)mapparam.get("fieldNums");
		
		// 将查询参数转为json
		JSONObject jo = (JSONObject) JSONObject.parse(exportParameter);
		jo = DpiUtils.editJo(jo);//xuxl修改，如果含有产品名称，则对产品名称加密
		// 获取json所有key
		Set<String> set = jo.keySet();
		// 创建class用于反射
		Class<?> cls = Class.forName(entityName);
		// 创建参数对象
		OperationFlow o = (OperationFlow) cls.newInstance();
		// 获取所有字段
		Field[] fs = cls.getDeclaredFields();
		// 循环便利字段，反射到对象中
		for (String key : set) {
			for (int i = 0; i < fs.length; i++) {
				Field f = fs[i];
				f.setAccessible(true);
				if (f.getName().equals(key)) {
					f.set(o, jo.get(key));
					break;
				}
			}
		}
		
		// 根绝beanName调用导出查询方法
		OperationFlowServiceI ofi = (OperationFlowServiceI) applicationContext.getBean(beanName);
		Integer exportDataNum = ofi.exportDataNum(o, exportType);
		Integer maxNum = Integer.parseInt(maxDataNumForExcle);//导入excle的最大数
		if(exportDataNum>maxNum){
			return "0";//不可以导，大于最大数量
		}
		
		return "1";//可以导
	}
}