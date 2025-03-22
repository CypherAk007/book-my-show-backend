package com.backend.BookMyShowBackend.dtos;

import com.backend.BookMyShowBackend.models.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponseDto {
    private PaymentStatus responseStatus;
    private String refno;
    private double amount;

}
