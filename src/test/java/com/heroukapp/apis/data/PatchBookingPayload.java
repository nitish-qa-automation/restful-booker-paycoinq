package com.heroukapp.apis.data;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.File;

import static com.heroukapp.apis.utils.JsonUtil.readJSON;

@Component
public class PatchBookingPayload {
    private JSONObject jsonObject;


    public JSONObject getBookingNamesData(GuestDetails guestDetails) {
        jsonObject = new JSONObject();
        jsonObject.put("firstname", guestDetails.getFirstName());
        jsonObject.put("lastname", guestDetails.getLastName());
        return jsonObject;
    }


    public JSONObject getBookingPriceData(GuestDetails guestDetails) {
        jsonObject = new JSONObject();
        jsonObject.put("totalprice", guestDetails.getTotalPrice());
        return jsonObject;
    }

}
