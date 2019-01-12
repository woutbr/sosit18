/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.util.Date;

/**
 *
 * @author Vincent
 */
public class Helper {
    
    public  static boolean IsNullOrEmpty(String s){

        return s==null || s.length()==0 || s.equals("null");
    }
    
    public static Date findCurrentDateTime() {
        Date d = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(d.toInstant(), ZoneId.systemDefault());
        Date CurrentDateTime = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
     
        return CurrentDateTime;
    }
    
}
