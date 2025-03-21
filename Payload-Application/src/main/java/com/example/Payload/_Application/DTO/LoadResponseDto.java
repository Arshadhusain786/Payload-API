package com.example.Payload._Application.DTO;

import com.example.Payload._Application.Entity.Facility;
import com.example.Payload._Application.Entity.Load;
import lombok.*;

import java.util.UUID;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoadResponseDto {
    private UUID loadId;
    private Facility facility;
    private String productType;
    private String truckType;
    private int noOfTrucks;
    private double weight;
    private String comment;
    private UUID shipperId;
    private Timestamp date;

    // Static method to map Load entity to LoadResponseDto
    public static LoadResponseDto mapToDTO(Load load) {
        if (load == null) {
            return null;
        }

        return new LoadResponseDto(
                load.getLoadId(),
                new Facility(
                        null,  // Assuming facilityId is auto-generated
                        load.getFacility().getLoadingPoint(),
                        load.getFacility().getUnloadingPoint(), // Fixed typo
                        load.getFacility().getLoadingDate(),
                        load.getFacility().getUnloadingDate()
                ),
                load.getProductType(),
                load.getTruckType(),
                load.getNoOfTrucks(),
                load.getWeight(),
                load.getComment(),
                load.getShipperId(),
                load.getDate()
        );
    }
}
