package org.microservices.clients.fraud;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FraudCheckResponse {
    private boolean isFraudster;
}
