package com.flame.flame.repository;

import com.flame.flame.model.GovernorateModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GovernorateRepository extends JpaRepository<GovernorateModel,Integer> {
    GovernorateModel findGovernorateModelByName(String name);
}
