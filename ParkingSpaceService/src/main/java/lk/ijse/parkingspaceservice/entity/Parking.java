package lk.ijse.parkingspaceservice.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String location;
    @Column(unique = true)
    private int locationCode;
    @Column(nullable = false)
    private String city;
    private boolean available;
    @Column(nullable = false)
    private String email;
    private int payAmount;

}
