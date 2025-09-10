package com.bonc.dpi.action;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonc.dpi.dao.entity.DIdentifiedPandect;
import com.bonc.dpi.dao.entity.DmDContentInfo;
import com.bonc.dpi.dao.entity.DmDLexiconInfo;
import com.bonc.dpi.dao.entity.DpiCommon;
import com.bonc.dpi.service.DIdentifiedPandectService;
import com.bonc.dpi.service.KeywordSearchRuleService;
import com.bonc.dpi.service.PublicCPeriodService;
import com.bonc.dpi.utils.DpiUtils;
import com.github.abel533.echarts.Grid;
import com.github.abel533.echarts.Legend;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.Title;
import com.github.abel533.echarts.Tooltip;
import com.github.abel533.echarts.axis.AxisLabel;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.SplitLine;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.AxisType;
import com.github.abel533.echarts.code.Position;
import com.github.abel533.echarts.code.SeriesType;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Pie;
import com.github.abel533.echarts.series.Series;
import com.github.abel533.echarts.style.ItemStyle;
import com.github.abel533.echarts.style.itemstyle.Normal;

/**
 * 知识库监控
 * 数据识别情况总览
 * dm_d_identified_pandect
 * @author BONC-XUXL
 *
 */
@Controller
@RequestMapping(value = "/dIdentifiedPandect")
public class DIdentifiedPandectAction {
	
	@Resource
	private DIdentifiedPandectService service;
	
	@Resource
	private KeywordSearchRuleService ksrService;
	
	@Resource
	private PublicCPeriodService pcpService;
	
	public static String dateId_fromDataBase ;//数据库中获取的日期
	
	public static DIdentifiedPandect vo_DIdentifiedPandect_cache;//缓存
	public static List<DpiCommon> dcList_lable;//缓存

