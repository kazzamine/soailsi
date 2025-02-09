package org.tp.invoice.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @ElementCollection
    private List<Long> productIds;

    private double totalAmount;
    private LocalDateTime createdAt;

    // ✅ Default Constructor (Required for JPA)
    public Invoice() {}

    // ✅ Constructor that matches the InvoiceService
    public Invoice(Long userId, List<Long> productIds, double totalAmount) {
        this.userId = userId;
        this.productIds = productIds;
        this.totalAmount = totalAmount;
        this.createdAt = LocalDateTime.now();  // Set timestamp when created
    }

    // ✅ Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public List<Long> getProductIds() { return productIds; }
    public void setProductIds(List<Long> productIds) { this.productIds = productIds; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
