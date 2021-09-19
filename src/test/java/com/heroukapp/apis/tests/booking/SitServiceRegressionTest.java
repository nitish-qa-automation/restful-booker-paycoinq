package com.heroukapp.apis.tests.booking;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;

import static com.heroukapp.apis.data.GuestDetails.*;
import static com.heroukapp.apis.expectations.GatewayErrorExpectations.FORBIDDEN;


@Epic("Booking")
@Feature("Booking Hotels")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public
class SitServiceRegressionTest extends BaseTest {



    @BeforeEach
    void createToken(){
       bookingsSteps.GivenIHaveAccessToken();
    }

    @AfterEach
    void teardown() {
        bookingsSteps.AndIDeleteBooking();
        headerUtil.clearHeaders();
        queryParamUtil.clearQueryParams();

    }

    /**
     * Regression Scenario 1 : Create Booking Service and Delete booking.
     **/

    @Test
    @DisplayName("Create a valid Booking and verify")
    void testCreateAValidBooking() {
        bookingsSteps.
                GivenIRequestToCreateBooking(GUEST_DEPOSIT_PAID, 5,10).
                WhenICreateBooking().
                ThenIVerifyBookingIsCreated();
    }

    @Test
    @DisplayName("Create a booking and verify booking id ")
    void testCreateAValidBookingAndVerifyBookingId() {
        bookingsSteps.
                GivenIRequestToCreateBooking(GUEST_DEPOSIT_PAID, 5,10).
                WhenICreateBooking().
                ThenIVerifyBookingIsCreated().
                ThenIVerifyBookingIDIsNotNull();
    }

    @Test
    @DisplayName("Create a booking and verify names ")
    void testCreateAValidBookingAndVerifyNames() {
        bookingsSteps.
                GivenIRequestToCreateBooking(GUEST_DEPOSIT_PAID, 5,10).
                WhenICreateBooking().
                ThenIVerifyBookingIsCreated().
                ThenIVerifyCreateBookingNames(GUEST_DEPOSIT_PAID);
    }

    @Test
    @DisplayName("Create a booking and verify Total price ")
    void testCreateAValidBookingAndVerifyTotalPrice() {
        bookingsSteps.
                GivenIRequestToCreateBooking(GUEST_DEPOSIT_PAID, 5,10).
                WhenICreateBooking().
                ThenIVerifyBookingIsCreated().
                ThenIVerifyCreateBookingTotalPrice(GUEST_DEPOSIT_PAID);
    }

    @Test
    @DisplayName("Create a booking and verify deposit paid ")
    void testCreateAValidBookingAndVerifyDepositPaid() {
        bookingsSteps.
                GivenIRequestToCreateBooking(GUEST_DEPOSIT_PAID, 5,10).
                WhenICreateBooking().
                ThenIVerifyBookingIsCreated().
                ThenIVerifyCreateBookingDepositPaid(GUEST_DEPOSIT_PAID);
    }
    @Test
    @DisplayName("Create a booking and verify no deposit paid ")
    void testCreateAValidBookingAndVerifyNoDepositPaid() {
        bookingsSteps.
                GivenIRequestToCreateBooking(GUEST_NO_DEPOSIT_PAID, 5,10).
                WhenICreateBooking().
                ThenIVerifyBookingIsCreated().
                ThenIVerifyCreateBookingDepositPaid(GUEST_NO_DEPOSIT_PAID);
    }

    @Test
    @DisplayName("Create a booking and verify deposit dates ")
    void testCreateAValidBookingAndVerifyDates() {
        bookingsSteps.
                GivenIRequestToCreateBooking(GUEST_DEPOSIT_PAID, 5,10).
                WhenICreateBooking().
                ThenIVerifyBookingIsCreated().
                ThenIVerifyCreateBookingDates(5,10);
    }

    @Test
    @DisplayName("Create a booking and verify no additional request ")
    void testCreateAValidBookingAndVerifyNoAdditionalRqst() {
        bookingsSteps.
                GivenIRequestToCreateBooking(GUEST_NO_DEPOSIT_PAID, 5,10).
                WhenICreateBooking().
                ThenIVerifyBookingIsCreated().
                ThenIVerifyCreateBookingAdditionalNeeds(GUEST_NO_DEPOSIT_PAID);
    }

