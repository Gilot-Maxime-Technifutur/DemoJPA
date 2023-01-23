package be.technifutur.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "shippers")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Shipper {
    @Id
    @Column(name = "shipper_id")
    private Long id;
    @Column(name = "company_name")
    private String companyName;
    private String phone;

    @OneToMany(mappedBy = "shipper")
    private Set<Order> orders = new LinkedHashSet<>();
}
