package com.mimacom.cloud.test.app;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/request", produces = "text/plain")
public class RequestController {
    @RequestMapping(method = RequestMethod.GET)
    public String doRequest(@RequestParam String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, String.class);

        StringBuilder resultBuilder = new StringBuilder("Response code: ").append(response.getStatusCode()).append("\n\n");

        resultBuilder.append("Headers:\n");
        for (Map.Entry<String, List<String>> header : response.getHeaders().entrySet()) {
            resultBuilder.append(header.getKey()).append(": ").append(StringUtils.join(header.getValue(), ", ")).append("\n");
        }

        resultBuilder.append("\n").append("CONTENT:\n").append(response.getBody());
        return resultBuilder.toString();
    }
}