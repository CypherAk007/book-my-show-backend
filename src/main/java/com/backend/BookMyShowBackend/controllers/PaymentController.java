package com.backend.BookMyShowBackend.controllers;

import com.backend.BookMyShowBackend.dtos.PaymentRequestDto;
import com.backend.BookMyShowBackend.dtos.PaymentResponseDto;
import com.backend.BookMyShowBackend.models.Payment;
import com.backend.BookMyShowBackend.models.PaymentStatus;
import com.backend.BookMyShowBackend.services.PaymentService;
import org.springframework.stereotype.Controller;

@Controller
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    public PaymentResponseDto processPayment(PaymentRequestDto paymentRequestDto){
        PaymentResponseDto paymentResponseDto = new PaymentResponseDto();
        try{
            Payment payment  = paymentService.processPaymentAsync(paymentRequestDto.getBookingId(),paymentRequestDto.getPaymentProvider());
            paymentResponseDto.setResponseStatus(PaymentStatus.SUCCESS);
            paymentResponseDto.setRefno(payment.getReferenceNumber());
            paymentResponseDto.setAmount(payment.getAmount());
        }catch (Exception e){
            paymentResponseDto.setResponseStatus(PaymentStatus.FAILED);
        }
        return paymentResponseDto;
    }
}
