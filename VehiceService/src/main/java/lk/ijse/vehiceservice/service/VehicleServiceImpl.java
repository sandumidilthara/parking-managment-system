package lk.ijse.vehiceservice.service;


import lk.ijse.vehiceservice.dto.VehicleDTO;
import lk.ijse.vehiceservice.entity.Vehicle;
import lk.ijse.vehiceservice.repo.UserRepository;
import lk.ijse.vehiceservice.repo.VehicleRepo;
import lk.ijse.vehiceservice.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleRepo vehicleRepo;
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public int saveVehicle(VehicleDTO vehicle) {
        System.out.println("Inside VehicleServiceImpl::saveVehicleDTO :" + vehicle);
try{
if(userRepo.existsUserByEmail(vehicle.getEmail().toLowerCase())) {
    if(vehicleRepo.existsVehicleByLicensePlate(vehicle.getLicensePlate())){
        return VarList.All_Ready_Added;
    }
    Vehicle vehicleEntity = modelMapper.map(vehicle, Vehicle.class);
    String email = vehicle.getEmail().toLowerCase();
    vehicleEntity.setEmail(email);
    System.out.println("VehicleServiceImpl:saveVehicleEntity :" + vehicleEntity);
    Vehicle vehicleEntity1 = vehicleRepo.save(vehicleEntity);
    System.out.println("Vehicle save successful");
    return VarList.Created;
}
return VarList.Not_Found;

}catch (Exception e){
    throw  new RuntimeException();
}
    }

    @Override
    public int updateVehicle(VehicleDTO vehicleDTO) {
        System.out.println("Inside VehicleServiceImpl::updateVehicleDTO :" + vehicleDTO);
       try{
           if(userRepo.existsUserByEmail(vehicleDTO.getEmail().toLowerCase())) {
               if(vehicleRepo.existsVehicleByLicensePlate(vehicleDTO.getLicensePlate())){
                   Vehicle vehicleEntity = vehicleRepo.findByLicensePlate(vehicleDTO.getLicensePlate());
                   if(vehicleEntity != null && vehicleDTO.getEmail().equals(vehicleEntity.getEmail())){
                       vehicleEntity.setLicensePlate(vehicleDTO.getLicensePlate());
                       vehicleEntity.setModel(vehicleDTO.getModel());
                       vehicleEntity.setEmail(vehicleDTO.getEmail().toLowerCase());
                       Vehicle vehicleEntity1 = vehicleRepo.save(vehicleEntity);
                       return VarList.Created;
                   }
                   return VarList.Not_Found;
               }

           }
           return VarList.Not_Found;
       }catch (Exception e){
           throw  new RuntimeException();
       }
    }

    @Override
    public int deleteVehicle(VehicleDTO vehicleDTO) {
        System.out.println("Inside VehicleServiceImpl::deleteVehicle :" + vehicleDTO.getLicensePlate());
       try{
           if(userRepo.existsUserByEmail(vehicleDTO.getEmail().toLowerCase())) {
               if(vehicleRepo.existsVehicleByLicensePlate(vehicleDTO.getLicensePlate())){
                   Vehicle vehicleEntity = vehicleRepo.findByLicensePlate(vehicleDTO.getLicensePlate());
                   vehicleRepo.delete(vehicleEntity);
                   System.out.println("Vehicle delete successful");
                   return VarList.OK;
               }
               return VarList.Not_Found;
           }
           return VarList.Not_Found;
       }catch (Exception e){
           System.out.println("exception inside VehicleServiceImpl::deleteVehicle :" + e.getMessage());
           throw new RuntimeException();
       }
    }
    @Override
    public List<VehicleDTO> getAllVehicle() {
        System.out.println("Get All vehicle :........");
        List<Vehicle> vehicles = vehicleRepo.findAll();
        return modelMapper.map(vehicles, new TypeToken<List<VehicleDTO>>() {}.getType());
    }

    @Override
    public VehicleDTO getVehicleByNumberPlate(String licenPlate) {
        System.out.println("Get vehicle use by number plate : ..."+licenPlate);
        if(vehicleRepo.existsVehicleByLicensePlate(licenPlate)){
            Vehicle vehicle = vehicleRepo.findByLicensePlate(licenPlate);
            return modelMapper.map(vehicle, VehicleDTO.class);
        }
        return null;
    }
}
