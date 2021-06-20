package com.iot.dao;

import com.iot.model.SIMCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface IotDeviceRepository extends JpaRepository<SIMCard,Integer> {

    @Query(value = "select simCard from SIMCard simCard, Operation o where simCard.operation.id = o.id and status ='waiting for activation'")
    Optional<List<SIMCard>> getAllWaitingForActivationDevices();
}
