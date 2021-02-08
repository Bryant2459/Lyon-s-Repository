package com.yang.test.mapper;

import com.yang.test.po.Score;
import com.yang.test.po.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreMapper {
    //查询
    List<Score> selectScores();

    //按照ID 修改记录
    int updateScoreByID(Score score);

    //新增
    int addScore(Score score);

    //删除
    int deleteScoreByID(String id);

}
