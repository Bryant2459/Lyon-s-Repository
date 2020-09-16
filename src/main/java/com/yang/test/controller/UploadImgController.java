package com.yang.test.controller;

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
    public String upload(@RequestParam(value = "file", required=false) MultipartFile file) {
        return uploadImgService.uploadImage(file);
    }


}
