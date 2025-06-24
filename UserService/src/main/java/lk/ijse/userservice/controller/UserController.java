package lk.ijse.userservice.controller;


import lk.ijse.userservice.dto.ResponseDTO;
import lk.ijse.userservice.dto.UserDTO;
import lk.ijse.userservice.dto.UserDTO2;
import lk.ijse.userservice.entity.User;
import lk.ijse.userservice.service.UserService;
import lk.ijse.userservice.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping(value = "/saveUser")
    public ResponseEntity<ResponseDTO>  SaveUser(@RequestBody UserDTO user){
        System.out.println("User Data Come to Controller :" + user);
        try{
            Date date = Date.valueOf(LocalDate.now());
            user.setCreatedAt(date);
            System.out.println("User Data Come to Controller :" + user);

            int res = userService.saveUser(user);
            switch(res){
                case VarList.Created -> {
                    System.out.println("Create User Success");
                    return ResponseEntity.ok(new ResponseDTO(VarList.Created, "User saved successfully", user));
                }
                case VarList.All_Ready_Added -> {
                    System.out.println("User Already Exists");
                    ResponseDTO response = new ResponseDTO(VarList.All_Ready_Added, "User Already Exists", null);
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
                }

                default -> {
                    System.out.println("User already exists");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(new ResponseDTO(VarList.Internal_Server_Error, "Error saving User", null));
                }
            }
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    @PutMapping(value = "/updateUser")
    public ResponseEntity<ResponseDTO>  UpdateUser(@RequestBody UserDTO user) {
        System.out.println("User Data Come to Controller :" + user);

        try {
            Date date = Date.valueOf(LocalDate.now());
            user.setCreatedAt(date);
            System.out.println("User Data Come to Controller :" + user);

            int res = userService.updateUser(user);
            switch (res) {
                case VarList.OK -> {
                    System.out.println("Update User Success");
                    return ResponseEntity.ok(new ResponseDTO(VarList.OK, "User saved successfully", user));
                }
                case VarList.Not_Found -> {
                    System.out.println("User Not Found");
                    ResponseDTO response = new ResponseDTO(VarList.Not_Found, "User Not Found", null);
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
                }

                default -> {
                    System.out.println("User Not exists");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(new ResponseDTO(VarList.Internal_Server_Error, "Error saving User", null));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    @GetMapping(value = "/getUserInfo")
    public ResponseEntity<ResponseDTO> getMemberByUserId(@RequestBody UserDTO2 user){
            System.out.println("Request Accepted get User");

            UserDTO userDTO = userService.getUserByEmail(user.getEmail());

            if (userDTO == null) {
                return ResponseEntity.ok(
                        new ResponseDTO(VarList.Not_Found, "not found a member", null)
                );
            } else {
                return ResponseEntity.ok(
                        new ResponseDTO(VarList.OK, "Member detail", userDTO)
                );

        }}


    @DeleteMapping(value = "/deleteUser")
    public ResponseEntity<ResponseDTO>  DeleteUser(@RequestBody UserDTO2 user) {
                System.out.println("User Data Come to Controller for delete :" + user);

                try {

                    int res = userService.deleteUser(user.getEmail(),user.getPassword());
                    switch (res) {
                        case VarList.OK -> {
                            System.out.println("Delete User Success");
                            return ResponseEntity.ok(new ResponseDTO(VarList.OK, "User Delete successfully", user));
                        }
                        case VarList.Not_Found -> {
                            System.out.println("User Not Found");
                            ResponseDTO response = new ResponseDTO(VarList.Not_Found, "User Not Found", null);
                            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
                        }

                        default -> {
                            System.out.println("User Not exists");
                            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                    .body(new ResponseDTO(VarList.Internal_Server_Error, "Error saving User", null));
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException();
                }

            }


    @GetMapping(value = "/getAllUser")
    public List<UserDTO> getAll(){
        List<UserDTO> userList = userService.getAll();
        return userList;

    }
    }