    @Test
    @DisplayName("Create a booking and verify additional request ")
    void testCreateAValidBookingAndVerifyAdditionalRqst() {
        bookingsSteps.
                GivenIRequestToCreateBooking(GUEST_ADDITIONAL_NEEDS, 5,10).
                WhenICreateBooking().
                ThenIVerifyBookingIsCreated().
                ThenIVerifyCreateBookingAdditionalNeeds(GUEST_ADDITIONAL_NEEDS);
    }



    /**
     * Regression Scenario 2 : Create Booking Service, Get Bookings IDs and Delete booking.
     **/

    @Test
    @DisplayName("Get All booking ids without filter")
    void testGetBookingIdsWithoutFilter() {
        bookingsSteps.
                GivenIHaveRequestForGetBookingIds().
                WhenIRetrieveBookingIds().
                ThenISeeHotelListIsRetrieved();
    }
    @Test
    @DisplayName("Create Booking and Get booking ids without filter")
    void testCreateBookingAndVerifyGetBookingIds() {
        bookingsSteps.
                GivenIHaveCreatedABooking(GUEST_DEPOSIT_PAID,10,12).
                GivenIHaveRequestForGetBookingIds().
                WhenIRetrieveBookingIds().
                ThenISeeHotelListIsRetrieved().
                ThenIVerifyBookingIdIsPresent();
    }

    @Test
    @DisplayName("Create Booking and Get booking ids - Filter by First Name")
    void testCreateBookingAndVerifyGetBookingIdsWithFirstName() {
        bookingsSteps.
                GivenIHaveCreatedABooking(GUEST_FIRST_NAME_ONLY,10,12).
                GivenIHaveRequestForGetBookingIds(GUEST_FIRST_NAME_ONLY).
                WhenIRetrieveBookingIds().
                ThenISeeHotelListIsRetrieved().
                ThenIVerifyBookingIdIsPresent();
    }

    @Test
    @DisplayName("Create Booking and Get booking ids - Filter by Last Name")
    void testCreateBookingAndVerifyGetBookingIdsWithLastName() {
        bookingsSteps.
                GivenIHaveCreatedABooking(GUEST_LAST_NAME_ONLY,10,12).
                GivenIHaveRequestForGetBookingIds(GUEST_LAST_NAME_ONLY).
                WhenIRetrieveBookingIds().
                ThenISeeHotelListIsRetrieved().
                ThenIVerifyBookingIdIsPresent();
    }

    @Test
    @DisplayName("Create Booking and Get booking ids - Filter by Full Name")
    void testCreateBookingAndVerifyGetBookingIdsWithFullName() {
        bookingsSteps.
                GivenIHaveCreatedABooking(GUEST_DEPOSIT_PAID,10,12).
                GivenIHaveRequestForGetBookingIds(GUEST_DEPOSIT_PAID).
                WhenIRetrieveBookingIds().
                ThenISeeHotelListIsRetrieved().
                ThenIVerifyBookingIdIsPresent();
    }

    @Test
    @DisplayName("Create Booking and Get booking ids - Filter by checkin Date")
    void testCreateBookingAndVerifyGetBookingIdsWithCheckInDates() {
        bookingsSteps.
                GivenIHaveCreatedABooking(GUEST_DEPOSIT_PAID,10,12).
                GivenIHaveRequestForGetBookingIds(10).
                WhenIRetrieveBookingIds().
                ThenISeeHotelListIsRetrieved().
                ThenIVerifyBookingIdIsPresent();
    }
    @Test
    @DisplayName("Create Booking and Get booking ids - Filter by Dates")
    void testCreateBookingAndVerifyGetBookingIdsWithDates() {
        bookingsSteps.
                GivenIHaveCreatedABooking(GUEST_DEPOSIT_PAID,10,12).
                GivenIHaveRequestForGetBookingIds(10,12).
                WhenIRetrieveBookingIds().
                ThenISeeHotelListIsRetrieved().
                ThenIVerifyNoBookingIdIsPresent();
    }

    @Test
    @DisplayName("Create Booking and Get booking ids - Filter by Names and Dates")
    void testCreateBookingAndVerifyGetBookingIdsWithNamesAndDates() {
        bookingsSteps.
                GivenIHaveCreatedABooking(GUEST_DEPOSIT_PAID,10,12).
                GivenIHaveRequestForGetBookingIds(10,12).
                WhenIRetrieveBookingIds().
                ThenISeeHotelListIsRetrieved().
                ThenIVerifyNoBookingIdIsPresent();
    }

