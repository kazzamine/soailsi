package org.tp.invoice.models;

import java.util.List;

public class OrderRequest {
    private Long userId;
    private List<Long> productIds;

    // Constructors
    public OrderRequest() {}

    public OrderRequest(Long userId, List<Long> productIds) {
        this.userId = userId;
        this.productIds = productIds;
    }

    // Getters and Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public List<Long> getProductIds() { return productIds; }
    public void setProductIds(List<Long> productIds) { this.productIds = productIds; }
}
