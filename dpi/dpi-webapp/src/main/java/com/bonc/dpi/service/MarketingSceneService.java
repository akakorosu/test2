package com.bonc.dpi.service;

import com.alibaba.fastjson.JSON;
import com.bonc.common.cst.CST;
import com.bonc.dpi.dao.mapper.MarketingSceneMapper;
import com.bonc.dpi.entity.*;
import com.bonc.dpi.utils.HanziZhuanPinyin;
import com.bonc.dpi.utils.HttpClientUtil;
import com.bonc.dpi.utils.Loadjar;
import com.bonc.system.dao.entity.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
public class MarketingSceneService {

    private final static Logger logger = LoggerFactory.getLogger(MarketingSceneService.class);

    @Resource
    private MarketingSceneMapper marketingSceneMapper;

    public int getSceneTotalNum(MarketingScene scene) {
        return marketingSceneMapper.getSceneTotalNum(scene);
    }

    public Map<String, Object> getSceneList(String pagenum, MarketingScene scene) {
        Map<String, Object> resultmap = new HashMap<String, Object>();
        int totalnum = marketingSceneMapper.getSceneTotalNum(scene);
        Map<String , Object> params = scene.getMap();
        int limitnum = 15;//每页显示条数
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
        List<MarketingScene> boardList = marketingSceneMapper.getSceneList(params);
        if (boardList != null && boardList.size() > 0) {
            int i = 1;
            for (MarketingScene wd : boardList) {
                wd.setId(Integer.toString(i++));
            }
        }
        resultmap.put("boardList", boardList);
        resultmap.put("totalpages", totalpages);
        resultmap.put("totalnum", totalnum);
        return resultmap;
    }

    public Boolean save(MarketingScene scene) {
        scene = setSceneOperator(scene);
        logger.info(JSON.toJSONString(scene));
        return marketingSceneMapper.save(scene.getMap());
    }

    public Boolean update(MarketingScene scene) {
        return marketingSceneMapper.update(scene.getMap());
    }

    public List getProdInfo(String querystr) {
        return marketingSceneMapper.getProdInfo(querystr);
    }


    public String fabu(MarketingScene scene) {
        //1.app域名配置，2.视频内容配置，3.关键词配置
        MarketBushi bushi ;
        if("1".equals(scene.getType_code())){
            //先获取Host
            handleDomain(scene);
            //todo:2配置部施表（一对多）--dim_scene_busi_market 2.1 - 通过codes查询mysql中rule表
            //创建一条部施
            //todo:4同步状态到Redis
        }else if("2".equals(scene.getType_code())){
            handleDomain(scene);
            handleContent(scene);
        }else if("3".equals(scene.getType_code())){
        	//handleDomain(scene);//add by yk
            handleKeywords(scene);
            //配置布施表
        }

        if ( StringUtils.isEmpty(scene.getBusi_code()) ) {
            bushi = getBushi(scene);
            logger.info(JSON.toJSONString(bushi));
            //插入表
            int insertbushiid = marketingSceneMapper.insertbushi(bushi);
            logger.info(String.valueOf(bushi.getMarket_id()));

            MarketBushiCondition condition = getCondition(scene, bushi);
            logger.info(JSON.toJSONString(condition));
            int insertbushiconditionid = marketingSceneMapper.insertbushicondition(condition);
            logger.info(String.valueOf(condition.getCondition_id()));

            scene.setBusi_code(bushi.getBusi_code());
        } else {
            updateBushi(scene);
            updateCondition(scene);
        }

        updateSceneStateAndBushicode(scene);
        return redisUpDateSceneCheckTime(scene);
    }

    //更新已有场景表的信息
    private boolean updateBushi(MarketingScene scene) {
        HashMap map = new HashMap();
        map.put("busi_code", scene.getBusi_code());
        map.put("busi_name", scene.getScene_text());

        MarketBushi bushi = new MarketBushi();
        bushi= setTypeListAndRuleList(scene,bushi);
        map.put("type_list", bushi.getType_list());
        map.put("rule_list", bushi.getRule_list());

        map.put("start_date", scene.getStart_date() + getLocalTime());
        map.put("end_date", scene.getEnd_date() + getLocalTime());
        return marketingSceneMapper.updateBushi(map);
    }

