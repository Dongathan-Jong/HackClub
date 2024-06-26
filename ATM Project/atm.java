
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
