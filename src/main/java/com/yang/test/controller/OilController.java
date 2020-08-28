package com.yang.test.controller;

import com.yang.test.common.Response;
import com.yang.test.constants.Constants;
import com.yang.test.po.OilRecord;
import com.yang.test.service.impl.OilRecordSerivceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping(value = "/oil")
public class OilController {
     Logger logger= LoggerFactory.getLogger(OilController.class);

    @Autowired(required=true)
    private OilRecordSerivceImpl oilRecordSerivceImpl;

    //查询所有
    @RequestMapping("/findAll")
    public  Response findAll(){
    List<OilRecord> listRecord= oilRecordSerivceImpl.selectAllOilRecords();
        Response response=new Response();
    if(CollectionUtils.isEmpty(listRecord)){
        response.setStatus(Constants.FAILED_CODE);
        response.setMessage(Constants.SELECT_REMARK_MESSAGE);
        response.setErroCode(Constants.SELECT_FAILED_CODE);
        response.setData(null);
        return response;
    }
      // System.out.println("查出来的结果数量："+listRecord.size());
        response.setStatus(Constants.SELECT_SUCCESS_CODE);
        response.setMessage(Constants.SELECT_SUCCESS_MESSAGE);
        response.setErroCode(Constants.SELECT_SUCCESS_CODE);
        response.setData(listRecord);
        //String ResStr= JSON.toJSONString(response);
        logger.info("查出来 Oil record 的结果数量："+listRecord.size());
        return response;
    }

    /*//修改数据
    @RequestMapping(value = "/updateRecordById",method = RequestMethod.POST)
    public Response updatePerson(OilRecord oilRecord){
        OilRecord record=new OilRecord();
        Response response=new Response();
        if(StringUtils.isNotBlank(oilRecord.getId()) && StringUtils.isNotEmpty(oilRecord.getId()) ){
            record.setId(oilRecord.getId());
        }
        if(StringUtils.isNotBlank(printIncome.getDate()) && StringUtils.isNotEmpty(printIncome.getDate()) ){
            record.setDate(printIncome.getDate());
        }
        if(null != printIncome.getMoney()) {
            record.setMoney(printIncome.getMoney());
        }

        if(StringUtils.isNotBlank(printIncome.getRemark()) && StringUtils.isNotEmpty(printIncome.getRemark()) ){
            record.setRemark(printIncome.getRemark());
        }else{
            record.setRemark("");
        }
        Boolean updateResult = printIncomeService.updateRecordByID(record);
        if(!updateResult){
            response.setData(null);
            response.setMessage(Constants.UPDATE_FAILED_MESSAGE);
            response.setErroCode(Constants.UPDATE_FAILED_CODE);
            response.setStatus(Constants.FAILED_CODE);

            return  response;
        }
        response.setData(null);
        response.setMessage(Constants.UPDATE_SUCCESS_MESSAGE);
        response.setErroCode(Constants.UPDATE_SUCCESS_CODE);
        response.setStatus(Constants.SUCCESS_CODE);
        return response;
    }
*/
   /* //新增
    @RequestMapping(value = "/addRecord",method = RequestMethod.POST)
    public Response addRecord(PrintIncome printIncome){
        Response response=new Response();
        PrintIncome addPrintIncome=new PrintIncome();
        addPrintIncome.setId(UUID.randomUUID().toString());
        if(StringUtils.isNotBlank(printIncome.getDate()) && StringUtils.isNotEmpty(printIncome.getDate()) ){
            addPrintIncome.setDate(printIncome.getDate());
        }else{
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
            addPrintIncome.setDate(df.format(new Date()));
        }
        if( null != printIncome.getMoney()){
            addPrintIncome.setMoney(printIncome.getMoney());
        }else{
            addPrintIncome.setMoney(0.00);
        }
        if(StringUtils.isNotBlank(printIncome.getRemark()) && StringUtils.isNotEmpty(printIncome.getRemark()) ){
            addPrintIncome.setRemark(printIncome.getRemark());
        }
         Boolean addResult = printIncomeService.addRecord(addPrintIncome);

        if(!addResult){
            response.setData(null);
            response.setMessage(Constants.ADD_FAILED_MESSAGE);
            response.setErroCode(Constants.ADD_FAILED_CODE);
            response.setStatus(Constants.FAILED_CODE);
            return response;
        }
        response.setData(null);
        response.setMessage(Constants.ADD_SUCCESS_MESSAGE);
        response.setErroCode(Constants.ADD_SUCCESS_CODE);
        response.setStatus(Constants.SUCCESS_CODE);
        return response;
    }*/

    //删除
    @RequestMapping(value = "/deleteOilRecordByID",method = RequestMethod.POST)
    public Response deleteOilRecordByID(OilRecord oilRecord){
        Response response=new Response();
        Boolean deleteResult=false;
        Boolean confirm=false;
        String id=oilRecord.getId();
        if( StringUtils.isNotBlank(id)&&StringUtils.isNotEmpty(id)){
            //确保传来的id 数据库中存在
            List<OilRecord> allOilRecord= oilRecordSerivceImpl.selectAllOilRecords();
            for (OilRecord tempOilRecord:allOilRecord) {
                if(StringUtils.equals(tempOilRecord.getId(),tempOilRecord.getId())){
                    confirm=true;
                }
            }
            if(confirm){
                deleteResult = oilRecordSerivceImpl.deleteOilRecordByID(id);
            }else{
                response.setData(null);
                response.setMessage(Constants.DELETE_FAILED_MESSAGE_NO_COULD_DELETE_ID);
                response.setErroCode(Constants.DELETE_FAILED_CODE);
                response.setStatus(Constants.FAILED_CODE);
                System.out.println(Constants.DELETE_FAILED_MESSAGE_NO_COULD_DELETE_ID);
                return response;
            }
        }else{
            response.setData(null);
            response.setMessage(Constants.DELETE_FAILED_MESSAGE_NO_ID_FORM_RECEPTION);
            response.setErroCode(Constants.DELETE_FAILED_CODE);
            response.setStatus(Constants.FAILED_CODE);
            System.out.println(Constants.DELETE_FAILED_MESSAGE_NO_ID_FORM_RECEPTION);
            return response;
        }

        if(deleteResult==false){
            response.setData(null);
            response.setMessage(Constants.DELETE_FAILED_MESSAGE);
            response.setErroCode(Constants.DELETE_FAILED_CODE);
            response.setStatus(Constants.FAILED_CODE);
            return response;
        }
        response.setData(null);
        response.setMessage(Constants.DELETE_SUCCESS_MESSAGE);
        response.setErroCode(Constants.DELETE_SUCCESS_CODE);
        response.setStatus(Constants.SUCCESS_CODE);
        return response;
    }
}
