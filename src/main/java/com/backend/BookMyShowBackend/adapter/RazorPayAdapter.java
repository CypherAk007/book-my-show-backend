package com.backend.BookMyShowBackend.adapter;

import com.backend.BookMyShowBackend.dtos.PaymentResponseDto;
import com.backend.BookMyShowBackend.models.Booking;

public class RazorPayAdapter implements BMSPaymentProviderAPI{
    private final RazorPayProcessingAPI razorPayProcessingAPI;

    public RazorPayAdapter(RazorPayProcessingAPI razorPayProcessingAPI){
        this.razorPayProcessingAPI = razorPayProcessingAPI;
    }

    @Override
    public PaymentResponseDto makePayment(Booking booking) {
        return razorPayProcessingAPI.makePayment(booking);
    }
}
