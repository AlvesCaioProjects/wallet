package com.bank_service.wallet.service;

import com.bank_service.wallet.dto.AccountDTO;
import com.bank_service.wallet.model.Account;
import com.bank_service.wallet.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public AccountDTO getAccount(UUID id) {
        Account acc = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        return new AccountDTO(acc);
    }

    @Transactional
    public AccountDTO deposit(UUID id, BigDecimal value) {
        Account acc = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        acc.setBalance(acc.getBalance().add(value));
        accountRepository.save(acc);
        return new AccountDTO(acc);
    }

    @Transactional
    public AccountDTO withdraw(UUID id, BigDecimal value) {
        Account acc = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        acc.setBalance(acc.getBalance().subtract(value));
        accountRepository.save(acc);
        return new AccountDTO(acc);
    }

    @Transactional
    public AccountDTO transfer(UUID accountSender, UUID accountReceiver, BigDecimal value) {
        Account accSender = accountRepository.findById(accountSender)
                .orElseThrow(() -> new RuntimeException("Conta de origem não encontrada"));
        Account accReceiver = accountRepository.findById(accountReceiver)
                .orElseThrow(() -> new RuntimeException("Conta de destino não encontrada"));
        accSender.setBalance(accSender.getBalance().subtract(value));
        accReceiver.setBalance(accReceiver.getBalance().add(value));
        accountRepository.save(accSender);
        accountRepository.save(accReceiver);
        return new AccountDTO(accSender);
    }
}
