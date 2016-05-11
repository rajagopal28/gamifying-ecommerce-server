package com.dxtrs.hack.gamify.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDTO {
    private long productId;
    private long quantity;
    private long userId;
    private String promotionCode;
}
