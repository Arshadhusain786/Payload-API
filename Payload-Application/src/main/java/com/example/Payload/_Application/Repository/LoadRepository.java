package com.example.Payload._Application.Repository;

import com.example.Payload._Application.Entity.Load;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LoadRepository extends JpaRepository<Load, UUID> {

    List<Load> findByShipperId(UUID shipperId);

    List<Load> findByTruckTypeAndProductTypeAndFacility_LoadingPointAndFacility_UnloadingPoint(
            String truckType, String productType, String loadingPoint, String unloadingPoint);
}
