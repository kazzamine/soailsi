package org.tp.invoice.services;

import org.tp.invoice.clients.UserClient;
import org.tp.invoice.clients.ProductClient;
import org.tp.invoice.dto.UserDTO;
import org.tp.invoice.dto.ProductDTO;
import org.tp.invoice.models.Invoice;
import org.tp.invoice.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private UserClient userClient;

    @Autowired
    private ProductClient productClient;

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> getInvoiceById(Long id) {
        return invoiceRepository.findById(id);
    }

    public Invoice createInvoice(Long userId, List<Long> productIds) {
        // Fetch the user from User Service
        UserDTO user = userClient.getUserById(userId);
        if (user == null) throw new RuntimeException("User not found!");

        // Fetch products from Product Service
        List<ProductDTO> products = productIds.stream()
                .map(productClient::getProductById)
                .collect(Collectors.toList());

        if (products.isEmpty()) throw new RuntimeException("No valid products found!");

        // Calculate total price
        double totalAmount = products.stream().mapToDouble(ProductDTO::getPrice).sum();

        // ✅ Correctly Calling Constructor (Fixes the Error)
        Invoice invoice = new Invoice(userId, productIds, totalAmount);
        return invoiceRepository.save(invoice);
    }

    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }
}
