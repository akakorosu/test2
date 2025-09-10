package com.bonc.dpi.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bonc.common.cst.CST;
import com.bonc.common.utils.Ajax;
import com.bonc.dpi.service.FileHdfsService;
import com.bonc.dpi.service.FileImportService;

@Controller
@RequestMapping(value = "/download")
public class DownloadCenterAction {
	
	@Resource
	private FileImportService fileImportService;
	
	@Resource
	private FileHdfsService fileHdfsService;
	
	@RequestMapping(value = "/upload")
	@ResponseBody
	public String uploadFile(HttpServletRequest request,HttpServletResponse response,MultipartFile file) throws Exception {
		
		String path =  request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
		String filename = file.getOriginalFilename();
//		String fileUUIDname = UUID.randomUUID().toString();
//		String suffix = filename.substring(filename.lastIndexOf(".")+1);
//		String newFileName = fileUUIDname + "." + suffix;
		String saveFile = path +"\\"+ filename;
//		if(filename.endsWith(".xls") || filename.endsWith(".xlsx")){
			File targetFile = new File(saveFile);
			if (!targetFile.exists()) {
				targetFile.mkdirs();   
			}
			file.transferTo(targetFile);
//		}
		return Ajax.responseString(CST.RES_SECCESS);
	}
	
	@RequestMapping(value = "/download")
	@ResponseBody
	public String downloadFile(HttpServletRequest request,HttpServletResponse response,String path,String name) throws Exception {
		
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		File file = new File(path);
		name = name.replaceAll("\\s*", "");
		try {
				if (file.exists()) {
					response.reset();
					response.setContentType(request.getServletContext().getMimeType(name));
					// 设置文件名
					response.setHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode(name, "UTF-8"));
					byte[] buffer = new byte[1024];
					fis = new FileInputStream(file);
					bis = new BufferedInputStream(fis);
					OutputStream os = response.getOutputStream();
					int i = bis.read(buffer);
					while (i != -1) {
						os.write(buffer, 0, i);
						i = bis.read(buffer);
					}
				} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (bis != null) {
					bis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
