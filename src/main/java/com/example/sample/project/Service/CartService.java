package com.example.sample.project.Service;

import com.example.sample.project.Entity.Cart;
import com.example.sample.project.Repository.CartRepository;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public ResponseEntity<Cart> getCartByUserId(Long userId) {
        Optional<Cart> cart = cartRepository.findById(userId);
        return cart.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    public Cart createCart(Cart cart) {

        return cartRepository.save(cart);
    }

    public ResponseEntity<Cart> updateCart(Long id, Cart cartDetails) {
        return cartRepository.findById(id)
                .map(cart -> {
                    cart.setUserId(cartDetails.getUserId());
                    cart.setItems(cartDetails.getItems());
                    cart.setTotalPrice(cartDetails.getTotalPrice());
                    return ResponseEntity.ok(cartRepository.save(cart));
                })
                .orElseThrow(() -> new RuntimeException("Cart Items not found"));
    }

    public ResponseEntity<Void> deleteCart(Long id) {
        return cartRepository.findById(id)
                .map(cart -> {
                    cartRepository.delete(cart);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseThrow(() -> new RuntimeException("Cart Items not found"));
    }
}

