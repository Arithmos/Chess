package chess;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

/*
 * File Name: King.java
 *   Created: May 10, 2016
 *    Author: 
 */

public class King extends Piece
{

  public King(int xx, int yy, boolean b)
  {
    super(xx, yy, b);
  }

  @Override
  ArrayList<int[]> getPossible(boolean isB)
  {
    ArrayList<int[]> p =new ArrayList<>();
    if (x-1>=0 && (Chess.board[x-1][y]==null || Chess.board[x-1][y].isBlack!=isB)){
      int[] m=new int[2];
      m[0]=x-1; m[1]=y;
      p.add(m);
    }
    if (x-1>=0 && y-1>=0 && (Chess.board[x-1][y-1]==null || Chess.board[x-1][y-1].isBlack!=isB)){
      int[] m=new int[2];
      m[0]=x-1; m[1]=y-1;
      p.add(m);
    }
    if (y-1>=0 && (Chess.board[x][y-1]==null || Chess.board[x][y-1].isBlack!=isB)){
      int[] m=new int[2];
      m[0]=x; m[1]=y-1;
      p.add(m);
    }
    if (x+1<8 && (Chess.board[x+1][y]==null || Chess.board[x+1][y].isBlack!=isB)){
      int[] m=new int[2];
      m[0]=x+1; m[1]=y;
      p.add(m);
    }
    if (y+1<8 && (Chess.board[x][y+1]==null || Chess.board[x][y+1].isBlack!=isB)){
      int[] m=new int[2];
      m[0]=x; m[1]=y+1;
      p.add(m);
    }
    if (y+1<8 && x+1<8 && (Chess.board[x+1][y+1]==null || Chess.board[x+1][y+1].isBlack!=isB)){
      int[] m=new int[2];
      m[0]=x+1; m[1]=y+1;
      p.add(m);
    }
    if (y-1>=0 && x+1<8 && (Chess.board[x+1][y-1]==null || Chess.board[x+1][y-1].isBlack!=isB)){
      int[] m=new int[2];
      m[0]=x+1; m[1]=y-1;
      p.add(m);
    }
    if (x-1>=0 && y+1<8 && (Chess.board[x-1][y+1]==null || Chess.board[x-1][y+1].isBlack!=isB)){
      int[] m=new int[2];
      m[0]=x-1; m[1]=y+1;
      p.add(m);
    }
    
    return p;
  }

  @Override
  Image getImage()
  {
    Image kingImage;
    if (isBlack){
      kingImage = Toolkit.getDefaultToolkit().getImage(super.getClass().getResource("Black_King.png"));
    }
    else{
      kingImage = Toolkit.getDefaultToolkit().getImage(super.getClass().getResource("White_King.png"));
    }
    return kingImage;
  }
  
  @Override
  String pieceType(){
    return "King";
  }
  
  @Override
  public String toString(){
    return "King:  ("+x+", "+y+")";
  }
}
