package com.mysite.expensemanager.service;

import com.mysite.expensemanager.dto.ExpenseDTO;
import com.mysite.expensemanager.entity.Expense;
import com.mysite.expensemanager.repository.ExpenseRepository;
import com.mysite.expensemanager.util.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

}
