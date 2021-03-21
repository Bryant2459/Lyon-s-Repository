package com.yang.test.po;

import lombok.Data;

/**
 * @Author: Lyon
 * @Date: 2020/8/28 15:25
 * @Description:
 */
@Data
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

    @Override
    public int compareTo(OilRecord o) {

        return this.getAddOilDate().compareTo(o.getAddOilDate());
    }
}
