package com.heroukapp.apis.responseproviders;

import com.heroukapp.apis.dto.createbooking.BookingDates;
import com.heroukapp.apis.dto.createbooking.CreateBookingResponse;
import io.restassured.response.Response;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class CreateBookingProvider {

    private CreateBookingResponse createBookingResponse;

    public void setCreateBookingResponse(Response response){
        createBookingResponse =response.getBody().as(CreateBookingResponse.class);
    }

    public String getFirstName(){ return createBookingResponse.getBooking().getFirstname();}
    public String getLastName(){ return createBookingResponse.getBooking().getLastname();}
    public int getTotalPrice(){ return createBookingResponse.getBooking().getTotalprice();}
    public Boolean getDepositPaid(){ return createBookingResponse.getBooking().getDepositpaid();}
    public String getAdditionalNeeds(){ return createBookingResponse.getBooking().getAdditionalneeds();}
    public int getBookingId(){return  createBookingResponse.getBookingid();}
    public BookingDates getBookingDates(){return createBookingResponse.getBooking().getBookingdates();}


}
