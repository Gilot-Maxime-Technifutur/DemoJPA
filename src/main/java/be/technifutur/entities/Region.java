package be.technifutur.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Region {
    @Id
    @Column(name = "region_id")
    private Long id;
    @Column(name = "region_description")
    private String descr;

    @OneToMany(mappedBy = "region")
    private Set<Territory> territories = new LinkedHashSet<>();
}