    //更新已有场景条件表的信息
    private boolean updateCondition(MarketingScene scene) {
        HashMap map = new HashMap();
        map.put("busi_code", scene.getBusi_code());
        // 202309 实时转送增加累加条件 Start
//        map.put("condition_type", "1".equals(scene.getRule_type())?"1":"0");
//        map.put("send_num", "1".equals(scene.getRule_type())?scene.getGo_times():"");
//        map.put("send_rate_unit", "1".equals(scene.getRule_type())?"D":"UNLIMIT");
//        map.put("send_rate_value", "1".equals(scene.getRule_type())?scene.getFlow_time_circle():"");
//        map.put("field_name", "1".equals(scene.getRule_type())?"flow":"");
//        map.put("sum_field_unit", "1".equals(scene.getRule_type())?"MB":"");
//        map.put("sum_field_value", "1".equals(scene.getRule_type())?scene.getFlow_max():"");
//        map.put("sum_time_unit", "1".equals(scene.getRule_type())?"D":"");
//        map.put("sum_time_value", "1".equals(scene.getRule_type())?scene.getFlow_time_circle():"");
    	
        if ("1".equals(scene.getRule_type())) {
        	map.put("condition_type", "1");
        	map.put("send_num", scene.getGo_times());
        	map.put("send_rate_unit", "D");
        	map.put("send_rate_value", scene.getFlow_time_circle());
        	map.put("field_name", "flow");
        	map.put("sum_field_unit", "MB");
        	map.put("sum_field_value", scene.getFlow_max());
        	map.put("sum_time_unit", "D");
        	map.put("sum_time_value", scene.getFlow_time_circle());
        } else {
        	map.put("condition_type", "0");
        	map.put("send_num", scene.getGo_times());
        	map.put("field_name", "");
        	map.put("sum_field_unit", "");
        	map.put("sum_field_value", "");
        	if (StringUtils.isNotBlank(scene.getGo_times()) && StringUtils.isNumeric(scene.getGo_times()) 
        			&& Integer.parseInt(scene.getGo_times()) > 0) {
        		map.put("send_rate_unit", "D");
        		map.put("send_rate_value", scene.getFlow_time_circle());
        		map.put("sum_time_unit", "D");
        		map.put("sum_time_value", scene.getFlow_time_circle());
        	} else {
        		map.put("send_rate_unit", "UNLIMIT");
        		map.put("send_rate_value", "");
        		map.put("sum_time_unit", "");
        		map.put("sum_time_value", "");
        	}
        }
        // 202309 实时转送增加累加条件 End
        map.put("start_date", scene.getStart_date() + getLocalTime());
        map.put("end_date", scene.getEnd_date() + getLocalTime());
        return marketingSceneMapper.updateCondition(map);
    }

    public JSON fabuSyncPositionEventId(MarketingScene scene) {
        logger.info("-----------------调用聚合数据 身份证验证API BEGIN--------------->");

        String enumKey = "REG90000000000000001";
        String enumValue = "测试场景";
        String enumType = "ln_yx_scene";
        String state = "1";
        enumValue = scene.getScene_text();
//        enumType = scene.getBusi_code();
        enumKey = scene.getBusi_code();
        state = scene.getState_code().equals("4")?"1":"0";
        // 20250721 接口变更 start
//        String url2 = "http://10.68.76.66/mcd-web-ln/api/syncPositionEventId" +
        String url2 = "http://10.68.76.66/mcd7Backend/plan-svc/ln/event/interface/syncPositionEventId" +
                "?ENUM_KEY=" + enumKey + "&ENUM_VALUE=" + enumValue + "&ENUM_TPYE=" + enumType + "&STATE=" + state;
        // 20250721 接口变更 end
        logger.info("请求url2:" + url2);
        String result = ""; //是否匹配
        try {
            result = HttpClientUtil.doGet(url2);
            System.out.println("请求结果：" + result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.toString());
        }
        logger.info("<-----------------调用聚合数据 身份证验证API END---------------");
        return JSON.parseObject(result);

    }
    private void handleContent(MarketingScene scene) {
        System.out.println("==================="+JSON.toJSONString(scene.getVideo_code()));
        String[] split = scene.getVideo_code().split("\\|");
//        String[] splitApp = scene.getApp_codes().split("\\|");
        for (String item: split
             ) {
            if(marketingSceneMapper.videoContentIdExist(item)==0){
                HashMap map = new HashMap();
                System.out.println("==================="+scene.getScene_text());
                map.put("content_id","contid_"+HanziZhuanPinyin.ToFirstChar(scene.getScene_text())+System.currentTimeMillis());
                map.put("content_rule",item);
                marketingSceneMapper.videoContentInsert(map);
                updateRedis_content(map);
            };
        }

    }



