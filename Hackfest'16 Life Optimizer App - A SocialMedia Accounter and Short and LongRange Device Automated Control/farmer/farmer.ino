char incomingByte;  // incoming data
int  LED = 12;      // LED pin
 
void setup() {
 // initialization
  pinMode(3, INPUT);
  pinMode(13,OUTPUT);
  digitalWrite(13,LOW);
}
 
void loop() {                    
  while(true){
    if(digitalRead(3) == HIGH){
      digitalWrite(13,HIGH);
      break;
    }
    }
  }  

