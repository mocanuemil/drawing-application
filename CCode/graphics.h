#ifndef graphics 
#define graphics

enum colour {black,blue,cyan,darkgray,gray,green,lightgray,magenta,orange,pink,red,white,yellow};
typedef enum colour colour;

void drawLine(int,int,int,int);
void drawRect(int,int,int,int);
void drawOval(int,int,int,int);
void drawArc(int,int,int,int,int,int);
void fillRect(int,int,int,int);
void drawString(char*,int,int);
void drawImage(char*,int,int,int,int);
void setTurtle(int,int);
void turtleLeft(int);
void turtleRight(int);
void turtleForward(int);

void setColour(colour);
void setBackgroundColour(colour);
void setImagePattern(char*);

#endif 