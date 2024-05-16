package com.mysite.expensemanager.controller;

import com.mysite.expensemanager.dto.ExpenseDTO;
import com.mysite.expensemanager.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequiredArgsConstructor
public class ExpenseController {


    private final ExpenseService expService;

    @GetMapping("/expenses")
    public String showExpenseList(Model model) {
        model.addAttribute("expenses", expService.getAllExpenses() );
        return "expenses-list";
    }
}



