package com.bank_service.wallet.controller;

import com.bank_service.wallet.dto.AccountDTO;
import com.bank_service.wallet.dto.OperationDTO;
import com.bank_service.wallet.dto.TransferDTO;
import com.bank_service.wallet.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/wallet")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable UUID id) {
        return ResponseEntity.ok(accountService.getAccount(id));
    }

    @PatchMapping(value = "/{id}/deposit")
    public ResponseEntity<AccountDTO> deposit(@PathVariable UUID id, @RequestBody OperationDTO operation) {
        AccountDTO accountDTO = accountService.deposit(id, operation.getValue());
        return ResponseEntity.ok(accountDTO);
    }

    @PatchMapping(value = "/{id}/withdraw")
    public ResponseEntity<AccountDTO> withdraw(@PathVariable UUID id, @RequestBody OperationDTO operation) {
        AccountDTO accountDTO = accountService.withdraw(id, operation.getValue());
        return ResponseEntity.ok(accountDTO);
    }

    @PatchMapping(value = "/transfer")
    public ResponseEntity<AccountDTO> transfer(@RequestBody TransferDTO transfer) {
        AccountDTO accountDTO = accountService
                .transfer(transfer.getAccountSender(), transfer.getAccountReceiver(), transfer.getValue());
        return ResponseEntity.ok(accountDTO);
    }
}
