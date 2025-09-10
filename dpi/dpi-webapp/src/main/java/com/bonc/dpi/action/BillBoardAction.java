package com.bonc.dpi.action;

import com.alibaba.fastjson.JSON;
import com.bonc.common.cst.CST;
import com.bonc.common.utils.Ajax;
import com.bonc.common.utils.PageJqGrid;
import com.bonc.dpi.dao.entity.BillBoard;
import com.bonc.dpi.entity.VideoCategoryInfo;
import com.bonc.dpi.entity.VideoWebInfo;
import com.bonc.dpi.entity.WordCloud;
import com.bonc.dpi.service.BillBoardService;
import com.bonc.system.dao.entity.SysUser;
import com.github.pagehelper.Page;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 热搜风云榜
 */
@Controller
@RequestMapping(value = "/billBoard")
public class BillBoardAction {
    @Resource
    private BillBoardService billBoardService;
    private static final Logger logger = LoggerFactory.getLogger(BillBoardAction.class);

    /**
     * 实时热点
     *
     * @return
     */
    @RequestMapping(value = "/queryHotData", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String queryHotData(BillBoard bill) {
        try {
        	List<BillBoard> hotData = billBoardService.queryHotData(bill);
            return Ajax.responseString(CST.RES_SECCESS, hotData);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
        }
    }
    /**
     * 热词词云
     *
     * @return
     */
    @RequestMapping(value = "/getwordcloud", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getWordCloud(BillBoard bill) {
        try {
            List<WordCloud> wordCloud = billBoardService.getWordCloud(bill);
            return Ajax.responseString(CST.RES_SECCESS, wordCloud);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
        }
    }
    /**
     * 热搜排行
     *
     * @return
     */
    @RequestMapping(value = "/getSearchList",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public PageJqGrid<BillBoard> getSearchList(BillBoard bill) throws Exception {
        logger.info(JSON.toJSONString(bill));
        Page<BillBoard> result=billBoardService.getSearchList(bill);
        PageJqGrid<BillBoard> pageJqGrid = new PageJqGrid<BillBoard>(result);
        return pageJqGrid;
    }
    /**
     * 热搜排行
     *
     * @return
     */
    @RequestMapping(value = "/initCatagory",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> initCatagory() {
        Map<String, Object> resultmap = new HashMap<String, Object>();
        List<BillBoard> categorylist = billBoardService.getCategorylist();
        System.out.println(JSON.toJSONString(categorylist));

        resultmap.put("categorylist", categorylist);
        return resultmap;
    }
    /**
     * 热搜排行
     *
     * @return
     */
//    @RequestMapping(value = "/getHotWordTotalnum",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
//    @ResponseBody
//    public Map<String, Object> getHotWordTotalnum() {
//        Map<String, Object> resultmap = new HashMap<String, Object>();
//        List<BillBoard> categorylist = billBoardService.getCategorylist();
//        System.out.println(JSON.toJSONString(categorylist));
//        resultmap.put("categorylist", categorylist);
//        return resultmap;
//    }
    @RequestMapping(value = "/getHotWordTotalnum",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> getHotWordTotalnum(BillBoard billBoard) {
        System.out.println(JSON.toJSONString(billBoard));
        Map<String, Object> resultmap = new HashMap<String, Object>();
        int totalnum = billBoardService.getHotWordAmount(billBoard);
        resultmap.put("totalnum", totalnum);
        return resultmap;
    }
    /**
     * 热搜排行
     *
     * @return
     */
    @RequestMapping(value = "/getHotWordList",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> getHotWordList(BillBoard billBoard,String pagenum) {
        System.out.println(pagenum);
        System.out.println(JSON.toJSONString(billBoard));
        Map<String, Object> resultmap = new HashMap<String, Object>();
        try {
            resultmap = billBoardService.getHotWordList(pagenum, billBoard);
            System.out.println("resultmap:"+resultmap);
            int totalNum = (int) resultmap.get("totalnum");
            if(totalNum == 0) {
                resultmap.put("resultCode", "2");
                resultmap.put("resultMsg", "查询无数据！");
                logger.info("查询无数据！");
            }else {
                resultmap.put("resultCode", "1");
                resultmap.put("resultMsg", "查询成功！");
                logger.info("查询成功!");
            }
        } catch (Exception e) {
            resultmap.put("resultCode", "2");
            resultmap.put("resultMsg", "查询异常！");
            e.printStackTrace();
        }
        return resultmap;
    }
    /**
     * 获取二级分类
     *
     * @return
     */
    @RequestMapping(value = "/getclass2",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> getclass2(BillBoard billBoard) {
        System.out.println(JSON.toJSONString(billBoard));
        Map<String, Object> resultmap = new HashMap<String, Object>();
        List<BillBoard> categorylist = billBoardService.getClass2list(billBoard);
        for(BillBoard board:categorylist){
            if("".equals(board.getContentLabelName2().trim())){
                board.setContentLabelName2(board.getContentLabelName1());
            }
        }
        System.out.println(JSON.toJSONString(categorylist));
        resultmap.put("categorylist", categorylist);
        return resultmap;
    }

//    /**
//     * 导出数据
//     *
//     * @return
//     */
//    @RequestMapping(value = "/exportJob",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
//    @ResponseBody
//    public Map<String, Object> exportJob(BillBoard billBoard) {
//        System.out.println(JSON.toJSONString(billBoard));
//        Map<String, Object> resultmap = new HashMap<String, Object>();
//        String resultMsg = "";
//        String resultCode = "";
//        try {
//            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//            SysUser user = (SysUser) request.getSession().getAttribute(CST.SESSION_SYS_USER_INFO);
//            System.out.println(JSON.toJSONString(user));
//            String user_id = user.getUserId();
//            resultmap = billBoardService.exportVideoJob(user_id,billBoard );
//            int ifAllow = (int) resultmap.get("lognum");
//            if(ifAllow == 0) {
//                int exportnum = (int)resultmap.get("exportnum");
//                String task_id = (String) resultmap.get("task_id");
//                resultCode = "1";
//                resultMsg = "任务ID为"+ task_id +"数据导入成功！";
////				resultMsg = "任务ID为"+ task_id +",共计"+ exportnum + "条数据导入成功！";
//            }else {
//                resultCode = "2";
//                resultMsg = "同一用户相同的查询条件不可重复导入！";
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            resultCode = "0";
//            resultMsg = "导入操作异常！";
//        }
//        resultmap.put("resultMsg", resultMsg);
//        resultmap.put("resultCode", resultCode);
//        return resultmap;
//    }

    /**
     * 导出数据
     *
     * @return
     */
    @RequestMapping(value = "/exportJob",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> exportJob(BillBoard billBoard) {
        System.out.println(JSON.toJSONString(billBoard));
        Map<String, Object> resultmap = new HashMap<String, Object>();
        String resultMsg = "";
        String resultCode = "";
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            SysUser user = (SysUser) request.getSession().getAttribute(CST.SESSION_SYS_USER_INFO);
            System.out.println(JSON.toJSONString(user));
            String user_id = user.getUserId();
            resultmap = billBoardService.exportVideoJob(user_id,billBoard );
        } catch (Exception e) {
            e.printStackTrace();
            resultCode = "0";
            resultMsg = "导出操作异常！";
            resultmap.put("resultMsg", resultMsg);
            resultmap.put("resultCode", resultCode);
        }
        return resultmap;
    }

    /**
     * 热词排行
     *
     * @return
     */
    @RequestMapping(value = "/getWordList",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public PageJqGrid<BillBoard> getWordList(BillBoard word) throws Exception {
        logger.info(JSON.toJSONString(word));
        Page<BillBoard> result=billBoardService.getSearchTop(word);
        result.setPageSize(10);
        PageJqGrid<BillBoard> pageJqGrid = new PageJqGrid<BillBoard>(result);
        return pageJqGrid;
    }
    /**
     * 地图数据
     *
     * @return
     */
    @RequestMapping(value = "/queryMapData", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String queryMapData(BillBoard bill) {
        try {
        	List<BillBoard> mapData = billBoardService.queryMapData(bill);
            return Ajax.responseString(CST.RES_SECCESS, mapData);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
        }
    }

    /**
     * 跳转页面-实时热点-完整榜单
     *
     * @return
     */
    @RequestMapping(value = "/showHotData")
    public String showHotData(HttpServletRequest request,String mapJson, String cityId, String monthId) {
    	BillBoard bill = new BillBoard(cityId, monthId);
    	List<BillBoard> hotData = billBoardService.queryHotData(bill);
        request.setAttribute("hotData", JSON.toJSONString(hotData));
        request.setAttribute("mapJson", mapJson);
        return "pages/jsp/dpi/billBoard/allHotData";
    }

    /**
     * 热搜分类
     *
     * @return
     */
    @RequestMapping(value = "/selectLabel", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String selectLabel() {
        try {
        	List<BillBoard> labels = billBoardService.selectCategoryLabel();
            return Ajax.responseString(CST.RES_SECCESS, labels);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
        }
    }
    /**
     * 跳转页面-热搜分类-完整榜单
     *
     * @return
     */
    @RequestMapping("/showAllLabels")
    public String showAllLabels(HttpServletRequest request,String mapJson, String cityId, String monthId) {
        List<String> contentLabelName1List = new ArrayList<String>();//大分类
        Map<String, List<String>> contentLabelNameMap = new HashMap<String, List<String>>(); //key：大类  value：小类
        List<BillBoard> labels = billBoardService.selectCategoryLabel();
        for (BillBoard label : labels) {
            String label1 = label.getContentLabelName1();
            String label2 = label.getContentLabelName2();

            if (!contentLabelName1List.contains(label1)) {
                contentLabelName1List.add(label1);
            }

            if (!contentLabelNameMap.containsKey(label1)) {
                List<String> list = new ArrayList<String>();
                list.add(label2);
                contentLabelNameMap.put(label1, list);
            } else {
                List<String> list = contentLabelNameMap.get(label1);
                list.add(label2);
                contentLabelNameMap.put(label1, list);
            }
        }
        request.setAttribute("contentLabelName1List",JSON.toJSONString(contentLabelName1List));
        request.setAttribute("contentLabelNameMap",JSON.toJSONString(contentLabelNameMap));
        request.setAttribute("mapJson", mapJson);
        request.setAttribute("cityId", cityId);
        request.setAttribute("monthId", monthId);
        return "pages/jsp/dpi/billBoard/labelsView";
    }

    /**
     * 各分类榜单
     *
     * @return
     */
    @RequestMapping(value = "/selectData", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String selectData(BillBoard bill) {
        Map<String, List<BillBoard>> labelMap1 = new HashMap<String, List<BillBoard>>(); //大分类的数据
        Map<String, List<BillBoard>> labelMap2 = new HashMap<String, List<BillBoard>>(); //小分类的数据
        this.billBoardService.queryAndPutMap(bill, labelMap1, labelMap2);
        return JSON.toJSONString(labelMap1);
    }

    /**
     * 跳转页面展示分类数据
     *
     * @return
     */
    @RequestMapping("/showData")
    public String showData(HttpServletRequest request, String cityId, String monthId) {
        String contentLabelName1="";
        String contentLabelName2="";
        String mapJson = null;
        try {
            contentLabelName1 = URLDecoder.decode(request.getParameter("contentLabelName1"), "utf-8");
            contentLabelName2 = URLDecoder.decode(request.getParameter("contentLabelName2"), "utf-8");
            mapJson = request.getParameter("mapJson");
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
        }
        List<String> contentLabelName1List = new ArrayList<String>();//大分类
        Map<String, List<String>> contentLabelNameMap = new HashMap<String, List<String>>(); //key：大类  value：小类
        List<BillBoard> labels = billBoardService.selectCategoryLabel();
        for (BillBoard label : labels) {
            String label1 = label.getContentLabelName1();
            String label2 = label.getContentLabelName2();

            if (!contentLabelName1List.contains(label1)) {
                contentLabelName1List.add(label1);
            }

            if (!contentLabelNameMap.containsKey(label1)) {
                List<String> list = new ArrayList<String>();
                list.add(label2);
                contentLabelNameMap.put(label1, list);
            } else {
                List<String> list = contentLabelNameMap.get(label1);
                list.add(label2);
                contentLabelNameMap.put(label1, list);
            }
        }

        Map<String, List<BillBoard>> labelMap1 = new HashMap<String, List<BillBoard>>(); //大分类的数据
        Map<String, List<BillBoard>> labelMap2 = new HashMap<String, List<BillBoard>>(); //小分类的数据
        BillBoard bill = new BillBoard(cityId, monthId);
        this.billBoardService.queryAndPutMap(bill, labelMap1, labelMap2);

        request.setAttribute("contentLabelName1", contentLabelName1);
        request.setAttribute("contentLabelName2", contentLabelName2);
        request.setAttribute("mapJson", mapJson);
        request.setAttribute("cityId", cityId);
        request.setAttribute("monthId", monthId);
        request.setAttribute("contentLabelName1List", JSON.toJSONString(contentLabelName1List));
        request.setAttribute("contentLabelNameMap", JSON.toJSONString(contentLabelNameMap));
        request.setAttribute("contentLabelName1DataList", JSON.toJSONString(labelMap1.get(contentLabelName1)));
        if (StringUtils.isBlank(contentLabelName2)) {
            request.setAttribute("contentLabelName2DataList", JSON.toJSONString(contentLabelName1));
        } else {
            request.setAttribute("contentLabelName2DataList", JSON.toJSONString(labelMap2.get(contentLabelName1 + "~" + contentLabelName2)));
        }
        return "pages/jsp/dpi/billBoard/billAll";
    }

    /**
     * 热搜分类-完整榜单-顶部切换-获取大类数据
     *
     * @return
     */
    @RequestMapping(value = "/selectContentLabelName1Data", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String selectContentLabelName1Data(String contentLabelName1, String cityId, String monthId) {
        try {
            Map<String, List<BillBoard>> labelMap1 = new HashMap<String, List<BillBoard>>(); //大分类的数据
            Map<String, List<BillBoard>> labelMap2 = new HashMap<String, List<BillBoard>>(); //小分类的数据
            BillBoard bill = new BillBoard(cityId, monthId);
            this.billBoardService.queryAndPutMap(bill, labelMap1, labelMap2);
            List<BillBoard> result = labelMap1.get(contentLabelName1);
            return Ajax.responseString(CST.RES_SECCESS, result);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
        }
    }
    /**
     * 各分类榜单-点击分类-获取小类数据
     *
     * @return
     */
    @RequestMapping(value = "/selectContentLabelName2Data", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String selectContentLabelName2Data(BillBoard bill) {
        try {
            List<BillBoard> result=null;

            Map<String, List<BillBoard>> labelMap1 = new HashMap<String, List<BillBoard>>(); //大分类的数据
            Map<String, List<BillBoard>> labelMap2 = new HashMap<String, List<BillBoard>>(); //小分类的数据
            this.billBoardService.queryAndPutMap(bill, labelMap1, labelMap2);
            if(StringUtils.isBlank(bill.getContentLabelName2())){
                result=labelMap1.get(bill.getContentLabelName1());
            }else {
                result = labelMap2.get(bill.getContentLabelName1() + "~" + bill.getContentLabelName2());
            }
            return Ajax.responseString(CST.RES_SECCESS, result);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
        }
    }
}
