package com.jpn.lipo.excercise.lipoexcercise.pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrentPriceTest {

    @Test
    void parseCurrentPriceWithGsonFromJson() throws JsonProcessingException {
        String jsonString = "{\"time\":{\"updated\":\"Jan 11, 2020 16:06:00 UTC\",\"updatedISO\":\"2020-01-11T16:06:00+00:00\",\"updateduk\":\"Jan 11, 2020 at 16:06 GMT\"},\"disclaimer\":\"This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org\",\"chartName\":\"Bitcoin\",\"bpi\":{\"USD\":{\"code\":\"USD\",\"symbol\":\"&#36;\",\"rate\":\"8,073.2917\",\"description\":\"United States Dollar\",\"rate_float\":8073.2917},\"GBP\":{\"code\":\"GBP\",\"symbol\":\"&pound;\",\"rate\":\"6,179.3297\",\"description\":\"British Pound Sterling\",\"rate_float\":6179.3297},\"EUR\":{\"code\":\"EUR\",\"symbol\":\"&euro;\",\"rate\":\"7,259.5039\",\"description\":\"Euro\",\"rate_float\":7259.5039}}}";
        Gson g = new Gson();
        CurrentPrice currentPrice = g.fromJson(jsonString, CurrentPrice.class);
        assertNotNull(currentPrice);
    }

    @Test
    void parseCurrentPriceWithGsonFromCurrenPriceClass() throws JsonProcessingException {
        String jsonString = "{\"time\":{\"updated\":\"Jan 11, 2020 16:06:00 UTC\",\"updatedISO\":\"2020-01-11T16:06:00+00:00\",\"updateduk\":\"Jan 11, 2020 at 16:06 GMT\"},\"disclaimer\":\"This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org\",\"chartName\":\"Bitcoin\",\"bpi\":{\"USD\":{\"code\":\"USD\",\"symbol\":\"&#36;\",\"rate\":\"8,073.2917\",\"description\":\"United States Dollar\",\"rate_float\":8073.2917},\"GBP\":{\"code\":\"GBP\",\"symbol\":\"&pound;\",\"rate\":\"6,179.3297\",\"description\":\"British Pound Sterling\",\"rate_float\":6179.3297},\"EUR\":{\"code\":\"EUR\",\"symbol\":\"&euro;\",\"rate\":\"7,259.5039\",\"description\":\"Euro\",\"rate_float\":7259.5039}}}";
        Gson g = new Gson();
        CurrentPrice currentPrice = g.fromJson(jsonString, CurrentPrice.class);
        String newJsonString = g.toJson(currentPrice);
        assertNotNull(newJsonString);
    }


}