    private void handleKeywords(MarketingScene scene) {
        System.out.println("==================="+JSON.toJSONString(scene.getKeys_strs().split("\\|")));
        List<String> keywordnoexistlist = keywordsExistFilter(scene);
        for (String keywordnoexist: keywordnoexistlist
             ) {
            HashMap map = new HashMap();
            map.put("keyword",keywordnoexist);
            marketingSceneMapper.insertKeyword(map);
        }
        String[] keys = scene.getKeys_strs().split("\\|");
        List keywords = marketingSceneMapper.getKeywords(keys);
        if(keywords.size()>0){
            updateRedis_keywords(keywords);
        }
    }

    private void updateRedis_keywords(List keywords) {
        List<HashMap> keywords4redis = marketingSceneMapper.getkeywords4redis(keywords);
        logger.info(JSON.toJSONString(keywords4redis));
        for (HashMap map: keywords4redis
        ) {
            HashMap upd=new HashMap();
            upd.put(map.get("rule_id").toString(), map.get("rule_id")+"|"+map.get("keyword"));
            redisHset("gx_isop|dim_rule_keyword" ,upd);
            logger.info("===========================模拟关键字新增导入redis=====================");
            logger.info("redisHset(\"gx_isop|dim_rule_keyword\" ,upd)");
            logger.info(upd.toString());
            logger.info("===========================模拟结束===================================");
//            redisHget();
        }
    }
    private void updateRedis_content(HashMap map) {
        HashMap upd=new HashMap();
        upd.put(map.get("content_id"),map.get("content_id")+"|"+map.get("content_rule"));
        redisHset("gx_isop|dim_rule_content" ,upd);
        logger.info(map.toString());
        logger.info("===========================模拟新增视频内容导入redis=====================");
        logger.info("redisHset(\"gx_isop|dim_rule_content\" ,upd)");
        logger.info(upd.toString());
        logger.info("===========================模拟结束===================================");
//        redisHget();
    }

    private List keywordsExistFilter(MarketingScene scene) {
        String[] keys = scene.getKeys_strs().split("\\|");
        List<String> list = new ArrayList<>();
        for (String key:keys
             ) {
            Integer res=marketingSceneMapper.keywordsExist(key);
//            logger.info(res.toString());
            if(res==0){
                list.add(key);
            }
        }
        return list;
    }

