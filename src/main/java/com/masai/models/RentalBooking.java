package com.masai.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalBooking implements Serializable {

    private Integer id;
    private Integer customerId;
    private Integer crId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double totalCost;
}
