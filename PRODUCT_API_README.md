# Product Detail API Documentation

## Customer Product Endpoints

### Get Product Details
**Endpoint**: `GET /api/products/{id}`
**Description**: Retrieves comprehensive product information including specifications, stock status, and related products.

**Response Example**:
```json
{
  "id": "product123",
  "name": "Hammer",
  "category": "Tools",
  "description": "Professional grade hammer for construction work",
  "price": 25.99,
  "stock": 50,
  "images": ["hammer1.jpg", "hammer2.jpg"],
  "brand": "ProTools",
  "model": "HM-2023",
  "specifications": "Weight: 500g, Length: 30cm, Head: Steel",
  "warranty": "2 years",
  "weight": "500g",
  "dimensions": "30cm x 10cm x 5cm",
  "color": "Black with yellow handle",
  "material": "Steel head, Fiberglass handle",
  "isAvailable": true,
  "sku": "PT-HM-001",
  "stockStatus": "In Stock",
  "relatedProducts": [
    {
      "name": "Screwdriver Set",
      "price": 15.99,
      "images": ["screwdriver1.jpg"],
      "category": "Tools"
    },
    {
      "name": "Wrench Set",
      "price": 35.99,
      "images": ["wrench1.jpg"],
      "category": "Tools"
    }
  ]
}
```

### Get All Products (Customer View)
**Endpoint**: `GET /api/products` or `GET /api/products/all`
**Description**: Retrieves all products as DTOs for customer browsing.

### Get Basic Product Info
**Endpoint**: `GET /api/products/{id}/basic`
**Description**: Retrieves basic product information without related products (for faster loading).

## Stock Status Values
- **"In Stock"**: More than 5 items available
- **"Low Stock"**: 1-5 items available
- **"Out of Stock"**: 0 items available

## Related Products Logic
The system finds related products using the following criteria:
1. Products from the same category (excluding the current product)
2. If fewer than 4 products found, adds products from the same brand
3. Limits to maximum 4 related products

## Product Fields Added
The following fields have been added to support detailed product information:
- `brand`: Product manufacturer
- `model`: Product model number
- `specifications`: Detailed technical specifications
- `warranty`: Warranty information
- `weight`: Product weight
- `dimensions`: Product dimensions
- `color`: Product color
- `material`: Product material composition
- `isAvailable`: Availability flag
- `sku`: Stock keeping unit identifier

## Admin Product Endpoints
Admin endpoints remain under `/api/admin/products` for product management operations.