package com.company;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.*;


public class CruiseShip_Task2_Task3 {
    static Scanner input = new Scanner(System.in);
    static int cabinNum = 1;
    static boolean cabinFull;
    static Cabin[] cruiseShip = new Cabin[13];
    static String cabinName;
    static int cabinCounter = 0; //counter to keep track of no. of booked cabins


    static Passenger[] firstName = new Passenger[13];
    static Passenger[] surName = new Passenger[13];
    static Passenger[] expenses = new Passenger[13];
    static Passenger[] guestNum = new Passenger[13];




    // Queue Class Objects
    static Queue RName = new Queue();
    static Queue FName = new Queue();
    static Queue SName = new Queue();
    static Queue GNumber = new Queue();

    public static void main(String[] args) {
        //Creating Passenger class objects
        for (int x = 1; x < cruiseShip.length; x++){
            cruiseShip[x] = new Cabin();
        }
        for (int x = 1; x < firstName.length; x++) {
            firstName[x] = new Passenger(0,"e","e", 0);
        }
        for (int x = 1; x < surName.length; x++) {
            surName[x] = new Passenger(0,"e","e", 0);
        }
        for (int x = 1; x < expenses.length; x++) {
            expenses[x] = new Passenger(0,"e","e", 0);
        }
        for (int x = 1; x < guestNum.length; x++) {
            guestNum[x] = new Passenger(0,"e","e", 0);
        }
        initialise(cruiseShip);
        Menu();

    }

    private static void Menu() {
        System.out.print("""
                
                *----*----*------------Menu------------*----*----*
                Options:-
                         'A' to Add a Customer to a Cabin\s
                         'V' to View all Cabins\s
                         'E' to Display Empty Cabins\s
                         'D' to Delete Customer from Cabin\s
                         'F' to Find Cabin from Customer Name\s
                         'S' to Store Program Data to File\s
                         'L' to Load Program Data from File\s
                         'O' to View Passengers Ordered Alphabetically by Name\s
                         'T' to View the Expenses of Passenger\s""");
        validate();
    }

    private static void validate() {
        System.out.print("\nYour Preferred Option: ");
        String choice = input.next().toUpperCase();
        if ("A".equals(choice)) {
            AddCustomer(cruiseShip);//Add customer to cabin

        } else if ("V".equals(choice)) {
            ViewCabins(cruiseShip);//view all cabins

        } else if ("E".equals(choice)) {
            EmptyCabins(cruiseShip);//Display empty cabins

        } else if ("D".equals(choice)) {
            DeleteCustomer(cruiseShip);//Deleting a customer from cabin

        } else if ("F".equals(choice)) {
            FindCabin(cruiseShip);//Find cabin number with customer name

        } else if ("S".equals(choice)) {
            StoreInFile(cruiseShip);//Writing to file...

        } else if ("L".equals(choice)) {
            LoadFile();//Loading saved file...

        } else if ("O".equals(choice)) {  //Sorting the passengers in alphabetical order
            Sort(cruiseShip);

        } else if ("T".equals(choice)) {
            Expenses(guestNum);//Displays the Expenses
        } else{
            System.out.println("\nPlease enter a valid option!!!");//Any other input other than from menu
            Menu();
        }



        //Prompting user whether to continue or end program
        System.out.print("\n\nWould you like to continue? (Y/N): ");
        String option = input.next().toUpperCase();
        if (option.equals("Y")) {
            input.next();
            Menu();
        }
        if (option.equals("N")) {
            System.out.println("<<<<<<<<<<<<<<<<<<<< Thank You >>>>>>>>>>>>>>>>>>>>");
            System.out.println("<<<<<<<<<<<<< The Program is Ending... >>>>>>>>>>>>>");
            System.exit(0);//Ending the program
        }
    }

    private static void initialise(Cabin[] cruiseShip) {
        for (int x = 1; x < cruiseShip.length; x++) {
            cruiseShip[x].setCabinName("e");
        }
    }