    /*todo:定时任务，上下线营销场景--0.状态为未发布，不做检查，状态为暂停，不做检查
                                 1.未到开始时间：判断当前时间小于配置时间，不做操作
                                 2.判断时间到达（大于）配置结束时间，下线场景，结束场景
                                 3.判断时间大于开始小于结束时间，上线场景
     */
    public String redisUpDateSceneCheckTime(MarketingScene scene) {
        scene.getStart_date();
        Integer integer = DateUtil.compareDate(DateUtil.formatDate(new Date()), scene.getStart_date());
        if(integer<0){//当前时间小于发布时间
            return "发布成功";
        }else {
            Integer integer1 = DateUtil.compareDate(DateUtil.formatDate(new Date()), scene.getEnd_date());
            if(integer1<=0){//当前时间大于等于发布时间，小于等于结束时间
                redisOnline(scene);                                            //上线
                MarketingScene upd = new MarketingScene();
                upd.setScene_code(scene.getScene_code());
                upd.setState_code("4");
                upd.setState_name("运行中");
                upd.setBusi_code(scene.getBusi_code());
                upd.setScene_text(scene.getScene_text());
                Boolean  res = update( upd);
                fabuSyncPositionEventId(upd);//发短信通知IOP场景已上线线
                return "上线成功";
            }else {//当前时间大于结束时间

                redisOffline(scene);                                       //下线
                MarketingScene upd = new MarketingScene();
                upd.setScene_code(scene.getScene_code());
                upd.setState_code("5");
                upd.setState_name("已失效");
                upd.setBusi_code(scene.getBusi_code());
                upd.setScene_text(scene.getScene_text());
                Boolean  res = update( upd);
                fabuSyncPositionEventId(upd);
                return "已过期";
            }
        }
//        logger.info( "" + integer);
//        redisOnline(scene);
        /*
            todo:1.已发布：判断是否过期，过期下线
                2.：判断是否大于结束时间->已失效
         */
        //todo:1开始时间大于当前时间，不启动
        //todo:2大于开始时间小于结束时间，启动
        //todo:3大于结束时间，不启动，状态置为已失效
//        return false;
    }

    private boolean updateSceneStateAndBushicode(MarketingScene scene) {
        HashMap map = new HashMap();
        map.put("scene_code",scene.getScene_code());
        map.put("state_code","2");
        map.put("state_name","已发布");
        map.put("busi_code",scene.getBusi_code());
        return marketingSceneMapper.update(map);
    }

    private void handleDomain(MarketingScene scene) {
        List<MarketingHost> listsum = getHostList(scene);//查数据同步的表 //getProdHosts
//        logger.info(JSON.toJSONString(listsum));
        //todo:1查域名，并添加域名表--dim_scene_rule_domain--发布时添加
        List<MarketingHost> ruleListFilter = RuleListFilter(listsum);//查域名  dim_scene_rule_domain,不存在的规则，则保存 //searchhostrule
        //过滤表中规则,存在的就不添加
        List<Integer> rule_ids = insertRules(ruleListFilter);//addhostrule

        //添加不存在的规则到数据库，并同步到redis
        logger.info("rule_ids.size()="+rule_ids.size());
        if(rule_ids.size()>0){
        	updateRedis(rule_ids);//同步到redis
        }
    }

    private MarketBushiCondition getCondition(MarketingScene scene, MarketBushi bushi) {
        MarketBushiCondition condition = new MarketBushiCondition();
        condition.setBusi_code(bushi.getBusi_code());
        // 202309 实时转送增加累加条件 Start
//        condition.setCondition_type("1".equals(scene.getRule_type())?"1":"0");
//        condition.setSend_num("1".equals(scene.getRule_type())?scene.getGo_times():"");
//        condition.setSend_rate_unit("1".equals(scene.getRule_type())?"D":"UNLIMIT");
//        condition.setSend_rate_value("1".equals(scene.getRule_type())?scene.getFlow_time_circle():"");
//        condition.setField_name("1".equals(scene.getRule_type())?"flow":"");
//        condition.setSum_field_unit("1".equals(scene.getRule_type())?"MB":"");
//        condition.setSum_field_value("1".equals(scene.getRule_type())?scene.getFlow_max():"");
//        condition.setSum_time_unit("1".equals(scene.getRule_type())?"D":"");
//        condition.setSum_time_value("1".equals(scene.getRule_type())?scene.getFlow_time_circle():"");
    	
        if ("1".equals(scene.getRule_type())) {
        	// 累加值场景
        	condition.setCondition_type("1");
        	condition.setSend_num(scene.getGo_times());
        	condition.setSend_rate_unit("D");
        	condition.setSend_rate_value(scene.getFlow_time_circle());
        	condition.setField_name("flow");
        	condition.setSum_field_unit("MB");
        	condition.setSum_field_value(scene.getFlow_max());
        	condition.setSum_time_unit("D");
        	condition.setSum_time_value(scene.getFlow_time_circle());
        } else {
        	//实时触发场景
        	condition.setCondition_type("0");
        	condition.setSend_num(scene.getGo_times());
        	condition.setField_name("");
        	condition.setSum_field_unit("");
        	condition.setSum_field_value("");
        	if (StringUtils.isNotBlank(scene.getGo_times()) && StringUtils.isNumeric(scene.getGo_times()) 
        			&& Integer.parseInt(scene.getGo_times()) > 0) {
        		condition.setSend_rate_unit("D");
        		condition.setSend_rate_value(scene.getFlow_time_circle());
        		condition.setSum_time_unit("D");
        		condition.setSum_time_value(scene.getFlow_time_circle());
        	} else {
        		condition.setSend_rate_unit("UNLIMIT");
        		condition.setSend_rate_value("");
        		condition.setSum_time_unit("");
        		condition.setSum_time_value("");
        	}
        }
        // 202309 实时转送增加累加条件 End
        condition.setStatus("1");
        condition.setSend_start_date("0000001");
        condition.setSend_end_date("235959");
        condition.setStart_date(bushi.getStart_date());
        condition.setEnd_date(bushi.getEnd_date());
        return  condition;
    }

