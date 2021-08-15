package com.team4.HRIS.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Author - Joseph Huntley
// Team 4
@RestController
@RequestMapping(path = "/api/v1/address")
public class AddressController {
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService){
        this.addressService = addressService;
    }
    // Create a new address
    @PostMapping
    public void createAddress(@RequestBody Address address){
        addressService.createAddress(address);
    }
    // View existing addresses based upon employee ID
    @GetMapping(path = "/{id}")
    public List<Address> viewAddress(@PathVariable int id){
       return addressService.viewAddress(id);
    }
    // Updates an existing address in the db based upon an address ID
    @PutMapping(path = "/{id}")
    public void updateAddress(@RequestBody Address address, @PathVariable int id){
        addressService.updateAddress(address, id);
    }
    // Deletes an existing address from the db based upon an address ID
    @DeleteMapping(path = "/{id}")
    public void deleteAddress(@PathVariable int id){
        addressService.deleteAddress(id);
    }
}
