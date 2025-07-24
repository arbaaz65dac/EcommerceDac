package com.cdac.e_Commerce.service.impl;

import com.cdac.e_Commerce.service.PaymentService;
import com.cdac.e_Commerce.dto.PaymentDto;
import com.cdac.e_Commerce.model.Payment;
import com.cdac.e_Commerce.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    private PaymentDto toDto(Payment payment) {
        PaymentDto dto = new PaymentDto();
        dto.setId(payment.getId());
        dto.setOrderId(payment.getOrderId());
        dto.setUserId(payment.getUserId());
        dto.setAmount(payment.getAmount());
        dto.setStatus(payment.getStatus());
        dto.setTransactionId(payment.getTransactionId());
        return dto;
    }

    private Payment toEntity(PaymentDto dto) {
        Payment payment = new Payment();
        payment.setId(dto.getId());
        payment.setOrderId(dto.getOrderId());
        payment.setUserId(dto.getUserId());
        payment.setAmount(dto.getAmount());
        payment.setStatus(dto.getStatus());
        payment.setTransactionId(dto.getTransactionId());
        return payment;
    }

    @Override
    public PaymentDto initiatePayment(PaymentDto paymentDto) {
        Payment payment = toEntity(paymentDto);
        payment.setStatus("INITIATED");
        Payment saved = paymentRepository.save(payment);
        return toDto(saved);
    }

    @Override
    public PaymentDto confirmPayment(PaymentDto paymentDto) {
        Optional<Payment> paymentOpt = paymentRepository.findByOrderId(paymentDto.getOrderId());
        if (paymentOpt.isPresent()) {
            Payment payment = paymentOpt.get();
            payment.setStatus(paymentDto.getStatus());
            payment.setTransactionId(paymentDto.getTransactionId());
            Payment saved = paymentRepository.save(payment);
            return toDto(saved);
        }
        return null;
    }
} 