package com.iot.mapper;

import com.iot.dto.OperationDto;
import com.iot.model.Operation;
import org.springframework.stereotype.Service;


@Service
public class OperationMapper {

    public Operation mapDtoToEntity(OperationDto operationDto){
        Operation operation = Operation.builder()
                .operationName(operationDto.getOperationName())
                .operationCode(operationDto.getOperationCode())
                .build();
        return operation;
    }

    public OperationDto mapEntityToDTO(Operation operation){
        OperationDto dto = OperationDto.builder()
                .operationName(operation.getOperationName())
                .operationCode(operation.getOperationCode())
                .build();
        return dto;
    }
}
