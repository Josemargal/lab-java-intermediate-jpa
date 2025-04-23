package com.example.labIntermediateJPA.repository.task;

import com.example.labIntermediateJPA.model.task.BillableTask;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillableTaskRepository extends TaskRepository<BillableTask> {
    List<BillableTask> findByHourlyRateGreaterThan(Double rate);

    // Ejemplo de consulta SQL nativa que no se puede hacer fácilmente con JPQL
    @Query(value = "SELECT t.*, SUM(t.hourly_rate) OVER (PARTITION BY EXTRACT(MONTH FROM t.due_date)) as monthly_total " +
            "FROM billable_task t ORDER BY t.due_date", nativeQuery = true)
    List<Object[]> findBillableTasksWithMonthlyTotals();
}
