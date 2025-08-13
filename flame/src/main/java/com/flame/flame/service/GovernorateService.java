package com.flame.flame.service;


import com.flame.flame.model.GovernorateModel;
import com.flame.flame.repository.GovernorateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GovernorateService {
    @Autowired
    private GovernorateRepository governorateRepository;

    public List<GovernorateModel> getAllGovernorates() {
        return governorateRepository.findAll();
    }

    public GovernorateModel getGovernorateById(int id) {
        return governorateRepository.findById(id).orElse(null);
    }
}
