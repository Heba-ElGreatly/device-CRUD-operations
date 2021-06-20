package com.iot.admin.dao;

import com.iot.model.SIMCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DeviceAdminRepository extends JpaRepository<SIMCard, Integer> {

//    void removeDevice();

    Optional<SIMCard> findBySIMNumberAndOperationCode();
}
