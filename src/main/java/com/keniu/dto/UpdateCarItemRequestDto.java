package com.keniu.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateCarItemRequestDto {
    @NotNull
    private Integer quantity;
}
