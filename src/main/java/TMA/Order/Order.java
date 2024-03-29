package TMA.Order;

import TMA.Item.Item;
import TMA.Item.Unit;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Unit unit;

    private double quantity;

    private double priceNetto;

    private String comment;

    @OneToOne
    private Item item;

}

