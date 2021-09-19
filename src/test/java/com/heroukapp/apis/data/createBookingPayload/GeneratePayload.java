package com.heroukapp.apis.data.createBookingPayload;

import com.google.gson.Gson;
import com.heroukapp.apis.data.GuestDetails;
import com.heroukapp.apis.utils.TestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GeneratePayload {

    @Autowired
    public RequestPayload requestPayload;
    @Autowired
    TestUtil testUtil;

    public String getCreateBookingPayload(GuestDetails guestDetails, int checkInDaysFromToday ,
                                          int checkOutDaysFromToday){


        String checkInDateFromToday = testUtil.getFutureDateInFormat("yyyy-MM-dd",checkInDaysFromToday);
        String checkOutDateFromToday = testUtil.getFutureDateInFormat("yyyy-MM-dd",checkOutDaysFromToday);

        return new Gson().toJson(requestPayload.firstname(guestDetails.getFirstName()).
                lastname(guestDetails.getLastName()).depositpaid(guestDetails.getDepositPaid()).
                bookingdates(new BookingDates().checkin(checkInDateFromToday).checkout(checkOutDateFromToday)).
                additionalneeds(guestDetails.getNeeds()).totalprice(guestDetails.getTotalPrice()));

    }

}

