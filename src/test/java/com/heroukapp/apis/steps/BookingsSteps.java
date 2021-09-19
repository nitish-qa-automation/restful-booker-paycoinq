package com.heroukapp.apis.steps;

import com.heroukapp.apis.data.PatchBookingPayload;
import com.heroukapp.apis.data.CreateTokenPayload;
import com.heroukapp.apis.data.GuestDetails;
import com.heroukapp.apis.data.LoginDetails;
import com.heroukapp.apis.data.createBookingPayload.GeneratePayload;
import com.heroukapp.apis.expectations.ErrorExpectations;
import com.heroukapp.apis.responseproviders.CreateBookingProvider;
import com.heroukapp.apis.responseproviders.CreateTokenProvider;
import com.heroukapp.apis.responseproviders.GetBookingIdsProvider;
import com.heroukapp.apis.responseproviders.GetBookingProvider;
import io.qameta.allure.Step;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingsSteps extends BaseSteps<BookingsSteps> {

    private String endpoint;
    private String uriPath;
    private Response response;
    private String data;
    private String patchData;
    private int bookingId;
    public static String token;
    private GetBookingIdsProvider getBookingIdsProvider = new GetBookingIdsProvider();
    private CreateTokenProvider createTokenProvider = new CreateTokenProvider();
    private CreateBookingProvider createBookingProvider = new CreateBookingProvider();
    private GetBookingProvider getBookingProvider = new GetBookingProvider();

    @Autowired
    GeneratePayload generatePayload;
    @Autowired
    CreateTokenPayload createToken;
    @Autowired
    PatchBookingPayload createBookingPayload;


    @Step
    public BookingsSteps GivenIHaveRequestForGetBookingIds(){
        headerUtil.setDefaultHeaders();
        endpoint = endPoints.getBookingURI().get();
        uriPath = uriBuilder.buildURI(endpoint,queryParamUtil.getQueryParams());
        addAllureLogs("GetBookingIds Request" , endpoint);
        return this;
    }

    @Step
    public BookingsSteps GivenIHaveRequestForGetBookingIds(GuestDetails guestDetails){
        queryParamUtil.addQueryParam("firstname", guestDetails.getFirstName());
        queryParamUtil.addQueryParam("lastname", guestDetails.getLastName());
        headerUtil.setDefaultHeaders();
        endpoint = endPoints.getBookingURI().get();
        uriPath = uriBuilder.buildURI(endpoint,queryParamUtil.getQueryParams());
        addAllureLogs("GetBookingIds Request" , uriPath);
        return this;
    }

    @Step
    public BookingsSteps GivenIHaveRequestForGetBookingIds( int checkInDaysFromToday , int checkOutDaysFromToday){
        queryParamUtil.addQueryParam("checkin", testUtil.getFutureDateInFormat("yyyy-MM-dd",checkInDaysFromToday));
        queryParamUtil.addQueryParam("checkout", testUtil.getFutureDateInFormat("yyyy-MM-dd",checkOutDaysFromToday));
        headerUtil.setDefaultHeaders();
        endpoint = endPoints.getBookingURI().get();
        uriPath = uriBuilder.buildURI(endpoint,queryParamUtil.getQueryParams());
        addAllureLogs("GetBookingIds Request" , uriPath);
        return this;
    }

    @Step
    public BookingsSteps GivenIHaveRequestForGetBookingIds( int checkInDaysFromToday ){
        queryParamUtil.addQueryParam("checkin", testUtil.getFutureDateInFormat("yyyy-MM-dd",checkInDaysFromToday));
        headerUtil.setDefaultHeaders();
        endpoint = endPoints.getBookingURI().get();
        uriPath = uriBuilder.buildURI(endpoint,queryParamUtil.getQueryParams());
        addAllureLogs("GetBookingIds Request" , uriPath);
        return this;
    }


    @Step
    public BookingsSteps WhenIRetrieveBookingIds(){
        response = commonCalls.get(headerUtil.getHeaders(),uriPath);
        addAllureLogs("GetBookingIds Response", response.asString());
        return this;
    }

    @Step
    public BookingsSteps ThenISeeHotelListIsRetrieved(){
        assertThat.responseStatusIs(response, 200);
        getBookingIdsProvider.setGetBookingIdsResponse(response);
        System.out.println(getBookingIdsProvider.getBookingIds());
        return this;
    }

    @Step
    public BookingsSteps AndIRetrieveAllBookingIds(){
        GivenIHaveRequestForGetBookingIds();
        WhenIRetrieveBookingIds();
        ThenISeeHotelListIsRetrieved();
        return this;
    }

    @Step
    public BookingsSteps ThenIVerifyBookingIdIsPresent(){
        Assert.assertTrue("Booking id is not present",
                getBookingIdsProvider.getBookingIds().contains(bookingId));
        return this;
    }

    @Step
    public BookingsSteps ThenIVerifyNoBookingIdIsPresent(){
        Assert.assertEquals(getBookingIdsProvider.getBookingIds().size(),0);
        return this;
    }


    public BookingsSteps GivenIHaveAccessToken(){
        headerUtil.setDefaultHeaders();
        endpoint = endPoints.createTokenURI.get();
        response = commonCalls.post(createToken.getCreateTokenRequestData(LoginDetails.ADMIN).toString(),
                endpoint,headerUtil.getHeaders());
        assertThat.responseStatusIs(response,200);
        createTokenProvider.setCreateTokenResponse(response);
        token = createTokenProvider.getToken();
        System.out.println(token);
        return this;

    }


    @Step
    public BookingsSteps GivenIRequestToCreateBooking(GuestDetails guestDetails, int checkInDaysFromToday , int checkOutDaysFromToday){
        headerUtil.setDefaultHeaders();
        endpoint = endPoints.getBookingURI().get();
        uriPath = uriBuilder.buildURI(endpoint,queryParamUtil.getQueryParams());
        data = generatePayload.getCreateBookingPayload(guestDetails,checkInDaysFromToday,checkOutDaysFromToday);
        addAllureLogs("Create Booking Request" , endpoint);
        addAllureLogs("Payload" , data);
        return this;
    }

    @Step
    public BookingsSteps WhenICreateBooking(){
        response = commonCalls.post(data,endpoint,headerUtil.getHeaders());
        addAllureLogs("Create Booking  Response", response.asString());
        return this;
    }

    @Step
    public BookingsSteps ThenIVerifyBookingIsCreated(){
        assertThat.responseStatusIs(response, 200);
        createBookingProvider.setCreateBookingResponse(response);
        bookingId = createBookingProvider.getBookingId();
        return this;
    }

    @Step
    public BookingsSteps GivenIHaveCreatedABooking(GuestDetails guestDetails, int checkInDaysFromToday , int checkOutDaysFromToday){
        GivenIRequestToCreateBooking(guestDetails,checkInDaysFromToday,checkOutDaysFromToday);
        WhenICreateBooking();
        ThenIVerifyBookingIsCreated();
        return this;
    }
    @Step
    public BookingsSteps ThenIVerifyBookingIDIsNotNull(){
        assertThat.fieldDataIsNotEmpty(bookingId);
        addAllureLogs("Booking id", String.valueOf(bookingId));
        return this;
    }

    @Step
    public BookingsSteps ThenIVerifyCreateBookingNames(GuestDetails guestDetails){
        assertThat.fieldIsEqual(guestDetails.getFirstName(), createBookingProvider.getFirstName());
        assertThat.fieldIsEqual(guestDetails.getLastName(), createBookingProvider.getLastName());
        addAllureLogs("First name",createBookingProvider.getFirstName() );
        addAllureLogs("Last name", createBookingProvider.getLastName());
        return this;
    }

    @Step
    public BookingsSteps ThenIVerifyCreateBookingTotalPrice(GuestDetails guestDetails){
        assertThat.fieldIsEqual(guestDetails.getTotalPrice(), createBookingProvider.getTotalPrice());
        addAllureLogs("Price", String.valueOf(createBookingProvider.getTotalPrice()));
        return this;
    }

    @Step
    public BookingsSteps ThenIVerifyCreateBookingDepositPaid(GuestDetails guestDetails){
        assertThat.fieldIsEqual(guestDetails.getDepositPaid(), createBookingProvider.getDepositPaid());
        return this;
    }

    @Step
    public BookingsSteps ThenIVerifyCreateBookingAdditionalNeeds(GuestDetails guestDetails){
        assertThat.fieldIsEqual(guestDetails.getNeeds(), createBookingProvider.getAdditionalNeeds());
        return this;
    }

    @Step
    public BookingsSteps ThenIVerifyCreateBookingDates( int checkInDaysFromToday , int checkOutDaysFromToday ){
        assertThat.fieldIsEqual(testUtil.getFutureDateInFormat("yyyy-MM-dd",checkInDaysFromToday),
               createBookingProvider.getBookingDates().getCheckin() );
        assertThat.fieldIsEqual(testUtil.getFutureDateInFormat("yyyy-MM-dd",checkOutDaysFromToday),
                createBookingProvider.getBookingDates().getCheckout() );
        return this;
    }

    @Step
    public BookingsSteps AndIDeleteBooking(){
        headerUtil.defaultHeaders.add(new Header("Cookie", "token="+token));
        endpoint = endPoints.bookingURI.get() + "/" + bookingId;
        response = commonCalls.delete(endpoint,headerUtil.getHeaders());
        assertThat.responseStatusIs(response,201);
        return this;
    }


    @Step
    public BookingsSteps GivenIHaveRequestForGetBooking(){
        headerUtil.setDefaultHeaders();
        endpoint = endPoints.getBookingURI().get() + "/" +bookingId;
        addAllureLogs("GetBooking Request" , endpoint);
        return this;
    }

    @Step
    public BookingsSteps WhenIRetrieveGetBookingDetails(){
        response = commonCalls.get(headerUtil.getHeaders(),endpoint);
        addAllureLogs("GetBooking Response", response.asString());
        return this;
    }

    @Step
    public BookingsSteps ThenICanRetrieveBookingDetails(){
        assertThat.responseStatusIs(response, 200);
        getBookingProvider.setGetBookingResponse(response);
        return this;
    }

    @Step
    public BookingsSteps AndIGetBookingDetails(){
        GivenIHaveRequestForGetBooking();
        WhenIRetrieveGetBookingDetails();
        ThenICanRetrieveBookingDetails();
        return this;
    }

    @Step
    public BookingsSteps ThenIVerifyGetBookingNames(GuestDetails guestDetails){
        assertThat.fieldIsEqual(guestDetails.getFirstName(), getBookingProvider.getFirstName());
        assertThat.fieldIsEqual(guestDetails.getLastName(), getBookingProvider.getLastName());
        addAllureLogs("First name",getBookingProvider.getFirstName() );
        addAllureLogs("Last name", getBookingProvider.getLastName());
        return this;
    }

    @Step
    public BookingsSteps ThenIVerifyGetBookingTotalPrice(GuestDetails guestDetails){
        assertThat.fieldIsEqual(guestDetails.getTotalPrice(), getBookingProvider.getTotalPrice());
        addAllureLogs("Price", String.valueOf(getBookingProvider.getTotalPrice()));
        return this;
    }

    @Step
    public BookingsSteps ThenIVerifyGetBookingDepositPaid(GuestDetails guestDetails){
        assertThat.fieldIsEqual(guestDetails.getDepositPaid(), getBookingProvider.getDepositPaid());
        return this;
    }

    @Step
    public BookingsSteps ThenIVerifyGetBookingAdditionalNeeds(GuestDetails guestDetails){
        assertThat.fieldIsEqual(guestDetails.getNeeds(), getBookingProvider.getAdditionalNeeds());
        return this;
    }

    @Step
    public BookingsSteps ThenIVerifyGetBookingDates( int checkInDaysFromToday , int checkOutDaysFromToday ){
        assertThat.fieldIsEqual(testUtil.getFutureDateInFormat("yyyy-MM-dd",checkInDaysFromToday),
                getBookingProvider.getBookingDates().getCheckin() );
        assertThat.fieldIsEqual(testUtil.getFutureDateInFormat("yyyy-MM-dd",checkOutDaysFromToday),
                getBookingProvider.getBookingDates().getCheckout() );
        return this;
    }

    @Step
    public BookingsSteps GivenIRequestToUpdateBookingWithoutAccessToken(GuestDetails guestDetails, int checkInDaysFromToday , int checkOutDaysFromToday){
        headerUtil.setDefaultHeaders();
        endpoint = endPoints.getBookingURI().get() + "/" + bookingId;
        uriPath = uriBuilder.buildURI(endpoint,queryParamUtil.getQueryParams());
        data = generatePayload.getCreateBookingPayload(guestDetails,checkInDaysFromToday,checkOutDaysFromToday);
        addAllureLogs("Update Booking Request" , endpoint);
        addAllureLogs("Payload" , data);
        return this;
    }

    @Step
    public BookingsSteps GivenIRequestToUpdateBooking(GuestDetails guestDetails, int checkInDaysFromToday , int checkOutDaysFromToday){
        headerUtil.defaultHeaders.add(new Header("Cookie", "token="+token));
        endpoint = endPoints.getBookingURI().get() + "/" + bookingId;
        uriPath = uriBuilder.buildURI(endpoint,queryParamUtil.getQueryParams());
        data = generatePayload.getCreateBookingPayload(guestDetails,checkInDaysFromToday,checkOutDaysFromToday);
        addAllureLogs("Update Booking Request" , endpoint);
        addAllureLogs("Payload" , data);
        return this;
    }
    @Step
    public BookingsSteps WhenIUpdateBooking(){
        response = commonCalls.put(data,endpoint,headerUtil.getHeaders());
        addAllureLogs("Update Booking  Response", response.asString());
        return this;
    }

    @Step
    public BookingsSteps ThenIVerifyBookingIsUpdated(){
        assertThat.responseStatusIs(response, 200);
        return this;
    }


    @Step
    public BookingsSteps GivenIHaveUpdatedABooking(GuestDetails guestDetails, int checkInDaysFromToday , int checkOutDaysFromToday){
        GivenIRequestToUpdateBooking(guestDetails,checkInDaysFromToday,checkOutDaysFromToday);
        WhenIUpdateBooking();
        ThenIVerifyBookingIsUpdated();
        return this;
    }

    @Step
    public BookingsSteps GivenIRequestToPatchBookingWithoutAccessToken(GuestDetails guestDetail){
        headerUtil.setDefaultHeaders();
        endpoint = endPoints.getBookingURI().get() + "/" + bookingId;
        uriPath = uriBuilder.buildURI(endpoint,queryParamUtil.getQueryParams());
        patchData = createBookingPayload.getBookingNamesData(guestDetail).toString();
        System.out.println(patchData);
        addAllureLogs("Patch Booking Request" , endpoint);
        addAllureLogs("Payload" , patchData);
        return this;
    }
    @Step
    public BookingsSteps GivenIRequestToPatchBookingForNames(GuestDetails guestDetail){
        headerUtil.defaultHeaders.add(new Header("Cookie", "token="+token));
        endpoint = endPoints.getBookingURI().get() + "/" + bookingId;
        uriPath = uriBuilder.buildURI(endpoint,queryParamUtil.getQueryParams());
        patchData = createBookingPayload.getBookingNamesData(guestDetail).toString();
        addAllureLogs("Patch Booking Request" , endpoint);
        addAllureLogs("Payload" , patchData);
        System.out.println(patchData);
        return this;
    }

    @Step
    public BookingsSteps GivenIRequestToPatchBookingForPrice(GuestDetails guestDetail){
        headerUtil.defaultHeaders.add(new Header("Cookie", "token="+token));
        endpoint = endPoints.getBookingURI().get() + "/" + bookingId;
        uriPath = uriBuilder.buildURI(endpoint,queryParamUtil.getQueryParams());
        patchData = createBookingPayload.getBookingPriceData(guestDetail).toString();
        addAllureLogs("Patch Booking Request" , endpoint);
        addAllureLogs("Payload" , patchData);
        System.out.println(patchData);
        return this;
    }
    @Step
    public BookingsSteps WhenIPartialUpdateBooking(){
        response = commonCalls.patch(patchData,endpoint,headerUtil.getHeaders());
        addAllureLogs("Partial Update Booking  Response", response.asString());
        return this;
    }

    @Step
    public BookingsSteps ThenIVerifyBookingIsPartiallyUpdated(){
        assertThat.responseStatusIs(response, 200);
        return this;
    }

    @Step
    public BookingsSteps GivenIHavePartiallyUpdatedBookingForPrice(GuestDetails guestDetail){
        GivenIRequestToPatchBookingForPrice(guestDetail);
        WhenIPartialUpdateBooking();
        ThenIVerifyBookingIsPartiallyUpdated();
        return this;
    }

    @Step
    public BookingsSteps ThenIVerifyErrorCode(ErrorExpectations errorExpectations){
        assertThat.responseStatusIs(response, errorExpectations.getErrorCode());
        return this;
    }

}
