package com.iot.mapper;

import com.iot.dto.OperationDto;
import com.iot.model.Operation;
import org.springframework.stereotype.Service;


@Service
public class OperationMapper {

    public Operation mapDtoToEntity(OperationDto operationDto){
        Operation operation = new Operation();
        operation.setOperationName(operationDto.getOperationName());
        operation.setOperationCode(operationDto.getOperationCode());
        return operation;
    }

    public OperationDto mapEntityToDTO(Operation operation){
        OperationDto dto = new OperationDto();
        dto.setOperationName(operation.getOperationName());
        dto.setOperationCode(operation.getOperationCode());
        return dto;
    }
}
