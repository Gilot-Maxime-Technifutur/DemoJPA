package be.technifutur.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class OrderDetailID implements Serializable {
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "product_id")
    private Long productId;
}
