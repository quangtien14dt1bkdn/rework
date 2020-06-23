package com.example.demo.Services.contract;


import com.example.demo.models.contractWar.ContractDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContractDetailsService {
    Page<ContractDetails> getAllContractDetails(Pageable pageable);

    ContractDetails getContractDetailsById(Long id);

    void saveContractDetails(ContractDetails ContractDetails);

    void deleteContractDetailsById(Long id);
}
