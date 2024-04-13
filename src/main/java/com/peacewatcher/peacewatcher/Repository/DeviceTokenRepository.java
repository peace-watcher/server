package com.peacewatcher.peacewatcher.Repository;

import com.peacewatcher.peacewatcher.Entity.DeviceToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeviceTokenRepository extends JpaRepository<DeviceToken, Long> {
    DeviceToken findById(int id);
}
