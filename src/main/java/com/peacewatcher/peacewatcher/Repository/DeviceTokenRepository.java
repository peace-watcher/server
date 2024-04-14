package com.peacewatcher.peacewatcher.Repository;

import com.peacewatcher.peacewatcher.Entity.DeviceToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DeviceTokenRepository extends JpaRepository<DeviceToken, Long> {
    DeviceToken findById(int id);

    @Query("select deviceToken from DeviceToken ORDER BY id DESC LIMIT 1")
    String findByLastToken();
}
