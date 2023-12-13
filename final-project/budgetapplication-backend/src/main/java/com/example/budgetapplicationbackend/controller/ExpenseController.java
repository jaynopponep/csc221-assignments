package com.example.budgetapplicationbackend.controller;

import com.example.budgetapplicationbackend.model.Expense;
import com.example.budgetapplicationbackend.service.ExpenseService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;
    @Autowired
    public ExpenseController(ExpenseService expenseService) {this.expenseService = expenseService;}

    @GetMapping("/all")
    public ResponseEntity<List<Expense>> getAllExpenses() {
        List<Expense> expenseList = expenseService.getAllExpenses();
        return ResponseEntity.ok(expenseList);
    }

    @PostMapping("/new")
    public ResponseEntity<Expense> createOrUpdateExpense(@RequestBody Expense expense) {
        Expense savedExpense = expenseService.createOrUpdateExpense(expense);
        return new ResponseEntity<Expense>(savedExpense, HttpStatus.CREATED);
    }

    @DeleteMapping("/clear")
    @Transactional //
    public ResponseEntity<String> clearExpenses() {
        try {
            expenseService.clearExpenses();
            return ResponseEntity.ok("Expenses have been successfully cleared.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting");
        }
    }
}
