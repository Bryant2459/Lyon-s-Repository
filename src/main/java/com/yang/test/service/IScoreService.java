package com.yang.test.service;

import com.yang.test.po.Score;
import com.yang.test.po.Student;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Created By Lyon
 * Date: 2021/2/7
 * Time: 21:33
 */
public interface IScoreService {
    //查询所有
    List<Score> selectScores();

    //修改
    Boolean updateScoreByID(Score score);

    //新增
    Boolean addScore(Score score);

    //删除

    Boolean deleteScoreByID(String id);
}
