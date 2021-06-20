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
        SIMCard simCard = new SIMCard();
        simCard.setSimNumber(simCardDto.getSimNumber());
        simCard.setCountryName(simCardDto.getCountryName());
        simCard.setStatus(simCardDto.getStatus());
        simCard.setOperation(operationMapper.mapDtoToEntity(simCardDto.getOperation()));
        return simCard;
    }

    public SIMCardDto mapEntityToDTO(SIMCard simCard){
        SIMCardDto simCardDto = new SIMCardDto();
        simCardDto.setSimNumber(simCard.getSimNumber());
        simCardDto.setCountryName(simCard.getCountryName());
        simCardDto.setStatus(simCard.getStatus());
        simCardDto.setOperation(operationMapper.mapEntityToDTO(simCard.getOperation()));
        return simCardDto;
    }
}
