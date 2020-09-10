package com.yang.test.po;

import lombok.Data;

/**
 * @Author: Lyon
 * @Date: 2020/9/7 11:52
 * @Description:
 */
@Data
public class LifeRecord implements Comparable<LifeRecord> {
    private String id;
    private String produceRecordDate;
    private Integer categoryId;
    private String category;
    private Double money;
    private String recordProducer;
    private String firstAddDate;
    private String lastUpdateDate;
    private String remark;
    private String operator;

    @Override
    public int compareTo(LifeRecord o) {
        return this.getProduceRecordDate().compareTo(o.getProduceRecordDate());
    }
}
