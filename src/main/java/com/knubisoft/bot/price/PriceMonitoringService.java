package com.knubisoft.bot.price;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.knubisoft.bot.config.CryptoConfig;
import com.knubisoft.bot.model.PriceResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceMonitoringService {

    private final CryptoConfig cryptoConfig;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Scheduled(fixedDelayString = "${crypto.prices-update-delay-in-milliseconds}")
    public void checkCryptoPrices() {
        ResponseEntity<String> responseEntity =
                restTemplate.getForEntity(cryptoConfig.getPricesUrl(), String.class);
        List<PriceResponse> priceResponses = objectMapper.readValue(responseEntity.getBody(), new TypeReference<>() {});
        // persist prices to DB
        // process all prices and user session using ExecutorService and notify all users
    }
}
