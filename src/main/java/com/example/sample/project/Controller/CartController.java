package com.example.sample.project.Controller;
import com.example.sample.project.Entity.Cart;
import com.example.sample.project.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {





        @Autowired
        private CartService cartService;

        @GetMapping("/{userId}")
        public ResponseEntity<Cart> getCartByUserId(@PathVariable Long userId) {
            return cartService.getCartByUserId(userId);
        }

        @PostMapping
        public Cart createCart(@RequestBody Cart cart) {

            return cartService.createCart(cart);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Cart> updateCart(@PathVariable Long id, @RequestBody Cart cart) {
            return cartService.updateCart(id, cart);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
            return cartService.deleteCart(id);
        }
    }


