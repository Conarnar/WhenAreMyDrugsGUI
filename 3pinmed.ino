#include <time.h>
#include <TimeLib.h>

int ledPin1 = 12; 
int ledPin2 = 10; 
int ledPin3 = 8; 
int blinkTime = 500; 
int input_minutes = 1; 
// unsigned long range is from 0 to 4,294,967,295 ms or about 1193 hours, 60000 ms = 1 min 
unsigned long input_time = input_minutes * 60000; 

void setup() {
  // put your setup code here, to run once
  pinMode(ledPin1, OUTPUT); 
  pinMode(ledPin2, OUTPUT); 
  pinMode(ledPin3, OUTPUT); 

  delay(input_time); 
  blinkyBlinky3(5, blinkTime);
  delay(input_time); 
  blinkyBlinky2(5, blinkTime); 
  delay(input_time); 
  blinkyBlinky1(5, blinkTime); 

}

void loop() {
  //
}

void blinkyBlinky3(int repeats, int time)
{
  for (int i = 0; i < repeats; i++)
  {
    digitalWrite(ledPin3, HIGH);
    delay(time);
    digitalWrite(ledPin3, LOW);
    delay(time);
  }
}

void blinkyBlinky2(int repeats, int time)
{
  for(int j = 0; j < repeats; j++) {
    digitalWrite(ledPin2, HIGH);
    delay(time); 
    digitalWrite(ledPin2, LOW); 
    delay(time); 
  } 
}

void blinkyBlinky1(int repeats, int time) 
{
  for(int k = 0; k < repeats; k++) {
    digitalWrite(ledPin1, HIGH); 
    delay(time); 
    digitalWrite(ledPin1, LOW); 
    delay(time); 
  }
    
}
