package com.eldar.logistica.delivery.services.impl;

import com.eldar.logistica.delivery.domain.entities.Andreani;
import com.eldar.logistica.delivery.domain.repositories.AndreaniRepository;
import com.eldar.logistica.delivery.services.contracts.AndreaniService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@AllArgsConstructor
@Service
public class AndreaniServiceImpl implements AndreaniService {

    private final AndreaniRepository andreaniRepository;
    private final WebClient.Builder webClientBuilder;



    @Override
    public Andreani findById(Long id) {
        Andreani andreani = andreaniRepository.findById(id).orElse(null);
        if (andreani != null) {
            String status = callAndreaniApi("https://api.andreani.com/status/" + id);
            andreani.setStatus(status);
            andreaniRepository.save(andreani);
        }
        return andreani;
    }


    @Override
    public Andreani updateStatus(Long id, String status) {
        Andreani andreani = andreaniRepository.findById(id).orElse(null);
        if (andreani != null) {
            String apiStatus = callAndreaniApi("https://api.andreani.com/updateStatus/" + id + "?status=" + status);
            andreani.setStatus(apiStatus);
            andreaniRepository.save(andreani);
        }
        return andreani;
    }


    private String callAndreaniApi(String endpoint) {
        WebClient webClient = webClientBuilder.build();
        return webClient.get()
                .uri(endpoint)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}