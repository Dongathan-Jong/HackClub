#include <LiquidCrystal_I2C.h>

// C++ code
//
#define left 2
#define middle 3
#define right 4

int firstInt;
int secondInt;

int answers = 0;

int currentDigit = 0;
int digitPlace = 0;


boolean questionSelection = true;
boolean answer = false;

int option;

LiquidCrystal_I2C lcd(0x20,  16, 2);

void setup()
{
  lcd.init();
  lcd.backlight();
  Serial.begin(9600);
  pinMode(left, INPUT_PULLUP);
  pinMode(middle, INPUT_PULLUP);
  pinMode(right, INPUT_PULLUP);
  Serial.begin(9600);
  randomSeed(analogRead(0));

}

void loop(){
  if(questionSelection)
  {
    lcd.setCursor(0,0);
    lcd.print("Select a mode:");
  	if(digitalRead(left) == 0)
  	{
      delay(100);
  	  option = 1;
      questionSelection = false;
      firstInt = random(499);
      secondInt =  random(499);
      lcd.clear();
  	}
  	else if(digitalRead(middle) == 0)
  	{
      delay(100);
  	  option = 2;
      questionSelection = false;
      firstInt = random(500,1000);
      secondInt = random(250,499);
      lcd.clear();
  	}
  	else if(digitalRead(right) == 0)
  	{
      delay(100);
  	  option = 3;
      questionSelection = false;
      firstInt = random(10,30);
      secondInt = random(10,30);
      lcd.clear();
  	}
    
  }
  
  
  if(questionSelection == false && answer == false)
  {
  	lcd.setCursor(0,0);
  	
  	lcd.print(firstInt);
  	if(option == 1)
  	{
  	  lcd.print(" + ");
  	}
  	else if(option == 2)
  	{
  	  lcd.print(" - ");
  	}
  	else
  	{
  	  lcd.print(" x ");
  	}
  	lcd.print(secondInt);
    lcd.print("= ?");
    answer = true;
  }
  
  if(answer)
  {
    
    if(digitalRead(middle) == 0)
    {
      currentDigit++;
      delay(100);
    }
    if(currentDigit == 10)
    {
      currentDigit = 0;
    }
    lcd.setCursor(digitPlace,1);
    lcd.print(currentDigit);
    
    if(digitalRead(right) == 0 && digitPlace < 2)
    {
      delay(100);
      if(digitPlace == 0)
      {
        answers = (currentDigit * 100);
      }
      if(digitPlace == 1)
      {
        answers = answers + (currentDigit * 10);
      }
      if(digitPlace == 2)
      {
        answers = answers + currentDigit;
      }
      
      currentDigit = 0;
      if(digitPlace < 2)
      {
      	digitPlace++;
      }
      
    }
    Serial.println(answers);
    
    if(digitalRead(left) == 0)
    {
      answers = answers + currentDigit;
      
      if(option == 1)
      {
        if(firstInt+secondInt == answers)
        {
          lcd.clear();
          lcd.print("Correct!!!");
          delay(500);
          answer = false;
          questionSelection = true;
          lcd.clear();
        }
        else
        {
          lcd.clear();
          lcd.print("Incorrect!!!");
          delay(500);
          answer = false;
          questionSelection = true;
          lcd.clear();
        }
      }
      if(option == 2)
      {
        if(firstInt-secondInt == answers)
        {
          lcd.clear();
          lcd.print("Correct!!!");
          delay(500);
          answer = false;
          questionSelection = true;
          lcd.clear();
        }
        else
        {
          lcd.clear();
          lcd.print("Incorrect!!!");
          delay(500);
          answer = false;
          questionSelection = true;
          lcd.clear();
        }
      }
      if(option == 3)
      {
        if(firstInt*secondInt == answers)
        {
          lcd.clear();
          lcd.print("Correct!!!");
          delay(500);
          answer = false;
          questionSelection = true;
          lcd.clear();
        }
        else
        {
          lcd.clear();
          lcd.print("Incorrect!!!");
          delay(500);
          answer = false;
          questionSelection = true;
          lcd.clear();
        }
      }
      delay(100);
      
      currentDigit = 0;
      digitPlace = 0;
      
    }
  }
  

}
