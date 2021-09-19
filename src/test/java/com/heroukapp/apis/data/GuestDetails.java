package com.heroukapp.apis.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GuestDetails {

    GUEST_DEPOSIT_PAID("Tom","cruise", 130,true,""),
    GUEST_NO_DEPOSIT_PAID("James","potter", 150,false,""),
    GUEST_ADDITIONAL_NEEDS("Harry","potter", 100,true,"breakfast"),
    GUEST_FIRST_NAME_ONLY("Tom","", 130,true,""),
    GUEST_FULL_NAME_PRICE_200("Mark","Henry", 200,true,""),
    GUEST_LAST_NAME_ONLY("","Potter", 130,true,"");

    private String firstName;
    private String lastName;
    private int totalPrice;
    private Boolean depositPaid;
    private String needs;
}