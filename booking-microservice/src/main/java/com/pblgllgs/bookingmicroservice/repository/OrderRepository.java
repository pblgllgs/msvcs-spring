package com.pblgllgs.bookingmicroservice.repository;

import com.pblgllgs.bookingmicroservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
