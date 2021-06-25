package com.iot.util;

import com.iot.model.SIMCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DeviceUtil extends JpaRepository<SIMCard,Integer> {
}
