package com.example.labIntermediateJPA.model.task;

import jakarta.persistence.*;

@Entity
public class BillableTask extends Task {

    @Column(nullable = false)
    private Double hourlyRate;

    // Constructores
    public BillableTask() {
    }

    public BillableTask(String title, java.time.LocalDate dueDate, boolean status, Double hourlyRate) {
        super(title, dueDate, status);
        this.hourlyRate = hourlyRate;
    }

    // Getters y setters
    public Double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public String toString() {
        return "BillableTask{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", dueDate=" + getDueDate() +
                ", status=" + isStatus() +
                ", hourlyRate=" + hourlyRate +
                '}';
    }
}
