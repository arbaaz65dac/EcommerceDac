package com.cdac.e_Commerce.service;

import com.cdac.e_Commerce.dto.PaymentDto;

public interface PaymentService {
    PaymentDto initiatePayment(PaymentDto paymentDto);
    PaymentDto confirmPayment(PaymentDto paymentDto);
} 