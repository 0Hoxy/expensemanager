package com.mysite.expensemanager.repository;

import com.mysite.expensemanager.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    //SELECT * FROM tbl_expense WHERE expenseId=?
    Optional<Expense> findByExpenseId(String expenseId);
}
//클래스 검색창을 켜고 SimpleJpaRepository를 검색하여 찾았다면 이미 @Repository 어노테이션이 존재하므로 따로 추가할 필요가 없다.