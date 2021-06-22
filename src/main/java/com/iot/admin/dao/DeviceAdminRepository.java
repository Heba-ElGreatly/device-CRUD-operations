package com.iot.admin.dao;

import com.iot.model.SIMCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DeviceAdminRepository extends JpaRepository<SIMCard, Integer> {

//    void removeDevice();

    @Query("select simCard from SIMCard simCard where simCard.simNumber=:simNumber and simCard.operation.operationCode =:operationCode")
    Optional<SIMCard> findBySIMNumberAndOperationCode(@Param("simNumber") String simNumber, @Param("operationCode") String operationCode);
}
