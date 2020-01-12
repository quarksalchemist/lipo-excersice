package com.jpn.lipo.excercise.lipoexcercise.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;



@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@RedisHash("CurrentPrice")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentPrice implements Serializable{

//    @Id
//    private int id;
    private Time time;
    private String disclaimer;
    private String chartName;
    private Bpi bpi;


}
