package com.jpn.lipo.excercise.lipoexcercise.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jpn.lipo.excercise.lipoexcercise.pojo.CurrentPrice;
import com.jpn.lipo.excercise.lipoexcercise.repository.CurrentPriceRepository;
import com.jpn.lipo.excercise.lipoexcercise.service.CurrentPriceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

@RestController
public class CurrentPriceController {

    private static final Logger logger = LogManager.getLogger(CurrentPriceController.class);

    @Autowired
    CurrentPriceService currentPriceService;

    @Autowired
    CurrentPriceRepository currentPriceRepository;

    @GetMapping(value = "/healthcheck", produces = "application/json; charset=utf-8")
    public String getHealthCheck()
    {
        return "{ \"isWorking\" : true }";
    }

    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public ResponseEntity<?> query() {
        try {
            logger.info("Pasando por el Controller #1");

            String jsonResponse = currentPriceService.getCurrentPrice();
            logger.info("Controller despues de buscar el currentPrice");
            setCurrentPriceToRedis(jsonResponse);
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);

        }catch (Exception e) {
            logger.error(e);
            new ResponseEntity<>(e.getMessage(),HttpStatus.NO_CONTENT) ;
        }
        return new ResponseEntity<>(new CurrentPrice(), HttpStatus.NOT_FOUND);

    }

    private void setCurrentPriceToRedis(String currentPrice) {
        currentPriceRepository.add("CurrentPrice", currentPrice);
        logger.info("Set current price to Redis");
    }

    @GetMapping(value = "/currentpriceCacheable", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> cacheable() {
        try {
            logger.info("Pasando por el Controller Cacheable");

            Gson gson = new GsonBuilder().create();
            CurrentPrice currentPrice = gson.fromJson(currentPriceRepository.getBy("CurrentPrice")
                    , CurrentPrice.class);
            logger.info("Vuevlo a pasar...");

            return new ResponseEntity<>(currentPrice, HttpStatus.OK);

        }catch (Exception e) {
            logger.error(e);
            new ResponseEntity<>(e.getMessage(),HttpStatus.NO_CONTENT) ;
        }
        return new ResponseEntity<>(new CurrentPrice(), HttpStatus.NOT_FOUND);

    }

}