	/**
	 * 表单弹出
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/showForm")
	public String showForm(HttpServletRequest request) throws Exception{
		
		dateId_fromDataBase = pcpService.selectDateId("1001");//进入页面初始化时间
		request.setAttribute("dateId_fromDataBase", dateId_fromDataBase);
		
		//应用识别率
		vo_DIdentifiedPandect_cache = service.selectDIdentifiedPandect(dateId_fromDataBase);
		request.setAttribute("vo_dip", vo_DIdentifiedPandect_cache);//数据识别情况总览
		
		//规则库的新增，活跃数量 --->总数
		//1,域名
		String ym_hy = null;//活跃
		String ym_xz = null;//新增
		//2,产品 
		String cp_hy = null;//活跃
		String cp_xz = null;//新增
		//3,行业标签 
		String hybq_hy = null;//活跃
		String hybq_xz = null;//新增
		
		DpiCommon dcParam = new DpiCommon();
		String currentMonth = DpiUtils.getCurrentMonth();//201811,当前月;
		if(!"".equals(dateId_fromDataBase) && dateId_fromDataBase!=null){
			currentMonth = dateId_fromDataBase.substring(0,6);//如果PUBLIC_C_PERIOD中监控中心（1001）时间不为空
		}
		dcParam.setName9(currentMonth);
		dcList_lable = service.selectLabelNum(dcParam);
		for (DpiCommon dpiCommon : dcList_lable) {
			String name1 = dpiCommon.getName1();
			String name4 = dpiCommon.getName4();
			if("hy".equals(name1)){
				hybq_hy = name4;
			}else if("xz".equals(name1)){
				hybq_xz = name4;
			}
		}

		//4,内容
		String nr_hy = null;//活跃
		String nr_xz = null;//新增
		
		List<DpiCommon> gzkList = service.selectGzkData_Total(dateId_fromDataBase);
		for (DpiCommon dpiCommon : gzkList) {
			String name1 = dpiCommon.getName1();
			String name2 = dpiCommon.getName2();
			String name3 = dpiCommon.getName3();
			if("1".equals(name1) && "1".equals(name2)){
				ym_xz = name3;//1,域名--新增
			}else if("1".equals(name1) && "2".equals(name2)){
				ym_hy = name3;//1,域名--活跃
			}else if("2".equals(name1) && "1".equals(name2)){
				cp_xz = name3;//2,产品--新增
			}else if("2".equals(name1) && "2".equals(name2)){
				cp_hy = name3;//2,产品--活跃
			}else if("3".equals(name1) && "1".equals(name2)){
				nr_xz = name3;//4,内容--新增
			}else if("3".equals(name1) && "2".equals(name2)){
				nr_hy = name3;//4,内容--活跃
			}
		}
		
		request.setAttribute("ym_hy", (ym_hy!=null)?ym_hy:0);//
		request.setAttribute("ym_xz", (ym_xz!=null)?ym_xz:0);//
		request.setAttribute("cp_hy", (cp_hy!=null)?cp_hy:0);//
		request.setAttribute("cp_xz", (cp_xz!=null)?cp_xz:0);//
		request.setAttribute("hybq_hy", (hybq_hy!=null)?hybq_hy:0);//
		request.setAttribute("hybq_xz", (hybq_xz!=null)?hybq_xz:0);//
		request.setAttribute("nr_hy", (nr_hy!=null)?nr_hy:0);//
		request.setAttribute("nr_xz", (nr_xz!=null)?nr_xz:0);//
		
		//内容库
		DmDContentInfo vo_param = new DmDContentInfo();//条件
		vo_param.setDateId(dateId_fromDataBase);
		
		List<DmDContentInfo> DmDContentInfoList = service.selectDmDContentInfoList(vo_param);
		for (DmDContentInfo dmDContentInfo : DmDContentInfoList) {
			String contentType = dmDContentInfo.getContentType();
			if("G0009".equals(contentType)){
				request.setAttribute("vo_G0009", dmDContentInfo);//小说
			}else if("G0012".equals(contentType)){
				request.setAttribute("vo_G0012", dmDContentInfo);//视频
			}else if("G0004".equals(contentType)){
				request.setAttribute("vo_G0004", dmDContentInfo);//音乐
			}else if("G0014".equals(contentType)){
				request.setAttribute("vo_G0014", dmDContentInfo);//电商商品
			}
		}
		
		//分词词库统计情况
		DmDLexiconInfo vo_dli = this.service.selectDmDLexiconInfo(dateId_fromDataBase);
		request.setAttribute("vo_dli", vo_dli);//数据识别情况总览
		
		return "pages/jsp/dpi/dIdentifiedPandect/dIdentifiedPandectForm";
	}
	
	/**
	 * 转换为百分比
	 * @param vo
	 */
	public static void  changeToPercent(DmDContentInfo vo){
		
		DecimalFormat df = new DecimalFormat("0.00%");
		String recongnition = vo.getRecongnition();
		String useCnt = vo.getUseCnt();
		String errorCnt = vo.getErrorCnt();
		Integer.parseInt(df.format(recongnition));
		vo.setRecongnition(df.format(Integer.parseInt(df.format(recongnition))));
		vo.setUseCnt(df.format(Integer.parseInt(df.format(useCnt))));
		vo.setErrorCnt(df.format(Integer.parseInt(df.format(errorCnt))));
	}
	
