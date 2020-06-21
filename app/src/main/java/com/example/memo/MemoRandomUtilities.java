package com.example.memo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

//a class to dumb in random utilities of the application, though only one was needed for the project
public class MemoRandomUtilities {
    //use to format date from long into the pattern shown
    public static String dateFormatting(long date){
        //pattern for the date
        DateFormat format = new SimpleDateFormat("EEE, dd  yyyy 'at' hh:mm aaa", Locale.US);
        return format.format(new Date());
    }
}
