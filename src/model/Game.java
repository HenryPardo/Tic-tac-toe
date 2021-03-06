/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


/**
 *
 * @author Pardo
 */
public class Game {
    
    private static char turn = 'x';
    private static char currentGame[] = new char[9];
    private static Win[] wins = Win.generateWins();

    public static char[] getCurrentGame() {
        return currentGame;
    }

    public static void setCurrentGame(char[] currentGame) {
        Game.currentGame = currentGame;
    }

    public static boolean alreadyPlayed(int position){
       return currentGame[position] != ' ';
    }

    public static boolean isFull(){
        for(Character position: currentGame){
            if (position == ' ') return false;                
        }
        return true;
    }
    

    
    public static Win checkWin(int lastMove){
        char move = currentGame[lastMove-1];
        int coincidences = 0;
        for(Win win: checkPossibleWins(lastMove)){
            if(win == null) break;
            for(int number: win.numbers){
                if(move == currentGame[number-1]){
                    move = currentGame[number-1];
                    coincidences++;

                    if(coincidences == 3) return win;
                }
            }
            coincidences = 0;
            move = currentGame[lastMove-1];
        }
        return new Win();
    }
    
    
    public static Win[] checkPossibleWins(int move){
        Win[] possibleWins = new Win[4];
        int counter = 0;
        for(Win win: wins){
            for(int number: win.numbers){
                if(number == move){
                    possibleWins[counter] = win;
                    counter++;
                }
            }
        }
        return possibleWins;
    }
    
    public static void addMove(int where, char what){
        currentGame[where-1] = what;
    }
    
    public static char getTurn() {
        return turn;
    }

    public static void setTurn(char turn) {
        Game.turn = turn;
    }
    
    public static class Win{
        public static final int HORIZONTAL = 1;
        public static final int VERTICAL = 2;
        public static final int DIAGONAL_RIGHT = 3;
        public static final int DIAGONAL_LEFT = 4;
        
        public boolean isWin = true;
        public int type;
        public int[] numbers = new int[3];

        public Win(int type, int[] numbers) {
            this.type = type;
            this.numbers = numbers;
        }

        public Win(){
            this.isWin = false;
        }
        
        static Win[] generateWins(){
            return new Win[]{   new Win(HORIZONTAL,new int[]{1,2,3}),
                                new Win(HORIZONTAL,new int[]{4,5,6}),
                                new Win(HORIZONTAL,new int[]{7,8,9}),
                                new Win(VERTICAL,new int[]{1,4,7}),
                                new Win(VERTICAL,new int[]{2,5,8}),
                                new Win(VERTICAL,new int[]{3,6,9}),
                                new Win(DIAGONAL_LEFT,new int[]{1,5,9}),
                                new Win(DIAGONAL_RIGHT,new int[]{3,5,7})};
        }

        
    }
   
    
}
