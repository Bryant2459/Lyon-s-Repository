package com.yang.test.service.impl;

import com.yang.test.mapper.UploadImageMapper;
import com.yang.test.po.Image;
import com.yang.test.service.IUploadImgService;
import com.yang.test.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Author: Lyon
 * @Date: 2020/9/16 16:22
 * @Description:
 */
@Service
public class UploadImgServiceImpl implements IUploadImgService {
    Logger logger = LoggerFactory.getLogger(UploadImgServiceImpl.class);
    //图片存放根路径
    @Value("${file.rootPath}")
    private String ROOT_PATH;
    //图片存放根目录下的子目录
    @Value("${file.sonPath}")
    private String SON_PATH;

    @Value("${server.port}")
    //获取主机端口
    private String POST;

    @Autowired
    private UploadImageMapper uploadImageMapper;


    public Boolean uploadImage(@RequestParam("file") MultipartFile file) {
       Boolean result=true;
        if (!file.isEmpty()) {
            // 获取文件名称,包含后缀
            String fileName = file.getOriginalFilename();

            // 存放在这个路径下：该路径是该工程目录下的static文件下：(注：该文件可能需要自己创建)
            // 放在static下的原因是，存放的是静态文件资源，即通过浏览器输入本地服务器地址，加文件名时是可以访问到的+"static/img/"
            String path = null;
             path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/img/";
            //path=ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/";
            path=path.replaceAll("%20", " ");
            try {
                // 该方法是对文件写入的封装，在util类中，导入该包即可使用，后面会给出方法
                FileUtils.fileupload(file.getBytes(), path, fileName);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            // 接着创建对应的实体类，将以下路径进行添加，然后通过数据库操作方法写入
            //获取本机IP
            String host = null;
            try {
                host = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                logger.error("get server host Exception e:", e);
            }
            Image image = new Image();

            image.setId(UUID.randomUUID().toString());
            image.setFirstAddTime(df.format(new Date()));
            image.setRecordId("");
            image.setImgName(fileName);
            image.setUrl(host + ":" + POST + path);
            int uploadResult = uploadImageMapper.uploadImage(image);
            if (uploadResult < 0) {
                logger.info("上传图片失败，Imgae name：" + image.getImgName());
                result=false;
                return result;
            }
            logger.info("上传图片成功，Imgae name：" + image.getImgName());
            return result;

        }
        return result;
    }
}
