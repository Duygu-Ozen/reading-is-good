package com.readingIsGood.readingIsGood.models.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderStatisticDTO implements Serializable {

    private static final long serialVersionUID = 3734810910189440025L;
    private long totalOrderCount;
    private BigDecimal totalPurchasedAmount;
    private long totalBookCount;
    private String month;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public long getTotalOrderCount() {
        return totalOrderCount;
    }

    public void setTotalOrderCount(long totalOrderCount) {
        this.totalOrderCount = totalOrderCount;
    }

    public BigDecimal getTotalPurchasedAmount() {
        return totalPurchasedAmount;
    }

    public void setTotalPurchasedAmount(BigDecimal totalPurchasedAmount) {
        this.totalPurchasedAmount = totalPurchasedAmount;
    }

    public long getTotalBookCount() {
        return totalBookCount;
    }

    public void setTotalBookCount(long totalBookCount) {
        this.totalBookCount = totalBookCount;
    }


}
