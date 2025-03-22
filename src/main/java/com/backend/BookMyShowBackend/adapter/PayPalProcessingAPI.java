package com.backend.BookMyShowBackend.adapter;

import com.backend.BookMyShowBackend.dtos.PaymentResponseDto;
import com.backend.BookMyShowBackend.models.Booking;
import com.backend.BookMyShowBackend.models.PaymentStatus;

import java.util.UUID;

public class PayPalProcessingAPI {
    public PaymentResponseDto processPayment(Booking booking){
        PaymentResponseDto paymentResponseDto = new PaymentResponseDto();
        boolean paymentSuccess = Math.random() > 0.2; // 80% chance of success
        String referenceNumber = UUID.randomUUID().toString();
        if(paymentSuccess){
            paymentResponseDto.setResponseStatus(PaymentStatus.SUCCESS);
            paymentResponseDto.setRefno(referenceNumber);
        }else{
            paymentResponseDto.setResponseStatus(PaymentStatus.FAILED);
            paymentResponseDto.setRefno(referenceNumber);
        }
        return paymentResponseDto;
    }
}
