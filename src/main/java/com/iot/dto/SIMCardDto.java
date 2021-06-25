package com.iot.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SIMCardDto {

    private String simNumber;
    private String countryName;
    private String status;
    private OperationDto operation;

}
