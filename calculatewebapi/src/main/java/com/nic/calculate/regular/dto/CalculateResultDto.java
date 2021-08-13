package com.nic.calculate.regular.dto;

import lombok.Data;

@Data
public class CalculateResultDto {

    public CalculateResultDto() {
    }

    public CalculateResultDto(String payPerson, String receivePerson, Double amount) {
        this.payPerson = payPerson;
        this.receivePerson = receivePerson;
        this.amount = amount;
    }

    private String payPerson;
    private String receivePerson;
    private Double amount;
}
