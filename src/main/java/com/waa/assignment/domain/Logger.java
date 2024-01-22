package com.waa.assignment.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Logger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate localDate;
    private LocalDateTime time;
    private String Principle;
    private String Operation;

    public Logger(LocalDate localDate, LocalDateTime time, String principle, String operation) {
        this.localDate = localDate;
        this.time = time;
        Principle = principle;
        Operation = operation;
    }

}
