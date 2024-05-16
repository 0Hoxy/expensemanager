package com.mysite.expensemanager.controller;

import com.mysite.expensemanager.dto.ExpenseDTO;
import com.mysite.expensemanager.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;


@Controller
@RequiredArgsConstructor
public class ExpenseController {


    private final ExpenseService expService;


    @GetMapping("/expenses")
    public String showExpenseList(Model model) {
        model.addAttribute("expenses", expService.getAllExpenses());
        return "expenses-list";
    }

    //컨트롤러에서 뷰에 object 전달하기(전달할때는 model 사용)
    @GetMapping("/createExpense")
    public String createExpense(Model model) {
        model.addAttribute("expense", new ExpenseDTO());
        return "expense-form";
    }

    @PostMapping("/saveOrUpdateExpense")
    public String saveOrUpdateExpense(@ModelAttribute("expense") ExpenseDTO expenseDTO) throws ParseException {
        System.out.println("입력한 expenseDTO 객체 : " + expenseDTO);
        //저장 작업은 단방향으로만 전달하면 되는 경우가 많다. 특히, 저장 작업의 결과를 호출한 쪽에서 따로 처리할 필요가 없는 경우에는 반환 값이 필요하지 않다.
        // 반환 값이 필요 없는 경우에는 그냥 저장 작업을 수행하고 다음 단계로 진행하면 된다.
        expService.saveExpenseDetails(expenseDTO);
        return "redirect:/expenses";
    }

    @GetMapping("/deleteExpense")
    public String deleteExpense(@RequestParam String id) {
        System.out.println("삭제 비용 번호 : " + id);
        expService.deleteExpense(id);
        return "redirect:/expenses";
    }
}



