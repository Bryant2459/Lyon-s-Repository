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
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.nio.file.Paths;
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
    private static final String ROOT ="D:/Program Files/ProjectManage/src/main/resources/static/img/" ;
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
        String fileName = file.getOriginalFilename();
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

        response.setData(fileName);
        response.setStatus(Constants.SUCCESS_CODE);
        response.setErroCode(Constants.UPDATE_SUCCESS_CODE);
        response.setMessage(Constants.UPLOAD_SUCCESS_MESSAGE);
        return response;
    }
    private final ResourceLoader resourceLoader;
    @Autowired
    public UploadImgController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }


    //显示图片
    @RequestMapping(method = RequestMethod.GET, value = "/image/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> getFile(@PathVariable String filename) {

        try {
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(ROOT, filename).toString()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
