package com.nic.calculate.regular.request;

import com.nic.calculate.regular.dto.BillDetailDto;
import lombok.Data;

import java.util.List;

@Data
public class CalculateBillResultRequest {
    private List<BillDetailDto> list;
    private Integer resultCase;


}
