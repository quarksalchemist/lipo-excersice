package com.jpn.lipo.excercise.lipoexcercise.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Bpi implements Serializable {

    private CurrentDetail USD;
    private CurrentDetail GBP;
    private CurrentDetail EUR;



}
