package com.backend.BookMyShowBackend.dtos;

import com.backend.BookMyShowBackend.models.PaymentProvider;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestDto {
    private Long bookingId;
    private PaymentProvider paymentProvider;
}
