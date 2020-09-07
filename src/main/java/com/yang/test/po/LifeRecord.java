package com.yang.test.po;

/**
 * @Author: Lyon
 * @Date: 2020/9/7 11:52
 * @Description:
 */
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

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduceRecordDate() {
        return produceRecordDate;
    }

    public void setProduceRecordDate(String produceRecordDate) {
        this.produceRecordDate = produceRecordDate;
    }



    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRecordProducer() {
        return recordProducer;
    }

    public void setRecordProducer(String recordProducer) {
        this.recordProducer = recordProducer;
    }

    public String getFirstAddDate() {
        return firstAddDate;
    }

    public void setFirstAddDate(String firstAddDate) {
        this.firstAddDate = firstAddDate;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public int compareTo(LifeRecord o) {
        return this.getProduceRecordDate().compareTo(o.getProduceRecordDate());
    }
}
