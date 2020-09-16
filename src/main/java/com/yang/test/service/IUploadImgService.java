package com.yang.test.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Lyon
 * @Date: 2020/9/16 16:21
 * @Description:
 */
public interface IUploadImgService {

    String  uploadImage(MultipartFile file);
}
