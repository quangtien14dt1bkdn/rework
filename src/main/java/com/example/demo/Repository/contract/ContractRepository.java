package com.example.demo.Repository.contract;

import com.example.demo.models.contractWar.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ContractRepository extends PagingAndSortingRepository<Contract, Long> {
//    Page<Contract> findByi(String fullName, Pageable pageable);
}
