package com.eldar.logistica.delivery.services.contracts;

import com.eldar.logistica.delivery.domain.entities.Andreani;


public interface AndreaniService {

    public Andreani findById(Long id);

    public Andreani updateStatus(Long id, String status);


}
