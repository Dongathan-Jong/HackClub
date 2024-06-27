import java.util.*;
import java.lang.*;
import java.io.*;
/*--------------------------------------------------------------|
|Name: Atm                                                      |
|---------------------------------------------------------------|
|Programmer: Jonathan Dong                                      |
|---------------------------------------------------------------|
|Description: This program emulates a full working atm system.  |
|                                                               |
|                                                               |
|--------------------------------------------------------------*/
public class atm
{
   public static void main(String[] args)
   {
      // always looping menu
      while (true)
      {
         // create variables, array, and scanner
         int userPin;
         String userNumber;
         int counter;
         String[] values;
         String passBy = ",";
         String line;
         String fileName;
         Scanner sc = new Scanner(System.in);
         String currentLine;      
         userNumber=" ";
         userPin=0;
         
         // attempt to run code
         try
         {
            
            // prompt user for account number and store it in the variable
            System.out.print("Please enter an account number: ");
            
            userNumber = sc.nextLine(); 
            
            // get the filename by parsing the usernumber and adding .csv after it
            fileName = Integer.toString(Integer.parseInt(userNumber)) + ".csv";
         
            // create bufferedreader under new filename
            BufferedReader input = new BufferedReader(new FileReader(fileName));
            
            // get the first line of the csv file
            line = input.readLine();
            
            // set array to all the values in the line, splitting each cell by the character ","
            values = line.split(passBy, -1);
            
            // prompt user for pin and store it in variable
            System.out.print("Please enter the pin: ");
            userPin = sc.nextInt();
               
            // check if the pin entered is correct and go into menuofoptions method, or else tell user its wrong
            if (userPin == Integer.parseInt(values[1].replaceAll("\s+", "")))
            {
               // close reader and enter method
               input.close();
               menuOfOptions(Integer.parseInt(userNumber), values);
            }
            else
            {
               // tell user incorrect pin
               System.out.println("Incorrect pin!");
            }
            
         }
         // catch the file not found exception
         catch (FileNotFoundException e)
         {
            // enter the new profile method
            newProfile();
         }
         // catch the ioexception exception
         catch(IOException e)
         {
            // tell user something went wrong
            System.out.print(e + "ERROR!! Something went wrong while opening a file!");
         }
         // catch number format exception
         catch(NumberFormatException e)
         {
            // also go to new profile method
            newProfile();
         }
      }  
   }
   
   
/*--------------------------------------------------------------|
|Name:menuOfOptions(int userNumber, String values[])            |
|---------------------------------------------------------------|
|int userNumber - the account number of the user                |
|String values[] - the account details associated with the user |
|---------------------------------------------------------------|
|Description: This method is the main menu after the user logs  |
|             in, providing them with all the functions         |
|                                                               |
|--------------------------------------------------------------*/
   public static void menuOfOptions(int userNumber, String values[])
   {
      // create scanner and variables
      Scanner sc = new Scanner(System.in);
      int choice;
      boolean back = false;
   
      // do while loop
      do
      {
         // attempt to run the code
         try
         {
            // print out the menu of options
            System.out.println("Please select an option: ");
            System.out.println("     Check Balance[1]");
            System.out.println("     Deposit Money[2]");
            System.out.println("        Withdraw  [3]");
            System.out.println("     Close Account[4]");
            System.out.println("      New Account [5]");
            System.out.println("      Change PIN  [6]");
            System.out.println("        Log Out   [7]");
         
            // get a choice from the user
            choice = Integer.parseInt(sc.nextLine());
         
            // switch case for the user
            switch (choice)
            {
               case 1:
                  // enter checkbalance
                  checkBalance(userNumber);
                  break;
               case 2:
                  // enter deposit
                  deposit(userNumber, values);
                  break;
               case 3:
                  // enter withdraw
                  withdraw(userNumber, values);
                  break;
               case 4:
                  // enter closeaccount
                  closeAccount(Integer.toString(userNumber), values);
                  break;
               case 5:
                  // enter newaccount
                  newAccount(Integer.toString(userNumber), values);
                  break;
               case 6:
                  // enter changepin
                  changePin(Integer.toString(userNumber));
                  break;
               case 7:
                  // exit loop and tell user
                  System.out.println("Logging out... Returning to menu."); 
                  back = true;
                  break;
               default:
                  // tell user choice is not valid
                  System.out.println("Not a choice, please enter your choice corrseponding to the numbers 1-7");
            }
         }
         // catch number format exception
         catch (NumberFormatException e)
         {
            // tell user it needs to be proper
            System.out.println(e + ", Not an integer input, please enter your choice corresponding to the numbers 1-7");
         }
      
      } while (back != true);
   }
/*--------------------------------------------------------------|
|Name:closeAccount(String userNumber, String[] info)            |
|---------------------------------------------------------------|
|int userNumber - the account number of the user                |
|String values[] - the account details associated with the user |
|---------------------------------------------------------------|
|Description: This method will close a selected account by the  |
|             user, printing out the final balance and closing  |
|             it.                                               |
|--------------------------------------------------------------*/
   public static void closeAccount(String userNumber, String[] info)
   {
      // create variable
      String userChoice;
   
      try
      {
         // create bufferedwriter
         BufferedWriter out = new BufferedWriter(new FileWriter(userNumber + ".csv"));
      
         // create scanner
         Scanner sc = new Scanner(System.in);
      
         // check if user has both accounts
         if (Integer.parseInt(info[4]) != -1 && Integer.parseInt(info[5]) != -1)
         {
            // prompt user which one to close and store it in variable
            System.out.print("Which account would you like to close? \nPress 1 for Savings\nPress 2 for Chequings\nEnter your option: ");
            userChoice = sc.nextLine();
            
            // if user chooses savings, print final balance and close
            if (Integer.parseInt(userChoice) == 1)
            {
               System.out.print("Savings account closed, final balance: " + info[4]);
               info[4] = "-1";
            }
            // if user chooses chequings, print final balance and close
            else if (Integer.parseInt(userChoice) == 2)
            {
               System.out.print("Chequings account closed, final balance: " + info[5]);
               info[5] = "-1";
            }
            else
            {
               System.out.print("Invalid choice!");
            }
         }  
         // check if user only has chequings account, close and print final balance
         else if (Integer.parseInt(info[4]) == -1 && Integer.parseInt(info[5]) != -1)
         {
            System.out.print("Chequings account closed, final balance: " + info[5]);
            info[5] = "-1";
         }
         // check if user only has savings account, close and print final balance
         else if (Integer.parseInt(info[5]) == -1 && Integer.parseInt(info[4]) != -1)
         {
            System.out.print("Savings account closed, final balance: " + info[4]);
            info[4] = "-1";
         }
         
         // writes all the data to the user file, using a for loop and seperating each piece of data with a comma
         for (int i = 0; i < 6;i++)
         {
            out.write(info[i]);
            out.write(",");
         }
         // close the writer
         out.close();
      }
      // catch io exception and tell the user
      catch (IOException e)
      {
         System.out.print( e + "Error reading file: " + userNumber + ".csv !");
      }
      // catch inputmismatchexception and tell the user
      catch (InputMismatchException e)
      {
         System.out.print(e + "Please enter a valid option!");
      }
   }
/*--------------------------------------------------------------|
|Name:newAccount(String accountNumber, String values[])         |
|---------------------------------------------------------------|
|String accountNumber - the user's account number they entered  |
|String values[] - the account details associated with the user |
|---------------------------------------------------------------|
|Description: This method will create a new account that is     |
|             selected by the user, and set the balance to 0.   |
|                                                               |
|--------------------------------------------------------------*/
   
