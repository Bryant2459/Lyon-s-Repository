package com.yang.test.controller;

import com.yang.test.common.Response;
import com.yang.test.constants.AcitonConstants;
import com.yang.test.constants.Constants;
import com.yang.test.po.ActionRecord;
import com.yang.test.service.IActionRecordService;
import com.yang.test.service.IUploadImgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Author: Lyon
 * @Date: 2020/9/16 16:53
 * @Description:
 */

@RestController
@RequestMapping(value = "/file")
public class UploadImgController {
    Logger logger = LoggerFactory.getLogger(UploadImgController.class);

    @Autowired
    private IUploadImgService uploadImgService;

    @Autowired
    private IActionRecordService actionRecordService;

    // 传入的参数file是我们指定的文件
    @RequestMapping("/upload")
    @ResponseBody
    public Response upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpSession session) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String realname = (String) session.getAttribute("realName");
        Response response = new Response();
        Boolean uploadResult = uploadImgService.uploadImage(file);
        if (!uploadResult) {
            response.setData(null);
            response.setStatus(Constants.FAILED_CODE);
            response.setErroCode(Constants.UPLOAD_FAILED_CODE);
            response.setMessage(Constants.UPLOAD_FAILED_MESSAGE);
            return response;
        }
        ActionRecord actionRecord = new ActionRecord();
        actionRecord.setId(UUID.randomUUID().toString());
        actionRecord.setAction(AcitonConstants.ACTION_UPLOAD_IMAGE);
        actionRecord.setActionTime(df.format(new Date()));
        actionRecord.setOperator(realname);
        actionRecord.setService(AcitonConstants.SERVICE_UPLOAD_RECORD);
        actionRecord.setRecordId(null);
        actionRecordService.addActionRecord(actionRecord);

        response.setData(null);
        response.setStatus(Constants.SUCCESS_CODE);
        response.setErroCode(Constants.UPDATE_SUCCESS_CODE);
        response.setMessage(Constants.UPLOAD_SUCCESS_MESSAGE);
        return response;
    }


}