    @Test
    @DisplayName("Create Booking and Get booking ids - Filter by Wrong name")
    void testCreateBookingAndVerifyGetBookingIdsWithWrongName() {
        bookingsSteps.
                GivenIHaveCreatedABooking(GUEST_DEPOSIT_PAID,10,12).
                GivenIHaveRequestForGetBookingIds(10,12).
                WhenIRetrieveBookingIds().
                ThenISeeHotelListIsRetrieved().
                ThenIVerifyNoBookingIdIsPresent();
    }


    /**
     * Regression Scenario 3 : Create Booking Service, Get Booking Details and Delete booking.
     **/


    @Test
    @DisplayName("Create booking and Get Booking Details and verify")
    void testGetBookingDetails() {
        bookingsSteps.
                GivenIHaveCreatedABooking(GUEST_DEPOSIT_PAID,10,12).
                GivenIHaveRequestForGetBooking().
                WhenIRetrieveGetBookingDetails().
                ThenICanRetrieveBookingDetails();
    }

    @Test
    @DisplayName("Create booking and Get Booking Details and verify names")
    void testGetBookingDetailsAndVerifyNames() {
        bookingsSteps.
                GivenIHaveCreatedABooking(GUEST_DEPOSIT_PAID,10,12).
                GivenIHaveRequestForGetBooking().
                WhenIRetrieveGetBookingDetails().
                ThenICanRetrieveBookingDetails().
                ThenIVerifyGetBookingNames(GUEST_DEPOSIT_PAID);
    }

    @Test
    @DisplayName("Create booking and Get Booking Details and verify no deposit paid")
    void testGetBookingDetailsAndVerifyNoDeposit() {
        bookingsSteps.
                GivenIHaveCreatedABooking(GUEST_NO_DEPOSIT_PAID,10,12).
                GivenIHaveRequestForGetBooking().
                WhenIRetrieveGetBookingDetails().
                ThenICanRetrieveBookingDetails().
                ThenIVerifyGetBookingNames(GUEST_NO_DEPOSIT_PAID);
    }

    @Test
    @DisplayName("Create booking and Get Booking Details and verify price")
    void testGetBookingDetailsAndVerifyPrice() {
        bookingsSteps.
                GivenIHaveCreatedABooking(GUEST_DEPOSIT_PAID,10,12).
                GivenIHaveRequestForGetBooking().
                WhenIRetrieveGetBookingDetails().
                ThenICanRetrieveBookingDetails().
                ThenIVerifyGetBookingTotalPrice(GUEST_DEPOSIT_PAID);
    }



    @Test
    @DisplayName("Create booking and Get Booking Details and verify deposit paid")
    void testGetBookingDetailsAndVerifyDepositPaid() {
        bookingsSteps.
                GivenIHaveCreatedABooking(GUEST_DEPOSIT_PAID,10,12).
                GivenIHaveRequestForGetBooking().
                WhenIRetrieveGetBookingDetails().
                ThenICanRetrieveBookingDetails().
                ThenIVerifyGetBookingDepositPaid(GUEST_DEPOSIT_PAID);
    }

    @Test
    @DisplayName("Create booking and Get Booking Details and verify booking dates")
    void testGetBookingDetailsAndVerifyDates() {
        bookingsSteps.
                GivenIHaveCreatedABooking(GUEST_DEPOSIT_PAID,10,12).
                GivenIHaveRequestForGetBooking().
                WhenIRetrieveGetBookingDetails().
                ThenICanRetrieveBookingDetails().
                ThenIVerifyGetBookingDates(10,12);
    }

    @Test
    @DisplayName("Create booking and Get Booking Details and verify additional need")
    void testGetBookingDetailsAndVerifyAdditionalNeed() {
        bookingsSteps.
                GivenIHaveCreatedABooking(GUEST_ADDITIONAL_NEEDS,10,12).
                GivenIHaveRequestForGetBooking().
                WhenIRetrieveGetBookingDetails().
                ThenICanRetrieveBookingDetails().
                ThenIVerifyGetBookingAdditionalNeeds(GUEST_ADDITIONAL_NEEDS);
    }


    /**
     * Regression Scenario 4 : Create Booking Service, Update and Get Booking Details and Delete booking.
     **/