	/**
	 * 应用记录识别率,不含ip识别率
	 * @return
	 */
	@RequestMapping(value = "/echarts_numberUnipTotal")
	@ResponseBody()
	public String echarts_numberUnipTotal(String dateId) throws Exception{
		
//		dateId = dateId_fromDataBase;
//		DIdentifiedPandect vo = service.selectDIdentifiedPandect(dateId);//条件查询
		
		DIdentifiedPandect vo = vo_DIdentifiedPandect_cache;//继承缓存
        String[] name = {"不含ip识别率","含ip识别率"};//
        Long[] datas = {vo.getYyjlSblFz(),vo.getYyjlSblFm()-vo.getYyjlSblFz()};//数据
        String[] radius = {"75%","90%"};//param1: 圆心率，环形的厚度 。 param2：圆占div区域的比例
        List<Object> colorList = new ArrayList<Object>(); //颜色处理
        String[] color1 = {"#3966FF"};//适中的蓝色
        String[] color2 = {"#cfe3fc"};//矢车菊的蓝色
        colorList.addAll(Arrays.asList(color1));
        colorList.addAll(Arrays.asList(color2));
        
        Option option = new GsonOption();
        option.color(colorList.get(0));
        option.color(colorList.get(1));
        option.tooltip().show(true).formatter("{a} <br/>{b}: {c} ({d}%)");//设置显示的格式 当鼠标放到柱状图上时的显示格式
        Long zero = (long) 0;
        Long v1 = vo.getYyjlSblFz();
        Long v2 = vo.getYyjlSblFm();
        float num = (float)v1/v2;
        
		DecimalFormat df = new DecimalFormat("0.00");//格式化小数  
		String v3 = df.format(num*100);//返回的是String类型  
        
        Title title	= new Title();
        if(v2==zero){
        	title.setText("0.00%");
        }else{
        	title.setText(v3+"%");
        }
        
//        title.setSubtext("69");
        title.setX("center");
        title.setY("center");
        option.setTitle(title);
        
        Pie pie = new Pie();
        pie.name("应用记录IP识别率");
        pie.setRadius(radius);
        ItemStyle label = new ItemStyle();
        Normal normal = new Normal();
        normal.setShow(false);
        normal.setPosition(Position.center);
        label.setNormal(normal);
        pie.setLabel(label);
        for(int i = 0; i < name.length; i++){
			Map<String,Object> map = new HashMap<>();
			map.put("value",datas[i]);
			map.put("name",name[i]);
			pie.data(map);//装入指定数据
        }
        
        option.series(pie);//装入数据表中
        String result = option.toString();//option进行json格式处理
        return result ;
    }
	
	/**
	 * 应用流量识别率,不含ip识别率
	 * @return
	 */
	@RequestMapping(value = "/echarts_flowUnipTotal")
	@ResponseBody()
	public String echarts_flowUnipTotal(String dateId) throws Exception{
		
//		dateId = dateId_fromDataBase;
//		DIdentifiedPandect vo = service.selectDIdentifiedPandect(dateId);//条件查询
		
		DIdentifiedPandect vo = vo_DIdentifiedPandect_cache;//继承缓存
        String[] name = {"不含ip识别率","含ip识别率"};
        Long[] datas = {vo.getYyllSblFz(),vo.getYyllSblFm()-vo.getYyllSblFz()};//数据
        String[] radius = {"75%","90%"};//param1: 圆心率，环形的厚度 。 param2：圆占div区域的比例
        List<Object> colorList = new ArrayList<Object>(); //颜色处理
        String[] color1 = {"#3966FF"};//适中的蓝色
        String[] color2 = {"#cfe3fc"};//矢车菊的蓝色
        colorList.addAll(Arrays.asList(color1));
        colorList.addAll(Arrays.asList(color2));

        //####开始构建option对象 ######
        //这里先做最简单的测试用用例 其他渲染的属性 大家可以在了解之后自行测试使用
        //1.创建option对象    有两种方式 一种是直接创建option但是在封装好option之后要使用json转换工具进行格式的转换
        //这里使用第二种方式直接构建GsonOption 通过toString方法可转换成json
        Option option = new GsonOption();
        
        option.color(colorList.get(0));
        option.color(colorList.get(1));

        //5.设置显示工具
        option.tooltip().show(true).formatter("{a} <br/>{b}: {c} ({d}%)");//设置显示的格式 当鼠标放到柱状图上时的显示格式
        
        Long zero = (long) 0;
        Long v1 = vo.getYyllSblFz();
        Long v2 = vo.getYyllSblFm();
        float num = (float)v1/v2;
		DecimalFormat df = new DecimalFormat("0.00");//格式化小数  
		String v3 = df.format(num*100);//返回的是String类型  
        
        Title title	= new Title();
        if(v2==zero){
        	title.setText("0.00%");
        }else{
        	title.setText(v3+"%");
        }
//        title.setSubtext("69");
        title.setX("center");
        title.setY("center");
        option.setTitle(title);
        
        Pie pie = new Pie();
        pie.name("应用流量识别率");
        pie.setRadius(radius);
        ItemStyle label = new ItemStyle();
        Normal normal = new Normal();
        normal.setShow(false);
        normal.setPosition(Position.center);
        label.setNormal(normal);
        pie.setLabel(label);
        
        for(int i = 0; i < name.length; i++){
			Map<String,Object> map = new HashMap<>();
			map.put("value",datas[i]);
			map.put("name",name[i]);
			pie.data(map);//指定路段装入指定数据
        }
        //装入数据表中
        option.series(pie);
        //option进行json格式处理
        String result = option.toString();
        return result ;
    }
	
