package com.mullen.hemura.domain.tasks;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mullen.hemura.domain.session.SessionEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "task_id")
    private String id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private Boolean isCompleted;

    @Enumerated(EnumType.STRING)
    private DayOfWeek weekDay;

    @NotNull
    @JsonFormat(pattern = "HH:mm")
    private LocalTime time;

    @PrePersist
    public void prePersist() {
        this.isCompleted = false;
        var formatter = DateTimeFormatter.ofPattern("HH:mm");
        this.time = LocalTime.parse(this.time.toString(), formatter);
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="session_id")
    private SessionEntity session;

    public TaskEntity(String title, String description, DayOfWeek weekDay, LocalTime time, SessionEntity session) {
        this.title = title;
        this.description = description;
        this.weekDay = weekDay;
        this.time = time;
        this.session = session;
    }

}
