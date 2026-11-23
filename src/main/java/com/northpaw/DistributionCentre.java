package com.northpaw;

import jakarta.persistence.*;

@Entity
public class DistributionCentre {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dc_id")
    private Long id;

    @Column(nullable = false)
    private String name;
    private String address;
    private String city;
    private String province;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getProvince() { return province; }
    public void setProvince(String province) { this.province = province; }
}
