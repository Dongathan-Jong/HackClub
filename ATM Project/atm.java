
public class atm
{
   public static void main(String[] args)
   {
      
      while (true)
      {
         
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
         
         
         try
         {
            
            
            System.out.print("Please enter an account number: ");
            
            userNumber = sc.nextLine(); 
            
        
            fileName = Integer.toString(Integer.parseInt(userNumber)) + ".csv";
         
           
            BufferedReader input = new BufferedReader(new FileReader(fileName));
            
            line = input.readLine();
            
            values = line.split(passBy, -1);
            
            System.out.print("Please enter the pin: ");
            userPin = sc.nextInt();
               
            if (userPin == Integer.parseInt(values[1].replaceAll("\s+", "")))
            {
               input.close();
               menuOfOptions(Integer.parseInt(userNumber), values);
            }
            else
            {
               System.out.println("Incorrect pin!");
            }
            
         }
         catch (FileNotFoundException e)
         {
            newProfile();
         }
         catch(IOException e)
         {
            System.out.print(e + "ERROR!! Something went wrong while opening a file!");
         }
         catch(NumberFormatException e)
         {
            newProfile();
         }
      }  
   }


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
         catch (NumberFormatException e)
         {
            System.out.println(e + ", Not an integer input, please enter your choice corresponding to the numbers 1-7");
         }
      
      } while (back != true);
   }

