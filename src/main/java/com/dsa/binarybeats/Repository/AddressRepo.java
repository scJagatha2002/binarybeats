package com.dsa.binarybeats.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dsa.binarybeats.Entity.Address;

public interface AddressRepo extends JpaRepository<Address,Long>{
    
}
