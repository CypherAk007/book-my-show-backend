package com.backend.BookMyShowBackend.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Payment extends BaseModel{
    private PaymentProvider paymentProvider;
    private String referenceNumber;
    private PaymentStatus status;
    private double amount;
}
