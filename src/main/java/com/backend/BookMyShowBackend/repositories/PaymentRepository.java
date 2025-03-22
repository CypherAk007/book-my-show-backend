package com.backend.BookMyShowBackend.repositories;

import com.backend.BookMyShowBackend.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
