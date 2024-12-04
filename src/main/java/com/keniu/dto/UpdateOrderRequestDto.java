package com.keniu.dto;

import com.keniu.models.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateOrderRequestDto {
    @NotNull
    private Status status;
}
