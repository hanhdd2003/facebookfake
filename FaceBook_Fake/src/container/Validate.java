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
                    return day + "/" + month + "/" + year;
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
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return now.format(f);
    }

    public String instring(String a) {
        System.out.print(a);
        return sc.nextLine().trim();
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
                System.out.println("Must in status, story, reels");
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
                System.out.println("Must in friend, public, private");
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
                System.out.println("Must input Like, Love, haha, Angry, WOW, sad");
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
                    return s.toUpperCase();
                }
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Ivalid ID");
            }
        }
    }
    
    public String inIdUser() {
        String s;
        while (true) {
            try {
                System.out.print("Enter ID: ");
                s = sc.nextLine();
                if (s.matches("^(?i)US\\d+")) {
                    return s.toUpperCase();
                }
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Must input US...");
            }
        }
    }
    
    public String inIdPost() {
        String s;
        while (true) {
            try {
                System.out.print("Enter ID: ");
                s = sc.nextLine();
                if (s.matches("^(?i)PO\\d+")) {
                    return s.toUpperCase();
                }
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Must input PO...");
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

    public int inputChoice(int min, int max) {
        while (true) {
            try {
                System.out.print("Enter your choice: ");
                String input = sc.nextLine();

                int result = Integer.parseInt(input);

                if (result < min || result > max) {
                    System.out.println("Must input number from " + min + " to " + max);
                    continue;
                }
                return result;
            } catch (NumberFormatException e) {
                System.out.println("Must input a number!! Please try again");

            }
        }
    }

}
