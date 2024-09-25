/*
* Problem 1: Escape Room
* 
* V1.0
* 10/10/2019
* Copyright(c) 2019 PLTW to present. All rights reserved
*/
import java.util.Scanner;

public class EscapeRoom
{

      // describe the game with brief welcome message
      // determine the size (length and width) a player must move to stay within the grid markings
      // Allow game commands:
      //    right, left, up, down: if you try to go off grid or bump into wall, score decreases
      //    jump over 1 space: you cannot jump over walls
      //    if you land on a trap, spring a trap to increase score: you must first check if there is a trap, if none exists, penalty
      //    pick up prize: score increases, if there is no prize, penalty
      //    help: display all possible commands
      //    end: reach the far right wall, score increase, game ends, if game ended without reaching far right wall, penalty
      //    replay: shows number of player steps and resets the board, you or another player can play the same board
      // Note that you must adjust the score with any method that returns a score
      // Optional: create a custom image for your player use the file player.png on disk
    
      /**** provided code:
      // set up the game
      boolean play = true;
      while (play)
      {
        // get user input and call game methods to play 
        play = false;
      }
      */

  public static void main(String[] args) 
  {      
    GameTimerUtil gameTimer = new GameTimerUtil(10); // 60 seconds
    gameTimer.start();
    // Start the countdown
    // welcome message
    String name;
    System.out.println("Welcome to EscapeRoom!");
    System.out.println("Get to the other side of the room, avoiding walls and invisible traps,");
    System.out.println("pick up all the prizes.\n");
    
    GameGUI game = new GameGUI();
    game.createBoard();

    // size of move
    int m = 60; 
    // individual player moves
    int px = 0;
    int py = 0; 
    
    int score = 0;

    try (Scanner in = new Scanner(System.in)) {
      String[] validCommands = { "right", "left", "up", "down", "r", "l", "u", "d", "check","c",
      "jump", "jr", "jumpleft", "jl", "jumpup", "ju", "jumpdown", "jd",
      "pickup", "p", "quit", "q", "replay", "help", "?", "rm", "remove"};

      String[] trapValidCommands = { "right", "left", "up", "down", "r", "l", "u", "d"};

      //Variable for Game
      int length = validCommands.length;
      score = 0;

      // set up game
      

      //Keeps the game running
      boolean play = true;
      while (play)
      {
      if (gameTimer.GameTimerUtil <= 0){
        play = false;
      }
      score = 0;
          System.out.println("Commands for the game type: ? ");
          System.out.print("Enter command: ");

          
          String command = in.nextLine().toLowerCase();
          switch(command){
          // Allows player to MOVE
            case "right":
            case "r":
              game.movePlayer(m,0);
              px = px +m;
              break;
            case "left":
            case "l":
              game.movePlayer(-m,0);
              px= px -m;
              break;
            case "up":
            case "u":
              game.movePlayer(0,-m);
              py = py - m;
              break;
            case "down":
            case "d":
              game.movePlayer(0,m);
              py = py +m;
              break;
            // Allows player to jump
            case "jumpleft":
            case "jl":
              game.movePlayer(-m*2,0);
              px = px -m*2;
              break;
            case "jumpright":
            case "jr":
              game.movePlayer(m*2,0);
              px = px + m*2;
              break;
            case "jumpup":
            case "ju":
              game.movePlayer(0,-m*2);
              py = py - m*2;
              break;
            case "jumpdown":
            case "jd":
              game.movePlayer(0,m*2);
              py = py + m*2;
              break;
            // Allows me to pickup coin
            case "pickup":
            case "p":
              score += game.pickupPrize();
              break;
            // Allow player to check for trap
            case "check":
            case "c":
              if(game.isTrap(0,-m))
              {
                System.out.println("There is a trap up");

              }
              if(game.isTrap(0,m))
              {
                System.out.println("There is a trap under");

              }
              if(game.isTrap(m,0))
              {
                System.out.println("There is a trap right");

              }
              if(game.isTrap(-m,0))
              {
                System.out.println("There is a trap left");

              }
            break;
            // case for removing trap
            case "rm":
            case "remove":
              System.out.println("What direction would you like to remove the trap");
              String springInput = UserInput.getValidInput(trapValidCommands);
              if (springInput.equals("r") ||springInput.equals("right") ){
                score+=game.springTrap(m, 0);
              }
              else if (springInput.equals("l") ||springInput.equals("left") ){
                score+=game.springTrap(-m, 0);
              }
              else if (springInput.equals("u") ||springInput.equals("up") ){
                score+=game.springTrap(0, -m);
              }
              else if (springInput.equals("d") ||springInput.equals("down") ){
                score+=game.springTrap(0, m);
              }
            
            // Allows player to ask for help
            case "?":
            case "help":
            for (int i = 0; i < length; i++){
              System.out.println(validCommands[i]);
            }
            break;
            case "quit":
            case "q":
              play = false;
              break;
        } 
      }
            
          
    }
    score += game.endGame();

    System.out.println("score=" + score);
    System.out.println("steps=" + game.getSteps());
  }
}

        

        
