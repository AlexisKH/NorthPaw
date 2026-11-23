package com.northpaw;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

public class ProductForm {

    @NotBlank(message = "Product name is required.")
    @Size(max = 100, message = "Name must be 100 characters or fewer.")
    private String name;

    @Size(max = 500, message = "Description must be 500 characters or fewer.")
    private String description;

    @NotNull(message = "Please choose a brand.")
    private Long brandId;

    @NotNull(message = "Please choose a category.")
    private Long categoryId;

    @NotNull(message = "Please choose a pet type.")
    private Long petTypeId;

    @NotBlank(message = "SKU is required.")
    @Pattern(regexp = "[A-Z0-9-]{4,20}", message = "SKU must be 4-20 characters: capital letters, digits or dashes.")
    private String sku;

    @NotBlank(message = "Size is required (for example 2 kg).")
    private String size;

    @Size(max = 50, message = "Flavour must be 50 characters or fewer.")
    private String flavour;

    @NotBlank(message = "Barcode is required.")
    @Pattern(regexp = "\\d{12,13}", message = "Barcode must be 12 or 13 digits.")
    private String barcode;

    @NotNull(message = "Price is required.")
    @DecimalMin(value = "0.01", message = "Price must be at least $0.01.")
    @DecimalMax(value = "9999.99", message = "Price cannot be more than $9,999.99.")
    private BigDecimal unitPrice;

    @NotEmpty(message = "Please choose at least one distribution centre.")
    private List<Long> dcIds;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Long getBrandId() { return brandId; }
    public void setBrandId(Long brandId) { this.brandId = brandId; }
    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
    public Long getPetTypeId() { return petTypeId; }
    public void setPetTypeId(Long petTypeId) { this.petTypeId = petTypeId; }
    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }
    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }
    public String getFlavour() { return flavour; }
    public void setFlavour(String flavour) { this.flavour = flavour; }
    public String getBarcode() { return barcode; }
    public void setBarcode(String barcode) { this.barcode = barcode; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
    public List<Long> getDcIds() { return dcIds; }
    public void setDcIds(List<Long> dcIds) { this.dcIds = dcIds; }
}
