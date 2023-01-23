package be.technifutur.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "customer_demographics")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Demographic {
    @Id
    @Column(name = "customer_type_id")
    private String id;
    @Column(name = "customer_desc")
    private String descr;

    @ManyToMany(mappedBy = "types")
    private List<Customer> customers = new LinkedList<>();
}
