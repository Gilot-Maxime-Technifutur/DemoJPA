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
@Table(name = "employees")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @Column(name = "employee_id")
    private Long id;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    private String title;
    @Column(name = "title_of_courtesy")
    private String titleOfCourtesy;
    @Column(name = "birth_date")
    private LocalDate ddn;
    @Column(name = "hire_date")
    private LocalDate hireDate;
    private String address;
    private String city;
    private String region;
    @Column(name = "postal_code")
    private String postalCode;
    private String country;
    @Column(name = "home_phone")
    private String homePhone;
    private String extension;
    //photo
    private String notes;
    @Column(name = "photo_path")
    private String photoPath;

    @OneToMany(mappedBy = "employee")
    private Set<Order> orders = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "reports_to")
    private Employee reportTo;

//    @OneToMany(mappedBy = "reportTo")
//    private Set<Employee> employees = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(
            name = "employee_territories",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "territory_id")
    )
    private Set<Territory> territories = new LinkedHashSet<>();

}
