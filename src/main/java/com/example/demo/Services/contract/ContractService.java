package com.example.demo.Services.contract;

import com.example.demo.models.contractWar.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContractService {
    Page<Contract> getAllContract(Pageable pageable);

    Contract getContractById(Long id);

    void saveContract(Contract Contract);

    void deleteContractById(Long id);
}
