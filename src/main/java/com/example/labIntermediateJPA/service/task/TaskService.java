package com.example.labIntermediateJPA.service.task;

import com.example.labIntermediateJPA.model.task.BillableTask;
import com.example.labIntermediateJPA.model.task.InternalTask;
import com.example.labIntermediateJPA.model.task.Task;
import com.example.labIntermediateJPA.repository.task.BillableTaskRepository;
import com.example.labIntermediateJPA.repository.task.InternalTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final BillableTaskRepository billableTaskRepository;
    private final InternalTaskRepository internalTaskRepository;

    @Autowired
    public TaskService(BillableTaskRepository billableTaskRepository,
                       InternalTaskRepository internalTaskRepository) {
        this.billableTaskRepository = billableTaskRepository;
        this.internalTaskRepository = internalTaskRepository;
    }

    // Métodos para BillableTask
    public BillableTask createBillableTask(String title, LocalDate dueDate, boolean status, Double hourlyRate) {
        BillableTask task = new BillableTask(title, dueDate, status, hourlyRate);
        return billableTaskRepository.save(task);
    }

    public Optional<BillableTask> getBillableTaskById(Long id) {
        return billableTaskRepository.findById(id);
    }

    public List<BillableTask> getAllBillableTasks() {
        return billableTaskRepository.findAll();
    }

    public List<BillableTask> getOverdueBillableTasks() {
        return billableTaskRepository.findOverdueTasks();
    }

    // Métodos para InternalTask
    public InternalTask createInternalTask(String title, LocalDate dueDate, boolean status) {
        InternalTask task = new InternalTask(title, dueDate, status);
        return internalTaskRepository.save(task);
    }

    public Optional<InternalTask> getInternalTaskById(Long id) {
        return internalTaskRepository.findById(id);
    }

    public List<InternalTask> getAllInternalTasks() {
        return internalTaskRepository.findAll();
    }

    public List<InternalTask> getOverdueInternalTasks() {
        return internalTaskRepository.findOverdueTasks();
    }

    // Método para obtener todas las tareas (combinando ambos tipos)
    public List<Task> getAllTasks() {
        List<Task> allTasks = new ArrayList<>();
        allTasks.addAll(billableTaskRepository.findAll());
        allTasks.addAll(internalTaskRepository.findAll());
        return allTasks;
    }

    // Ejemplo de uso de consulta SQL nativa
    public List<Object[]> getBillableTasksWithMonthlyTotals() {
        return billableTaskRepository.findBillableTasksWithMonthlyTotals();
    }
}
