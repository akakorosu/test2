package com.bonc.dpi.dao.mapper;

import com.bonc.dpi.dao.entity.BillBoard;

import com.bonc.dpi.entity.WordCloud;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BillBoardMapper {
    List<BillBoard> selectCategoryLabel();

    List<BillBoard> queryHotData(BillBoard bill);

    List<WordCloud> getWordCloud(BillBoard billBoard);
    List<WordCloud> getWordCloud();

    Page<BillBoard> getWordlist(BillBoard word);

    Page<BillBoard> getSearchList(BillBoard word);

    List<BillBoard> getCategorylist();


    List<BillBoard> getClass2list(BillBoard billBoard);

    int getHotWordAmount(BillBoard billBoard);

    int insertHotData(@Param("billBoards") List<BillBoard> billBoards, @Param("user_id")String user_id);

    List<BillBoard> getHotWordList(Map<String, Object> params);

    List<BillBoard> queryHotData4Export(BillBoard billBoard);
}
