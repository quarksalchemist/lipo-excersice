package com.jpn.lipo.excercise.lipoexcercise.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jpn.lipo.excercise.lipoexcercise.pojo.CurrentPrice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class CurrentPriceService {

    private static final Logger logger = LogManager.getLogger(CurrentPriceService.class);

    public static String getCurrentPrice() throws JsonProcessingException {
        RestTemplate restTemplate = getRestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://api.coindesk.com/v1/bpi/currentprice.json",String.class);
        Gson gson = new GsonBuilder().create();
        //CurrentPrice currentPrice = gson.fromJson(responseEntity.getBody(), CurrentPrice.class);
        logger.info("Cron en Service...");
        return responseEntity.getBody();
    }

    @Cacheable("currentPrice")
    public CurrentPrice getCurrentPriceCacheable()
    {
        try
        {
            System.out.println("Going to sleep for 5 Secs.. to simulate backend call.");
            Thread.sleep(1000*5);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        return new CurrentPrice();
    }

    private static RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
        return restTemplate;
    }



}
