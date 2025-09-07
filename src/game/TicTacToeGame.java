package game;
import java.util.*;
import java.lang.*;
import java.io.*;
class TicTacToe{
    //create an char array
    
    static char[][]board;
    
     //create an char array with size 3*3
     
     TicTacToe(){
        board=new char[3][3];
        initBoard();
    }
     
    //assigning spaces initially
    
     void initBoard(){
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                board[i][j]=' ';
            }
        }
    }
    // display the board
    
    
    public static  void displayBoard(){
        System.out.println("----------");
        for(int i=0;i<board.length;i++){
            System.out.print("| ");
            for(int j=0;j<board[i].length;j++){
                System.out.print(board[i][j]+"| ");   
            }
        
        System.out.println();
        System.out.println("----------");
    }
    }
    
    // create a place mark method to keep mark
    
   static void placeMark(int row,int col,char mark){
        
        //place mark only if the rows and columns in boundary
        
        if(row>=0 && row<=2 && col>=0 && col<=2){
           board[row][col]=mark;
            
        }
        else{
            System.out.println("Invalid position");
        }
    }
    
    //check col win
 static boolean  colWin(){
    for(int j=0;j<=2;j++){
        if( (board[0][j]!=' ') && board[0][j]==board[1][j] && board[1][j]==board[2][j]){
            return true;
        }
    }
    return false;
}

   //check row win
 static boolean  rowWin(){
    for(int i=0;i<=2;i++){
        if((board[i][0]!=' ')  && board[i][0]==board[i][1] && board[i][1]==board[i][2]){
            return true;
        }
    }
    return false;
}

//check diagonal win
static boolean diagWin(){
    if( (board[0][0]!=' ') &&  board[0][0]==board[1][1] && board[1][1]==board[2][2] 
    ||(board[0][2]!=' ') && board[0][2]==board[1][1] && board[1][1]==board[2][0]){
        return true;
    }
    else{
        return false;
    }
}
//check draw
static boolean checkDraw(){
    for(int i=0;i<=2;i++){
        for(int j=0;j<=2;j++){
            if(board[i][j]==' '){
                return false;
            }
        }
    }
    return true;
}

}
//create Player
abstract class Player{
    String name;
    char mark;
   abstract void makeMove();
   
boolean isValidMove(int row,int col){
       if(row>=0 && row<=2 && col>=0 && col<=2){
           if(TicTacToe.board[row][col]==' '){
               return true;
               
           }
       }
       return false;
   } 
   
}

// create an Human class
class HumanPlayer extends Player{
    
    HumanPlayer(String name,char mark){
        this.name=name;
        this.mark=mark;
    }
    void makeMove(){
        
    Scanner sc=new Scanner(System.in);
    int row;
    int col;
    do{
    System.out.print("Enter row & col value: ");
     row=sc.nextInt();
     col=sc.nextInt();
    }while(!isValidMove(row,col));
    
   TicTacToe.placeMark(row,col,mark);
    
    }
}

// create an AI class
class AiPlayer extends Player{
    
    AiPlayer(String name,char mark){
        this.name=name;
        this.mark=mark;
    }
    void makeMove(){
        
    Scanner sc=new Scanner(System.in);
    int row;
    int col;
    do{
    Random r =new Random();
    row=r.nextInt(3);
    col=r.nextInt(3);
    }while(!isValidMove(row,col));
    
   TicTacToe.placeMark(row,col,mark);
    } 
}

class TicTacToeGame
{
	public static void main (String[] args) 
	{
	TicTacToe t=new TicTacToe();
HumanPlayer p1=new HumanPlayer("dharani",'X');
AiPlayer p2=new AiPlayer("Ai",'O');
Player cp;
cp=p1;

while(true){
    
    System.out.println(cp.name+" turn:  ");
cp.makeMove();
TicTacToe.displayBoard();
if(TicTacToe.rowWin()||TicTacToe.colWin()||TicTacToe.diagWin() ){
    System.out.print(cp.name+" has won");
    break;
}
else if(TicTacToe.checkDraw()){
    System.out.print("It is a Draw");
    break;
}
else{
    if(cp==p1){
        cp=p2;
    }
    else{
        cp=p1;
    }
}

}



	}
}