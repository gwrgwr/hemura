package com.mullen.hemura.domain.tasks.dto.request;


import java.time.DayOfWeek;
import java.time.LocalTime;

public record TaskRequestDTO(String title, String description, String weekDay, String time) {}
