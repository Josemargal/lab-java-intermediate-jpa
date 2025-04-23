package com.example.labIntermediateJPA.model.task;

import jakarta.persistence.*;

@Entity
public class InternalTask extends Task {

    // Constructores
    public InternalTask() {
    }

    public InternalTask(String title, java.time.LocalDate dueDate, boolean status) {
        super(title, dueDate, status);
    }

    @Override
    public String toString() {
        return "InternalTask{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", dueDate=" + getDueDate() +
                ", status=" + isStatus() +
                '}';
    }
}
