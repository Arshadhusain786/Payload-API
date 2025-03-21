package com.example.Payload._Application.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name="facilities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Facility
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonIgnore
    private java.util.UUID facilityId; // this will not return in response

    private String loadingPoint;

    private String unloadingPoint;


    private Timestamp loadingDate;


    private Timestamp unloadingDate;


}
