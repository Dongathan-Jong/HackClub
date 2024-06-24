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
  

}