    private static void AddCustomer(Cabin[] cruiseShip){
        for (int x = 1; x < 13; x++){
            cabinFull = false;
            if (cruiseShip[x].getCabinName().equals("e")){
                cabinFull = true;

            }else {
                cabinFull = false;
                break;
            }
        }
        try{
            System.out.println("\n>>>>>>>>>>>>>>> Add Passenger <<<<<<<<<<<<<<<");

            while (true){
                if (cabinCounter < 12) {
                    System.out.print("\nEnter Cabin number between (1-12): ");
                    cabinNum = input.nextInt();

                    System.out.println("Enter Passenger details for Cabin - " + cabinNum);
                    System.out.print("Enter Passenger name: ");
                    String cabinName = input.next().toUpperCase();

                    System.out.print("Enter Passenger First Name: ");
                    String fName = input.next().toUpperCase();

                    System.out.print("Enter Passenger Surname: ");
                    String sName = input.next().toUpperCase();

                    System.out.print("Enter No. of guests: ");
                    int gNum = input.nextInt();
                    while (true) {
                        if (gNum >= 4) {
                            System.out.println("\nA Cabin can only hold up to 3 passengers!!");
                            System.out.println("Please enter a value less than 4");
                            System.out.print("\nEnter No. of guests: ");
                            gNum = input.nextInt();
                        } else

                            break;
                    }

                    cruiseShip[cabinNum].setCabinName(cabinName);
                    firstName[cabinNum].setFirstName(fName);
                    surName[cabinNum].setSurName(sName);
                    guestNum[cabinNum].setGuestNum(gNum);
                    System.out.println("Passenger details added...");
                    cabinCounter++;
                    System.out.println("The number of booked cabins: "+ cabinCounter);

                    break;

                }else{
                    System.out.println("Sorry. Currently all Cabins are Full");
                    System.out.println("Please Enter Passenger Details to Add to Queue...\n\n");
                    System.out.println("Enter Passenger details for Cabin - " + cabinNum);
                    System.out.print("Enter Passenger name: ");
                    String cabinName = input.next().toUpperCase();

                    System.out.print("Enter Passenger First Name: ");
                    String fName = input.next().toUpperCase();

                    System.out.print("Enter Passenger Surname: ");
                    String sName = input.next().toUpperCase();

                    System.out.print("Enter No. of guests: ");
                    int gNum = input.nextInt();
                    System.out.println("\n");

                    //enqueue method to add value to the queues
                    RName.enQueueString(cabinName);
                    FName.enQueueString(fName);
                    SName.enQueueString(sName);
                    GNumber.enQueueNumber(gNum);

                    System.out.println("Passenger added to waiting list..");

                }




                    //checking if cabins are full
                    //for (int x = 1; x < 13; x++){
                        //if ("e".equals(fName)){
                           // cabinFull = true;
                           // break;
                       // }else {
                          //  cabinFull = false;
                      //  }
                   // }

                    //if (cruiseShip[cabinNum].getCabinName().equals("e")) {
                        //System.out.print("\nEnter Cabin number between (1-12): ");
                        //cabinNum = input.nextInt();

                       // if (!cabinFull) {
                            //cruiseShip[cabinNum].setCabinName(cabinName);
                            //firstName[cabinNum].setFirstName(fName);
                            //surName[cabinNum].setSurName(sName);
                            //guestNum[cabinNum].setGuestNum(gNum);
                            //System.out.println();
                            //System.out.println("Passenger details added...");

                   // }else {
                        //System.out.println();
                        //System.out.println("Sorry. Currently all Cabins are Full");
                        //enqueue method to add value to the queues
                        //RName.enQueueString(cabinName);
                        //FName.enQueueString(fName);
                        //SName.enQueueString(sName);
                       // GNumber.enQueueNumber(gNum);

                            //System.out.println("Customer added to waiting list..");
            }



                    //break;
                //}else {
                    //System.out.println("Please enter a valid Cabin number");
                    //System.out.print("\nEnter Cabin number between (1-12): ");
                    //cabinNum = input.nextInt();


        } catch (InputMismatchException e){
            System.out.println("Please enter a valid input type!!!");
            Menu();
        }

    }

