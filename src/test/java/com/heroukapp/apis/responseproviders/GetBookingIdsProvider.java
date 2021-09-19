package com.heroukapp.apis.responseproviders;

import com.heroukapp.apis.dto.getbookingids.GetBookingIdsResponse;
import io.restassured.response.Response;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Component
public class GetBookingIdsProvider {

    private GetBookingIdsResponse[] getBookingIdsResponse;

    public void setGetBookingIdsResponse(Response response){
        getBookingIdsResponse =response.getBody().as(GetBookingIdsResponse[].class);
    }

    public Integer getTotalBookingIds(){ return getBookingIdsResponse.length;}

    public List<Integer> getBookingIds(){
        List<Integer> bookingIdList = new ArrayList<>();
            Arrays.stream(getBookingIdsResponse).forEach(
                    x -> bookingIdList.add(x.getBookingid())
            );
        return bookingIdList;
   }


}
