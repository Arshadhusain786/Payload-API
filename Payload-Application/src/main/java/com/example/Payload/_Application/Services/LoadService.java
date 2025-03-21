package com.example.Payload._Application.Services;

import com.example.Payload._Application.Entity.Load;
import com.example.Payload._Application.Repository.LoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LoadService {

    @Autowired
    private LoadRepository loadRepository;

    public Load createLoad(Load load) {
        return loadRepository.save(load);
    }

    public List<Load> getLoads(UUID shipperId, String truckType, String productType, String loadingPoint, String unloadingPoint) {
        List<Load> loads = (shipperId != null) ? loadRepository.findByShipperId(shipperId) : loadRepository.findAll();

//        Dynamic filtering is used when query parameters are optional, meaning:
//
//          Users may or may not provide filters (shipperId, truckType, etc.).
//          If a filter is not provided, it should be ignored (not forcefully applied).
//          If multiple filters are provided, they should be applied correctly together.
        return loads.stream()
                .filter(load -> (truckType == null || truckType.equalsIgnoreCase(load.getTruckType())))
                .filter(load -> (productType == null || productType.equalsIgnoreCase(load.getProductType())))
                .filter(load -> (loadingPoint == null || loadingPoint.equalsIgnoreCase(load.getFacility().getLoadingPoint())))
                .filter(load -> (unloadingPoint == null || unloadingPoint.equalsIgnoreCase(load.getFacility().getUnloadingPoint())))
                .collect(Collectors.toList());
    }


    public Load getLoadById(UUID loadId) {
        return loadRepository.findById(loadId)
                .orElseThrow(() -> new RuntimeException("Load not found with ID: " + loadId));
    }

    public Load updateLoad(UUID loadId, Load updatedLoad) {
        Load existingLoad = loadRepository.findById(loadId)
                .orElseThrow(() -> new RuntimeException("Load not found with ID: " + loadId));

        existingLoad.setFacility(updatedLoad.getFacility());
        existingLoad.setProductType(updatedLoad.getProductType());
        existingLoad.setTruckType(updatedLoad.getTruckType());
        existingLoad.setNoOfTrucks(updatedLoad.getNoOfTrucks());
        existingLoad.setWeight(updatedLoad.getWeight());
        existingLoad.setComment(updatedLoad.getComment());
        existingLoad.setDate(updatedLoad.getDate());

        return loadRepository.save(existingLoad);
    }

    public void deleteLoad(UUID loadId) {
        loadRepository.deleteById(loadId);
    }
}