    private static void ViewCabins(Cabin[] cruiseShip){
        System.out.println("\n>>>>>>>>>>>>>>>>>>>>>> View Cabins <<<<<<<<<<<<<<<<<<<<<<\n");
        System.out.println("| CABIN NO. |     PASSENGER FULL NAME     | NO. OF GUESTS |");
        for (int x = 1; x < 13; x++) {
            if ("e".equals(cruiseShip[x].getCabinName())) {
                System.out.println("\n--------------------------------------------------------------");
                System.out.format("%-26s %-21s %-17s","      " +  x , "N/A" , "N/A");
            } else {
                System.out.println("\n--------------------------------------------------------------");
                System.out.format("%-19s %-29s %-17s","      " +  x, firstName[x].getFirstName() + " " +surName[x].getSurName(), guestNum[x].getGuestNum());

            }
        }
    }

    private static void EmptyCabins(Cabin[] cruiseShip){
        System.out.println("\n>>>>>>>>>>>>>>> Empty Cabins <<<<<<<<<<<<<<<");
        System.out.println("| CABIN NO. |     PASSENGER FULL NAME     | NO. OF GUESTS |");
        for (int x = 1; x < cruiseShip.length; x++) {
            if (cruiseShip[x].getCabinName().equals("e")) {
                System.out.println("\n--------------------------------------------------------------");
                System.out.format("%-26s %-21s %-17s","      " +  x , "N/A" , "N/A");
            }
        }
    }

    private static void DeleteCustomer(Cabin[] cruiseShip){
        System.out.println("\n>>>>>>>>>>>>>>> Delete Customer <<<<<<<<<<<<<<<");
        System.out.println();
        System.out.print("\nEnter customer cabin number to delete customer from: ");
        cabinNum = input.nextInt();
        //checking input
        while (cabinNum > 13){
            System.out.println("\nPlease enter a valid cabin number!!!");
            System.out.println("Enter a value between (1-12)");
            System.out.print("\nEnter customer cabin number to delete customer from: ");
            cabinNum = input.nextInt();
        }


        //Deleting customer details
        cruiseShip[cabinNum].setCabinName("e");
        firstName[cabinNum].setFirstName("e");
        surName[cabinNum].setSurName("e");
        guestNum[cabinNum].setGuestNum(0);

        while (true){
            if (RName.deQueueString().equals("e")){
                System.out.println("\nOld Customer Removed from Cabin " + cabinNum);
                break;
            }else{
                //Adding customers from waiting list
                cruiseShip[cabinNum].setCabinName(RName.deQueueString());
                firstName[cabinNum].setFirstName(FName.deQueueString());
                surName[cabinNum].setSurName(SName.deQueueString());
                guestNum[cabinNum].setGuestNum(GNumber.deQueueNumber());

                System.out.println("Old Customer Removed from Cabin " + cabinNum);
                System.out.println("New Customer Added from Waiting List to Cabin " + cabinNum);
                break;
            }
        }


    }

    private static void FindCabin(Cabin[] cruiseShip){
        System.out.println("\n>>>>>>>>>>>>>>> Find Cabin <<<<<<<<<<<<<<<");
        System.out.print("\nPlease enter customer first name to find cabin: ");
        cabinName = input.next().toUpperCase();
        while (true){
            //validating input type
            if (cabinName.matches("^[A-Z]*$")){
                break;
            }else{
                System.out.println("Please enter a valid input...");
                System.out.print("\nPlease enter customer first name to find cabin: ");
                cabinName = input.next().toUpperCase();
            }
        }
        //checks for customer name
        boolean check = true;
        for (int x = 1; x < 13; x++) {
            if (cabinName.equals(cruiseShip[x].getCabinName())) {
                System.out.println(cabinName + " is in cabin " + cabinNum);
                System.out.println("Passenger Full Name: " + firstName[x].getFirstName() + " " + surName[x].getSurName());
                System.out.println("No of Guests in Cabin: " + guestNum[x].getGuestNum());
                check = true;
                break;
            } else
                check = false;
        }
        if (!check){
            System.out.println("The cabin number with the name " + cabinName + " cannot be found!!!");
        }
    }

