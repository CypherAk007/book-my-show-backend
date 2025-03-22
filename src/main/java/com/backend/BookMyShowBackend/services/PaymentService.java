package com.backend.BookMyShowBackend.services;

import com.backend.BookMyShowBackend.adapter.BMSPaymentProviderAPI;
import com.backend.BookMyShowBackend.dtos.PaymentResponseDto;
import com.backend.BookMyShowBackend.factory.PaymentProviderAdapterFactory;
import com.backend.BookMyShowBackend.models.*;
import com.backend.BookMyShowBackend.repositories.BookingRepository;
import com.backend.BookMyShowBackend.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;

    public PaymentService(BookingRepository bookingRepository, PaymentRepository paymentRepository){
        this.bookingRepository = bookingRepository;
        this.paymentRepository = paymentRepository;
    }

    public Payment processPaymentAsync(Long bookingId, PaymentProvider paymentProvider){
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(()->new RuntimeException("Booking Not Found!!"));

        System.out.println("Processing payment with " + paymentProvider + " for Booking ID: " + bookingId);
        //factory to process payment;
        BMSPaymentProviderAPI paymentProviderAPI = PaymentProviderAdapterFactory.getAdapter(paymentProvider);
        PaymentResponseDto thirdPartyPaymentResponseDto = paymentProviderAPI.makePayment(booking);

        Payment payment = Payment.builder()
                .referenceNumber(thirdPartyPaymentResponseDto.getRefno())
                .amount(booking.getAmount())
                .status(thirdPartyPaymentResponseDto.getResponseStatus())
                .paymentProvider(paymentProvider)
                .build();
        Payment savedPayment = paymentRepository.save(payment);
        updateBooking(savedPayment,booking);
        return payment;
    }

    public void updateBooking(Payment payment,Booking booking){
        if(payment.getStatus().equals(PaymentStatus.SUCCESS)){
            booking.setStatus(BookingStatus.CONFIRMED);
            Booking savedBooking = bookingRepository.save(booking);
            System.out.println("Tickets Confirmed: "+savedBooking.getId()+" Movie: "+ savedBooking.getMovieShow().getMovie().getName()+" Status: "+savedBooking.getStatus());
        }else {
            booking.setStatus(BookingStatus.REJECTED);
            Booking savedBooking =bookingRepository.save(booking);
            System.out.println("Payment Failed : "+payment.getReferenceNumber()+" Movie: "+ savedBooking.getMovieShow().getMovie().getName()+" Status: "+savedBooking.getStatus());
        }
    }

}
