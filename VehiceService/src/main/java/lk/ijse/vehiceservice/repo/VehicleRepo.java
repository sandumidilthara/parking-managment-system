package lk.ijse.vehiceservice.repo;

import lk.ijse.vehiceservice.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
@EnableJpaRepositories
public interface VehicleRepo extends JpaRepository<Vehicle, UUID> {
    boolean existsVehicleByLicensePlate(String licensePlate);

    Vehicle findByLicensePlate(String licensePlate);
}
