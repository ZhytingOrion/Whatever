package com.nic.calculate.regular.dto;


import lombok.Data;

@Data
public class BillDetailDto implements Comparable<BillDetailDto> {
    private String userId;
    private Double payAmount;

    public BillDetailDto() {
    }

    public BillDetailDto(String userId, Double payAmount) {
        this.userId = userId;
        this.payAmount = payAmount;
    }

    @Override
    public int compareTo(BillDetailDto that) {
        return that.getPayAmount().compareTo(this.getPayAmount());
    }
}
