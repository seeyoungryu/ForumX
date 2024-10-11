package com.example.forumx.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/*
@WebMvcTest : RESTful API의 동작을 확인할 때 유용, 슬라이스 테스트임
슬라이스 테스트: 특정 레이어만 테스트하는 방식,컨트롤러 레이어만 테스트하는 테스트를 의미(컨트롤러 외에 Bean 들을 로드하지 않음)
-> 서비스나 리포지토리 같은 레이어는 제외하고 <웹 레이어>만 테스트
 */
@WebMvcTest
public class DataRest {

    private final MockMvc mvc;
    /*
    MockMvc: 웹 레이어 테스트를 할 때 실제 서버를 실행하지 않고 가상의 요청을 보낼 수 있게 해주는 도구
             MockMvc를 사용하면 서버를 띄우지 않고도 HTTP 요청을 테스트할 수 있어 빠르고 효율적
     */

    public DataRest(@Autowired MockMvc mvc) { //
        this.mvc = mvc;
    }
    //@Autowired: <MockMvc 객체>를 자동으로 스프링이 주입함, 직접 객체를 만들지 않아도 됨


    @Test
    void test() throws Exception {
        //Given

        //When , Then
        mvc.perform(get("/api.articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

    /*
    - mvc.perform(get("/api.articles")) : MockMvc를 통해 /api.articles라는 엔드포인트에 GET 요청을 보내는 코드
    이는 REST API 요청을 가상으로 보내고 그 결과를 검증
    - andExpect(status().isOk()): 서버 응답 상태가 200 OK 인지 확인,  API 호출이 정상적으로 이루어졌는지 체크
    - andExpect(content().contentType(MediaType.valueOf("application/hal+json"))):
      응답 콘텐츠 타입이 application/hal+json인지 확인, REST API에서 링크를 포함한 응답을 구조화하는 방식
     */



    /*
    @Autowired 관련 - 객체 생성시의 편리함(스프링이 자동으로 주입)
    -> 생성자에 @Autowired 없이  객체 생성 시, MockMvc 객체를 생성, 수동으로 DataRest 의 생성자에 전달해야 함
    (직접전달의 예시 :
        MockMvc mockMvc = new MockMvc();
        DataRest dataRest = new DataRest(mockMvc); // 개발자가 직접 전달)

    결론: Spring 환경에서 자동으로 `MockMvc` 같은 객체를 관리하고 싶다면, `@Autowired`를 사용한 방식이 더 적합
     */

}
