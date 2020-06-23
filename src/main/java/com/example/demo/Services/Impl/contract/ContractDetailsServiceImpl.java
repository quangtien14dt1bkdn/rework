package com.example.demo.Services.Impl.contract;

import com.example.demo.Repository.contract.ContractDetailsRepository;
import com.example.demo.Services.contract.ContractDetailsService;
import com.example.demo.models.contractWar.ContractDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContractDetailsServiceImpl implements ContractDetailsService {
    @Autowired
    ContractDetailsRepository contractDetailsRepository;

    @Override
    public Page<ContractDetails> getAllContractDetails(Pageable pageable) {
        return null;
    }

    @Override
    public ContractDetails getContractDetailsById(Long id) {
        return null;
    }

    @Override
    public void saveContractDetails(ContractDetails ContractDetails) {

    }

    @Override
    public void deleteContractDetailsById(Long id) {

    }
}
