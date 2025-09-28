package com.example.hardwaremanagement.service;

import com.example.hardwaremanagement.model.PurchaseOrder;
import com.example.hardwaremanagement.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    public List<PurchaseOrder> list() {
        return purchaseOrderRepository.findAll();
    }

    public Optional<PurchaseOrder> get(String id) {
        return purchaseOrderRepository.findById(id);
    }

    public PurchaseOrder create(PurchaseOrder po) {
        return purchaseOrderRepository.save(po);
    }

    public PurchaseOrder update(String id, PurchaseOrder update) {
        PurchaseOrder existing = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase order not found: " + id));
        if (!"CREATED".equalsIgnoreCase(existing.getStatus())) {
            throw new IllegalStateException("Only POs in CREATED status can be edited");
        }
        // Update editable fields
        existing.setSupplierId(update.getSupplierId());
        existing.setItems(update.getItems());
        existing.setProductIds(update.getProductIds());
        existing.setDeliveryDate(update.getDeliveryDate());
        // Keep status and createdAt as-is
        return purchaseOrderRepository.save(existing);
    }

    public void delete(String id) {
        purchaseOrderRepository.deleteById(id);
    }

    public PurchaseOrder cancel(String id) {
        PurchaseOrder po = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase order not found: " + id));
        if (!"CREATED".equalsIgnoreCase(po.getStatus())) {
            throw new IllegalStateException("Only POs in CREATED status can be canceled");
        }
        po.setStatus("CANCELED");
        return purchaseOrderRepository.save(po);
    }
}
