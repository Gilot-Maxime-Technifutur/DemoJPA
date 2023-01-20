package be.technifutur.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @Column(name = "product_id")
    private long id;
    @Column(name = "product_name")
    private String name;
    @Column(name = "quantity_per_unit")
    private String qttPerUnit;
    @Column(name = "units_in_stock")
    private int stock;
    @Column(name = "units_on_order")
    private int onOrder;
    @Column(name = "reorder_level")
    private int reorderLevel;
    @Column(columnDefinition = "int4")
    private boolean discontinued;
}
