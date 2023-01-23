package be.technifutur.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_details")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    private static final Long serialVersionID = 1L;
    
    @Id
    @EmbeddedId
    private OrderDetailID id;
    
    @MapsId("productId")
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @MapsId("orderId")
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private Double discount;
    private Integer quantity;
    @Column(name = "unit_price")
    private Double unitPrice;
}
