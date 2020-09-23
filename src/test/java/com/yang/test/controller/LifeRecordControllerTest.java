package com.yang.test.controller;

import com.alibaba.fastjson.JSON;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.yang.test.po.LifeRecord;
import com.yang.test.service.ILifeRecordService;
import com.yang.test.utils.HttpClient;
import com.yang.test.utils.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

/**
 * @Author: Lyon
 * @Date: 2020/9/8 11:29
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LifeRecordControllerTest {
    Logger logger = LoggerFactory.getLogger(LifeRecordControllerTest.class);
    @Autowired
    private ILifeRecordService lifeRecordService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisUtils redisUtils;

    private static final String QR_CODE_IMAGE_PATH = "D:/temp/Java/myTest.png";


    @Test
    public void selectAllLifeRecord() throws IOException, WriterException {
        List<LifeRecord> lifeRecords = lifeRecordService.selectAllLifeRecord();
        Double sumSlary = 0.0;
        Double sumClass = 0.0;
        for (LifeRecord temp : lifeRecords) {
            if (temp.getCategoryId() == 2) {
                sumSlary = sumSlary + temp.getMoney();
            }
            if (temp.getCategoryId() == 5) {
                sumClass = sumClass + temp.getMoney();
            }
        }
        // String str=lifeRecords.toString();
        System.out.println("工资：" + sumSlary);
        System.out.println("课时：" + sumClass);
        System.out.println("工资+课时：" + (sumClass + sumSlary));
        String s = JSON.toJSONString(lifeRecords);

        generateQRCodeImage("https://baidu.com", 350, 350, QR_CODE_IMAGE_PATH);
    }

    @Test
    public void selectAllrecord() {
        String redisID = "REDISID";
        List<LifeRecord> lifeRecords = null;
        if (lifeRecords==null) {
            lifeRecords=lifeRecordService.selectAllLifeRecord();
            logger.info("get record from DB :"+ JSON.toJSONString(lifeRecords));
            redisUtils.set(redisID,lifeRecords);
        }


    }

    @Test
    public void selectLifeRecordByCategoryID() throws IOException {
//        for(int a =0;a<=100;a++){
//            List<LifeRecord> lifeRecords = lifeRecordService.selectLifeRecordByCategoryID(3);
//        }



        List<LifeRecord> lifeRecords = lifeRecordService.selectLifeRecordByCategoryID(3);
        String s = JSON.toJSONString(lifeRecords);

        String encording = "utf-8";
        String URL = "http://localhost:8084/actionRecord/selectAllActionRecords";

        logger.info(JSON.toJSONString(lifeRecords));
        String response = HttpClient.sendGetData(URL, encording);
        logger.info("HttpClient 调用返回:" + response);

        ResponseEntity<String> results = restTemplate.exchange(URL, HttpMethod.GET, null, String.class);
        String json = results.getBody();

        logger.info("restTemplate 调用返回:" + json);

        String addURL = "http://localhost:8084/actionRecord/addActionRecord";
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();

        map.add("id", "0e00ecc3-068a-4021-b09e-235de93a481c");
        map.add("action", "Add");
        map.add("actionTime", "2020-09-14 21:13:01");
        map.add("operator", "Lyon");
        map.add("service", "Test");
        map.add("recordId", "d50d0137-b561-431f-be15-3c2688381735");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(addURL, request, String.class);
        logger.info("restTemplate Post 返回:" + stringResponseEntity.getBody());


    }


    @Test
    public void updateLifeRecordByID() {

    }

    @Test
    public void addLifeRecord() {
    }

    @Test
    public void deleteLifeRecordByID() {
    }

    public static void generateQRCodeImage(String text, int width, int height, String filePath) throws WriterException, IOException, WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);

        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

    }


}