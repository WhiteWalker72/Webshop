package utils;

import java.util.Date;

public class DateUtils {

    public static Date fromSQLToJavaDate(java.sql.Date sqlDate) {
        return new Date(sqlDate.getTime());
    }

    public static java.sql.Date fromJavaToSQLDate(Date javaDate) {
       return new java.sql.Date(javaDate.getTime());
    }

}
