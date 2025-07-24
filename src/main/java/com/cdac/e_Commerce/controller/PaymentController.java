package com.cdac.e_Commerce.controller;

import com.cdac.e_Commerce.dto.PaymentDto;
import com.cdac.e_Commerce.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/initiate")
    public ResponseEntity<PaymentDto> initiatePayment(@RequestBody PaymentDto paymentInitiateDto) {
        PaymentDto payment = paymentService.initiatePayment(paymentInitiateDto);
        return ResponseEntity.ok(payment);
    }

    @PostMapping("/confirm")
    public ResponseEntity<PaymentDto> confirmPayment(@RequestBody PaymentDto paymentConfirmDto) {
        PaymentDto payment = paymentService.confirmPayment(paymentConfirmDto);
        if (payment == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(payment);
    }
} 