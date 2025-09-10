package com.bonc.dpi.dao.mapper;

import com.bonc.dpi.entity.MarketBushi;
import com.bonc.dpi.entity.MarketBushiCondition;
import com.bonc.dpi.entity.MarketingScene;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface MarketingSceneMapper {

    int getSceneTotalNum(MarketingScene scene);

    List<MarketingScene> getSceneList(Map<String, Object> params);

    List<MarketingScene> getSceneByState(String state_code);

    Boolean save(Map<String, Object> map);

    Boolean update(Map<String, Object> map);

    Boolean updateBushi(Map<String, Object> map);

    Boolean updateCondition(Map<String, Object> map);

    List getProdInfo(String querystr);

    List getProdHosts(String querystr);

    int searchhostrule(HashMap map);

    int addhostrule(HashMap map);

    List<HashMap> getRules(List<Integer> rule_ids);

    List getRuleIds(String[] codes);

    int insertbushi(MarketBushi bushi);

    int insertbushicondition(MarketBushiCondition condition);

    List<Integer> keywordsExistFilter(String[] keys);

    int keywordsExist(String key);

    int insertKeyword(HashMap map);

    List getKeywords(String[] keys);

    List<HashMap> getkeywords4redis(List keywords);

    int videoContentIdExist(String video_code);

    void videoContentInsert(HashMap map);

    String getVideoContentId(String video_code);

    List getScanSceneList();

//    void getMarketingScene(MarketingScene scene);

    MarketBushi getMarketBushi(MarketingScene scene);

    String getauthority(String user_id);

    MarketBushiCondition getMarketBushiCondition(MarketingScene scene);

    Boolean deleteMarketBushi(MarketingScene scene);

    Boolean deleteMarketBushiCondition(MarketingScene scene);
}