public static void closeAccount(String userNumber, String[] info)
   {
      String userChoice;
   
      try
      {
         BufferedWriter out = new BufferedWriter(new FileWriter(userNumber + ".csv"));
      
         Scanner sc = new Scanner(System.in);
      
         if (Integer.parseInt(info[4]) != -1 && Integer.parseInt(info[5]) != -10)
         {
            System.out.print("Which account would you like to close? \nPress 1 for Savings\nPress 2 for Chequings\nEnter your option: ");
            userChoice = sc.nextLine();
            
            if (Integer.parseInt(userChoice) == 1)
            {
               System.out.print("Savings account closed, final balance: " + info[45]);
               info[4] = "-1";
            }
            else if (Integer.parseInt(userChoice) == 2)
            {
               System.out.print("Chequings account closed, final balance: " + info[5]);
               info[5] = "-1";
            }
            else
            {
               System.out.print("Invalid choie!");
            }
         else if (Integer.parseIt(info[4]) == -1 && Integer.parseInt(info[5]) != -1)
         {
            System.out.print"Chequings account closed, final balance: " + info[5]);
            info[5] = "-1";
         }
         else if (Integer.parseInt(info[5]) == -1 && Integer.parseInt(info[4]) != -1)
         {
            System.out.print("Savings account closed, final balance: " + info[4]);
            info[4] = "-1";
         }
         
         for (int i = 0; i < 6;i++)
         {
            out.write(info[i]);
            out.write(",");
         out.close();
      }
      catch (IOException e)
      {
         System.out.print( e + "Error reading file: " + userNumber + ".csv !");
      }
      catch (InputMismatchException e)
      {
         System.out.print(e + "Please enter a valid option!");
      }
   }

   public static void newAccount(String accountNumber, String values[])
   {
      try
      {
         String accountType;
         String userChoice;
         Scanner sc = new Scanner(System.in);
         BufferedWriter writer = new BufferedWriter(new FileWriter(accountNumber+ ".csv"));
         
         if (Double.parseDouble(values[4]) == -1 && Double.parseDouble(values[5]) == -1)
         {
            System.out.println("You have neither accounts, please enter an option: ");
            System.out.println("Option 1 : Savings, type '1'");
            System.out.println("Option 2 : Chequing, type '2'");
         
            accountType = sc.nextLine();
            
            switch (Integer.parseInt(accountType))
            {
               case 1:
               
                  System.out.println("Savings account created! Current balance: $0.00");
                  values[3] = "0.00";
                  break;
                  
               case 2:
               
                  System.out.println("Chequing account created! Current balance: $0.00");
                  values[4] = "0.00";
                  break;
            
               default:
               
                  System.out.print("Error!");
                  break;
            
            
            }
         }
         else if (Double.parseDouble(values[4]) != -1 && Double.parseDouble(values[5]) == -1)
         {
            System.out.print("Are you sure you would like to create a Chequings account? Press 1 to confirm: ");
            userChoice = sc.nextLine();
            
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
         else if (Double.parseDouble(values[5]) != -1 && Double.parseDouble(values[4]) == -1)
         {
            System.out.print("Are you sure you would like to create a Savings account? Press 1 to confirm: ");
            userChoice = sc.nextLine();
            
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
      
         for (int i = 0; i < 6;i++)
         {
            writer.write(values[i]);
            writer.write(",");
         }
         writer.close();
      }
      catch (IOException e)
      {
         System.out.print(e + "Error! Something went wrong when trying to access the filesystem.");
      }
      catch (InputMismatchException e)
      {
         System.out.print(e + "Please enter a valid number!");
      }
      
   
   }

    public static void newProfile()
   {
      Random rnd = new Random();
      String userChoice;
      String userNewPin="";
      String userFirstName;
      String userLastName;
      Scanner sc = new Scanner(System.in);
      boolean repeat=true;
      boolean repeatPin=true;
      
      System.out.print("Incorrect account number! Would you like to create a new account? Type 'yes' or 'no': ");
      
      while(repeat==true)
      {
         userChoice = sc.nextLine();
         if (userChoice.compareTo("yes") == 0)
         {
            int accountNumber;
         
            try
            {
               accountNumber = rnd.nextInt(899999) + 100000;
               
               BufferedWriter writer = new BufferedWriter(new FileWriter(String.format("%06d", accountNumber) + ".csv"));
                         
               System.out.println("Your account number is: " + String.format("%06d", accountNumber));
                  
               System.out.print("Please enter a 6 digit pin: ");
               
               
               while(repeatPin==true)
               {
                  userNewPin = sc.nextLine();
                  try
                  {
                     if(Integer.parseInt(userNewPin)<100000 || Integer.parseInt(userNewPin)>999999)
                     {
                        System.out.print("Error: Pin is not 6 digits. Please enter a 6 digit pin: ");
                        int[] breaker= new int[-1];
                     }
                     repeatPin=false;
                  }
                  catch(NumberFormatException e)
                  {
                     System.out.print("Error: Invalid pin. Please enter a 6 digit pin: ");
                  }
                  catch(NegativeArraySizeException e)
                  {
                  }
               }
               System.out.print("Please enter your first name: ");
               userFirstName = sc.nextLine();
                  
               System.out.print("Please enter your last name: ");
               userLastName = sc.nextLine();
               
               String[] data = {Integer.toString(accountNumber), userNewPin, userFirstName, userLastName, "-1", "-1"};
               
               for (int i = 0; i < 6;i++)
               {
                  writer.write(data[i]);
                  writer.write(",");
               }
               repeat=false;
               writer.close();
            }
            catch (IOException e)
            {
               System.out.println(e + "ERROR!! Something went wrong while opening a file!");
            }
            catch(NumberFormatException e)
            {
               System.out.println("Please enter a valid option: yes or no");
            }
         }
         else if(userChoice.compareTo("no") == 0)
         {
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

   public static void checkBalance(int userNumber)
   {
      Scanner sc = new Scanner(System.in);
      int whichAccount;
      boolean validInput = false;
      int countLines = -1;
      int lineNumber = -1;
      String line;
      String splitBy = ",";
      
      try
      {
         BufferedReader in = new BufferedReader(new FileReader(userNumber + ".csv"));
         line=in.readLine();
         
         String[] info = line.split(splitBy, -1);
         
         if (!info[4].equals("-1") && !info[5].equals("-1"))
         {
            System.out.println("Which account would you like to check the balance of?\n1. savings\n2. chequeings");
            whichAccount = sc.nextInt();
            while(validInput==false)
            {
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
         else if (info[4].equals("-1") && info[5].equals("-1"))
         {
            System.out.println("Error: You do not have any accounts. Please create one to check your balance.");
         }
         else if (!info[4].equals("-1"))
         {
            System.out.println("Your savings account balance is: " + info[4]);
         }
         else
         {
            System.out.println("Your chequeings account balance is: " + info[5]);
         }
      }
      catch(IOException e)
      {
         System.out.println(e + "problem reading " + userNumber + ".csv");
      }
   }

 public static void withdraw(int userNumber, String[] info)
   {
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
         BufferedReader in = new BufferedReader(new FileReader(userNumber + ".csv"));
         BufferedWriter out = new BufferedWriter(new FileWriter(userNumber + ".csv", false));
      
         // check if user has both accounts 
         if (!info[4].equals("-1") && !info[5].equals("-1"))
         {
            System.out.println("Which account would you like to withdraw from?\n1. savings\n2. chequeings");
            while(validInput==false)
            {
               whichAccount = sc.nextLine();
               switch(whichAccount)
               {
                  case "1":
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
                     info[4]=Double.toString(Math.round((Double.parseDouble(info[4])-amount)*100)/100.0);
                     out.write(info[0] + splitBy + info[1] + splitBy + info[2] + splitBy + info[3] + splitBy + info[4] + splitBy + info[5]);
                     validInput=true;
                     break;
                  case "2":
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
                     info[5]=Double.toString(Math.round((Double.parseDouble(info[5])-amount)*100)/100.0);
                     out.write(info[0] + splitBy + info[1] + splitBy + info[2] + splitBy + info[3] + splitBy + info[4] + splitBy + info[5]);
                     validInput=true;
                     break;
                  default:
                     System.out.println("Please enter a valid account name.\n1. savings\n2. chequeings");
               }
            }
         }
         else if (info[4].equals("-1") && info[5].equals("-1"))
         {
            System.out.println("Error: You do not have any accounts. Please create one to withdraw from.");
         }
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
            info[4]=Double.toString(Math.round((Double.parseDouble(info[4])-amount)*100)/100.0);
            out.write(info[0] + splitBy + info[1] + splitBy + info[2] + splitBy + info[3] + splitBy + info[4] + splitBy + info[5]);
            validInput=true;
         }
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
            info[5]=Double.toString(Math.round((Double.parseDouble(info[5])-amount)*100)/100.0);
            out.write(info[0] + splitBy + info[1] + splitBy + info[2] + splitBy + info[3] + splitBy + info[4] + splitBy + info[5]);
            validInput=true;
         }
         out.close();
      }
      catch(IOException e)
      {
         System.out.println(e + "problem reading" + userNumber + ".csv");
      }
   }

public static void deposit(int userNumber, String[] info)
   {
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
         BufferedReader in = new BufferedReader(new FileReader(userNumber+".csv"));
         BufferedWriter out = new BufferedWriter(new FileWriter(userNumber+".csv"));
         
         if (Double.parseDouble(info[4]) != -1 && Double.parseDouble(info[5]) != -1)
         {
            System.out.println("Which account would you like to deposit into?\n1. savings\n2. chequeings");
            while(validInput==false)
            {
               whichAccount = sc.nextLine();
               switch(whichAccount)
               {
               
                  case "1":
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
                     info[4]=Double.toString(Math.round((Double.parseDouble(info[4])+amount)*100)/100.0);
                     out.write(info[0] + splitBy + info[1] + splitBy + info[2] + splitBy + info[3] + splitBy + info[4] + splitBy + info[5]);
                     validInput=true;
                     break;
                  case "2":

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
                     info[5]=Double.toString(Math.round((Double.parseDouble(info[5])+amount)*100)/100.0);
                     out.write(info[0] + splitBy + info[1] + splitBy + info[2] + splitBy + info[3] + splitBy + info[4] + splitBy + info[5]);
                     validInput=true;
                     break;
                  default:
                     System.out.println("Please enter a valid account name.\n1. savings\n2. chequeings");
               }
            }
         }
         else if (info[4].equals("-1") && info[5].equals("-1"))
         {
            System.out.println("Error: You do not have any accounts. Please create one to deposit into.");
         }
         else if (!info[4].equals("-1"))
         {
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
            info[4]=Double.toString(Math.round((Double.parseDouble(info[4])+amount)*100)/100.0);
            out.write(info[0] + splitBy + info[1] + splitBy + info[2] + splitBy + info[3] + splitBy + info[4] + splitBy + info[5]);
            validInput=true;
         }
         else
         {
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
            info[5]=Double.toString(Math.round((Double.parseDouble(info[4])+amount)*100)/100.0);
            out.write(info[0] + splitBy + info[1] + splitBy + info[2] + splitBy + info[3] + splitBy + info[4] + splitBy + info[5]);
            validInput=true;     
         out.close();
      }
      catch(IOException e)
      {
         System.out.println(e + "problem reading" + userNumber + ".csv");
      }
   }
