package com.mysite.expensemanager.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {

    public static String convertDateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
        return sdf.format(date);
    }
}


