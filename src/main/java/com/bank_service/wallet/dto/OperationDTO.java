package com.bank_service.wallet.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class OperationDTO {

    private UUID accountId;
    private BigDecimal value;
}