    private static void StoreInFile(Cabin[] cruiseShip){
        try {
            FileWriter fileWriter = new FileWriter("CustomerList.txt");
            for (int x = 1; x < 13; x++) {
                if (cruiseShip[x].getCabinName().equals("e")){
                    fileWriter.write("Cabin" + x + "is empty ********** \n");
                }else{
                    fileWriter.write(cruiseShip[x].getCabinName()
                    + firstName[x].getFirstName() + surName[x].getSurName()
                    + guestNum[x].getGuestNum());
                }
            }
            fileWriter.close();
            System.out.print("\nFile saved successfully");

        } catch (IOException e) {
            System.out.println("An error occurred please try again!!!");
            e.printStackTrace();
        }
    }

    private static void LoadFile(){
        //Loading file

        try{
            int i = 1;
            File inputFile = new File("CustomerList.txt");
            Scanner readFile = new Scanner(inputFile);

            while (readFile.hasNextLine()){
                String loadedData = readFile.nextLine();
                String[] splitData = loadedData.split("-");
                //Cabin newCruise = new Cabin(splitData[0]);
                //cruiseShip[i] = newCruise;
                i++;
            }
//note check option O for better viewing

            readFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void Sort(Cabin[] cruiseShip){
        //Cabin temp;

        //for (int x = 1; x < cruiseShip.length; x++){
            //for(int i = x + 1; i < cruiseShip.length; i++){

                //if ((cruiseShip[x].getCabinName().compareTo(cruiseShip[i].getCabinName()) > 1)){
                   // temp = cruiseShip[x];
                    //cruiseShip[x] = cruiseShip[i];
                    //cruiseShip[i] = temp;
               // }
           // }
      // }

        Cabin tempCabin;
        Passenger tempPassenger;
        for (int x = 0; x < cruiseShip.length; x++){
            for(int i = x + 1; i < cruiseShip.length; i++){
                if (firstName[x] != null && firstName[i] != null && firstName[x].getFirstName().compareTo(firstName[i].getFirstName()) > 0){
                    tempCabin = cruiseShip[x];
                    cruiseShip[x] = cruiseShip[i];
                    cruiseShip[i] = tempCabin;
                    tempPassenger = firstName[x];
                    firstName[x] = firstName[i];
                    firstName[i] = tempPassenger;
                    tempPassenger = surName[x];
                    surName[x] = surName[i];
                    surName[i] = tempPassenger;
                    tempPassenger = guestNum[x];
                    guestNum[x] = guestNum[i];
                    guestNum[i] = tempPassenger;
                }
            }
        }

        System.out.println("Passengers Sorted Alphabetically Successfully...");

        System.out.println("| CABIN NO. |     PASSENGER FULL NAME     | NO. OF GUESTS |");
        for (int x = 1; x < 13; x++) {
            if ("e".equals(cruiseShip[x].getCabinName())) {
                System.out.println("\n--------------------------------------------------------------");
                System.out.format("%-26s %-21s %-17s","      " +  x , "N/A" , "N/A");
            } else {
                System.out.println("\n--------------------------------------------------------------");
                System.out.format("%-19s %-29s %-17s","      " +  x, firstName[x].getFirstName() + " " +surName[x].getSurName(), guestNum[x].getGuestNum());

            }
        }
    }


    private static void Expenses(Passenger[] guestNum){
        System.out.println("\n>>>>>>>>>>>>>>> Expenses <<<<<<<<<<<<<<<");
        System.out.println("Expense per Passenger: $800.00");//default expense amount for 1 passenger
        System.out.print("Please enter your Cabin No. to view expenses: ");//to find specific passenger to calculate expense
        cabinNum = input.nextInt();
        int totalCost = (guestNum[cabinNum].getGuestNum())*800;
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String result = currency.format(totalCost);
        System.out.println("Your total expenses: " + result);
    }
}

//    private static void LoadFile(){
//        //Loading file
//
//        try{
//            int i = 1;
//            File inputFile = new File("CustomerList.txt");
//            Scanner readFile = new Scanner(inputFile);
//
////            while (readFile.hasNextLine()){
////                String loadedData = readFile.nextLine();
////                String[] splitData = loadedData.split("-");
//////                Cabin newCruise = new Cabin(splitData[0]);
////                cruiseShip[i] = newCruise;
////                i++;
////            }
//
//            readFile.close();
//
//        } catch (IOException e) {
//            System.out.println("File Not Found!!!");
//            e.printStackTrace();
//        } catch (FileNotFoundException e){
//
//        }
//
//    }