    @Test
    @DisplayName("Create booking ,Update booking with No access token and verify")
    void testUpdateBookingAndVerifyWithoutAccessToken() {
        bookingsSteps.
                GivenIHaveCreatedABooking(GUEST_ADDITIONAL_NEEDS,10,12).
                GivenIRequestToUpdateBookingWithoutAccessToken(GUEST_DEPOSIT_PAID,12,14).
                WhenIUpdateBooking().
                ThenIVerifyErrorCode(FORBIDDEN);
    }

    @Test
    @DisplayName("Create booking ,Update booking with access token and verify")
    void testUpdateBookingAndVerifyWithAccessToken() {
        bookingsSteps.
                GivenIHaveCreatedABooking(GUEST_ADDITIONAL_NEEDS,10,12).
                GivenIRequestToUpdateBooking(GUEST_DEPOSIT_PAID,12,14).
                WhenIUpdateBooking().
                ThenIVerifyBookingIsUpdated();
    }

    @Test
    @DisplayName("Create booking ,Update booking , get booking and verify names")
    void testUpdateBookingAndVerifyNames() {
        bookingsSteps.
                GivenIHaveCreatedABooking(GUEST_ADDITIONAL_NEEDS,10,12).
                GivenIHaveUpdatedABooking(GUEST_DEPOSIT_PAID,12,14).
                AndIGetBookingDetails().
                ThenIVerifyGetBookingNames(GUEST_DEPOSIT_PAID);
    }

    @Test
    @DisplayName("Create booking ,Update booking , get booking and verify no deposit Paid")
    void testUpdateBookingAndVerifyNoDepositPaid() {
        bookingsSteps.
                GivenIHaveCreatedABooking(GUEST_ADDITIONAL_NEEDS,10,12).
                GivenIHaveUpdatedABooking(GUEST_DEPOSIT_PAID,12,14).
                AndIGetBookingDetails().
                ThenIVerifyGetBookingNames(GUEST_DEPOSIT_PAID);
    }

    @Test
    @DisplayName("Create booking ,Update booking , get booking and verify price updated")
    void testUpdateBookingAndVerifyPrice() {
        bookingsSteps.
                GivenIHaveCreatedABooking(GUEST_ADDITIONAL_NEEDS,10,12).
                GivenIHaveUpdatedABooking(GUEST_DEPOSIT_PAID,12,14).
                AndIGetBookingDetails().
                ThenIVerifyGetBookingTotalPrice(GUEST_DEPOSIT_PAID);
    }

    @Test
    @DisplayName("Create booking ,Update booking , get booking and verify additional need")
    void testUpdateBookingAndVerifyAdditionalNeeds() {
        bookingsSteps.
                GivenIHaveCreatedABooking(GUEST_DEPOSIT_PAID,10,12).
                GivenIHaveUpdatedABooking(GUEST_ADDITIONAL_NEEDS,12,14).
                AndIGetBookingDetails().
                ThenIVerifyGetBookingAdditionalNeeds(GUEST_ADDITIONAL_NEEDS);
    }

    @Test
    @DisplayName("Create booking ,Update booking , get booking and verify booking dates")
    void testUpdateBookingAndVerifyBookingDates() {
        bookingsSteps.
                GivenIHaveCreatedABooking(GUEST_DEPOSIT_PAID,10,12).
                GivenIHaveUpdatedABooking(GUEST_ADDITIONAL_NEEDS,12,14).
                AndIGetBookingDetails().
                ThenIVerifyGetBookingDates(12,14);
    }

    /**
     * Regression Scenario 5 : Create Booking Service, Partial Update and Get Booking Details and Delete booking.
     **/


    @Test
    @DisplayName("Create booking ,Partial Update booking with No access token and verify")
    void testPartialUpdateBookingAndVerifyWithoutAccessToken() {
        bookingsSteps.
                GivenIHaveCreatedABooking(GUEST_ADDITIONAL_NEEDS,10,12).
                GivenIRequestToPatchBookingWithoutAccessToken(GUEST_DEPOSIT_PAID).
                WhenIPartialUpdateBooking().
                ThenIVerifyErrorCode(FORBIDDEN);
    }

    @Test
    @DisplayName("Create booking ,Partial Update booking with access token and verify")
    void testPartialUpdateBookingAndVerifyWithAccessToken() {
        bookingsSteps.
                GivenIHaveCreatedABooking(GUEST_ADDITIONAL_NEEDS,10,12).
                GivenIRequestToPatchBookingForNames(GUEST_DEPOSIT_PAID).
                WhenIPartialUpdateBooking().
                ThenIVerifyBookingIsPartiallyUpdated();
    }

