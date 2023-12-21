package com.laboratory.auth.external.bot;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class WebhookService {
    @Value("${discord.webhookURL}")
    private String url;

    public void callEvent(final Long userId) {
        JSONObject data = new JSONObject();
        data.put("content" , "[작자미상] 📢" + String.valueOf(userId) + "번 째 유저가 가입하였습니다!❗️");
        send(data);
    }

    private void send(final JSONObject object) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<>(object.toString(), httpHeaders);
        restTemplate.postForObject(url, entity, String.class);
    }
}
