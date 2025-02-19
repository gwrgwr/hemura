package com.mullen.hemura.domain.tasks.dto.response;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record TaskResponseDTO(String id, String title, String description, boolean isCompleted, DayOfWeek weekDay, LocalTime time) {
}
