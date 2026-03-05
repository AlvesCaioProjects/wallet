package com.bank_service.wallet.dto;

import com.bank_service.wallet.model.Account;
import com.bank_service.wallet.model.Status;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class AccountDTO {

    private UUID id;
    private BigDecimal amount;
    private Status status;

    public AccountDTO(Account entity) {
        BeanUtils.copyProperties(entity, this, "createdAt", "userId");
    }
}