    private MarketBushi getBushi(MarketingScene scene) {
        MarketBushi bushi = new MarketBushi();
        bushi.setAccount_id("cmcc");
        bushi.setBusi_name(scene.getScene_text());
        bushi.setBusi_code("yx-"+System.currentTimeMillis());
        bushi= setTypeListAndRuleList(scene,bushi);
        bushi.setStatus("1");
        bushi.setStart_date(scene.getStart_date() + getLocalTime());
        bushi.setEnd_date(scene.getEnd_date() + getLocalTime());
//        logger.info(JSON.toJSONString(rule_list));
        return bushi;
    }

    private MarketBushi setTypeListAndRuleList(MarketingScene scene, MarketBushi bushi) {
        HashMap map = new HashMap();
        map.put("1","2");
        map.put("2","2");
        map.put("3","3");
        String rule_list = "";
        String type_list = "";
        List<Integer> ruleIds;
        if("3".equals(scene.getType_code())){
            String[] keys = scene.getKeys_strs().split("\\|");
            ruleIds = marketingSceneMapper.getKeywords(keys);
        }else {
            String[] codes=scene.getApp_codes().split("\\|");
            ruleIds = marketingSceneMapper.getRuleIds(codes);
        }

        for (Integer id:ruleIds
             ) {
            rule_list += id;
            rule_list += ",";
            type_list += map.get(scene.getType_code());
            type_list += ",";
        }
        if("2".equals(scene.getType_code())){
            String[] split = scene.getVideo_code().split("\\|");
            for (String item:split
                 ) {
                String videoContentId = marketingSceneMapper.getVideoContentId(item);
                rule_list += videoContentId;
                rule_list += ",";
                type_list += "5";
                type_list += ",";
            }

        }
        rule_list = rule_list.substring(0,rule_list.length()-1);
        type_list = type_list.substring(0,type_list.length()-1);
        bushi.setRule_list(rule_list);
        bushi.setType_list(type_list);
        return bushi;
    }

    private ArrayList insertRules(List<MarketingHost> ruleListFilter) {
        ArrayList res = new ArrayList();
        for (MarketingHost host:ruleListFilter
        ) {
            HashMap map = host.parameterMap();
            marketingSceneMapper.addhostrule(map);
            res.add(map.get("rule_id"));
        }
        return res;
    }

    private ArrayList<MarketingHost> RuleListFilter(List<MarketingHost> listsum) {
        ArrayList<MarketingHost> res = new ArrayList<>();
        for (MarketingHost host:listsum
        ) {
            Integer searchhostrule = marketingSceneMapper.searchhostrule(host.parameterMap());
            logger.info(searchhostrule.toString());
            if(searchhostrule==0){
                res.add(host);
            }
        }
        return res;
    }

    private ArrayList getHostList(MarketingScene scene) {
        ArrayList result = new ArrayList();
        String[] codes=scene.getApp_codes().split("\\|");
        for (String code:codes
        ) {
            List list=marketingSceneMapper.getProdHosts(code);
            result.addAll(list);
        }
        return result;
    }




