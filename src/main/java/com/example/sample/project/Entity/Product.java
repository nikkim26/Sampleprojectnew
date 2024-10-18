package com.example.sample.project.Entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "product_id")  // Specify the column name in the database
        private Long productId;

        @Column(name = "name")          // Specify the column name in the database
        private String name;

        @Column(name = "description")   // Specify the column name in the database
        private String description;

        @Column(name = "price")         // Specify the column name in the database
        private Double price;

        @Column(name = "stock_quantity") // Specify the column name in the database
        private Integer stockQuantity;






    }







