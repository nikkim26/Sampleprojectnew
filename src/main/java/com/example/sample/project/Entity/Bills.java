package com.example.sample.project.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Bill")
@Data
@NoArgsConstructor
public class Bills {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long billId;

        @Column(name = "order_id")
        private Long orderId;

        private String invoiceDate;
        private Double totalAmount;
        private Double taxAmount;
        private Double shippingAmount;




}
