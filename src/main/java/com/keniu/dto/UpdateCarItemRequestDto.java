package com.keniu.dto;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class UpdateCarItemRequestDto {

    @Positive
    private int quantity;
}
