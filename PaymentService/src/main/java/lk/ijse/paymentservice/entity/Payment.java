package lk.ijse.paymentservice.entity;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
public class Payment{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID paymentId;
    @Column(nullable = false)
    private String licensePlate;
    @Column(nullable = false)
    private String userEmail;
    @Column(nullable = false)
    private String parkingLocation;
    @Column(nullable = false)
    private int amount;
    @Column(nullable = false)
    private Date paymentDate;
    @Column(nullable = false)
    private Date bookingDate;
    @Column(nullable = false)
    private Time paymentTime;

}
