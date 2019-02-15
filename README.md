# Fika - Assignment 3

Requirements 

    •	At least five classes have been created and used 
    •	At least one inheritance has been implemented
    •	At least two active threads are used
    •	Mechanisms for synchronization are used in an appropriate way


Objects: PersonBehavior, Person, CoffeeMachine, Coffee (Subclass, BlackCoffee, Cappuccino, Latte).

PersonBehavior (implements runnable) 

    •	Functions for controlling behaviors (if energyLevel is 0, < 30, > 100).
    •	Handel thread states (stop, suspend, sleep).

Person

    •	int energyLevel, String name.
    •	Functions getters and setters(energyLevel)

CoffieMachine

    •	ArrayList<Coffee> reservedCoffee
    •	Create new coffee objects and add it to reservedCoffee.
    •	Randomly generate 5 new coffees with a 20% chance after consumed a coffee. (if ArrayList is decreased by 1 run function) 
    •	Function for drinkingCoffe should be synchronized (can only be used one at the time)
  
Coffee (Subclasses)

    •	BlackCoffee
       o	int energyLevel (rand 20 - 40)
    •	Cappuccino
       o	int energyLevel (rand 20 - 30)
    •	Latte
       o	int energyLevel (rand 25 - 35)
    
Threads: One thread for each active person.

    •	Behavior 
  
       o	When energyLevel is greater then 100 set state suspend
       o	When energyLevel is less then 30 set state resume
       o	When energyLevel is equal with 0 set state stop
       o	Each person loses 10 energyLevel each second (sleep (10,000)) 
    

Beginner settings

    •	ArrayList<Coffee> reservedCoffee obtains 20 random coffees 
    •	Each person has an energyLevel between 30 – 90.	
  