   public static void newAccount(String accountNumber, String values[])
   {
      try
      {
         // create variables, scanner, and bufferedwriter
         String accountType;
         String userChoice;
         Scanner sc = new Scanner(System.in);
         BufferedWriter writer = new BufferedWriter(new FileWriter(accountNumber+ ".csv"));
         
         // check if user has neither accounts, ask which one they want to create
         if (Double.parseDouble(values[4]) == -1 && Double.parseDouble(values[5]) == -1)
         {
            System.out.println("You have neither accounts, please enter an option: ");
            System.out.println("Option 1 : Savings, type '1'");
            System.out.println("Option 2 : Chequing, type '2'");
         
            accountType = sc.nextLine();
            
            // create savings or chequings as per user request
            switch (Integer.parseInt(accountType))
            {
               case 1:
               
                  System.out.println("Savings account created! Current balance: $0.00");
                  values[4] = "0.00";
                  break;
                  
               case 2:
               
                  System.out.println("Chequing account created! Current balance: $0.00");
                  values[5] = "0.00";
                  break;
            
               default:
               
                  System.out.print("Error!");
                  break;
            
            
            }
         }
         // if user already has savings, program will prompt for chequings and store it in variable
         else if (Double.parseDouble(values[4]) != -1 && Double.parseDouble(values[5]) == -1)
         {
            System.out.print("Are you sure you would like to create a Chequings account? Press 1 to confirm: ");
            userChoice = sc.nextLine();
            
            // creates the account and tells the user
            if (Integer.parseInt(userChoice) == 1)
            {
               System.out.println("Chequings account successfully created, current balance: $0.00");
               values[5] = "0.00";
            }
            else
            {
               System.out.print("Error!");
            }
         }
         // if user already has chequings account, program will prompt for savings and store it in variable
         else if (Double.parseDouble(values[5]) != -1 && Double.parseDouble(values[4]) == -1)
         {
            System.out.print("Are you sure you would like to create a Savings account? Press 1 to confirm: ");
            userChoice = sc.nextLine();
            
            // creates the account and tells the user
            if (Integer.parseInt(userChoice) == 2)
            {
               System.out.println("Savings acount successfully created, current balance: $0.00");
               values[4] = "0.00";
            }
            else
            {
               System.out.print("Error!");
            }
         }
      
         // writes the array into the file, seperating each piece of data with a comma for the csv
         for (int i = 0; i < 6;i++)
         {
            writer.write(values[i]);
            writer.write(",");
         }
         // close the bufferedwriter
         writer.close();
      }
      // catch the io exception and print out the error
      catch (IOException e)
      {
         System.out.print(e + "Error! Something went wrong when trying to access the filesystem.");
      }
      // catch the input mismatch exception and print out the error
      catch (InputMismatchException e)
      {
         System.out.print(e + "Please enter a valid number!");
      }
      
   
   }
/*--------------------------------------------------------------|
|Name:newProfile()                                              |
|---------------------------------------------------------------|
|                                                               |
|                                                               |
|---------------------------------------------------------------|
|Description:This method creates a new file for a new user.     |
|            it prompts the user for all the info required.     |
|                                                               |
|--------------------------------------------------------------*/
   
