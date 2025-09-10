package com.bonc.dpi.service;

import com.bonc.dpi.cache.CacheService;
import com.bonc.dpi.config.Constant;
import com.bonc.dpi.dao.entity.BillBoard;
import com.bonc.dpi.dao.mapper.BillBoardMapper;
import com.bonc.dpi.entity.DateUtil;
import com.bonc.dpi.entity.VideoContent;
import com.bonc.dpi.entity.WordCloud;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class BillBoardService {
    @Resource
    private BillBoardMapper billBoardMapper;

    private final static Logger logger = LoggerFactory.getLogger(VideoStoreService.class);

    @Resource
    private CacheService cacheService;
	public void queryAndPutMap(BillBoard bill, Map<String, List<BillBoard>> labelMap1, Map<String, List<BillBoard>> labelMap2) {
		List<BillBoard> result = queryHotData(bill);
        List<String> cate1List = new ArrayList<String>();//大分类
        String contentLabelName1="";
        String contentLabelName2="";
        List<BillBoard> labels = selectCategoryLabel();
        for (BillBoard label : labels) {
            contentLabelName1=label.getContentLabelName1();
            contentLabelName2=label.getContentLabelName2();
            if (!cate1List.contains(contentLabelName1)) {
                cate1List.add(contentLabelName1);
                //保存大分类数据
                labelMap1.put(contentLabelName1, new ArrayList<BillBoard>());
            }
            //保存小分类分类数据
            labelMap2.put(contentLabelName1 + "~" + contentLabelName2, new ArrayList<BillBoard>());
        }

        String str = "";
        List<BillBoard> list = null;
        for (BillBoard obj : result) {
            contentLabelName1=obj.getContentLabelName1();
            contentLabelName2=obj.getContentLabelName2();
            //添加大分类数据
            list = labelMap1.get(contentLabelName1);
            list.add(obj);
            labelMap1.put(contentLabelName1, list);
            //添加小分类数据
            str = contentLabelName1 + "~" + contentLabelName2;
            list = labelMap2.get(str);
            list.add(obj);
            labelMap2.put(str, list);
        }
	}

    public List<BillBoard> queryHotData(BillBoard bill) {
        List<BillBoard> list=billBoardMapper.queryHotData(bill);
        return list;
    }

    public List<BillBoard> queryMapData(BillBoard bill) {
    	bill.setQueryMapFlag(Constant.COMMON_VALUE);
    	List<BillBoard> list = billBoardMapper.queryHotData(bill);
        return list;
    }

    public List<BillBoard> selectCategoryLabel() {
        return billBoardMapper.selectCategoryLabel();
    }

    public List<WordCloud> getWordCloud(BillBoard billBoard) {
	    return billBoardMapper.getWordCloud(billBoard);
    }

    public Page<BillBoard> getSearchTop(BillBoard word) {
        Page<BillBoard> topList = null;
//        String tableKey = cacheService.obtainKey(Constant.CACHE_KEY_PREFIX, Constant._CACHE_KEY_CONTENTPROJECTRANK_APPTOP,
//                bill.getMonthId(), bill.getLabelName1());
//        String conditionKey = bill.getCityId();
//        if(cacheService.hasHashCache(tableKey, conditionKey)) {
//            topList = cacheService.getHashCache(tableKey, conditionKey);
//            logger.info("缓存命中[" +tableKey + "," + conditionKey + "]：size=" + (topList == null ? 0 : topList.size()));
//            return topList;
//        }

        PageHelper.startPage(0, 10);
        topList = (Page<BillBoard>) billBoardMapper.getWordlist(word);
        if (topList != null && topList.size() > 0) {
            int i = 1;
            for (BillBoard wd : topList) {
                wd.setId(Integer.toString(i++));
            }
//            cacheService.setHashCacheWithExpireTime(tableKey, conditionKey, topList, CacheService.COMMON_TIMEOUTSECOND);
//            logger.info("缓存数据[" +tableKey + "," + conditionKey + "]：size=" + (topList == null ? 0 : topList.size()));
        }
        return topList;
    }

    public Page<BillBoard> getSearchList(BillBoard word) {
        Page<BillBoard> topList = null;
        PageHelper.startPage(0, 10);
        topList = (Page<BillBoard>) billBoardMapper.getSearchList(word);
        if (topList != null && topList.size() > 0) {
            int i = 1;
            for (BillBoard wd : topList) {
                wd.setId(Integer.toString(i++));
            }
        }
        return topList;
    }

    public List<BillBoard> getCategorylist() {
	    return billBoardMapper.getCategorylist();
    }

    public List<BillBoard> getClass2list(BillBoard billBoard) {
	    return billBoardMapper.getClass2list(billBoard);
    }

    public int getHotWordAmount(BillBoard billBoard) {
        return billBoardMapper.getHotWordAmount(billBoard);
    }

    public Map<String, Object> getHotWordList(String pagenum, BillBoard billBoard) {
        Map<String, Object> resultmap = new HashMap<String, Object>();
        int totalnum = getHotWordAmount(billBoard);
        Map<String , Object> params = new HashMap<String, Object>();
        params.put("contentLabelName1", billBoard.getContentLabelName1());
        params.put("contentLabelName2", billBoard.getContentLabelName2());
        params.put("keyWord", billBoard.getKeyWord());
        params.put("startdate", billBoard.getStartdate());
        params.put("enddate", billBoard.getEnddate());
        int limitnum = 20;//每页显示条数
        int totalpages = (int)Math.ceil(totalnum/(limitnum*1.0));//总页数
        if(Integer.parseInt(pagenum) < 1) {
            pagenum = "1";
        }
        if(Integer.parseInt(pagenum) > totalpages) {
            pagenum =  Integer.toString(totalpages);
        }
        int startrow = 0;//开始行数
        if(Integer.parseInt(pagenum)>1) {
            startrow = (Integer.parseInt(pagenum) - 1) * limitnum;
        }
        params.put("startrow", startrow);
        params.put("limitnum", limitnum);
        System.out.println(params);
        List<BillBoard> boardList = billBoardMapper.getHotWordList(params);
        if (boardList != null && boardList.size() > 0) {
            int i = 1;
            for (BillBoard wd : boardList) {
                wd.setId(Integer.toString(i++));
            }
        }
        resultmap.put("boardList", boardList);
        resultmap.put("totalpages", totalpages);
        resultmap.put("totalnum", totalnum);
        return resultmap;
    }

    public Map<String, Object> exportVideoJob(String user_id, BillBoard billBoard) {
        Map<String, Object> resultmap = new HashMap<String, Object>();
        List<BillBoard> billBoards = billBoardMapper.queryHotData4Export(billBoard);
        if(billBoards!=null && billBoards.size()!=0) {
            int i = billBoardMapper.insertHotData(billBoards, user_id);
            if (i>0){
                resultmap.put("resultMsg", "导出成功!");
                resultmap.put("resultCode", "1");
            }else {
                resultmap.put("resultMsg", "导出失败!");
                resultmap.put("resultCode", "2");
            }
        }else {
            resultmap.put("resultMsg", "导出成功!");
            resultmap.put("resultCode", "1");
        }
        return resultmap;
    }


//    public Page<TopWord> getWordList(TopWord word) {
//        Page<TopWord> topList = null;
//        String tableKey = cacheService.obtainKey(Constant.CACHE_KEY_PREFIX, Constant._CACHE_KEY_CONTENTPROJECTRANK_APPTOP,
//                bill.getMonthId(), bill.getLabelName1());
//        String conditionKey = bill.getCityId();
//        if(cacheService.hasHashCache(tableKey, conditionKey)) {
//            topList = cacheService.getHashCache(tableKey, conditionKey);
//            logger.info("缓存命中[" +tableKey + "," + conditionKey + "]：size=" + (topList == null ? 0 : topList.size()));
//            return topList;
//        }

//        PageHelper.startPage(0, 10);
//        System.out.println(billBoardMapper.getWordList(word));
//        topList = (Page<TopWord>) billBoardMapper.getWordList(word);
//        if (topList != null && topList.size() > 0) {
//            int i = 1;
//            for (TopWord wd : topList) {
//                wd.setId(Integer.toString(i+1));
//            }
//            cacheService.setHashCacheWithExpireTime(tableKey, conditionKey, topList, CacheService.COMMON_TIMEOUTSECOND);
//            logger.info("缓存数据[" +tableKey + "," + conditionKey + "]：size=" + (topList == null ? 0 : topList.size()));
//        }
//        return null;
//    }
}
