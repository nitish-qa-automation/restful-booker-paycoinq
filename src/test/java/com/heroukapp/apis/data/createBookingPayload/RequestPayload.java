package com.heroukapp.apis.data.createBookingPayload;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@Accessors(fluent = true, chain = true)
public class RequestPayload {
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("totalprice")
    @Expose
    private Integer totalprice;
    @SerializedName("depositpaid")
    @Expose
    private Boolean depositpaid;
    @SerializedName("bookingdates")
    @Expose
    private BookingDates bookingdates;
    @SerializedName("additionalneeds")
    @Expose
    private String additionalneeds;

}
