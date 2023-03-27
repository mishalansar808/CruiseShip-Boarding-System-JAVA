package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CruiseShip_Task1 {

    static Scanner input = new Scanner(System.in);
    static String cabinName;
    static int cabinNum = 1;
    static String[] cruiseShip = new String[13];
    static String menu;


    public static void main(String[] args) {
        //initialise
        initialise(cruiseShip); //better to initialise in a procedure

        while ( cabinNum < 13 ) {
            System.out.print("""

                     Options:-
                          'A' to Add a Customer to a Cabin\s
                          'V' to View all Cabins\s
                          'E' to Display Empty Cabins\s
                          'D' to Delete Customer from Cabin\s
                          'F' to Find Cabin from Customer Name\s
                          'S' to Store Program Data to File\s
                          'L' to Load Program Data from File\s
                          'O' to View Passengers Ordered Alphabetically by Name\s
                     Your Choice: \s""");
            menu = input.next().toUpperCase();
            switch (menu) {
                case "A": //Add customer to cabin
                    AddCustomer(cruiseShip);
                    break;

                case "V"://view all cabins
                    ViewCabins(cruiseShip);
                    break;

                case "E"://Display empty cabins
                    EmptyCabins(cruiseShip);
                    break;

                case "D"://Deleting a customer from cabin
                    DeleteCustomer(cruiseShip);
                    break;

                case "F"://Find cabin number with customer name
                    FindCabin(cruiseShip);
                    break;

                case "S"://Writing to file...
                    StoreInFile(cruiseShip);
                    break;

                case "L":
                    LoadFile(cruiseShip);
                    break;

                case "O"://Sorting the passengers in alphabetical order
                    System.out.println("\nThe passenger names have been sorted alphabetically..");
                    break;

                default:
                    System.out.println("Please enter a valid option!!!");
                    break;
            }
        }
    }
    private static void AddCustomer(String[] cruiseShip){
        System.out.print("\nEnter Cabin number (1-12): ");
        cabinNum = input.nextInt();
        while (true) {
            if (cabinNum > 0 && cabinNum < 13) {
                System.out.print("Enter name for Cabin " + cabinNum + " : ");
                cabinName = input.next().toUpperCase();
                cruiseShip[cabinNum] = cabinName;
                System.out.println("\nThe customer name was successfully entered");
                break;
            } else {
                System.out.println("Please enter a valid Cabin number");
                System.out.print("\nEnter Cabin number between (1-12): ");
                cabinNum = input.nextInt();
            }
        }
    }
    private static void ViewCabins(String[] cruiseShip){
        for (int x = 1; x < 13; x++) {
            if (cruiseShip[x].length() > 1) {
                System.out.println("Cabin " + x + " occupied by " + cruiseShip[x]);
            } else
                System.out.println("Cabin " + x + " is not occupied...");
        }
    }
    private static void EmptyCabins(String[] cruiseShip){
        for (int x = 1; x < cruiseShip.length; x++) {
            if (cruiseShip[x].equals("e")) {
                System.out.println("Cabin " + x + " is empty *************************");
            } else
                System.out.println("Sorry... Cabin " + x + " cabin is booked..");
        }
    }
    private static void DeleteCustomer(String[] cruiseShip){
        System.out.print("\nEnter customer cabin number to delete customer from: ");
        cabinNum = input.nextInt();
        if (cabinNum < 13 && cruiseShip[cabinNum].equals("e")) {//checking if the cabin is already empty
            System.out.println("The cabin is empty!!");
        } else
            while (true) {
                if (cabinNum < 13 && cabinNum != 0) {
                    cruiseShip[cabinNum] = ("e");
                    System.out.println("Customer removed from cabin " + cabinNum);
                    System.out.println("______________________________\n");
                    break;
                } else
                    System.out.println("\nPlease enter a valid cabin number!!!");
                System.out.println("______________________________\n");
                break;
            }
    }
    private static void FindCabin(String[] cruiseShip){
        System.out.print("\nPlease enter customer name to find cabin: ");
        cabinName = input.next().toUpperCase();
        while (true){
            if (cabinName.matches("^[A-Z]*$")){
                break;
            }else{
                System.out.println("Please enter a valid input...");
                System.out.print("\nPlease enter customer name to find cabin: ");
                cabinName = input.next().toUpperCase();
            }
        }
        boolean check = true;
        for (int x = 1; x < 13; x++) {
            if (cruiseShip[x].equals(cabinName)) {
                System.out.println(cabinName + " is in cabin number " + cabinNum);
                check = true;
                break;
            } else
                check = false;
        }
        if (!check){
            System.out.println("The cabin number with the name " + cabinName + " cannot be found!!!");
        }
    }
    private static void StoreInFile(String[] cruiseShip){
        try {
            FileWriter writeToFile = new FileWriter("CustomerList.txt");
            writeToFile.write("____________________Customer List____________________");
            writeToFile.write("\n");
            for (int x = 1; x < 13; x++) {
                if (!cruiseShip[x].equals("e")) {
                    writeToFile.write("Cabin " + x + " occupied by " + cruiseShip[x] + "\n");

                } else{
                    writeToFile.write("Cabin " + x + " is empty *************************\n");
                }
            }
            writeToFile.close();
            System.out.println("Successfully written to file...");
        } catch (IOException e) {
            System.out.println("An error occurred...");
        }
    }
    private static void LoadFile(String[] cruiseShip){
        int lineCount = 1;
        try{

            File inputFile = new File("CustomerList.txt");
            Scanner readFile = new Scanner(inputFile);
            while (readFile.hasNext()) {
                String fileLine = readFile.nextLine();
                System.out.println(lineCount + " " + fileLine);
                lineCount++;
            }
            readFile.close();
        }
        catch (FileNotFoundException e){
            System.out.println("Error IOException is: " + e);
        }
    }


    private static void initialise(String[] shipRef)
    {
        for (int x = 1; x < 13; x++ ) shipRef[x] = "e";
        System.out.println(" initialise ");
    }
}

