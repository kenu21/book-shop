package com.keniu.dto;

import com.keniu.models.Status;
import lombok.Data;

@Data
public class UpdateOrderRequestDto {
    private Status status;
}
