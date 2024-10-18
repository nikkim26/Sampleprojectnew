package com.example.sample.project.Controller;
import com.example.sample.project.Entity.Bills;
import com.example.sample.project.Service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")

public class BillController {


        @Autowired
        private BillService billService;

//        @GetMapping("/{orderId}")
//        public List<Bills> getBillsByOrderId(@PathVariable Long orderId) {
//            return billService.getBillsByOrderId(orderId);
//        }

        @PostMapping
        public Bills createBill(@RequestBody Bills bills) {
            return billService.createBill(bills);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Bills> getBillById(@PathVariable Long id) {
            return billService.getBillById(id);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Bills> updateBill(@PathVariable Long id, @RequestBody Bills bills) {
            return billService.updateBill(id,bills );
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteBill(@PathVariable Long id) {
            return billService.deleteBill(id);
        }
    }


