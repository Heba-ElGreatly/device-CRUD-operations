package com.iot.dto;

import lombok.Data;

@Data
public class SIMCardDto {

    private String simNumber;
    private String countryName;
    private String status;
    private OperationDto operation;
}