    private void updateRedis(List<Integer> rule_ids) {
        List<HashMap> updrules = marketingSceneMapper.getRules(rule_ids);
        logger.info(JSON.toJSONString(updrules));
        for (HashMap map: updrules
        ) {
            HashMap upd=new HashMap();
            //concat(rule_id,'|',rule_id,'|',prod_type_code,'|',prod_type_name,'|',prod_code,'|',prod_name,'|',host_rule)
            upd.put(map.get("rule_id").toString(), "|"+map.get("rule_id")+"|"+map.get("prod_type_code")+"|"+map.get("prod_type_name")+"|"+map.get("prod_code")+"|"+map.get("prod_name")+"|"+map.get("host_rule"));
//            logger.info("===========================模拟域名新增导入redis=====================");
//            logger.info("redisHset(\"gx_isop|dim_rule_domain\" ,upd)");
//            logger.info(upd.toString());
//            logger.info("===========================模拟结束==================================");
//            redisHget();
            logger.info("===========================域名新增导入redis=====================");
            logger.info("redisHset(\"gx_isop|dim_rule_domain\" ,upd)");
            redisHset("gx_isop|dim_rule_domain" ,upd);
            logger.info(upd.toString());
            logger.info("===========================导入结束==================================");

        }
        logger.info(updrules.toString());
    }

