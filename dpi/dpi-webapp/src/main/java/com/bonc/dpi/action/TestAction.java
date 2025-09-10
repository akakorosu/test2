package com.bonc.dpi.action;

import com.alibaba.fastjson.JSON;
import com.bonc.dpi.entity.VideoCategoryInfo;
import com.bonc.dpi.entity.VideoWebInfo;
import com.bonc.dpi.service.VideoStoreService;
import com.bonc.dpi.utils.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestAction {

    private final static Logger logger = LoggerFactory.getLogger(TestAction.class);

    @RequestMapping(value = "/nologin/test/fabuSyncPositionEventId",method= RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public JSON fabuSyncPositionEventId() {
        logger.info("-----------------调用聚合数据 身份证验证API BEGIN--------------->");

        String enumKey = "REG00000000000000001";
        String enumValue = "测试场景";
        String enumType = "ln_yx_scene";
        String state = "1";
        String result2 = ""; //是否匹配

        try {
        	// 20250721 接口变更 start
//            String url2 = "http://10.68.76.66/mcd-web-ln/api/syncPositionEventId" +
            String url2 = "http://10.68.76.66/mcd7Backend/plan-svc/ln/event/interface/syncPositionEventId" +
            // 20250721 接口变更 end
                    "?ENUM_KEY=" + enumKey + "&ENUM_VALUE=" + enumValue + "&ENUM_TPYE=" + enumType + "&STATE=" + state;
            logger.info("请求url2:" + url2);
            result2 = HttpClientUtil.doGet(url2);
            logger.info("请求结果2：" + result2);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.toString());
        }
//        String result = ""; //是否匹配
//        try {
//            String url = "http://10.68.159.2:6000/mcd-web-ln/api/syncPositionEventId" +
//                    "?ENUM_KEY=" + enumKey + "&ENUM_VALUE=" + enumValue + "&ENUM_TPYE=" + enumType + "&STATE=" + state;
//            logger.info("请求url:" + url);
//            result = HttpClientUtil.doGet(url);
//            logger.info("请求结果：" + result);
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.info(e.toString());
//        }

        logger.info("<-----------------调用聚合数据 身份证验证API END---------------");
        return JSON.parseObject(result2);

//        return resultmap;
    }
}
