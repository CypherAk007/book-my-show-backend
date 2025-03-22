package com.backend.BookMyShowBackend.factory;

import com.backend.BookMyShowBackend.adapter.*;
import com.backend.BookMyShowBackend.models.PaymentProvider;

public class PaymentProviderAdapterFactory {
    public static BMSPaymentProviderAPI getAdapter(PaymentProvider paymentProvider){
        return switch (paymentProvider){
            case PAYU -> null;
            case RAZOR_PAY -> new RazorPayAdapter(new RazorPayProcessingAPI());
            case PAYPAL -> new PayPalAdapter(new PayPalProcessingAPI());
            default -> new PayPalAdapter(new PayPalProcessingAPI());
        };
    }
}
