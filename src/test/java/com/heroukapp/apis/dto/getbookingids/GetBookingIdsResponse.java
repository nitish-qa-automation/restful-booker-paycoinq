package com.heroukapp.apis.dto.getbookingids;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class GetBookingIdsResponse {
    @JsonProperty("bookingid")
    private Integer bookingid;
}
