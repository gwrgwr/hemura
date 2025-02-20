package com.mullen.hemura.mappers;

import com.mullen.hemura.domain.session.SessionEntity;
import com.mullen.hemura.domain.tasks.TaskEntity;
import com.mullen.hemura.domain.tasks.dto.request.TaskRequestDTO;
import com.mullen.hemura.domain.tasks.dto.response.TaskResponseDTO;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TaskEntityMapper {
    public static TaskEntity toEntity (TaskRequestDTO taskRequestDTO, SessionEntity sessionEntity) {
        LocalTime time = LocalTime.parse(taskRequestDTO.time());
        return new TaskEntity(taskRequestDTO.title(), taskRequestDTO.description(), taskRequestDTO.weekDay(), time, sessionEntity);
    }

    public static TaskResponseDTO toTaskResponseDTO (TaskEntity taskEntity) {
        return new TaskResponseDTO(taskEntity.getId(), taskEntity.getTitle(), taskEntity.getDescription(), taskEntity.getIsCompleted(), taskEntity.getWeekDay(), taskEntity.getTime());
    }

    public static List<TaskResponseDTO> toTaskListResponseDTO (List<TaskEntity> taskEntities) {
        List<TaskResponseDTO> taskResponseDTOList = new ArrayList<>();
        for (TaskEntity taskEntity : taskEntities) {
            taskResponseDTOList.add(toTaskResponseDTO(taskEntity));
        }
        return taskResponseDTOList;
    }
}