package com.backend.BookMyShowBackend.adapter;

import com.backend.BookMyShowBackend.dtos.PaymentResponseDto;
import com.backend.BookMyShowBackend.models.Booking;

public class PayPalAdapter implements BMSPaymentProviderAPI{
    private final PayPalProcessingAPI paypalProcessingAPI;

    public PayPalAdapter(PayPalProcessingAPI payPalProcessingAPI){
        this.paypalProcessingAPI = payPalProcessingAPI;
    }

    @Override
    public PaymentResponseDto makePayment(Booking booking) {
        return paypalProcessingAPI.processPayment(booking);
    }
}
