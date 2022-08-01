package ru.mgubin.tbot.service;

import org.springframework.web.client.RestTemplate;

public interface AbstractService {
    RestTemplate restTemplate = new RestTemplate();
}
