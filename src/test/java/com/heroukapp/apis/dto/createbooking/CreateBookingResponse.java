package com.heroukapp.apis.dto.createbooking;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookingResponse {
    @JsonProperty("bookingid")
    private Integer bookingid;
    @JsonProperty("booking")
    private Booking booking;
}
