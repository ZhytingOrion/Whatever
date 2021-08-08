package com.nic.calculate.regular.request;

import com.nic.calculate.regular.dto.AliasDto;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SignBillInfoRequest {

    private String billName;
    private Double originFund;
    private Date beginDay;
    private int billCount;
    private List<AliasDto> aliasNames;
}
