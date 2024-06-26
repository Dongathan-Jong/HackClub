
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
         // catch number format exception
         catch (NumberFormatException e)
         {
            // tell user it needs to be proper
            System.out.println(e + ", Not an integer input, please enter your choice corresponding to the numbers 1-7");
         }
      
      } while (back != true);
   }

