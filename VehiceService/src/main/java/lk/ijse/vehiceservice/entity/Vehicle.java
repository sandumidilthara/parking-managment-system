package lk.ijse.vehiceservice.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(unique = true, nullable = false)
    private String licensePlate;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private String email;
}
