package com.example.hardwaremanagement.controller;

import com.example.hardwaremanagement.model.Invoice;
import com.example.hardwaremanagement.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    // Create new invoice
    @PostMapping("/invoices")
    public Invoice createInvoice(@RequestBody Invoice invoice) {
        return invoiceService.createInvoice(invoice);
    }

    // Get invoice by ID
    @GetMapping("/invoices/{id}")
    public Invoice getInvoice(@PathVariable String id) {
        return invoiceService.getInvoiceById(id);
    }
    @GetMapping("/invoices/all")
    public List<Invoice> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }
}
