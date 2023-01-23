package be.technifutur.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class OrderDetailID implements Serializable {
    private Long orderId;
    private Long productId;
}
