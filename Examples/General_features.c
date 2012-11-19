#include "graphics.h"

int main(void)
{
  setBackgroundColour(yellow);
  drawArc(150,200,70,50,30,50);
  setImagePattern("http://www.robots.ox.ac.uk/~vgg/research/flowers_demo/images/flower_4.jpg");
  drawOval(50,50,30,20);
  drawString("Hello World",300,300);
  drawImage("http://www.robots.ox.ac.uk/~vgg/research/flowers_demo/images/flower_4.jpg",200,200,50,50);
  return 0; 
}