package com.mysite.expensemanager.controller;

import com.mysite.expensemanager.dto.ExpenseDTO;
import com.mysite.expensemanager.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class ExpenseController {


    private final ExpenseService expService;

    @GetMapping("/expenses")
    public String showExpenseList(Model model) {
        model.addAttribute("expenses", expService.getAllExpenses());
        return "expenses-list";
    }

    @GetMapping("/createExpense")
    public String createExpense() {
        return "expense-form";
    }

    //컨트롤러에서 뷰에 object 전달하기(전달할때는 model 사용)
    @PostMapping("/createExpense")
    public String createExpense(Model model) {
        model.addAttribute("expenses", new ExpenseDTO());
        return "expense-form";
    }
}



