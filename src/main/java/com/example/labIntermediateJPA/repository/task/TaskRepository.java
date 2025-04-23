package com.example.labIntermediateJPA.repository.task;

import com.example.labIntermediateJPA.model.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.time.LocalDate;
import java.util.List;

@NoRepositoryBean
public interface TaskRepository<T extends Task> extends JpaRepository<T, Long> {
    List<T> findByStatus(boolean status);
    List<T> findByDueDateBefore(LocalDate date);
    List<T> findByTitleContaining(String keyword);

    // Ejemplo de consulta JPQL
    @Query("SELECT t FROM #{#entityName} t WHERE t.dueDate <= CURRENT_DATE AND t.status = false")
    List<T> findOverdueTasks();

    // Ejemplo de consulta SQL nativa
    @Query(value = "SELECT * FROM #{#entityName} t WHERE EXTRACT(MONTH FROM t.due_date) = ?1 AND EXTRACT(YEAR FROM t.due_date) = ?2", nativeQuery = true)
    List<T> findTasksByMonthAndYear(int month, int year);
}
