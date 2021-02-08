package com.yang.test.service.impl;

import com.yang.test.mapper.ScoreMapper;
import com.yang.test.mapper.StudentMapper;
import com.yang.test.po.Score;
import com.yang.test.po.Student;
import com.yang.test.service.IScoreService;
import com.yang.test.service.IStudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Created By Lyon
 * Date: 2021/2/7
 * Time: 21:39
 */
@Service
public class ScoreServiceImpl implements IScoreService {
    Logger logger = LoggerFactory.getLogger(ScoreServiceImpl.class);

    @Autowired(required = true)
    private ScoreMapper scoreMapper;

    @Override
    public List<Score> selectScores() {
        List<Score> scoreList = scoreMapper.selectScores();
        if (CollectionUtils.isEmpty(scoreList)) {
            logger.info("select All studentList 查回来，没有记录");
        }
        logger.info("select All scores的结果：" + scoreList.size());
        return scoreList;

    }

    @Override
    public Boolean updateScoreByID(Score score) {
        Boolean updateResult = true;
        int impactNum = scoreMapper.updateScoreByID(score);
        if (impactNum <= 0) {
            updateResult = false;
            return updateResult;
        }
        logger.info("updateScoreByID  --> ：" + score.getId() + " impact num:" + impactNum);
        return updateResult;
    }

    @Override
    public Boolean addScore(Score score) {
        Boolean addresult = true;
        int impactNum = scoreMapper.addScore(score);
        if (impactNum <= 0) {
            addresult = false;
            return addresult;
        }
        logger.info("add Score --> " + score.getId() + " impact num：" + impactNum);
        return addresult;
    }

    @Override
    public Boolean deleteScoreByID(String id) {
        Boolean delResult = true;
        int impactNum = scoreMapper.deleteScoreByID(id);
        if (impactNum <= 0) {
            delResult = false;
            return delResult;
        }
        logger.info("deleteScoreByID   --> " + id + " 的结果：" + impactNum);
        return delResult;
    }
}
