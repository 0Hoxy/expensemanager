package com.mysite.expensemanager.service;

import com.mysite.expensemanager.dto.ExpenseDTO;
import com.mysite.expensemanager.entity.Expense;
import com.mysite.expensemanager.repository.ExpenseRepository;
import com.mysite.expensemanager.util.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expRepo;
    private final ModelMapper modelMapper;

    public List<ExpenseDTO> getAllExpenses(){
        List<Expense> list = expRepo.findAll();
        List<ExpenseDTO> listDTO = list.stream().map(this::mapToDTO).collect(Collectors.toList());
        return listDTO;
    }

    //엔티티 => DTO 변환
    private ExpenseDTO mapToDTO(Expense expense) {
        ExpenseDTO expenseDTO = modelMapper.map(expense, ExpenseDTO.class);
        expenseDTO.setDateString(DateTimeUtil.convertDateToString(expenseDTO.getDate()));
        return expenseDTO;
    }

    public ExpenseDTO saveExpenseDetails(ExpenseDTO expenseDTO) throws ParseException {
        //map the DTO to entity
        Expense expense = mapToEntity(expenseDTO);

        //save the entity to database
        expense = expRepo.save(expense);

        //map the entity to DTO
        return mapToDTO(expense);
    }

    // DTO => 엔티티 변환
    private Expense mapToEntity(ExpenseDTO expenseDTO) throws ParseException {
        //map the DTO to entity
        Expense expense =modelMapper.map(expenseDTO, Expense.class);

        //generate the expense id
        expense.setExpenseId(UUID.randomUUID().toString());

        //set the expense date
        expense.setDate(DateTimeUtil.convertStringToDate(expenseDTO.getDateString()));

        //return the expense entity
        return expense;
    }

    public void deleteExpense(String id) {
        Expense expense = expRepo.findByExpenseId(id).orElseThrow(() -> new RuntimeException("해당 ID의 아이템을 찾을 수 없습니다"));
        expRepo.delete(expense);
    }
}
