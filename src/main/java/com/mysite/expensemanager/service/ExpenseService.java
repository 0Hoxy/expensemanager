package com.mysite.expensemanager.service;

import com.mysite.expensemanager.dto.ExpenseDTO;
import com.mysite.expensemanager.entity.Expense;
import com.mysite.expensemanager.repository.ExpenseRepository;
import com.mysite.expensemanager.util.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expRepo;

    public List<ExpenseDTO> getAllExpenses(){
        List<Expense> list = expRepo.findAll();
        List<ExpenseDTO> listDTO = list.stream().map(this::mapToDTO).collect(Collectors.toList());
        return listDTO;
    }
    private final ModelMapper modelMapper;

    //엔티티 => DTO 변환
    private ExpenseDTO mapToDTO(Expense expense) {
        ExpenseDTO expenseDTO = modelMapper.map(expense, ExpenseDTO.class);
        expenseDTO.setDateString(DateTimeUtil.convertDateToString(expenseDTO.getDate()));
        return expenseDTO;
    }

    public ExpenseDTO saveExpenseDetails(ExpenseDTO expenseDTO) {
        // 1. DTO => Entity
        Expense expense = mapToEntity(expenseDTO);
        // 2. DB에 저장
        expense = expRepo.save(expense);
        // 3. Entity => DTO
        ExpenseDTO savedExpenseDTO = mapToDTO(expense);
        return savedExpenseDTO;
    }

    private Expense mapToEntity(ExpenseDTO expenseDTO) {
        Expense expense = modelMapper.map(expenseDTO, Expense.class);
        // 1. expenseId 입력 ( 유니크 문자열 자동생성 )
        expense.setExpenseId(UUID.randomUUID().toString());
        // 2. date 입력
        expense.setDate(DateTimeUtil.convertStringToDate(expenseDTO.getDate()));
        return expense;
    }

}
