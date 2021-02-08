package com.yang.test.controller;

import com.yang.test.common.Response;
import com.yang.test.constants.AcitonConstants;
import com.yang.test.constants.Constants;
import com.yang.test.po.ActionRecord;
import com.yang.test.po.Score;
import com.yang.test.service.IActionRecordService;
import com.yang.test.service.IScoreService;
import com.yang.test.service.IScoreService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA
 * Created By Lyon
 * Date: 2021/2/7
 * Time: 21:45
 */
@RestController
@RequestMapping(value = "/score")
public class ScoreController {
    Logger logger = LoggerFactory.getLogger(ScoreController.class);

    @Autowired(required = true)
    private IScoreService scoreService;

    @Autowired(required = true)
    private IActionRecordService actionRecordService;

    //查询所有
    @RequestMapping("/selectScores")
    public Response selectScores(HttpSession session) {
        String realname = (String) session.getAttribute("realName");
        //设置日期格式   df.format(new Date())
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        List<Score> listRecord = scoreService.selectScores();
        Response response = new Response();
        if (CollectionUtils.isEmpty(listRecord)) {
            response.setStatus(Constants.FAILED_CODE);
            response.setMessage(Constants.SELECT_REMARK_MESSAGE);
            response.setErroCode(Constants.SELECT_FAILED_CODE);
            response.setData(null);
            return response;
        }

        ActionRecord actionRecord = new ActionRecord();
        actionRecord.setId(UUID.randomUUID().toString());
        actionRecord.setAction(AcitonConstants.ACTION_SELECT);
        actionRecord.setActionTime(df.format(new Date()));
        actionRecord.setOperator(realname);
        actionRecord.setService(AcitonConstants.SERVICE_SCORE_RECORD);
        actionRecord.setRecordId(null);
        actionRecordService.addActionRecord(actionRecord);

        // System.out.println("查出来的结果数量："+listRecord.size());
        response.setStatus(Constants.SELECT_SUCCESS_CODE);
        response.setMessage(Constants.SELECT_SUCCESS_MESSAGE);
        response.setErroCode(Constants.SELECT_SUCCESS_CODE);
        response.setData(listRecord);
        //String ResStr= JSON.toJSONString(response);
        logger.info("查出来 Score 的结果数量：" + listRecord.size());
        return response;
    }

    //修改数据
    @RequestMapping(value = "/updateScoreByID", method = RequestMethod.POST)
    public Response updateScoreByID(Score Score, HttpSession session) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String realname = (String) session.getAttribute("realName");
        Score record = new Score();
        Response response = new Response();
        if (StringUtils.isNotBlank(Score.getId())) {
            record.setId(Score.getId());
        }
        if (Score.getChinese() != null) {
            record.setChinese(Score.getChinese());
        } else {
            record.setChinese(null);
        }
        if (Score.getMathematic() != null) {
            record.setMathematic(Score.getMathematic());
        } else {
            record.setMathematic(null);
        }
        if (Score.getEnglish() != null) {
            record.setEnglish(Score.getEnglish());
        } else {
            record.setEnglish(null);
        }
        if (Score.getPhysic() != null) {
            record.setPhysic(Score.getPhysic());
        } else {
            record.setPhysic(null);
        }
        if (Score.getChemistry() != null) {
            record.setChemistry(Score.getChemistry());
        } else {
            record.setChemistry(null);
        }
        if (Score.getHistory() != null) {
            record.setHistory(Score.getHistory());
        } else {
            record.setHistory(null);
        }
        if (Score.getPolitical() != null) {
            record.setPolitical(Score.getPolitical());
        } else {
            record.setPolitical(null);
        }
        if (Score.getSport() != null) {
            record.setSport(Score.getSport());
        } else {
            record.setSport(null);
        }

        if (Score.getBiological() != null) {
            record.setBiological(Score.getBiological());
        } else {
            record.setBiological(null);
        }
        if (Score.getGeography() != null) {
            record.setGeography(Score.getGeography());
        } else {
            record.setGeography(null);
        }
        if (StringUtils.isNoneBlank(Score.getExaminationTime())) {
            record.setExaminationTime(Score.getExaminationTime());
        } else {
            record.setExaminationTime("");
        }
        if (StringUtils.isNoneBlank(Score.getFirstAddTime())) {
            record.setFirstAddTime(Score.getFirstAddTime());
        } else {
            record.setFirstAddTime("");
        }

        record.setLastModifiedTime(df.format(new Date()));
        if (StringUtils.isNoneBlank(Score.getOperator())) {
            record.setOperator(Score.getOperator());
        } else {
            record.setOperator("");
        }

