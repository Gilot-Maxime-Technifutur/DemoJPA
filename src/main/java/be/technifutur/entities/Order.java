package be.technifutur.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @Column(name = "order_id")
    private Long id;
    private Double freight;
    @Column(name = "order_date")
    private LocalDate date;
    @Column(name = "required_date")
    private LocalDate requiredDate;
    @Column(name = "ship_city")
    private String shipCity;
    @Column(name = "ship_country")
    private String shipCountry;
    @Column(name = "ship_name")
    private String shipName;
    @Column(name = "ship_postal_code")
    private String shipPostalCode;
    @Column(name = "ship_region")
    private String shipRegion;
    @Column(name = "shipped_date")
    private LocalDate shippedDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "ship_via")
    private Shipper shipper;

    @ManyToMany
    @JoinTable(
            name = "order_details",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products = new LinkedHashSet<>();
}
