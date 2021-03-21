package com.yang.test.controller;


import com.alibaba.fastjson.JSONObject;
import com.yang.test.common.Response;
import com.yang.test.constants.Constants;
import com.yang.test.constants.MockConstans;
import com.yang.test.po.MockData;
import com.yang.test.service.IMockService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Lyon
 * @Date: 2020/8/3 21:45
 * @Description:
 */
@RestController
@RequestMapping(value = "/mock")
public class MockController {
    Logger logger = LoggerFactory.getLogger(MockController.class);

    @Autowired(required = true)
    private IMockService mockService;

    //查询所有
    @RequestMapping("/findAllMockDatas")
    public Response findAllMockDatas() {
        List<MockData> listRecord = mockService.findAllMockDatas();
        Response response = new Response();
        if (CollectionUtils.isEmpty(listRecord)) {
            response.setStatus(Constants.FAILED_CODE);
            response.setMessage(Constants.SELECT_REMARK_MESSAGE);
            response.setErroCode(Constants.SELECT_FAILED_CODE);
            response.setData(null);
            return response;
        }
        for (MockData mock : listRecord) {
            if (StringUtils.equals(mock.getId(), MockConstans.MOCK_OIL_ID)) {
                Object parse = JSONObject.parse(mock.getMessage());
                response.setData(parse);
            }
        }

        response.setStatus(Constants.SELECT_SUCCESS_CODE);
        response.setMessage(Constants.SELECT_SUCCESS_MESSAGE);
        response.setErroCode(Constants.SELECT_SUCCESS_CODE);

        logger.info("查出来mock的结果数量：" + listRecord.size());
        return response;
    }

}