   public static void newProfile()
   {
      // create a random, scanner, and variables
      Random rnd = new Random();
      String userChoice;
      String userNewPin="";
      String userFirstName;
      String userLastName;
      Scanner sc = new Scanner(System.in);
      boolean repeat=true;
      boolean repeatPin=true;
      
      // prompt user if they would like to create new account
      System.out.print("Incorrect account number! Would you like to create a new account? Type 'yes' or 'no': ");
      
      // loop    
      while(repeat==true)
      {
         // store answer in userChoice
         userChoice = sc.nextLine();
         if (userChoice.compareTo("yes") == 0)
         {
            // create variable
            int accountNumber;
         
            try
            {
               // create a new number using the random, and add 100000 to make sure it does not start with a 0
               accountNumber = rnd.nextInt(899999) + 100000;
               
               // create new bufferedwriter 
               BufferedWriter writer = new BufferedWriter(new FileWriter(String.format("%06d", accountNumber) + ".csv"));
               
               // tell user new account number and prompt user for pin             
               System.out.println("Your account number is: " + String.format("%06d", accountNumber));
                  
               System.out.print("Please enter a 6 digit pin: ");
               
               
               while(repeatPin==true)
               {
                  // store the new pin in the variable
                  userNewPin = sc.nextLine();
                  try
                  {
                     // make sure the pin is 6 digits, and tell the user
                     if(Integer.parseInt(userNewPin)<100000 || Integer.parseInt(userNewPin)>999999)
                     {
                        System.out.print("Error: Pin is not 6 digits. Please enter a 6 digit pin: ");
                        int[] breaker= new int[-1];
                     }
                     repeatPin=false;
                  }
                  // catch numberformatexception and tell the user
                  catch(NumberFormatException e)
                  {
                     System.out.print("Error: Invalid pin. Please enter a 6 digit pin: ");
                  }
                  catch(NegativeArraySizeException e)
                  {
                  }
               }
               // prompt user for first and last name
               System.out.print("Please enter your first name: ");
               userFirstName = sc.nextLine();
                  
               System.out.print("Please enter your last name: ");
               userLastName = sc.nextLine();
               
               // set the string of values to correct format
               String[] data = {Integer.toString(accountNumber), userNewPin, userFirstName, userLastName, "-1", "-1"};
               
               // for loop to write the array into the file, seperated by commas
               for (int i = 0; i < 6;i++)
               {
                  writer.write(data[i]);
                  writer.write(",");
               }
               // close the writer and stop the loop
               repeat=false;
               writer.close();
            }
            // catch io exception and tell user
            catch (IOException e)
            {
               System.out.println(e + "ERROR!! Something went wrong while opening a file!");
            }
            // catch numberformat exception and tell the user
            catch(NumberFormatException e)
            {
               System.out.println("Please enter a valid option: yes or no");
            }
         }
         else if(userChoice.compareTo("no") == 0)
         {
            // dont do anything
            repeat=false;
         }
         else
         {
            {
               System.out.println("Error: Please enter yes or no");
            }
         }
      }
   }
/*--------------------------------------------------------------|
|Name:checkBalance(int userNumber)                              |
|---------------------------------------------------------------|
|int userNumber - the account number associated to the user     |
|                                                               |
|---------------------------------------------------------------|
|Description:This program will tell the user their balance in   |
|            their account from the specific account the user   |
|            specifies.                                         |
|--------------------------------------------------------------*/

