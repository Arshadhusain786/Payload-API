package com.example.Payload._Application.Controllers;

import com.example.Payload._Application.DTO.LoadResponseDto;
import com.example.Payload._Application.Entity.Load;
import com.example.Payload._Application.Services.LoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.Payload._Application.DTO.LoadResponseDto.mapToDTO;

@RestController
@RequestMapping("/loads")
public class LoadController {

    @Autowired
    private LoadService loadService;

    @PostMapping("/add")
    public ResponseEntity<LoadResponseDto> createLoad(@RequestBody Load load) {
        Load savedLoad = loadService.createLoad(load);
        return new ResponseEntity<>(mapToDTO(savedLoad), HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<List<LoadResponseDto>> getLoads(
            @RequestParam(required = false) UUID shipperId,
            @RequestParam(required = false) String truckType,
            @RequestParam(required = false) String productType,
            @RequestParam(required = false) String loadingPoint,
            @RequestParam(required = false) String unloadingPoint) {

        List<LoadResponseDto> responseDTOs = loadService.getLoads(shipperId, truckType, productType, loadingPoint, unloadingPoint)
                .stream().map(LoadResponseDto::mapToDTO).collect(Collectors.toList());

        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<LoadResponseDto> getLoadById(@PathVariable UUID id)
    {
        UUID uuid;
        try {
            uuid = UUID.fromString(String.valueOf(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
        Load load = loadService.getLoadById(id);
        return ResponseEntity.ok(mapToDTO(load));
    }

    @PutMapping("/update/{loadId}")
    public ResponseEntity<LoadResponseDto> updateLoad(@PathVariable UUID loadId, @RequestBody Load updatedLoad)
    {
        UUID uuid;
        try {
            uuid = UUID.fromString(String.valueOf(loadId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
        Load load = loadService.updateLoad(loadId, updatedLoad);
        return ResponseEntity.ok(mapToDTO(load));
    }

    @DeleteMapping("/delete/{loadId}")
    public ResponseEntity<Void> deleteLoad(@PathVariable UUID loadId)
    {
        UUID uuid;
        try {
            uuid = UUID.fromString(String.valueOf(loadId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
        loadService.deleteLoad(loadId);
        return ResponseEntity.noContent().build();
    }
}
