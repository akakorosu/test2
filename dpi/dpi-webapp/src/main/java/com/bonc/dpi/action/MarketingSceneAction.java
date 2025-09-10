package com.bonc.dpi.action;

import com.alibaba.fastjson.JSON;
import com.bonc.common.cst.CST;
import com.bonc.dpi.cache.CacheService;
import com.bonc.dpi.entity.MarketingScene;
import com.bonc.dpi.service.MarketingSceneService;
import com.bonc.system.dao.entity.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
public class MarketingSceneAction {

    private final static Logger logger = LoggerFactory.getLogger(MarketingSceneAction.class);

    @Resource
    private MarketingSceneService marketingSceneService;
    @Resource
    private CacheService cacheService;

    @RequestMapping(value = "/marketingScene/getSceneTotalNum",method= RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> getSceneTotalNum(MarketingScene scene) {

        Map<String, Object> resultmap = new HashMap<String, Object>();
        System.out.println(JSON.toJSONString(scene));
        int totalnum = marketingSceneService.getSceneTotalNum(scene);
        resultmap.put("totalnum", totalnum);
        return resultmap;
    }
    //查询产品名及id
    @RequestMapping(value = "/marketingScene/queryprod",method= RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> queryprod(String querystr) {
        Map<String, Object> resultmap = new HashMap<String, Object>();
        System.out.println(querystr);
        List res = marketingSceneService.getProdInfo(querystr);
        logger.info(res.toString());
        resultmap.put("res", res);
        return resultmap;
    }
    /*
    * 查询场景配置列表
    * */
    @RequestMapping(value = "/marketingScene/getSceneList",method= RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> getSceneList(String pagenum,MarketingScene scene) {
        System.out.println(pagenum);
        System.out.println(JSON.toJSONString(scene));
        Map<String, Object> resultmap = new HashMap<String, Object>();
        try {
            resultmap = marketingSceneService.getSceneList(pagenum, scene);
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
    @RequestMapping(value = "/marketingScene/save",method= RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> save(MarketingScene scene) {
        Map<String, Object> resultmap = new HashMap<String, Object>();
        logger.info(JSON.toJSONString(scene));
        logger.info(scene.getScene_code());
        Boolean res ;
        if(scene.getScene_code()==null||scene.getScene_code()==""){
            logger.info(JSON.toJSONString(scene));
            res = marketingSceneService.save(scene);
            logger.info(res.toString());
            if(res){
                resultmap.put("result", "添加成功");
            }else {
                resultmap.put("result", "添加失败");
            }
        }else {
            res = marketingSceneService.update( scene);
            if(res){
                resultmap.put("result", "修改成功");
            }else {
                resultmap.put("result", "修改失败");
            }
        }


        return resultmap;
    }

    @RequestMapping(value = "/marketingScene/fabu",method= RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> fabu(MarketingScene scene) {
        Map<String, Object> resultmap = new HashMap<String, Object>();

        if("2".equals(scene.getState_code())){
            resultmap.put("result", "已经发布过了");
            return resultmap;
        }

        resultmap.put("result", marketingSceneService.fabu(scene));
        System.out.println(JSON.toJSONString(scene));
        return resultmap;
    }

    @RequestMapping(value = "/marketingScene/authority",method= RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> authority() {
        Map<String, Object> resultmap = new HashMap<String, Object>();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        SysUser user = (SysUser) request.getSession().getAttribute(CST.SESSION_SYS_USER_INFO);
        String user_id = user.getUserId();
        String authority = marketingSceneService.getauthority(user_id);
//        System.out.println(JSON.toJSONString(user));
//        System.out.println(JSON.toJSONString(scene));
//        Boolean res = marketingSceneService.save(scene);
//        int totalnum = marketingSceneService.getSceneTotalNum(scene);

        resultmap.put("result", authority);
        return resultmap;
    }
    @RequestMapping(value = "/marketingScene/stop",method= RequestMethod.POST, produces="application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> stop(MarketingScene scene) {
        Map<String, Object> resultmap = new HashMap<String, Object>();
        if("3".equals(scene.getState_code())){
            resultmap.put("result", "已经暂停过了");
            return resultmap;
        }
        MarketingScene upd = new MarketingScene();
        upd.setScene_code(scene.getScene_code());
        upd.setState_code("3");
        upd.setState_name("已暂停");
        Boolean  res = marketingSceneService.update(upd);
        upd.setBusi_code(scene.getBusi_code());
        upd.setScene_text(scene.getScene_text());
        marketingSceneService.redisOffline(scene);
        marketingSceneService.fabuSyncPositionEventId(upd);
//        System.out.println(JSON.toJSONString(user));
//        System.out.println(JSON.toJSONString(scene));
//        Boolean res = marketingSceneService.save(scene);
//        int totalnum = marketingSceneService.getSceneTotalNum(scene);

        resultmap.put("result", "产品暂停成功");
        return resultmap;
    }

    @Scheduled(cron="0 0 8 * * ?")
    public void RedisJob () {
        logger.info("=============定时场景管理功能开始============");
        marketingSceneService.executeRedisJob();
        logger.info("=============定时场景管理功能结束============");
    }
}
