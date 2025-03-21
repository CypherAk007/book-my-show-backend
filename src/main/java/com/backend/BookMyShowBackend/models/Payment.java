package com.backend.BookMyShowBackend.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Payment extends BaseModel{
    private PaymentProvider paymentProvider;
    private String referenceNumber;
    private PaymentStatus status;
    private double amount;
}
