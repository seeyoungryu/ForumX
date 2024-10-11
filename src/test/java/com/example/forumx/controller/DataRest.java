package com.example.forumx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public class DataRest {

    private final MockMvc mvc;


    public DataRest(@Autowired MockMvc mvc) { //
        this.mvc = mvc;
    }


    /*
    @Autowired 관련 - 객체 생성시의 편리함(스프링이 자동으로 주입)
    -> 생성자에 @Autowired 없이  객체 생성 시, MockMvc 객체를 생성, 수동으로 DataRest 의 생성자에 전달해야 함
    (직접전달의 예시 :
        MockMvc mockMvc = new MockMvc();
        DataRest dataRest = new DataRest(mockMvc); // 개발자가 직접 전달)

    결론: Spring 환경에서 자동으로 `MockMvc` 같은 객체를 관리하고 싶다면, `@Autowired`를 사용한 방식이 더 적합합니다.
     */

}