	/**
	 * 应用流量识别率,不含ip识别率old
	 * @return
	 */
//	@RequestMapping(value = "/echarts_numberUnipTotal_old")
//	@ResponseBody()
//	public String echarts_numberUnipTotal_old() throws Exception{
//		DIdentifiedPandect vo = this.service.selectDIdentifiedPandect("1");
//        String[] name = {"不含ip识别率","含ip识别率"};//路段
//        int[] datas = {vo.getNumberUnipTotal(),vo.getNumberTotal()};//路段数据
//        String[] radius = {"45%","60%"};//param1: 圆心率，环形的厚度 。 param2：圆占div区域的比例
//        List<Object> colorList = new ArrayList<Object>(); //颜色处理
//        String[] color1 = {"#FF6347"};
//        String[] color2 = {"#4169E1"};//皇家蓝
//        colorList.addAll(Arrays.asList(color1));
//        colorList.addAll(Arrays.asList(color2));
//        
//        Option option = new GsonOption();
//        option.color(colorList.get(0));
//        option.color(colorList.get(1));
//        option.tooltip().show(true).formatter("{a} <br/>{b}: {c} ({d}%)");//设置显示的格式 当鼠标放到柱状图上时的显示格式
//
//        Pie pie = new Pie();
//        pie.name("应用流量识别率");
//        pie.setRadius(radius);
//        for(int i = 0; i < name.length; i++){
//			Map<String,Object> map = new HashMap<>();
//			map.put("value",datas[i]);
//			map.put("name",name[i]);
//			pie.data(map);//指定路段装入指定数据
//        }
//        
//        option.series(pie);//装入数据表中
//        String result = option.toString();//option进行json格式处理
//        return result ;
//    }
	
	
	