        Boolean updateResult = scoreService.updateScoreByID(record);
        if (!updateResult) {
            response.setData(null);
            response.setMessage(Constants.UPDATE_FAILED_MESSAGE);
            response.setErroCode(Constants.UPDATE_FAILED_CODE);
            response.setStatus(Constants.FAILED_CODE);

            return response;
        }
        ActionRecord actionRecord = new ActionRecord();
        actionRecord.setId(UUID.randomUUID().toString());
        actionRecord.setAction(AcitonConstants.ACTION_UPDATE);
        actionRecord.setActionTime(df.format(new Date()));
        actionRecord.setOperator(realname);
        actionRecord.setService(AcitonConstants.SERVICE_SCORE_RECORD);
        actionRecord.setRecordId(record.getId());
        actionRecordService.addActionRecord(actionRecord);
        response.setData(null);
        response.setMessage(Constants.UPDATE_SUCCESS_MESSAGE);
        response.setErroCode(Constants.UPDATE_SUCCESS_CODE);
        response.setStatus(Constants.SUCCESS_CODE);
        return response;
    }

    //新增
    @RequestMapping(value = "/addScore", method = RequestMethod.POST)
    public Response addScore(Score score, HttpSession session) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String realname = (String) session.getAttribute("realName");
        Response response = new Response();
        Score record = new Score();

        record.setId(UUID.randomUUID().toString());
        if (score.getChinese() != null) {
            record.setChinese(score.getChinese());
        } else {
            record.setChinese(null);
        }
        if (score.getMathematic() != null) {
            record.setMathematic(score.getMathematic());
        } else {
            record.setMathematic(null);
        }
        if (score.getEnglish() != null) {
            record.setEnglish(score.getEnglish());
        } else {
            record.setEnglish(null);
        }
        if (score.getPhysic() != null) {
            record.setPhysic(score.getPhysic());
        } else {
            record.setPhysic(null);
        }
        if (score.getChemistry() != null) {
            record.setChemistry(score.getChemistry());
        } else {
            record.setChemistry(null);
        }
        if (score.getHistory() != null) {
            record.setHistory(score.getHistory());
        } else {
            record.setHistory(null);
        }
        if (score.getPolitical() != null) {
            record.setPolitical(score.getPolitical());
        } else {
            record.setPolitical(null);
        }
        if (score.getSport() != null) {
            record.setSport(score.getSport());
        } else {
            record.setSport(null);
        }

        if (score.getBiological() != null) {
            record.setBiological(score.getBiological());
        } else {
            record.setBiological(null);
        }
        if (score.getGeography() != null) {
            record.setGeography(score.getGeography());
        } else {
            record.setGeography(null);
        }
        if (StringUtils.isNoneBlank(score.getExaminationTime())) {
            record.setExaminationTime(score.getExaminationTime());
        } else {
            record.setExaminationTime("");
        }
        if (StringUtils.isNoneBlank(score.getFirstAddTime())) {
            record.setFirstAddTime(score.getFirstAddTime());
        } else {
            record.setFirstAddTime("");
        }

        record.setLastModifiedTime(df.format(new Date()));
        if (StringUtils.isNoneBlank(score.getOperator())) {
            record.setOperator(score.getOperator());
        } else {
            record.setOperator("");
        }

        Boolean addResult = scoreService.addScore(record);

        if (!addResult) {
            response.setData(null);
            response.setMessage(Constants.ADD_FAILED_MESSAGE);
            response.setErroCode(Constants.ADD_FAILED_CODE);
            response.setStatus(Constants.FAILED_CODE);
            return response;
        }
        ActionRecord actionRecord = new ActionRecord();
        actionRecord.setId(UUID.randomUUID().toString());
        actionRecord.setAction(AcitonConstants.ACTION_ADD);
        actionRecord.setActionTime(df.format(new Date()));
        actionRecord.setOperator(realname);
        actionRecord.setService(AcitonConstants.SERVICE_SCORE_RECORD);
        actionRecord.setRecordId(score.getId());
        actionRecordService.addActionRecord(actionRecord);
        response.setData(null);
        response.setMessage(Constants.ADD_SUCCESS_MESSAGE);
        response.setErroCode(Constants.ADD_SUCCESS_CODE);
        response.setStatus(Constants.SUCCESS_CODE);
        return response;
    }

    //删除
    @RequestMapping(value = "/deleteScoreByID", method = RequestMethod.POST)
    public Response deleteScoreByID(Score score, HttpSession session) {
        String realname = (String) session.getAttribute("realName");
        //设置日期格式   df.format(new Date())
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Response response = new Response();
        Boolean deleteResult = false;
        Boolean confirm = false;
        String id = score.getId();
        if (StringUtils.isNotBlank(id) && StringUtils.isNotEmpty(id)) {
            //确保传来的id 数据库中存在
            List<Score> allRecord = scoreService.selectScores();
            for (Score tempScore : allRecord) {
                if (StringUtils.equals(tempScore.getId(), id)) {
                    confirm = true;
                }
            }
            if (confirm) {
                deleteResult = scoreService.deleteScoreByID(id);
            } else {
                response.setData(null);
                response.setMessage(Constants.DELETE_FAILED_MESSAGE_NO_COULD_DELETE_ID);
                response.setErroCode(Constants.DELETE_FAILED_CODE);
                response.setStatus(Constants.FAILED_CODE);
                System.out.println(Constants.DELETE_FAILED_MESSAGE_NO_COULD_DELETE_ID);
                return response;
            }
        } else {
            response.setData(null);
            response.setMessage(Constants.DELETE_FAILED_MESSAGE_NO_ID_FORM_RECEPTION);
            response.setErroCode(Constants.DELETE_FAILED_CODE);
            response.setStatus(Constants.FAILED_CODE);
            System.out.println(Constants.DELETE_FAILED_MESSAGE_NO_ID_FORM_RECEPTION);
            return response;
        }

        if (deleteResult == false) {
            response.setData(null);
            response.setMessage(Constants.DELETE_FAILED_MESSAGE);
            response.setErroCode(Constants.DELETE_FAILED_CODE);
            response.setStatus(Constants.FAILED_CODE);
            return response;
        }

        ActionRecord actionRecord = new ActionRecord();
        actionRecord.setId(UUID.randomUUID().toString());
        actionRecord.setAction(AcitonConstants.ACTION_DELETE);
        actionRecord.setActionTime(df.format(new Date()));
        actionRecord.setOperator(realname);
        actionRecord.setService(AcitonConstants.SERVICE_SCORE_RECORD);
        actionRecord.setRecordId(id);
        actionRecordService.addActionRecord(actionRecord);

        response.setData(null);
        response.setMessage(Constants.DELETE_SUCCESS_MESSAGE);
        response.setErroCode(Constants.DELETE_SUCCESS_CODE);
        response.setStatus(Constants.SUCCESS_CODE);

        return response;
    }

}
