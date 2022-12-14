package com.coast.brenno.bikestoreback.repository;

import com.coast.brenno.bikestoreback.model.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreetingRepository extends JpaRepository<Greeting, Long> {

}