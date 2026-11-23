package com.northpaw;

import jakarta.persistence.*;

@Entity
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "assigned_dc_id")
    private DistributionCentre assignedDc;

    @Column(nullable = false)
    private String storeName;

    private String contactEmail;

    private String phone;
    private String address;
    private String city;
    private String province;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public DistributionCentre getAssignedDc() { return assignedDc; }
    public void setAssignedDc(DistributionCentre assignedDc) { this.assignedDc = assignedDc; }
    public String getStoreName() { return storeName; }
    public void setStoreName(String storeName) { this.storeName = storeName; }
    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getProvince() { return province; }
    public void setProvince(String province) { this.province = province; }
}
