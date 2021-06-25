package com.iot.mapper;

import com.iot.dto.SIMCardDto;
import com.iot.model.SIMCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SIMCardMapper {

    @Autowired
    private OperationMapper operationMapper;

    public SIMCard mapDtoToEntity(SIMCardDto simCardDto){
        SIMCard simCard = SIMCard.builder()
                .simNumber(simCardDto.getSimNumber())
                .countryName(simCardDto.getCountryName())
                .status(simCardDto.getStatus()).build();
        simCard.setOperation(operationMapper.mapDtoToEntity(simCardDto.getOperation()));
        return simCard;
    }

    public SIMCardDto mapEntityToDTO(SIMCard simCard){
        SIMCardDto simCardDto = SIMCardDto.builder()
                .simNumber(simCard.getSimNumber())
                .countryName(simCard.getCountryName())
                .status(simCard.getStatus())
                .operation(operationMapper.mapEntityToDTO(simCard.getOperation()))
                .build();
        return simCardDto;
    }
}
