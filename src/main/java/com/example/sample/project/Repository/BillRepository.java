package com.example.sample.project.Repository;


import com.example.sample.project.Entity.Bills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

    @Repository
    public interface BillRepository extends JpaRepository<Bills, Long> {
        List<Bills> findByOrderId(Long orderId);
    }


