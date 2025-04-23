package com.example.labIntermediateJPA.controller.task;

import com.example.labIntermediateJPA.model.task.BillableTask;
import com.example.labIntermediateJPA.model.task.InternalTask;
import com.example.labIntermediateJPA.model.task.Task;
import com.example.labIntermediateJPA.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/billable")
    public ResponseEntity<List<BillableTask>> getAllBillableTasks() {
        return ResponseEntity.ok(taskService.getAllBillableTasks());
    }

    @GetMapping("/internal")
    public ResponseEntity<List<InternalTask>> getAllInternalTasks() {
        return ResponseEntity.ok(taskService.getAllInternalTasks());
    }

    @GetMapping("/billable/{id}")
    public ResponseEntity<BillableTask> getBillableTaskById(@PathVariable Long id) {
        return taskService.getBillableTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/internal/{id}")
    public ResponseEntity<InternalTask> getInternalTaskById(@PathVariable Long id) {
        return taskService.getInternalTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/billable")
    public ResponseEntity<BillableTask> createBillableTask(@RequestBody Map<String, Object> payload) {
        String title = (String) payload.get("title");
        LocalDate dueDate = LocalDate.parse((String) payload.get("dueDate"));
        boolean status = (boolean) payload.get("status");
        Double hourlyRate = Double.valueOf(payload.get("hourlyRate").toString());

        BillableTask task = taskService.createBillableTask(title, dueDate, status, hourlyRate);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @PostMapping("/internal")
    public ResponseEntity<InternalTask> createInternalTask(@RequestBody Map<String, Object> payload) {
        String title = (String) payload.get("title");
        LocalDate dueDate = LocalDate.parse((String) payload.get("dueDate"));
        boolean status = (boolean) payload.get("status");

        InternalTask task = taskService.createInternalTask(title, dueDate, status);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @GetMapping("/billable/overdue")
    public ResponseEntity<List<BillableTask>> getOverdueBillableTasks() {
        return ResponseEntity.ok(taskService.getOverdueBillableTasks());
    }

    @GetMapping("/internal/overdue")
    public ResponseEntity<List<InternalTask>> getOverdueInternalTasks() {
        return ResponseEntity.ok(taskService.getOverdueInternalTasks());
    }

    @GetMapping("/billable/monthly-totals")
    public ResponseEntity<List<Object[]>> getBillableTasksWithMonthlyTotals() {
        return ResponseEntity.ok(taskService.getBillableTasksWithMonthlyTotals());
    }
}
