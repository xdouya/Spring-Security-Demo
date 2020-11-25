package org.dy.security05;

import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author caiwl
 * @date 2020/9/8 22:17
 */
class RestTemplateTest {

    @Test
    void testRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/login";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        Map<String, String> body = new HashMap<>();
        body.put("username", "admin");
        body.put("password", "088114");
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(body, httpHeaders);
        ResponseEntity<Map> result = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Map.class);
        if (result.getStatusCode() == HttpStatus.OK){
            String token = (String) Objects.requireNonNull(result.getBody()).get("token");
            url = "http://localhost:8080/admin/hello";
            HttpHeaders httpHelloHeaders = new HttpHeaders();
            httpHelloHeaders.set("X-Access_Token",token);
            HttpEntity<String> helloRequestEntity = new HttpEntity<>(null, httpHelloHeaders);
            ResponseEntity<String> helloResult = restTemplate.exchange(url, HttpMethod.GET, helloRequestEntity, String.class);
            if(helloResult.getStatusCode() == HttpStatus.OK){
                System.out.println(helloResult.getBody());
            }
        }
    }
}
