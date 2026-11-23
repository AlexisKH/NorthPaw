package com.northpaw;

import jakarta.persistence.*;

@Entity
public class Inventory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "variant_id")
    private ItemVariant variant;

    @ManyToOne(optional = false)
    @JoinColumn(name = "dc_id")
    private DistributionCentre dc;

    @Column(nullable = false)
    private int quantityOnHand;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public ItemVariant getVariant() { return variant; }
    public void setVariant(ItemVariant variant) { this.variant = variant; }
    public DistributionCentre getDc() { return dc; }
    public void setDc(DistributionCentre dc) { this.dc = dc; }
    public int getQuantityOnHand() { return quantityOnHand; }
    public void setQuantityOnHand(int quantityOnHand) { this.quantityOnHand = quantityOnHand; }
}
