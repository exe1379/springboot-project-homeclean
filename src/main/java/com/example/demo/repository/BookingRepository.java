package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    // 查詢某使用者的所有預約
    List<Booking> findAllByUser_UserName(String username);

    // 查詢某使用者的一筆特定預約（用於授權驗證）
    Optional<Booking> findByIdAndUser_UserName(Integer bookingId, String username);
}
