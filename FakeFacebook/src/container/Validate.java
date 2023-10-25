/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package container;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Hanh
 */
public class Validate {

    private final Scanner sc = new Scanner(System.in);

    public String inDate() {
        int day, month, year;
        while (true) {
            try {
                System.out.print("Enter day: ");
                day = Integer.parseInt(sc.nextLine());
                System.out.print("Enter month: ");
                month = Integer.parseInt(sc.nextLine());
                System.out.print("Enter year: ");
                year = Integer.parseInt(sc.nextLine());

                if (isValidDate(day, month, year)) {
                    return day+"/"+month+"/"+year;
                } else {
                    System.out.println("Invalid date. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter day, month, year as numbers.");
            }
        }
    }

    public boolean isValidDate(int day, int month, int year) {
        if (month >= 1 && month <= 12 && year >= 0) {
            if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                return day >= 1 && day <= 31;
            } else if (month == 2 && isLeapYear(year)) {
                return day >= 1 && day <= 29;
            } else if (month == 2 && !isLeapYear(year)) {
                return day >= 1 && day <= 28;
            } else {
                return day >= 1 && day <= 30;
            }
        }
        return false;
    }

    public boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public String getDateNow() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return now.format(pattern);
    }

    public String instring(String a) {
        System.out.print(a);
        return sc.nextLine();
    }

    public String inType() {
        String s;
        while (true) {
            try {
                System.out.print("Enter type of Post: ");
                s = sc.nextLine();
                if (s.equalsIgnoreCase("status") || s.equalsIgnoreCase("story") || s.equalsIgnoreCase("reels")) {
                    return s;
                }
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Ivalid type post");
            }
        }
    }

    public String inPrivacy() {
        String s;
        while (true) {
            try {
                System.out.print("Enter Privacy: ");
                s = sc.nextLine();
                if (s.equalsIgnoreCase("friend") || s.equalsIgnoreCase("public") || s.equalsIgnoreCase("private")) {
                    return s;
                }
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Ivalid privacy");
            }
        }
    }

    public String inEmotion() {
        String s;
        while (true) {
            try {
                System.out.print("Enter Emotion: ");
                s = sc.nextLine();
                if (s.equalsIgnoreCase("Like") || s.equalsIgnoreCase("Love") || s.equalsIgnoreCase("WOW")
                        || s.equalsIgnoreCase("haha") || s.equalsIgnoreCase("Angry") || s.equalsIgnoreCase("sad")) {
                    return s;
                }
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Ivalid Emotion");
            }
        }
    }

    public String inID() {
        String s;
        while (true) {
            try {
                System.out.print("Enter ID: ");
                s = sc.nextLine();
                if (s.matches("[a-zA-Z0-9]+")) {
                    return s;
                }
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Ivalid ID");
            }
        }
    }

    public String inName() {
        String s;
        while (true) {
            try {
                System.out.print("Enter Name: ");
                s = sc.nextLine();
                if (s.matches("[a-zA-Z\\s]+")) {
                    return s;
                }
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Ivalid name");
            }
        }
    }

    public static void main(String[] args) {
        Validate val = new Validate();
        System.out.println(val.inName());
    }
}