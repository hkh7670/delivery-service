package com.example.deliveryservice.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BaseTimeResponse {
    private LocalDateTime regDate;
    private LocalDateTime uptDate;

    public BaseTimeResponse(LocalDateTime regDate, LocalDateTime uptDate) {
        this.regDate = regDate;
        this.uptDate = uptDate;
    }
}
