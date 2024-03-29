package TMA.Item;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ItemGroup itemGroup;

    private String name;

    @Enumerated(EnumType.STRING)
    private Unit unit;

    private double quantity;

    private double priceNetto;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String storageLocation;

    private String firstName;

    private String secondName;

    private String comment;

}
