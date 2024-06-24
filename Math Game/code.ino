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
}
