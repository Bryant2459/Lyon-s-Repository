package com.yang.test.po;

/**
 * @Author: Lyon
 * @Date: 2020/8/28 15:25
 * @Description:
 */
public class OilRecord implements Comparable<OilRecord> {
    private String id;
    private String addOilDate;
    private Double oilVolume;
    private String dashboardMileage;
    private Double cost;
    private Double realCost;
    private Double oilUnitPrice;
    private Double realOilUnitPrice;
    private String remark;
    private String recordFirstDate;
    private String recordLastUpdateDate;
    private String operator;

    public String getRecordLastUpdateDate() {
        return recordLastUpdateDate;
    }

    public void setRecordLastUpdateDate(String recordLastUpdateDate) {
        this.recordLastUpdateDate = recordLastUpdateDate;
    }

    public Double getOilVolume() {
        return oilVolume;
    }

    public void setOilVolume(Double oilVolume) {
        this.oilVolume = oilVolume;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddOilDate() {
        return addOilDate;
    }

    public void setAddOilDate(String addOilDate) {
        this.addOilDate = addOilDate;
    }

    public String getDashboardMileage() {
        return dashboardMileage;
    }

    public void setDashboardMileage(String dashboardMileage) {
        this.dashboardMileage = dashboardMileage;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRecordFirstDate() {
        return recordFirstDate;
    }

    public void setRecordFirstDate(String recordFirstDate) {
        this.recordFirstDate = recordFirstDate;
    }


    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getRealCost() {
        return realCost;
    }

    public void setRealCost(Double realCost) {
        this.realCost = realCost;
    }


    public Double getOilUnitPrice() {
        return oilUnitPrice;
    }

    public void setOilUnitPrice(Double oilUnitPrice) {
        this.oilUnitPrice = oilUnitPrice;
    }

    public Double getRealOilUnitPrice() {
        return realOilUnitPrice;
    }

    public void setRealOilUnitPrice(Double realOilUnitPrice) {
        this.realOilUnitPrice = realOilUnitPrice;
    }

    @Override
    public int compareTo(OilRecord o) {

        return this.getAddOilDate().compareTo(o.getAddOilDate());
    }
}
