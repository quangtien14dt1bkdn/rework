package com.example.demo.Services.Impl.contract;

import com.example.demo.Repository.contract.ContractRepository;
import com.example.demo.Services.contract.ContractService;
import com.example.demo.models.contractWar.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    ContractRepository contractRepository;

    @Override
    public Page<Contract> getAllContract(Pageable pageable) {
        return contractRepository.findAll(pageable);
    }

    @Override
    public Contract getContractById(Long id) {
        return contractRepository.findById(id).orElse(null);
    }

    @Override
    public void saveContract(Contract Contract) {
        contractRepository.save(Contract);
    }

    @Override
    public void deleteContractById(Long id) {
        contractRepository.deleteById(id);
    }
}
