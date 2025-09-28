package com.example.hardwaremanagement.controller;

import com.example.hardwaremanagement.model.PurchaseOrder;
import com.example.hardwaremanagement.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supplier/purchase-orders")
@CrossOrigin(origins = "*")
public class SupplierPurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @GetMapping
    public List<PurchaseOrder> list() {
        return purchaseOrderService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrder> get(@PathVariable String id) {
        return purchaseOrderService.get(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PurchaseOrder> create(@RequestBody PurchaseOrder po) {
        if (po.getItems() != null && !po.getItems().isEmpty()) {
            po.setProductIds(po.getItems().stream().map(i -> i.getProductId()).toList());
        }
        po.setStatus("CREATED");
        if (po.getCreatedAt() == null) po.setCreatedAt(java.time.LocalDateTime.now());
        PurchaseOrder saved = purchaseOrderService.create(po);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody PurchaseOrder po) {
        try {
            if (po.getItems() != null && !po.getItems().isEmpty()) {
                po.setProductIds(po.getItems().stream().map(i -> i.getProductId()).toList());
            }
            PurchaseOrder updated = purchaseOrderService.update(id, po);
            return ResponseEntity.ok(updated);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        purchaseOrderService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<?> cancel(@PathVariable String id) {
        try {
            PurchaseOrder canceled = purchaseOrderService.cancel(id);
            return ResponseEntity.ok(canceled);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
