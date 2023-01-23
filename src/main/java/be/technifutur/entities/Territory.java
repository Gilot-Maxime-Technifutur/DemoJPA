package be.technifutur.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "territories")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Territory {
    @Id
    @Column(name = "territory_id")
    private String id;
    @Column(name = "territory_description")
    private String descr;

    @ManyToMany(mappedBy = "territories")
    private List<Employee> employees = new LinkedList<>();

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;
}
