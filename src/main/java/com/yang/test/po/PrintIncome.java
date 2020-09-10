package com.yang.test.po;

import lombok.Data;

@Data
public class PrintIncome implements Comparable<PrintIncome> {

    private String id;

    private String date;

    private Double money;

    private String remark;

    @Override
    public int compareTo(PrintIncome o) {
        return this.getDate().compareTo(o.getDate());
    }
}
