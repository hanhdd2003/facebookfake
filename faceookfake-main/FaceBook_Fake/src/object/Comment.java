/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import container.Validate;
import java.time.LocalDateTime;

/**
 *
 * @author MyPC
 */
public class Comment {
    
    private final User owner;
    private String content;
    private final LocalDateTime time;
    private boolean edited;


    public Comment(User owner, String content) {
        this.owner = owner;
        this.content = content;
        this.time = LocalDateTime.now();
        this.edited = false;
    }

    public User getOwner() {
        
        return owner;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    public boolean isEdited() {
        return edited;
    }

    public void setEdited() {
        this.edited = true;
    }
    
    
    public String getTimeAgo() {
        Validate v = new Validate();
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear() - this.time.getYear();
        int month = now.getMonthValue() - this.time.getMonthValue();
        int day = now.getDayOfMonth() - this.time.getDayOfMonth();
        int hour = now.getHour() - this.time.getHour();
        int minute = now.getMinute() - this.time.getMinute();
        int second = now.getSecond() - this.time.getSecond();
        if (second < 0) {
            minute--;
            second += 60;
        }
        if (minute < 0) {
            hour--;
            minute += 60;
        }
        if (hour < 0) {
            day--;
            hour += 24;
        }
        if (day < 0) {
            month--;
            day += this.time.getMonth().length(v.isLeapYear(this.time.getYear()));
        }
        if (month < 0) {
            year--;
            month += 12;
        }
        String str = "";
        if (year > 0) {
            str += year + " year";
            if (year > 1) str += "s";
        } else if (month > 0) {
            str += month + "month";
            if (month > 1) str += "s";
        } else if (day > 0) {
            str +=  day + " day";
            if (day > 1) str += "s";
        } else if (hour > 0) {
            str += hour + " hour";
            if (hour > 1) str += "s";
        } else if (minute > 0) {
            str += minute + " minute";
            if (minute > 1) str += "s";
        } else {
            str += second + " second";
            if (second > 1) str += "s";
        }
        return str;
    }
    
    
    
}
