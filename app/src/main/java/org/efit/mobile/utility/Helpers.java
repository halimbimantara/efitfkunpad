package org.efit.mobile.utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Helpers {
    public Helpers(){

    }
    public  String getTanggalNow() {
        Date date = new Date();
        SimpleDateFormat dateFormatWithZone = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String currentDate = dateFormatWithZone.format(date);
        return currentDate;
    }

    public  String getTanggalTime() {
        Date date = new Date();
        SimpleDateFormat dateFormatWithZone = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String currentDate = dateFormatWithZone.format(date);
        return currentDate;
    }

}
