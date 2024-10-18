package com.example.sample.project.Service;

import com.example.sample.project.Entity.Bills;
import com.example.sample.project.Repository.BillRepository;
import org.springframework.http.ResponseEntity;



        import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

    @Service
    public class BillService {



            @Autowired
            private BillRepository billRepository;





    public Bills createBill(Bills bills) {
        return billRepository.save(bills);
    }

    public ResponseEntity<Bills> getBillById(Long id) {
        return billRepository.findById(id)
                .map(bill -> ResponseEntity.ok().body(bill))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Bills> updateBill(Long id, Bills bills) {
        return billRepository.findById(id)
                .map(bill -> {
                    bill.setInvoiceDate(bills.getInvoiceDate());
                    bill.setTotalAmount(bills.getTotalAmount());
                    bill.setTaxAmount(bills.getTaxAmount());
                    bill.setShippingAmount(bills.getShippingAmount());
                    return ResponseEntity.ok(billRepository.save(bill));
                })
                .orElseThrow(() -> new RuntimeException("Bill not found"));
    }

    public ResponseEntity<Void> deleteBill(Long id) {
        return billRepository.findById(id)
                .map(bill -> {
                    billRepository.delete(bill);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseThrow(() -> new RuntimeException("Bill not found"));
    }

        public List<Bills> getBillsByOrderId(Long orderId) {
            return billRepository.findByOrderId(orderId);
        }
    }
