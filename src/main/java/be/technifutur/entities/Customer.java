package be.technifutur.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @Column(name = "customer_id")
    private String id;
    private String address;
    private String city;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "contact_name")
    private String contactName;
    @Column(name = "contact_title")
    private String contactTitle;
    private String country;
    private String fax;
    private String phone;
    @Column(name = "postal_code")
    private String postalCode;
    private String region;

    @ManyToMany
    @JoinTable(
            name = "customer_customer_demo",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_type_id")
    )
    private List<Demographic> types = new LinkedList<>();

    @OneToMany(mappedBy = "customer")
    private List<Order> orders = new LinkedList<>();
}
