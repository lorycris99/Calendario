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
     * Construct a Calendario object with the given parameters
     * @param year The value to set the YEAR field 
     * @param month The value to set the MONTH field. It starts from 1, so January is 1
     * @param dayOfMonth The value to set the DAY_OF_MONTH field
     */
    public Calendario(int year, int month, int dayOfMonth){
        
        super(year, month -1, dayOfMonth);
//        this.date = new GregorianCalendar(year, month, dayOfMonth);
        
    }
    
    /**
     * Constructs a Calendario object with the given parameters
     * @param year The value to set the YEAR field
     * @param month The value to set the MONTH field. It starts from 1 so January is 1
     * @param dayOfMonth The value to set the DAY_OF_MONTH field.
     * @param hour The value to set the HOUR_OF_DAY field. It is the 24h format
     * @param minute The value to set the MINUTE field
     * @param second The value to set the SECOND field
     */
    public Calendario(int year, int month, int dayOfMonth, int hour, int minute, int second){
    
        super(year, month - 1, dayOfMonth, hour, minute, second);
    }
    
    /**
     * Constructs a default Calendario using actual time, date and time zone 
     */
    public Calendario(){
        super();
    
    }
    
    //methods
    
    /**
     * Returns the day saved in the given Calendario
     * @return the value saved in the DAY_OF_MONTH field
     */
    public int getDayOfMonth(){
   
         return super.get(GregorianCalendar.DAY_OF_MONTH);
    }
    
    /**
     * Returns the month saved in the given Calendario
     * @return the value saved in the MONTH field
     */
    public int getMonth(){
    
        return super.get(GregorianCalendar.MONTH) + 1;
    }
    
    /**
     * Returns the year saved in the given Calendario
     * @return the value saved in the YEAR field
     */
    public int getYear(){
    
        return super.get(GregorianCalendar.YEAR);
    }
    
    /**
     * Returns the hour saved in the given Calendario in the 24h format
     * @return the value saved in the HOUR_OF_DAY field
     */
    public int getHour(){
    
        return super.get(GregorianCalendar.HOUR_OF_DAY);
    }
    
    /**
     * Returns the minute saved in the given Calendario
     * @return the value saved in the MINUTE field
     */
    public int getMinute(){
    
        return super.get(GregorianCalendar.MINUTE);
    }
    
    /**
     * Rteurns the second saved in the given Calendario
     * @return the value saved in the SECOND field
     */
    public int getSecond(){
    
        return super.get(GregorianCalendar.SECOND);
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
     * Gives a formatted String containing only the main date and time informations
     * Is like a toString()
     * @return a string containing year, month, dayOfMonth, hour, minute, second
     */
    public String getDateTimeString(){
    
        return getDateString() + " Hour:" + getHour() + " Minute: " + getMinute() + " Second: " + getSecond();
    }
    
    /**
     * Used to easily convert the Calendario date in the format YYYY-MM-DD, usable by SQL, JS, etc.
     * @return The date in the format YYYY-MM-DD
     */
    public String formatDateString(){
        
        String year, month, day;
        //aggiungo zeri altrimenti non viene rispettato il formato
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
    
    /**
     * Returns a formatted string containing the date and time stored in the Calendario object in the format YYYY-MM-DD HH:MM:SS
     * @return Returns a formatted string in the format YYYY-MM-DD HH:MM:SS
     */
    public String formatDateTimeString(){
    
        String hour, minute, second;
        //aggiunge gli zeri altrimenti non viene rispettato il formato
        if(getHour() < 10){
        
            hour = "0" + getHour();
        
        }else{
        
            hour = "" + getHour();
        }
        
        if(getMinute() < 10){
        
            minute = "0" + getMinute();
        
        }else{
        
            minute = "" + getMinute();
        }
        
        if(getSecond() < 10){
        
            second = "0" + getSecond();
            
        }else{
        
            second = "" + getSecond();
        }
        
        return formatDateString() + " " + hour + ":" + minute + ":" + second;
    }
    
    /**
     * This static method gives the possibility to construct a Calendario from a formatted string YYYY-MM-DD or YYYY-MM-DD HH:MM:SS
     * @param date The date in the format YYYY-MM-DD or YYYY-MM-DD HH:MM:SS
     * @return The Calendario with the date specified in the string
     * 
     * @throws NumberFormatException if the given doesn't match the right pattern
     */
    public static Calendario parseString(String date){
    
        int year, month, day, hour, minute, second;
        Calendario temp = null;  
            
        if(date.matches("\\d{4}-\\d{2}-\\d{2}")){//YYYY-MM-DD

            try {
                
                year = Integer.parseUnsignedInt(date.substring(0, 4));
                month = Integer.parseUnsignedInt(date.substring(5, 7));
                day = Integer.parseUnsignedInt(date.substring(8));

                temp = new Calendario(year, month, day);

            } catch (NumberFormatException e) {//in teoria non dovrebbe essere raggiungibile
            
                throw new NumberFormatException("Check the passed string, ensure is in the YYYY-MM-DD format");
            }
            
        }else if(date.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")){ //YYYY-MM-DD HH:MM:SS
        
            try{
                
                year = Integer.parseUnsignedInt(date.substring(0, 4));
                month = Integer.parseUnsignedInt(date.substring(5, 7));
                day = Integer.parseUnsignedInt(date.substring(8, 10));
                hour = Integer.parseUnsignedInt(date.substring(11, 13));
                minute = Integer.parseUnsignedInt(date.substring(14, 16));
                second = Integer.parseUnsignedInt(date.substring(17));
                
                return new Calendario(year, month, day, hour, minute, second);
            
            }catch(NumberFormatException e){//in teoria non dovrebbe essere raggiungibile
            
                throw new NumberFormatException("Check the passed string, ensure is in the YYYY-MM-DD HH:MM:SS format");
            }
        }else{ //non matcha niente
        
            throw new NumberFormatException("Check the passed string, ensure is in the YYYY-MM-DD HH:MM:SS format or in the YYYY-MM-DD format");
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
    
        return earlier.before(this) && later.after(this);
    }
    
    //variables
    
    public static final long serialVersionUID = 123L;
}