    private Object redisSet(String key, String value) {
        String path = "/data/webapp/kafka-redis-bill-1.0-SNAPSHOT-jar-with-dependencies.jar";

        Loadjar.loadJar(path);
        Class<?> aClass = null;
        Object strip = null;
        try {
            aClass = Class.forName("com.bonc.main.RedisDataIn");
            Object instance = aClass.newInstance();
            strip = aClass.getDeclaredMethod("redisSet", String.class, String.class).invoke(instance, key, value);
            System.out.println(strip);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        logger.info(JSON.toJSONString(strip));
        return strip;
    }

    private Object redisHget() {
        String path = "/data/webapp/kafka-redis-bill-1.0-SNAPSHOT-jar-with-dependencies.jar";
//        String path = "e:/kafka-redis-bill-1.0-SNAPSHOT-jar-with-dependencies.jar";
        Loadjar.loadJar(path);
        Class<?> aClass ;
        Object strip = null;
        try {
            aClass = Class.forName("com.bonc.main.RedisDataIn");
            Object instance = aClass.newInstance();
//            strip = aClass.getDeclaredMethod("Test", String.class).invoke(instance, "1");
            strip = aClass.getDeclaredMethod("redisHGet", String.class,String.class).invoke(instance, "1","1");
            System.out.println(strip);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        logger.info(JSON.toJSONString(strip));
        return strip;
    }

    private Object redisHset(String key,Map hash) {
        String path = "/data/webapp/kafka-redis-bill-1.0-SNAPSHOT-jar-with-dependencies.jar";
//      String path = "e:/kafka-redis-bill-1.0-SNAPSHOT-jar-with-dependencies.jar";
        Loadjar.loadJar(path);
        Class<?> aClass = null;
        Object strip = null;
        try {
            aClass = Class.forName("com.bonc.main.RedisDataIn");
            Object instance = aClass.newInstance();
            strip = aClass.getDeclaredMethod("redisHset", String.class,Map.class).invoke(instance, key,hash);
            System.out.println(strip);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        logger.info(JSON.toJSONString(strip));
        return strip;
    }

    private Object redisHdel(String key1,String key2) {
        String path = "/data/webapp/kafka-redis-bill-1.0-SNAPSHOT-jar-with-dependencies.jar";
//        String path = "e:/kafka-redis-bill-1.0-SNAPSHOT-jar-with-dependencies.jar";
        Loadjar.loadJar(path);
        Class<?> aClass ;
        Object strip = null;
        try {
            aClass = Class.forName("com.bonc.main.RedisDataIn");
            Object instance = aClass.newInstance();
//            strip = aClass.getDeclaredMethod("Test", String.class).invoke(instance, "1");
            strip = aClass.getDeclaredMethod("redisHDel", String.class,String.class).invoke(instance, key1,key2);
            System.out.println(strip);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        logger.info(JSON.toJSONString(strip));
        return strip;
    }

    public void executeRedisJob() {
    	// 202310 定时程序取消从已发布->运行中的状态变更 Start
//        String stateCode = "2";
//        List<MarketingScene> scanSceneList = marketingSceneMapper.getSceneByState(stateCode);
//        logger.info(JSON.toJSONString(scanSceneList));
//        if(scanSceneList.size()>0){
//            for (MarketingScene sc: scanSceneList
//                 ) {
//                redisUpDateSceneCheckTime(sc);
//            }
//        }
//        stateCode = "4";
    	String stateCode = "4";
        // 202310 定时程序取消从已发布->运行中的状态变更 End
        List<MarketingScene> scanSceneList2 = marketingSceneMapper.getSceneByState(stateCode);
        logger.info(JSON.toJSONString(scanSceneList2));
        if(scanSceneList2.size()>0){
            for (MarketingScene sc: scanSceneList2
            ) {
                redisUpDateSceneCheckTime(sc);
            }
        }
    }

    public void redisOffline(MarketingScene scene) {
        MarketBushi marketBushi4Redis = marketingSceneMapper.getMarketBushi(scene);
        MarketBushiCondition marketBushiCondition4Redis = marketingSceneMapper.getMarketBushiCondition(scene);

        logger.info("=============场景状态下线功能开始============");
        logger.info("下线场景："+scene.getScene_text()+" code:"+scene.getScene_code()+" state:"+scene.getState_name());
        logger.info(JSON.toJSONString(scene));
        redisHdel("gx_isop|sys_busi_market", marketBushi4Redis.getMarket_id());
        redisHdel("gx_isop|sys_busi_market_condition", marketBushiCondition4Redis.getCondition_id());
//        marketingSceneMapper.deleteMarketBushi(scene);
//        marketingSceneMapper.deleteMarketBushiCondition(scene);
         redisSet("gx_isop|time","true");
        logger.info("=============场景状态下线功能结束============");
    }

    public void redisOnline(MarketingScene scene) {
        MarketBushi marketBushi4Redis = marketingSceneMapper.getMarketBushi(scene);
        MarketBushiCondition marketBushiCondition4Redis = marketingSceneMapper.getMarketBushiCondition(scene);

//        marketingSceneMapper.getMarketingScene(scene);
        logger.info("=============场景状态上线功能开始============");
        logger.info("上线场景："+scene.getScene_text()+" code:"+scene.getScene_code()+" state:"+scene.getState_name());
        logger.info(JSON.toJSONString(scene));
        logger.info(JSON.toJSONString(marketBushi4Redis));
//        logger.info(JSON.toJSONString(marketBushi4Redis.redisString()));
        logger.info(JSON.toJSONString(marketBushi4Redis.redisMap()));
        logger.info(JSON.toJSONString(marketBushiCondition4Redis.redisMap()));
        redisHset("gx_isop|sys_busi_market",marketBushi4Redis.redisMap());
        redisHset("gx_isop|sys_busi_market_condition",marketBushiCondition4Redis.redisMap());
        redisSet("gx_isop|time","true");
        logger.info("=============场景状态上线功能结束============");

    }
    //权限
    public String getauthority(String user_id) {
        return marketingSceneMapper.getauthority(user_id);
    }
    //操作人
    private MarketingScene setSceneOperator(MarketingScene scene) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        SysUser user = (SysUser) request.getSession().getAttribute(CST.SESSION_SYS_USER_INFO);
        String user_id = user.getUserId();
        String loginId = user.getLoginId();
        scene.setOperator_code(user_id);
        scene.setOperator_name(loginId);
//        logger.info(JSON.toJSONString(user));
        return scene;
    }

    private String getLocalTime() {
        String localTime;
        try{
            localTime= " " + LocalTime.now().toString().substring(0,8);
        } catch(Exception e) {
            e.printStackTrace();
            localTime = " 08:00:00";
        }
        return localTime;
    }

    public static void main(String[] args) {
        System.out.println();
    }

}
