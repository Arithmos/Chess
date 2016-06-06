package chess;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

/*
 * File Name: Knight.java
 *   Created: May 10, 2016
 *    Author: 
 */

public class Knight extends Piece
{

  public Knight(int xx, int yy, boolean b)
  {
    super(xx, yy, b);
  }

  @Override
  ArrayList<int[]> getPossible(boolean isB)
  {
    ArrayList<int[]> p =new ArrayList<>();
    if (x-2>=0 && y+1<8 && (Chess.board[x-2][y+1]==null || Chess.board[x-2][y+1].isBlack!=isB)){
      int[] m=new int[2];
      m[0]=x-2; m[1]=y+1;
      p.add(m);
    }
    if (x-2>=0 && y-1>=0 && (Chess.board[x-2][y-1]==null || Chess.board[x-2][y-1].isBlack!=isB)){
      int[] m=new int[2];
      m[0]=x-2; m[1]=y-1;
      p.add(m);
    }
    if (x+2<8 && y+1<8 && (Chess.board[x+2][y+1]==null || Chess.board[x+2][y+1].isBlack!=isB)){
      int[] m=new int[2];
      m[0]=x+2; m[1]=y+1;
      p.add(m);
    }
    if (x+2<8 && y-1>=0 && (Chess.board[x+2][y-1]==null || Chess.board[x+2][y-1].isBlack!=isB)){
      int[] m=new int[2];
      m[0]=x+2; m[1]=y-1;
      p.add(m);
    }
    
    if (y-2>=0 && x+1<8 && (Chess.board[x+1][y-2]==null || Chess.board[x+1][y-2].isBlack!=isB)){
      int[] m=new int[2];
      m[0]=x+1; m[1]=y-2;
      p.add(m);
    }
    if (y-2>=0 && x-1>=0 && (Chess.board[x-1][y-2]==null || Chess.board[x-1][y-2].isBlack!=isB)){
      int[] m=new int[2];
      m[0]=x-1; m[1]=y-2;
      p.add(m);
    }
    if (y+2<8 && x+1<8 && (Chess.board[x+1][y+2]==null || Chess.board[x+1][y+2].isBlack!=isB)){
      int[] m=new int[2];
      m[0]=x+1; m[1]=y+2;
      p.add(m);
    }
    if (y+2<8 && x-1>=0 && (Chess.board[x-1][y+2]==null || Chess.board[x-1][y+2].isBlack!=isB)){
      int[] m=new int[2];
      m[0]=x-1; m[1]=y+2;
      p.add(m);
    }
    
    return p;
  }

  @Override
  Image getImage()
  {
    Image knightImage;
    if (isBlack){
      knightImage = Toolkit.getDefaultToolkit().getImage(super.getClass().getResource("Black_Knight.png"));
    }
    else{
      knightImage = Toolkit.getDefaultToolkit().getImage(super.getClass().getResource("White_Knight.png"));
    }
    return knightImage;
  }
  
  @Override
  String pieceType(){
    return "Knight";
  }
}
