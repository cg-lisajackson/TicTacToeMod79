import static java.lang.System.out;
import java.util.Scanner;

public class TicTacToeWorks79
{

    public static void main (String [] args)
    {
        Scanner keyboard = new Scanner(System.in);

        out.println ("Welcome to Tic Tac Toe!\n");

        out.println ("To play, enter a number for which box to play in:");
        out.println (1 + " " + 2 + " " + 3);
        out.println (4 + " " + 5 + " " + 6);
        out.println (7 + " " + 8 + " " + 9);

        out.println ("\nYou'll need a buddy to play with.  Let's begin!");

        //initialize xstarts to be true for X starting
        boolean xstarts = true;
        //initialize playagain to be true for continuing to play the game
        String playagain = "y";
        //declare the board's grid
        int [][] numgrid = new int [3][3];      
        //initialize variable that is set when there is a winner      
        boolean winner = false;
        //initialize variable that is set when there is a draw
        boolean draw = false;
        //declare variable that stores the location chosen by the user
        int location;
        //initialize variable that states location is empty
        boolean empty = true;
        //declare array to hold which row and column corresponds with location chosen
        int [] rowcol = new int [2];
        //row corresponding to chosen location
        int row;
        //column corresponding to chosen location
        int col;
        //variable that is used to toggle between player Xs turn and player Os turn
        boolean isxturn;

        while ("y".equals (playagain))
        {
            //call function that initializes the number grid
            numgrid = initializegrid (numgrid);
            //Check if xstarts is true and print X if it is or O if false
            if (xstarts)
            {
                out.println("\nX moves first.");
                isxturn = true;
            }
            else
            {
                out.println("\nO moves first.");
                isxturn = false;
            }
            //call function that prints the number grid (initialized)
            printgrid (numgrid);
            //ask for player input, print the current board, check for winner and continue to play
            //until someone wins

            //keep playing until there is a winner
            do
            {                    
                //loop through until user chooses a location that was not already chosen (making
                //sure the location is empty
                do
                {
                    empty = true;
                    out.print ("\n? ");
                    location = keyboard.nextInt();
                    //loop that asks for the location they choose, but keeps looping until they
                    //choose a valid location
                    while (location < 1 || location > 9)
                    {
                        out.println ("Not a valid number");
                        out.print ("\n? ");
                        location = keyboard.nextInt();
                    }                           
                    //call function that determines which row and which column the location is at
                    rowcol = getrowandcol(location);
                    row = rowcol[0];
                    col = rowcol[1];
                    //check if the location the player wants to put the X or O is already filled
                    //and keep asking for new input until he chooses a location that is empty

                    if (numgrid [row][col] !=0)
                    {
                        out.println ("That location is chosen, please choose another");
                        empty = false;
                    }
                }while (!empty);
                //check whose turn it is and replace location chosen with either X or O
                if (isxturn)
                {
                    numgrid[row][col] = 1;
                }
                else
                {
                    numgrid[row][col] = 2;
                }
                //call function that prints the current grid with locations populated
                printgrid(numgrid);
                //call function to check to see if won
                if  (isxturn)
                {
                    winner = iswinner(numgrid, 1);
                    if (winner)
                    {
                        out.println ("\nX is the winner!");
                                            }
                    else
                    //call function to check if draw
                    {
                        draw = isdraw(numgrid);
                        if (draw)
                        {
                            out.println ("\nIt's a draw!");
                        }
                    }
                    
                }
                else 
                {
                    winner = iswinner(numgrid, 2);
                    if (winner)
                    {
                        out.println ("\nO is the winner!");
                    }
                    else
                    //call function to check if draw
                    {
                        draw = isdraw(numgrid);
                        if (draw)
                        {
                            out.println ("\nIt's a draw!");
                        }
                    }
                }
                //toggle whose turn it is
                isxturn = !isxturn;
            }while( (!winner) && (!draw));

            //before playing again, toggle the xstarts variable so a different person
            //starts the game
            xstarts = !xstarts;

            //Ask if the want to play again.  Set playagain variable to true if they do, false if not
            out.println ("\nWould you like to play again?");
            out.print ("y or n? ");
            playagain = keyboard.next();

            //make sure user enters y if want to play again or n if does not       
            while (!"y".equals (playagain) && !"n".equals (playagain))
            {
                out.println ("You must choose y or n");
                out.print ("y or n? ");
                playagain = keyboard.next();
            }           
        }
    }
    //function that receives current number grid and resets it to all 0s,
    //which will represent dots (.)
    public static int [][]initializegrid (int [][] currentgrid)
    {
        for (int row = 0; row <3; row++)
        {
            for (int col = 0; col < 3; col++)
            {
                currentgrid [row][col]= 0;
            }
        }
        return currentgrid;
    }
    //function that prints the current grid
    public static void printgrid(int [][] codedgrid)
    {
        for (int row = 0; row <3; row++)
        {
            out.print (" ");
            //call function that checks whether to print a ., X, or O)
            dotorxoro (codedgrid [row][0]);
            out.print (" | ");
            dotorxoro (codedgrid [row][1]);
            out.print (" | ");
            dotorxoro (codedgrid [row][2]);
            out.println();
        }
    }  
    //function that prints the null (.), X, or O, based on whether 0,1,or 2
    public static void dotorxoro (int gridvalue)
    {
        if (gridvalue == 0)
        {
            out.print (".");
        }
        else if (gridvalue == 1)
        {
            out.print ("X");
        }
        //then grid value must be 2, so print O
        else
        {
            out.print ("O");
        }
        return;
    }
    //function that receives the location chosen and returns the row and column where it is located
    public static int [] getrowandcol (int chosenlocal)
    {
        int rowandcol [] = new int [2];
        if (chosenlocal == 1)
        {
            rowandcol[0] = 0;
            rowandcol[1] = 0;
        }
        else if (chosenlocal == 2)
        {
            rowandcol[0] = 0;
            rowandcol[1] = 1;
        }
        else if (chosenlocal == 3)
        {
            rowandcol[0] = 0;
            rowandcol[1] = 2;
        }
        else if (chosenlocal == 4)
        {
            rowandcol[0] = 1;
            rowandcol[1] = 0;
        }
        else if (chosenlocal == 5)
        {
            rowandcol[0] = 1;
            rowandcol[1] = 1;
        }
        else if (chosenlocal == 6)
        {
            rowandcol[0] = 1;
            rowandcol[1] = 2;
        }
        else if (chosenlocal == 7)
        {
            rowandcol[0] = 2;
            rowandcol[1] = 0;
        }
        else if (chosenlocal == 8)
        {
            rowandcol[0] = 2;
            rowandcol[1] = 1;
        }
        else if (chosenlocal == 9)
        {
            rowandcol[0] = 2;
            rowandcol[1] = 2;
        }
        return rowandcol;
    }
    //function that receives current number grid after X has taken a turn to check if X won.
    //returns true if a winner
    public static boolean iswinner (int [][]winxgrid, int isxoro)
    {
        boolean wongame = false;
        if (winxgrid[0][0] == isxoro && winxgrid[0][1] == isxoro && winxgrid [0][2] ==isxoro)
        {
            wongame = true;
        }
        else if (winxgrid [1][0] == isxoro && winxgrid [1][1] == isxoro && winxgrid [1][2]== isxoro)
        {
            wongame = true;
        }
        else if (winxgrid [2][0] == isxoro && winxgrid [2][1] == isxoro && winxgrid [2][2] == isxoro)
        {
            wongame = true;
        }
        else if (winxgrid [0][0] == isxoro && winxgrid [1][0] == isxoro && winxgrid [2][0] == isxoro)
        {
            wongame = true;
        }
        else if (winxgrid [0][1] == isxoro && winxgrid [1][1] == isxoro && winxgrid [2][1] == isxoro)
        {
            wongame = true;
        }
        else if (winxgrid [0][2] == isxoro && winxgrid [1][2] == isxoro && winxgrid [2][2] == isxoro)
        {
            wongame = true;
        }
        else if (winxgrid [0][0] == isxoro && winxgrid [1][1] == isxoro && winxgrid [2][2] == isxoro)
        {
            wongame = true;
        }
        else if (winxgrid [2][0] == isxoro && winxgrid [1][1] == isxoro && winxgrid [0][2] == isxoro)
        {
            wongame = true;
        }
        return wongame;        
    }


    public static boolean isdraw (int drawgrid [][])
    {
        //set filledgrid to true, meaning it is a draw, because going to set to false if any one of 
        //the locations is empty (no draw)
        boolean filledgrid = true;
        for (int row = 0; row < 3; row++)
        {
            for (int col = 0; col < 3; col++)
            {
                if (drawgrid[row][col] == 0)
                {
                    filledgrid = false;
                }
            }
        }
        return filledgrid;
    }
                
}