   public static void checkBalance(int userNumber)
   {
      // create variables, scanner
      Scanner sc = new Scanner(System.in);
      int whichAccount;
      boolean validInput = false;
      int countLines = -1;
      int lineNumber = -1;
      String line;
      String splitBy = ",";
      
      try
      {
         // create bufferedreader and store first line in variable
         BufferedReader in = new BufferedReader(new FileReader(userNumber + ".csv"));
         line=in.readLine();
         
         // split first line into array by split method
         String[] info = line.split(splitBy, -1);
         
         // check if both accounts are existent and ask user for choice
         if (!info[4].equals("-1") && !info[5].equals("-1"))
         {
            System.out.println("Which account would you like to check the balance of?\n1. savings\n2. chequeings");
            whichAccount = sc.nextInt();
            while(validInput==false)
            {
               // print balance of whichever account user chooses
               switch(whichAccount)
               {
                  case 1:
                     System.out.println("Your savings account balance is: " + info[4]);
                     validInput=true;
                     break;
                  case 2:
                     System.out.println("Your chequeings account balance is: " + info[5]);
                     validInput=true;
                     break;
                  default:
                     System.out.println("Please enter a valid account name.\n1. savings\n2. chequeings");
                     break;
               }
            }
         }
         // tell user they dont have any accounts
         else if (info[4].equals("-1") && info[5].equals("-1"))
         {
            System.out.println("Error: You do not have any accounts. Please create one to check your balance.");
         }
         // check if they only have one account and tell them the balance of it
         else if (!info[4].equals("-1"))
         {
            System.out.println("Your savings account balance is: " + info[4]);
         }
         else
         {
            System.out.println("Your chequeings account balance is: " + info[5]);
         }
      }
      // catch io exception and tell the user the problem
      catch(IOException e)
      {
         System.out.println(e + "problem reading " + userNumber + ".csv");
      }
   }

/*--------------------------------------------------------------|
|Name:withdraw(int userNumber, String[] info)                   |
|---------------------------------------------------------------|
|int userNumber - the user's account number they entered        |
|String info[] - the account details associated with the user   |
|---------------------------------------------------------------|
|Description: This method will allow the user to withdraw money |
|             from any of their accounts, as long as they don't |
|             go into debt.                                     |
|--------------------------------------------------------------*/

