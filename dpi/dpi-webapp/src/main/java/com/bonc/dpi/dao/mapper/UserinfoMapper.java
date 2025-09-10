package com.bonc.dpi.dao.mapper;

import com.bonc.login.action.City;

import java.util.List;
import java.util.Map;

public interface UserinfoMapper {
    List<City> selectByCitydm(Map<String, String> paramMap);
}