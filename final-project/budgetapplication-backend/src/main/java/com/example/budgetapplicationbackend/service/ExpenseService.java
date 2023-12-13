package com.example.budgetapplicationbackend.service;

import com.example.budgetapplicationbackend.model.Expense;
import com.example.budgetapplicationbackend.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository) { this.expenseRepository = expenseRepository; }
    public List<Expense> getAllExpenses() { return expenseRepository.findAll(); }
    public Optional<Expense> getExpenseById(int id) { return expenseRepository.findById(id); }
    public Expense createOrUpdateExpense(Expense expense) { return expenseRepository.save(expense);}
    public void deleteExpense(int id) { expenseRepository.deleteById(id);}
    public void clearExpenses() { expenseRepository.deleteAll(); }

}
