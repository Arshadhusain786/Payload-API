package com.example.Payload._Application.Repository;

import com.example.Payload._Application.Entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FacilityRepository extends JpaRepository<Facility, UUID>
{

}
