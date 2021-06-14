# Need for Java

**Word/time limit:** N/A
Weighting: 50%
Due date: 5pm AEST Sunday 20 June 2021 (Week 6)

After you have read this information, head over to the Assessment 2 Q&A discussion in Ed to ask any questions and see what your peers are saying about this assessment.

## Assessment overview

This assessment is a mandatory submission, which means that in order to pass this unit of study, you must submit this assessment. This is an individual assessment task and must be your own original work. You must attribute the source of any part of your code that you have not written yourself. Please note the section on plagiarism in this document. 

This assessment task will require you to write a program to play the game 'Need for Java'. You will be supplied with a series of specifications that your program will need to meet.

**Note:** You will also be required to complete an interview with your OLA during Holiday Week 1, in which you will demonstrate your program and explain your code and design.

This assessment supports unit learning outcomes 1, 2, & 3.

## Assessment details

**You will complete this assessment in the workspace environment within Ed.**

In this assessment, you will write a program to play the game ‘Need for Java’. The objective of the game is for the player (human) to control a car on a highway. The player must dodge obstacles on the highway and outrun the authorities that are pursuing them. The player begins the game on a random lane on a three-lane highway. With each turn, the player has to make a choice between a few alternatives in order to attempt to escape the authorities. While traversing the highway, the player will encounter obstacles, some of which offer benefits and others that should be avoided. The vehicle the player starts with has certain characteristics such as boost speed, max fuel, and damage sustainable. These characteristics determine the success or failure of the pursuit. The pursuing authorities will attempt to stop the player at any cost. The game ends when the player sustains damage greater than the maximum sustainable to their vehicle, runs out of fuel, or manages to get to the end of the highway.

With this assessment, you are free to choose your own design to achieve the outcome. However, should there be questions or aspects of the assessment which require further clarification, please use the discussion forums to seek help from your OLA. You are allowed to add any additional aspects to the assessment to make it more engaging provided you do not miss out on any of the requirements outlined within this specification document. Select the following headings for details on the gameplay that is required and other program requirements.

## Gameplay

* The following criteria outline the gameplay specifications your program will need to meet:

* When the game starts, the program reads the file ‘vehicles.txt’. This file contains the various vehicles which can be used as the escape vehicle by the player on the highway. Each vehicle in the file has the type, boost speed, max fuel allowed, and max sustainable damage in each line of the file.

* The game begins by requesting the player enter their name. This name cannot be blank and can only be between 3 and 12 characters (inclusive) in length. If these requirements are not met, the player should be asked to re-enter their name until the requirement has been met.

* The player selects from a difficulty level, with the corresponding specifications for each level as follows:

  * **Easy**

    * Highway length: randomly selected between 10 and 15

    * Max fuel for vehicle of choice: 100%

    * Number of obstacles across three lanes of highway: 12

  * **Moderate**

    * Highway length: randomly selected between 15 and 30

    * Max fuel for vehicle of choice: 80%

    * Number of obstacles across three lanes of highway: 24

  * **Hard**

    * Highway length: randomly selected between 30 and 50

    * Max fuel for vehicle of choice: 50%

    * Number of obstacles across three lanes of highway: 45

  * The player is then provided an option to select their escape vehicle which is read from the file.

  * Once the player has selected their choice of vehicle they are informed of the length of highway and the current fuel present in their vehicle.

  * The game should then randomly generate all the various obstacles which will be present on the highway based on the difficulty level. (Students can use collections to achieve this concept). **There will be 1/3 chance of appearing the obstacle in each lane.  No obstacles must be placed within the first 3 sections of the highway.**

  * The game will then only show at the most 10 sections of the highway to the player at any point of time including all obstacles on each lane.

  * The player begins the game randomly placed on any lane of the highway on the first section. The player can be represented using the character ‘@’

  * The player is given the following options they can perform:

    * **Move forward**

      * This option allows the player to move forward one section on the highway. This move is always in the same lane and costs 1 fuel point.

    * **Swerve up**

      * This option allows the player to move forward one section on the highway. However, this move allows the player to change to the lane above their current lane. This move is only applicable if the player is not in the topmost lane of the highway. This move costs 2 fuel points.

    * **Swerve down**

      * This option allows the player to move forward one section on the highway. However, this move allows the player to change to the lane below their current lane. This move is only applicable if the player is not in the lowest lane on the highway. This move costs 2 fuel points.

    * **Boost**

      * This option allows the player to move forward **x** number of sections. The **x** is determined based on the vehicle’s boost speed. This move costs **x** * 3 fuel points.

  * While traversing the high way the player can encounter some of the following obstacles which are randomly placed along the highway:

    * **Fuel**

      * Provides the player with 10 fuel points

      * The probability of occurring is 30%

      * Represented in the game as an ‘**F**”

    * **Roadblock**

      * Cause 20 damage to the player’s vehicle

      * The probability of occurring is 40%

      * Represented in the game as an ‘**B**'

    * **Tyre Spikes**

      * Cause 45 damage to the player’s vehicle

      * Probability of occurring is 20%

      * Represented in the game as an ‘**S**’

    * **Open manhole**

      * Cause 60 damage to the player’s vehicle

      * The Probability of occurring is 10%

      * Represented in the game as an ‘**O**’

    * During gameplay, if the player crosses an obstacle, the penalty or benefit is applied to the player, irrespective of whether the player ends up in a position in front of the obstacle once their move has been completed. If the fuel is picked up by the player, the total fuel cannot exceed the maximum capacity for the player's vehicle.

    * The player must navigate the length of highway without sustaining damage greater than their vehicle can sustain, or run out of fuel before reaching the end of the highway.

    * On completion of the game, the game should write the distance covered and if the player managed to escape to a file called ‘output.txt’. Feel free to make descriptive outcomes which are written to the file, should the player not succeed in escaping.

## Requirements

You must use the workspace environment in the Ed platform to code all parts of your program, and must not copy paste huge chunks of code from somewhere else.

Ensure that your program code meets the coding standard requirements outlined in the course, that you use appropriate collections within your program, and are able to justify your choice. You must appropriate good design within your program by correctly applying abstraction and modularisation techniques.

Your program must only read and write to the file one time (at the start of the program to read and at the end of the program to write). Ensure that your program uses exception handling correctly as it must not crash no matter what the user enters. Finally, the main class in your program must be called Game.java and it should contain the main() method to start the program.

## Hints and suggestions

Get your program working with a single lane highway first. You can then add any more that you would like. Remember to set your highway length to be a smaller number at the start, and use the numbers detailed in the specifications later.

## Questions and clarifications

This assessment has an open design that can be adopted by students to achieve the outcome. However, should there be questions or aspects of the assessment which require further clarification, please use the Assessment 2: Project discussion forum to seek help from your OLA. You are allowed to add any additional aspects to the assessment to make it more engaging, provided you do not miss out on any of the requirements outlined within this specification document.

## Interview

You will be asked to demonstrate your program at an interview following the submission date. At the interview you will be asked to explain you code/design, modify your code, and discuss your design decisions and alternatives. Marks will not be awarded for any section of your code/design/functionality that you cannot explain satisfactorily (the marker may also delete excessive in-code comments before you are asked to explain the code). The interview will focus on your understanding of the code, not the actual code itself.

## Assessment criteria

Coding standards (5%)

Design (45%)

Functionality (50%).

Your work will be assessed using the assessment criteria described, more details on this can be found within the assessment section of Ed.
