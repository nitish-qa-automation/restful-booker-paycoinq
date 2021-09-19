package com.heroukapp.apis.responseproviders;

import com.heroukapp.apis.dto.getbooking.BookingDates;
import com.heroukapp.apis.dto.getbooking.GetBookingResponse;
import io.restassured.response.Response;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class GetBookingProvider {

    private GetBookingResponse getBookingResponse;

    public void setGetBookingResponse(Response response){
        getBookingResponse =response.getBody().as(GetBookingResponse.class);
    }

    public String getFirstName(){ return getBookingResponse.getFirstname();}
    public String getLastName(){ return getBookingResponse.getLastname();}
    public int getTotalPrice(){ return getBookingResponse.getTotalprice();}
    public Boolean getDepositPaid(){ return getBookingResponse.getDepositpaid();}
    public String getAdditionalNeeds(){ return getBookingResponse.getAdditionalneeds();}
    public BookingDates getBookingDates(){ return getBookingResponse.getBookingdates();}


}
