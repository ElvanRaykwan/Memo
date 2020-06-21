package com.example.memo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MemoRandomUtilities {
    public static String DateFormatting(long date){
        DateFormat format = new SimpleDateFormat("EEE, dd  yyyy 'at' hh:mm aaa", Locale.US);
        return format.format(new Date());
    }
}