    @Test
    @DisplayName("Create booking ,Partial Update Names booking with access token and verify names")
    void testPartialUpdateBookingAndVerifyNames() {
        bookingsSteps.
                GivenIHaveCreatedABooking(GUEST_ADDITIONAL_NEEDS,10,12).
                GivenIRequestToPatchBookingForNames(GUEST_FULL_NAME_PRICE_200).
                WhenIPartialUpdateBooking().
                ThenIVerifyBookingIsPartiallyUpdated().
                AndIGetBookingDetails().
                ThenIVerifyGetBookingNames(GUEST_FULL_NAME_PRICE_200);
    }

    @Test
    @DisplayName("Create booking ,Partial Update price booking with access token and verify price")
    void testPartialUpdateBookingAndVerifyPrice() {
        bookingsSteps.
                GivenIHaveCreatedABooking(GUEST_ADDITIONAL_NEEDS,10,12).
                GivenIRequestToPatchBookingForPrice(GUEST_FULL_NAME_PRICE_200).
                WhenIPartialUpdateBooking().
                ThenIVerifyBookingIsPartiallyUpdated().
                AndIGetBookingDetails().
                ThenIVerifyGetBookingTotalPrice(GUEST_FULL_NAME_PRICE_200);
    }

    @Test
    @DisplayName("Create booking ,Partial Update price booking with access token and verify additional")
    void testPartialUpdateBookingAndVerifyAdditionalNeeds() {
        bookingsSteps.
                GivenIHaveCreatedABooking(GUEST_ADDITIONAL_NEEDS,10,12).
                GivenIRequestToPatchBookingForPrice(GUEST_FULL_NAME_PRICE_200).
                WhenIPartialUpdateBooking().
                ThenIVerifyBookingIsPartiallyUpdated().
                AndIGetBookingDetails().
                ThenIVerifyGetBookingTotalPrice(GUEST_FULL_NAME_PRICE_200).
                ThenIVerifyCreateBookingAdditionalNeeds(GUEST_ADDITIONAL_NEEDS);
    }

    @Test
    @DisplayName("Create booking ,Partial Update price booking with access token and verify no deposit add")
    void testPartialUpdateBookingAndVerifyNoDepositPaid() {
        bookingsSteps.
                GivenIHaveCreatedABooking(GUEST_NO_DEPOSIT_PAID,10,12).
                GivenIRequestToPatchBookingForPrice(GUEST_FULL_NAME_PRICE_200).
                WhenIPartialUpdateBooking().
                ThenIVerifyBookingIsPartiallyUpdated().
                AndIGetBookingDetails().
                ThenIVerifyGetBookingTotalPrice(GUEST_FULL_NAME_PRICE_200).
                ThenIVerifyGetBookingDepositPaid(GUEST_NO_DEPOSIT_PAID);
    }

    @Test
    @DisplayName("Create booking ,Partial Update price booking with access token and verify dates")
    void testPartialUpdateBookingAndVerifyDates() {
        bookingsSteps.
                GivenIHaveCreatedABooking(GUEST_NO_DEPOSIT_PAID,10,12).
                GivenIRequestToPatchBookingForPrice(GUEST_FULL_NAME_PRICE_200).
                WhenIPartialUpdateBooking().
                ThenIVerifyBookingIsPartiallyUpdated().
                AndIGetBookingDetails().
                ThenIVerifyGetBookingTotalPrice(GUEST_FULL_NAME_PRICE_200).
                ThenIVerifyGetBookingDates(10,12);
    }


    /**
     * Regression Scenario 6 : End to end -Create Booking Service, Update , Partial Update
     * and Get Booking Details and Delete booking.
     **/


    @Test
    @DisplayName("End to End")
    void testEndToEndTest() {
        bookingsSteps.
                GivenIHaveCreatedABooking(GUEST_DEPOSIT_PAID,10,12).
                AndIRetrieveAllBookingIds().
                ThenIVerifyBookingIdIsPresent().
                GivenIHaveUpdatedABooking(GUEST_ADDITIONAL_NEEDS,12,14).
                AndIGetBookingDetails().
                ThenIVerifyGetBookingAdditionalNeeds(GUEST_ADDITIONAL_NEEDS);
    }

}