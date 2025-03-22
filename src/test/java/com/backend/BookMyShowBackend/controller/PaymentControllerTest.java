package com.backend.BookMyShowBackend.controller;

import com.backend.BookMyShowBackend.controllers.PaymentController;
import com.backend.BookMyShowBackend.dtos.PaymentRequestDto;
import com.backend.BookMyShowBackend.dtos.PaymentResponseDto;
import com.backend.BookMyShowBackend.models.Payment;
import com.backend.BookMyShowBackend.models.PaymentProvider;
import com.backend.BookMyShowBackend.models.PaymentStatus;
import com.backend.BookMyShowBackend.services.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PaymentControllerTest {
    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private PaymentController paymentController;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcessPayment_Success(){
        Long bookingId = 22L;
        PaymentProvider paymentProvider = PaymentProvider.PAYPAL;
        String referenceNo = "#PayPal1234567";

        PaymentRequestDto paymentRequestDto = new PaymentRequestDto();
        paymentRequestDto.setPaymentProvider(paymentProvider);
        paymentRequestDto.setBookingId(bookingId);

        Payment mockPayment = new Payment();
        mockPayment.setId(33L);
        mockPayment.setPaymentProvider(PaymentProvider.PAYPAL);
        mockPayment.setAmount(1000.00);
        mockPayment.setReferenceNumber(referenceNo);
        mockPayment.setStatus(PaymentStatus.SUCCESS);

        when(paymentService.processPaymentAsync(bookingId,paymentProvider)).thenReturn(mockPayment);

        PaymentResponseDto paymentResponseDto = paymentController.processPayment(paymentRequestDto);

        assertEquals(PaymentStatus.SUCCESS,paymentResponseDto.getResponseStatus());
        assertEquals(referenceNo,paymentResponseDto.getRefno());
        assertEquals(1000.00,paymentResponseDto.getAmount());

        System.out.println("Payment Completed Successfully. RefNo. : "+paymentResponseDto.getRefno()+" Status: "+paymentResponseDto.getResponseStatus());


    }

    @Test
    void testProcessPayment_Failure() {
        Long bookingId = 22L;
        PaymentProvider paymentProvider = PaymentProvider.PAYPAL;
        String referenceNo = "#PayPal1234567";

        PaymentRequestDto paymentRequestDto = new PaymentRequestDto();
        paymentRequestDto.setPaymentProvider(paymentProvider);
        paymentRequestDto.setBookingId(bookingId);

        Payment mockPayment = new Payment();
        mockPayment.setId(33L);
        mockPayment.setPaymentProvider(PaymentProvider.PAYPAL);
        mockPayment.setAmount(1000.00);
        mockPayment.setReferenceNumber(referenceNo);
        mockPayment.setStatus(PaymentStatus.FAILED);

        when(paymentService.processPaymentAsync(any(), any()))
                .thenThrow(new RuntimeException("Booking Not Found!!"));

        PaymentResponseDto paymentResponseDto = paymentController.processPayment(paymentRequestDto);

        assertEquals(PaymentStatus.FAILED, paymentResponseDto.getResponseStatus());

        System.out.println("Payment Failed : RefNo. : " + paymentResponseDto.getRefno() + " Status: " + paymentResponseDto.getResponseStatus());

    }
}
