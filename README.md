# COMP2042_CW_hcykt2
Java Coursework
Name : Kyrel Tan Yan Hee
Course : Computer Science with Artificial Intelligence 
OWA: hcykt2

---------How To Run
. Open up the project on IntelliJ IDEA after extracting the zip file
. Open the GraphicsMain Class
. And Click On the Green Play Button
. The Game will Start once the Green Play Button is Clicked

---------How To Play
. To shoot ball, press 'Spacebar' key
. To move the player left, press 'A' key
. To move the player right, press 'D' key
. Move the player so that the ball does not fall
. Collect "small black squares" to get extra points

---------Git Use
. Record of commit history
. Meaningful commit messages are used when naming the commit

---------Refactoring
. All classes are arranged accordingly by packages
. Some variables such as radiusA and radiusB are removed and renamed to just one variable "radius"
. Large Classes are broken down to enhance cohesion such as crack class is extracted from brick class
. All unused resources are deleted
. Some of the classes are arranged in MVC pattern
  . Brick Class
  . Ball Class
  . Player Class
. Some Design patterns are also applied
  . Factory method on Brick Class and Ball Class to allow subclass which is GetBrickFactory and GetBallFactory to choose the type of objects to be created.
  . Singleton on Player Class to restrict the instantiation of player class and ensures that only one instance of player class
. Meaningful JUnit test are added and tested
  . BallControlTest
  . BrickControlTest
  . PlayerTest
  . WallTest
 
--------Additions
. Added new level and two new bricks which is BombBrick and DiamondBrick, BombBrick will end the game immediately once it has impact with the ball and DiamondBrick has the strongest strength among all of the bricks
. Added Instruction screen where it can be access through the Home Menu and the Pause Menu
. Added Leaderboard screen which stores high scores and users are able to view it after the game
. Added Pop-up dialog which will appear after losing or winning the game to take name input to store in the Leaderboard
. Added 'Options' choice in Pause menu to allow users to skip level, change ball speed or reset ball

--------Javadocs Documentation
Javadocs comments are added to most of the classes and methods to explain what are the functions for that particular methods and classes
