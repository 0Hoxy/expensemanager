package com.mysite.expensemanager.repository;

import com.mysite.expensemanager.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
//클래스 검색창을 켜고 SimpleJpaRepository를 검색하여 찾았다면 이미 @Repository 어노테이션이 존재하므로 따로 추가할 필요가 없다.