	//********************************************************规则库*****************************************************
	
	
	/**
	 * 标签域名分布
	 * @return
	 * 
		option = {
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['name1','name2']
		    },
		    xAxis : [
		        {
		            type : 'category',
		            data : ['1月','2月']
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value'
		        }
		    ],
		    series : [
		        {
		            name:'name1',
		            type:'bar',
		            data:[2.0, 4.9],
		        },
		        {
		            name:'name2',
		            type:'bar',
		            data:[2.6, 5.9],
		
		        }
		    ]
		};
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/echarts_domain")
	@ResponseBody()
	public String echarts_domain(String dateId) throws Exception{
		dateId = dateId_fromDataBase;
		
		List<DpiCommon> voList_dc_hy = new ArrayList<>();//活跃
		List<DpiCommon> voList_dc_xz = new ArrayList<>();//新增
		
		DpiCommon dcParam = new DpiCommon();
//		dcParam.setName1(dateId);//时间
		List<DpiCommon> dcList = service.selectDomainRule(dcParam);//活跃
		for (DpiCommon dpiCommon : dcList) {
			String name1 = dpiCommon.getName1();//新增或者活跃（1，2）
			String name2 = dpiCommon.getName2();//标签名称（休闲娱乐...其他）
			String name3 = dpiCommon.getName3();//统计数据
			if("2".equals(name1)){
				DpiCommon dc = new DpiCommon();
				dc.setName1(name2);
				dc.setName2(name3);
				voList_dc_hy.add(dc);//活跃
			}else if("1".equals(name1)){
				DpiCommon dc = new DpiCommon();
				dc.setName1(name2);
				dc.setName2(name3);
				voList_dc_xz.add(dc);//新增
			}
		}
		
        Option option = new GsonOption();
        
        //1.color
        
        //2.tooltip
        Tooltip tooltip = new Tooltip();
        tooltip.setTrigger(Trigger.axis);
        option.tooltip(tooltip);
        
        //3.legend
        Legend legend = new Legend();
        List<String> legendData = new ArrayList<String>();
        legendData.add("活跃域名");
        legendData.add("新增域名");
        legend.data(legendData);
        option.legend(legend);

        //4.xAxis,设置x轴数据
		CategoryAxis categoryAxis = new CategoryAxis();
		categoryAxis.setType(AxisType.category);
		//4.1，categoryAxisData
		List<String> categoryAxisData = new ArrayList<String>();
		for (DpiCommon dpiCommon : voList_dc_hy) {
			categoryAxisData.add(dpiCommon.getName1());//域名-标签名称
		}
		categoryAxis.setData(categoryAxisData);
		
		//4.2，axisLabel
		AxisLabel axisLabel = new AxisLabel();
		axisLabel.setInterval(0);
		axisLabel.setRotate(30);//横坐标文字显示角度，90是直角
		
		categoryAxis.setAxisLabel(axisLabel);
		option.xAxis(categoryAxis);

		//5.yAxis,设置y轴 这里不给指定数据  自动调整
		ValueAxis valueAxis = new ValueAxis();
		valueAxis.setType(AxisType.value);
		SplitLine splitLine = new SplitLine();//
		splitLine.setShow(false);//去掉网格背后的分割线
		valueAxis.setSplitLine(splitLine);
		option.yAxis(valueAxis);
        
		//6.series
        String[] name = {"活跃域名","新增域名"};
        List<List> data = new ArrayList<List>();
		List data_hy = new ArrayList<>();
		List data_xz = new ArrayList<>();
		for (DpiCommon dpiCommon : voList_dc_hy) {
			data_hy.add(dpiCommon.getName2());//活跃-值
		}
		for (DpiCommon dpiCommon : voList_dc_xz) {
			data_xz.add(dpiCommon.getName2());//新增-值
		}
		data.add(data_hy);
		data.add(data_xz);
        List<Series> barList = new ArrayList<Series>();
        List<String> colorList = new ArrayList<String>();
        colorList.add("#5EB6F9");//蓝色
        colorList.add("#34C36D");//绿色
        for(int i = 0; i < name.length; i++){
        	Bar bar = new Bar();
        	bar.setName(name[i]);
        	bar.setType(SeriesType.bar);
        	bar.setData(data.get(i));
        	
        	//设置颜色
        	ItemStyle itemStyle = new ItemStyle();
        	Normal normal = new Normal();
        	normal.setColor(colorList.get(i));
        	itemStyle.setNormal(normal);
        	bar.setItemStyle(itemStyle);
			barList.add(bar);
        }
        Grid grid = new Grid();//
        grid.setLeft(60);//解决y轴数据过大时显示不全问题
        option.setGrid(grid);
        option.series(barList);//装入数据表中
        String result = option.toString();//option进行json格式处理
        
        return result ;
    }

	
	/**
	 * 标签产品分布
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/echarts_product")
	@ResponseBody()
	public String echarts_product(String dateId) throws Exception{
		dateId = dateId_fromDataBase;
		
		List<DpiCommon> voList_dc_hy = new ArrayList<>();//活跃
		List<DpiCommon> voList_dc_xz = new ArrayList<>();//新增
		
		DpiCommon dcParam = new DpiCommon();
//		dcParam.setName1(dateId);//时间
		List<DpiCommon> dcList = service.selectProductInfo(dcParam);//活跃
		for (DpiCommon dpiCommon : dcList) {
			String name1 = dpiCommon.getName1();//新增或者活跃（1，2）
			String name2 = dpiCommon.getName2();//标签名称（休闲娱乐...其他）
			String name3 = dpiCommon.getName3();//统计数据
			if("2".equals(name1)){
				DpiCommon dc = new DpiCommon();
				dc.setName1(name2);
				dc.setName2(name3);
				voList_dc_hy.add(dc);//活跃
			}else if("1".equals(name1)){
				DpiCommon dc = new DpiCommon();
				dc.setName1(name2);
				dc.setName2(name3);
				voList_dc_xz.add(dc);//新增
			}
		}
		
        Option option = new GsonOption();
        
        //2.tooltip
        Tooltip tooltip = new Tooltip();
        tooltip.setTrigger(Trigger.axis);
        option.tooltip(tooltip);
        
        //3.legend
        Legend legend = new Legend();
        List<String> legendData = new ArrayList<String>();
        legendData.add("活跃产品");
        legendData.add("新增产品");
        legend.data(legendData);
        option.legend(legend);

        //4.xAxis,设置x轴数据
		CategoryAxis categoryAxis = new CategoryAxis();
		categoryAxis.setType(AxisType.category);
		//4.1，categoryAxisData
		List<String> categoryAxisData = new ArrayList<String>();
		for (DpiCommon dpiCommon : voList_dc_hy) {
			categoryAxisData.add(dpiCommon.getName1());//产品-标签名称
		}
		categoryAxis.setData(categoryAxisData);
		
		//4.2，axisLabel
		AxisLabel axisLabel = new AxisLabel();
		axisLabel.setInterval(0);
		axisLabel.setRotate(30);//横坐标文字显示角度，90是直角
		
		categoryAxis.setAxisLabel(axisLabel);
		option.xAxis(categoryAxis);
		
		//5.yAxis,设置y轴 这里不给指定数据  自动调整
		ValueAxis valueAxis = new ValueAxis();
		valueAxis.setType(AxisType.value);
		SplitLine splitLine = new SplitLine();//
		splitLine.setShow(false);//去掉网格背后的分割线
		valueAxis.setSplitLine(splitLine);
		option.yAxis(valueAxis);
        
		//6.series
        String[] name = {"活跃产品","新增产品"};
        
        List<List> data = new ArrayList<List>();
		List data_hy = new ArrayList<>();
		List data_xz = new ArrayList<>();
		for (DpiCommon dpiCommon : voList_dc_hy) {
			data_hy.add(dpiCommon.getName2());//活跃-值
		}
		for (DpiCommon dpiCommon : voList_dc_xz) {
			data_xz.add(dpiCommon.getName2());//新增-值
		}
		data.add(data_hy);
		data.add(data_xz);
        List<Series> barList = new ArrayList<Series>();
        List<String> colorList = new ArrayList<String>();
        colorList.add("#5EB6F9");//蓝色
        colorList.add("#34C36D");//海洋绿
        for(int i = 0; i < name.length; i++){
        	Bar bar = new Bar();
        	bar.setName(name[i]);
        	bar.setType(SeriesType.bar);
        	bar.setData(data.get(i));
        	//设置颜色
        	ItemStyle itemStyle = new ItemStyle();
        	Normal normal = new Normal();
        	normal.setColor(colorList.get(i));
        	itemStyle.setNormal(normal);
        	bar.setItemStyle(itemStyle);
        	
			barList.add(bar);
        }
        Grid grid = new Grid();//
        grid.setLeft(60);//解决y轴数据过大时显示不全问题
        option.setGrid(grid);
        option.series(barList);//装入数据表中
        String result = option.toString();//option进行json格式处理
        return result ;
    }
	


	/**
	 * 标签层级分布
	 * @param labelCode: 活跃和新增
	 * @param labelType 一级标签，二级标签
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/echarts_label")
	@ResponseBody()
	public String echarts_label(String dateId) throws Exception{
		dateId = dateId_fromDataBase;
		
		//String labelCode,String labelType
		String labelCode1_num_hy = "0";//一级活跃标签
		String labelCode1_num_xz = "0";//一级新增标签
		String labelCode2_num_hy = "0";//二级活跃标签
		String labelCode2_num_xz = "0";//二级新增标签
		
		for (DpiCommon dpiCommon : dcList_lable) {
			String name1 = dpiCommon.getName1();
			String name2 = dpiCommon.getName2();
			String name3 = dpiCommon.getName3();
			if("hy".equals(name1)){
				labelCode1_num_hy = name2;
				labelCode2_num_hy = name3;
			}else if("xz".equals(name1)){
				labelCode1_num_xz = name2;
				labelCode2_num_xz = name3;
			}
		}
		
        Option option = new GsonOption();
        
        //2.tooltip
        Tooltip tooltip = new Tooltip();
        tooltip.setTrigger(Trigger.axis);
        option.tooltip(tooltip);
        
        //3.legend
        Legend legend = new Legend();
        List<String> legendData = new ArrayList<String>();
        legendData.add("活跃标签");
        legendData.add("新增标签");
        legend.data(legendData);
        option.legend(legend);

        //4.xAxis,设置x轴数据
		CategoryAxis categoryAxis = new CategoryAxis();
		categoryAxis.setType(AxisType.category);
		//4.1，categoryAxisData
		List<String> categoryAxisData = new ArrayList<String>();
		categoryAxisData.add("一级标签");//dwa_d_product_info 表的prod_type_name
		categoryAxisData.add("二级标签");
		categoryAxis.setData(categoryAxisData);
		
		//4.2，axisLabel
		AxisLabel axisLabel = new AxisLabel();
		axisLabel.setInterval(0);
		axisLabel.setRotate(0);//横坐标文字显示角度，90是直角
		
		categoryAxis.setAxisLabel(axisLabel);
		option.xAxis(categoryAxis);
		
		//5.yAxis,设置y轴 这里不给指定数据  自动调整
		ValueAxis valueAxis = new ValueAxis();
		valueAxis.setType(AxisType.value);
		SplitLine splitLine = new SplitLine();//
		splitLine.setShow(false);//去掉网格背后的分割线
		valueAxis.setSplitLine(splitLine);
		option.yAxis(valueAxis);
        
		//6.series
        String[] name = {"活跃标签","新增标签"};
        List<List> data = new ArrayList<List>();
		List labelCode1_data = new ArrayList<>();//
        labelCode1_data.add(labelCode1_num_hy);//一级标签活跃
        labelCode1_data.add(labelCode2_num_hy);//二级标签活跃
        
		List labelCode2_data = new ArrayList<>();//
        labelCode2_data.add(labelCode1_num_xz);//一级标签新增
        labelCode2_data.add(labelCode2_num_xz);//二级标签新增
        
        data.add(labelCode1_data);
        data.add(labelCode2_data);
        List<Series> barList = new ArrayList<Series>();
        List<String> colorList = new ArrayList<String>();
        colorList.add("#5EB6F9");//蓝色
        colorList.add("#34C36D");//海洋绿
        for(int i = 0; i < name.length; i++){
        	Bar bar = new Bar();
        	bar.setName(name[i]);
        	bar.setType(SeriesType.bar);
        	bar.setData(data.get(i));
        	//设置颜色
        	ItemStyle itemStyle = new ItemStyle();
        	Normal normal = new Normal();
        	normal.setColor(colorList.get(i));
        	itemStyle.setNormal(normal);
        	bar.setItemStyle(itemStyle);
        	
			barList.add(bar);
        }
        Grid grid = new Grid();//
        grid.setLeft(60);//解决y轴数据过大时显示不全问题
        option.setGrid(grid);
        option.series(barList);//装入数据表中
        String result = option.toString();//option进行json格式处理
        return result ;
    }
	
	/**
	 * 深度规则分布
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/echarts_rule")
	@ResponseBody()
	public String echarts_rule(String dateId) throws Exception{
//		dateId = dateId_fromDataBase;
		
		List<String> list_hy = new ArrayList<>();//活跃
		List<String> list_xz = new ArrayList<>();//新增
		
		DpiCommon dcParam = new DpiCommon();
		List<DpiCommon> dcList = service.selectDeepRule(dcParam);
		for (DpiCommon dpiCommon : dcList) {
			String name1 = dpiCommon.getName1();
			String name2 = dpiCommon.getName2();
			if("2".equals(name1)){
				list_hy.add(name2);//活跃
			}else if("1".equals(name1)){
				list_xz.add(name2);//新增
			}
		}
		
        Option option = new GsonOption();
        
        //2.tooltip
        Tooltip tooltip = new Tooltip();
        tooltip.setTrigger(Trigger.axis);
        option.tooltip(tooltip);
        
        //3.legend
        Legend legend = new Legend();
        List<String> legendData = new ArrayList<String>();
        legendData.add("活跃规则");
        legendData.add("新增规则");
        legend.data(legendData);
        option.legend(legend);

        //4.xAxis,设置x轴数据
		CategoryAxis categoryAxis = new CategoryAxis();
		categoryAxis.setType(AxisType.category);
		//4.1，categoryAxisData
		List<String> categoryAxisData = new ArrayList<String>();
		categoryAxisData.add("动作");//dwa_d_product_info 表的prod_type_name
		categoryAxisData.add("内容");
		categoryAxisData.add("目录");
		categoryAxis.setData(categoryAxisData);
		
		//4.2，axisLabel
		AxisLabel axisLabel = new AxisLabel();
		axisLabel.setInterval(0);
		axisLabel.setRotate(0);//横坐标文字显示角度，90是直角
		
		categoryAxis.setAxisLabel(axisLabel);
		option.xAxis(categoryAxis);
		
		//5.yAxis,设置y轴 这里不给指定数据  自动调整
		ValueAxis valueAxis = new ValueAxis();
		valueAxis.setType(AxisType.value);
		SplitLine splitLine = new SplitLine();//
		splitLine.setShow(false);//去掉网格背后的分割线
		valueAxis.setSplitLine(splitLine);
		option.yAxis(valueAxis);
        
		//6.series
        String[] name = {"活跃规则","新增规则"};
		List<List> data = new ArrayList<List>();
        data.add(list_hy);
        data.add(list_xz);

        List<Series> barList = new ArrayList<Series>();
        List<String> colorList = new ArrayList<String>();
        colorList.add("#5EB6F9");//蓝色
        colorList.add("#34C36D");//海洋绿
        for(int i = 0; i < name.length; i++){
        	Bar bar = new Bar();
        	bar.setName(name[i]);
        	bar.setType(SeriesType.bar);
        	bar.setData(data.get(i));
        	//设置颜色
        	ItemStyle itemStyle = new ItemStyle();
        	Normal normal = new Normal();
        	normal.setColor(colorList.get(i));
        	itemStyle.setNormal(normal);
        	bar.setItemStyle(itemStyle);
        	
			barList.add(bar);
        }
        Grid grid = new Grid();//
        grid.setLeft(60);//解决y轴数据过大时显示不全问题
        option.setGrid(grid);
        option.series(barList);//装入数据表中
        String result = option.toString();//option进行json格式处理
        return result ;
    }
}
