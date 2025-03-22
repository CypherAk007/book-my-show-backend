package com.backend.BookMyShowBackend.adapter;

import com.backend.BookMyShowBackend.dtos.PaymentResponseDto;
import com.backend.BookMyShowBackend.models.Booking;

public interface BMSPaymentProviderAPI {
    PaymentResponseDto makePayment(Booking booking);
}
