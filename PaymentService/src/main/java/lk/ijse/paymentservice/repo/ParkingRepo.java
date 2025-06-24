package lk.ijse.paymentservice.repo;

import lk.ijse.paymentservice.entity.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ParkingRepo extends JpaRepository<Parking, UUID> {
    boolean existsParkingByLocation(String location);

    Parking findByLocation(String location);

    boolean existsParkingByLocationCode(int locationCode);

    List<Parking> findAllByCity(String city);

    boolean existsParkingByCity(String lowerCase);
}
