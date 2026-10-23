package com.eventplatform.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderPayload {
    private String orderId;

    private String customerId;

    private BigDecimal amount;

    private String currency;

}
