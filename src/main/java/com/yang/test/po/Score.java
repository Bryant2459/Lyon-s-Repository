package com.yang.test.po;

import lombok.Data;

/**
 * Created with IntelliJ IDEA
 * Created By Lyon
 * Date: 2021/2/8
 * Time: 16:45
 */
@Data
public class Score {
    private String id;
    private Double chinese;
    private Double mathematic;
    private Double english;
    private Double physic;
    private Double chemistry;
    private Double history;
    private Double political;
    private Double sport;
    private Double biological;
    private Double geography;
    private String examinationTime;
    private String firstAddTime;
    private String lastModifiedTime;
    private String operator;
}
