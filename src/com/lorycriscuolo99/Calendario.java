/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lorycriscuolo99;

import java.util.GregorianCalendar;

/**
 * The purpose of this class is to fix some things i don't understand of GregorianCalendar class
 * and add some useful features that GregorianCalendar doesn't have
 * @author Lorenzo Criscuolo
 */
public class Calendario extends GregorianCalendar{
    
    
    //constructors
    /**
     * Construct a Calendario object with the same parameters of GregorianCalendar
     * @param year The value to set the YEAR field 
     * @param month The value to set th MONTH field, but it starts from 1, so January is 1
     * @param dayOfMonth The value to set the DAY_OF_MONTH field
     */
    public Calendario(int year, int month, int dayOfMonth){
        
        super(year, month -1, dayOfMonth);
//        this.date = new GregorianCalendar(year, month, dayOfMonth);
        
    }
    
    /**
     * Constructs a default Calendario using actual time, date and time zone 
     */
    public Calendario(){
        super();
    
    }
    
    //methods
    
    /**
     * Returns the day of the given Calendario
     * @return the value saved in the DAY_OF_MONTH field
     */
    public int getDayOfMonth(){
   
         return super.get(GregorianCalendar.DAY_OF_MONTH);
    }
    
    /**
     * Returns the month of the given Calendario
     * @return the value saved in the MONTH field
     */
    public int getMonth(){
    
        return super.get(GregorianCalendar.MONTH) + 1;
    }
    
    /**
     * Returns the year of the given Calendario
     * @return the value saved in the YEAR field
     */
    public int getYear(){
    
        return super.get(GregorianCalendar.YEAR);
    }
    
    /**
     * Gives a formatted String containing only the main date informations without all the GregorianCalendar stuff
     * Is like a toString()
     * @return a string containing year, month and dayOfMonth
     */
    public String getDateString(){
    
        return "Year: " + getYear() + " Month: " + getMonth() + " Day: " + getDayOfMonth();
    }
    
    /**
     * Used to easily convert the Calendario date in the format YYYY-MM-DD, usable by SQL, JS, etc.
     * @return The date in the format YYYY-MM-DD
     */
    public String formatDateString(){
        
        String year, month, day;
        
        if(getYear() < 1000){
        
            year = "0" + getYear();
        
        }else{
            
            year = "" + getYear();
        }
        
        if(getMonth() < 10 ){
        
            month = "0" + getMonth();
        
        }else{
        
            month = "" + getMonth();
        }
        
        if(getDayOfMonth() < 10){
        
            day = "0" + getDayOfMonth();
            
        }else{
        
            day = "" + getDayOfMonth();
            
        }
    
        return year + "-" + month + "-" + day;
    }
    
    //da finire
    private String formatJSString(){
    
        return "";
    }
    
    
    /**
     * This static method gives the possibility to construct a Calendario from a formatted string (YYYY-MM-DD)
     * @param date the date in the format YYYY-MM-DD
     * @return the Calendario with the date specified in the string
     * 
     * @throws NumberFormatException if the given doesn't match the right pattern
     */
    public static Calendario parseDateString(String date){//YYYY-MM-DD
    
        int year, month, day;
        Calendario temp = null;
        
        
            
        if(date.matches("\\d{4}-\\d{2}-\\d{2}")){

            try {
                
                year = Integer.parseUnsignedInt(date.substring(0, 4));
                month = Integer.parseUnsignedInt(date.substring(5, 7));
                day = Integer.parseUnsignedInt(date.substring(8));

                temp = new Calendario(year, month, day);

            } catch (NumberFormatException e) {
            
                throw new NumberFormatException("Check the passed string, ensure is in the YYYY-MM-DD format");
            }
            
        }else{

            throw new NumberFormatException("Check the passed string, ensure is in the YYYY-MM-DD format");
        }
   
        return temp;
    }
    
    
    /**
     * Checks if the Calendario is between two other GregorianCalendar objects. Note that "earlier" have to be smaller 
     * than "later", otherwise the method will always return false
     * @param earlier the GregorianCalendar supposed to be smaller 
     * @param later the GregorianCalendar supposed to be bigger
     * @return true if the current Calendario date is among the two GregorianCalendars specified. false otherwise
     */
    public boolean between(GregorianCalendar earlier, GregorianCalendar later){
    
        if(earlier.before(this) && later.after(this)){
        
            return true;
        }
        
        return false;
    }
    
    //variables
    
    public static final long serialVersionUID = 123L;
}