   public static void withdraw(int userNumber, String[] info)
   {
      // create scanner, variables, and arrays
      Scanner sc = new Scanner(System.in);
      String whichAccount;
      boolean validInput = false;
      boolean validAmount = false;
      int countLines = -1;
      int lineNumber = -1;
      String line;
      String splitBy = ",";
      double amount=0;
      int intedAmount = -1;
      double[] detectNegative;
      double[] detectTooMuch;
      try
      {
         // create buffered reader and writer
         BufferedReader in = new BufferedReader(new FileReader(userNumber + ".csv"));
         BufferedWriter out = new BufferedWriter(new FileWriter(userNumber + ".csv", false));
      
         // check if user has both accounts 
         if (!info[4].equals("-1") && !info[5].equals("-1"))
         {
            System.out.println("Which account would you like to withdraw from?\n1. savings\n2. chequeings");
            while(validInput==false)
            {
               // store selection in variable 
               whichAccount = sc.nextLine();
               // switch case for each option, withdraw money and write it to the file
               switch(whichAccount)
               {
                  case "1":
                  // prompt user for how much to withdraw, and minus it from the balance 
                     System.out.println("How much would you like to withdraw?");
                     while(validAmount==false)
                     {
                        amount=Double.parseDouble(sc.nextLine());
                        if (amount<0)
                        {
                           System.out.println("Please enter a positive number.");
                        }
                        else if((Double.parseDouble(info[4])-amount)<0)
                        {
                           System.out.println("Please enter a number smaller than your current balance of $" + info[4]);
                        }
                        else
                        {
                           validAmount=true;
                        }
                     }
                     // write info into the file
                     info[4]=Double.toString(Math.round((Double.parseDouble(info[4])-amount)*100)/100.0);
                     out.write(info[0] + splitBy + info[1] + splitBy + info[2] + splitBy + info[3] + splitBy + info[4] + splitBy + info[5]);
                     validInput=true;
                     break;
                  case "2":
                  // prompt user for how much to withdraw, and minus it from the balance
                     System.out.println("How much would you like to withdraw?");
                     while(validAmount==false)
                     {
                        amount=Double.parseDouble(sc.nextLine());
                        if (amount<0)
                        {
                           System.out.println("Please enter a positive number.");
                        }
                        else if((Double.parseDouble(info[5])-amount)<0)
                        {
                           System.out.println("Please enter a number smaller than your current balance of $" + info[4]);
                        }
                        else
                        {
                           validAmount=true;
                        }
                     }
                     // write info into the file
                     info[5]=Double.toString(Math.round((Double.parseDouble(info[5])-amount)*100)/100.0);
                     out.write(info[0] + splitBy + info[1] + splitBy + info[2] + splitBy + info[3] + splitBy + info[4] + splitBy + info[5]);
                     validInput=true;
                     break;
                  default:
                     System.out.println("Please enter a valid account name.\n1. savings\n2. chequeings");
               }
            }
         }
         // tell user that user has no accounts
         else if (info[4].equals("-1") && info[5].equals("-1"))
         {
            System.out.println("Error: You do not have any accounts. Please create one to withdraw from.");
         }
         // if user only has chequing
         else if (!info[4].equals("-1"))
         {
            System.out.println("How much would you like to withdraw?");
            while(validAmount==false)
            {
               amount=Double.parseDouble(sc.nextLine());
               if (amount<0)
               {
                  System.out.println("Please enter a positive number.");
               }
               else if((Double.parseDouble(info[4])-amount)<0)
               {
                  System.out.println("Please enter a number smaller than your current balance of $" + info[4]);
               }
               else
               {
                  validAmount=true;
               }
            }
            // write to file
            info[4]=Double.toString(Math.round((Double.parseDouble(info[4])-amount)*100)/100.0);
            out.write(info[0] + splitBy + info[1] + splitBy + info[2] + splitBy + info[3] + splitBy + info[4] + splitBy + info[5]);
            validInput=true;
         }
            // if user only has savings
         else
         {
            System.out.println("How much would you like to withdraw?");
            while(validAmount==false)
            {
               amount=Double.parseDouble(sc.nextLine());
               if (amount<0)
               {
                  System.out.println("Please enter a positive number.");
               }
               else if((Double.parseDouble(info[5])-amount)<0)
               {
                  System.out.println("Please enter a number smaller than your current balance of $" + info[4]);
               }
               else
               {
                  validAmount=true;
               }
            }
            // write to file
            info[5]=Double.toString(Math.round((Double.parseDouble(info[5])-amount)*100)/100.0);
            out.write(info[0] + splitBy + info[1] + splitBy + info[2] + splitBy + info[3] + splitBy + info[4] + splitBy + info[5]);
            validInput=true;
         }
         // close the writer to save
         out.close();
      }
      // catch io exception and tell user
      catch(IOException e)
      {
         System.out.println(e + "problem reading" + userNumber + ".csv");
      }
   }
/*--------------------------------------------------------------|
|Name:deposit(int userNumber, String[] info)                    |
|---------------------------------------------------------------|
|int userNumber - the user's account number they entered        |
|String info[] - the account details associated with the user   |
|---------------------------------------------------------------|
|Description: This method will allow the user to deposit money  |
|             to any of their accounts.                         |
|                                                               |
|--------------------------------------------------------------*/
   public static void deposit(int userNumber, String[] info)
   {
      // create variables, scanner, and array
      Scanner sc = new Scanner(System.in);
      String whichAccount;
      boolean validInput = false;
      boolean validAmount = false;
      int countLines = -1;
      int lineNumber = -1;
      String line;
      String splitBy = ",";
      double amount=0;
      int intedAmount = -1;
      double[] detectNegative;
      
      try
      {
         // create buffered writer and reader
         BufferedReader in = new BufferedReader(new FileReader(userNumber+".csv"));
         BufferedWriter out = new BufferedWriter(new FileWriter(userNumber+".csv"));
         
         // if user has both accounts, prompt user for which one and store it in variable
         if (Double.parseDouble(info[4]) != -1 && Double.parseDouble(info[5]) != -1)
         {
            System.out.println("Which account would you like to deposit into?\n1. savings\n2. chequeings");
            while(validInput==false)
            {
               whichAccount = sc.nextLine();
               switch(whichAccount)
               {
               
                  case "1":
                     // if user wants to deposit into savings, prompt user for how much and make sure it is a positive amount and store it in array
                     System.out.println("How much would you like to deposit?");
                     while(validAmount==false)
                     {
                        amount=Double.parseDouble(sc.nextLine());
                        if (amount<0)
                        {
                           System.out.println("Please enter a positive number.");
                        }
                        else
                        {
                           validAmount=true;
                        }
                     }
                     // update the file by writing the array into the file
                     info[4]=Double.toString(Math.round((Double.parseDouble(info[4])+amount)*100)/100.0);
                     out.write(info[0] + splitBy + info[1] + splitBy + info[2] + splitBy + info[3] + splitBy + info[4] + splitBy + info[5]);
                     validInput=true;
                     break;
                  case "2":
                  // if user wants to deposit into chequings, prompt user for how much and make sure it is a positive amount and store it in array

                     System.out.println("How much would you like to deposit?");
                     while(validAmount==false)
                     {
                        amount=Double.parseDouble(sc.nextLine());
                        if (amount<0)
                        {
                           System.out.println("Please enter a positive number.");
                        }
                        else
                        {
                           validAmount=true;
                        }
                     }
                     // update the user file by writing info into the file
                     info[5]=Double.toString(Math.round((Double.parseDouble(info[5])+amount)*100)/100.0);
                     out.write(info[0] + splitBy + info[1] + splitBy + info[2] + splitBy + info[3] + splitBy + info[4] + splitBy + info[5]);
                     validInput=true;
                     break;
                  default:
                     // user did not enter a valid option
                     System.out.println("Please enter a valid account name.\n1. savings\n2. chequeings");
               }
            }
         }
         // if the user has no accounts, tell the user 
         else if (info[4].equals("-1") && info[5].equals("-1"))
         {
            System.out.println("Error: You do not have any accounts. Please create one to deposit into.");
         }
         // if user only has savings
         else if (!info[4].equals("-1"))
         {
            // prompt user and make sure the amount is over 0 and store it in variable
            System.out.println("How much would you like to desposit?");
            while(validAmount==false)
            {
               amount=Double.parseDouble(sc.nextLine());
               if (amount<0)
               {
                  System.out.println("Please enter a positive number.");
               }
               else
               {
                  validAmount=true;
               }
            }
            // update the file by writing the array to the file
            info[4]=Double.toString(Math.round((Double.parseDouble(info[4])+amount)*100)/100.0);
            out.write(info[0] + splitBy + info[1] + splitBy + info[2] + splitBy + info[3] + splitBy + info[4] + splitBy + info[5]);
            validInput=true;
         }
         else
         {
            // prompt user how much to deposit, make sure its positive, and store it in variable
            System.out.println("How much would you like to desposit?");
            while(validAmount==false)
            {
               amount=Double.parseDouble(sc.nextLine());
               if (amount<0)
               {
                  System.out.println("Please enter a positive number.");
               }
               else
               {
                  validAmount=true;
               }
            }
            // update the file by writing the array to the file
            info[5]=Double.toString(Math.round((Double.parseDouble(info[4])+amount)*100)/100.0);
            out.write(info[0] + splitBy + info[1] + splitBy + info[2] + splitBy + info[3] + splitBy + info[4] + splitBy + info[5]);
            validInput=true;         }
            // close the writer
         out.close();
      }
      // catch io exception and tell user something went wrong
      catch(IOException e)
      {
         System.out.println(e + "problem reading" + userNumber + ".csv");
      }
   }
/*--------------------------------------------------------------|
|Name:changePin(String userNumber)                              |
|---------------------------------------------------------------|
|String userNumber - the user's account number they entered     |
|                                                               |
|---------------------------------------------------------------|
|Description: This method will allow the user to deposit money  |
|             to any of their accounts.                         |
|                                                               |
|--------------------------------------------------------------*/
   public static void changePin(String userNumber)
   {
      // create scanner and variables
      Scanner sc = new Scanner(System.in);
      
      String splitBy = ",";
      String line;
      String choice = " ";
      
      boolean back = false;
      boolean stop = false;
      
      String newNumber;
      
      try
      {
         // create buffered reader and store first line in variable
         BufferedReader in = new BufferedReader(new FileReader(userNumber+".csv"));
         line = in.readLine();
         // split the line into the array and close the reader
         String[] info = line.split(splitBy);
         in.close();
         
         // create buffered writer
         BufferedWriter out = new BufferedWriter(new FileWriter(userNumber+".csv"));
         do
         {    
            // prompt user if they want to change their pin and store choice in variable 
            System.out.println("Do you want to edit your pin?\nTo confirm, Enter your full name to continue. Otherwise press 0 to exit.");
            choice=sc.nextLine();   
               
            
            if (choice.equalsIgnoreCase(info[2] + " " + info[3]))
            {
               do
               {  
                  try
                  {
                     // tell user to enter new pin and store value in variable
                     System.out.print("Enter your new 6 digit pin: ");
                     newNumber = sc.nextLine();
                     
                     // check if number a valid 6 digits
                     if (newNumber.length() >= 6 && newNumber.length() <= 6 && Integer.parseInt(newNumber) > 0)
                     {
                        // tell user the new pin and store it in the file by writing the array to the file
                        System.out.println("Your new 6 PIN code is " + newNumber);
                        info[1] = newNumber;
                        out.write(info[0] + splitBy + info[1] + splitBy + info[2] + splitBy + info[3] + splitBy + info[4] + splitBy + info[5]);
                        
                        // close the writer and exit the loop
                        out.close();
                        stop = true;
                     }
                     // if the integer is not 6 digits tell the user it needs to be of proper length
                     else if (Integer.parseInt(newNumber) < 0) 
                     {
                        System.out.println("New pin must be a positive 6 digit number, please try again.");
                     }
                     back=true;
                  }
                  // catch a numberformat exception and tell the user 
                  catch (NumberFormatException e)
                  {
                     System.out.println(e + "Not a positive 6 digit number, please try again and enter only integers.");
                  }
               } while (stop != true);
               
            }
            // tell user it is exiting and write the array to the file
            else if (choice.equals("0"))
            {
               System.out.println("Going back to menu of options... ");
               out.write(info[0] + splitBy + info[1] + splitBy + info[2] + splitBy + info[3] + splitBy + info[4] + splitBy + info[5]);
               out.close();
               back = true;
            }
            else
            {
               System.out.print("Not a valid input, please enter your first and last with a space inbetween or 0 to exit.");
            }
         } while (back != true);
      }
      // catch file not found exception and tell the user then exit the loop
      catch (FileNotFoundException e)
      {
         System.out.println(e + " User not found, going back to menu of options.");
         back=true;
      }
      // catch io exception and tell the user
      catch(IOException e)
      {
         
         System.out.println(e + "File not found");
      }
   }
   
}
