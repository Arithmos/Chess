package chess;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

/*
 * File Name: Pawn.java
 *   Created: May 10, 2016
 *    Author: 
 */

public class Pawn extends Piece
{
  public Pawn(int xx, int yy, boolean b){
    super(xx,yy,b);
  }

  @Override
  ArrayList<int[]> getPossible(boolean isB)
  {
    ArrayList<int[]> p =new ArrayList<>();
    if (isBlack && !Chess.isFlipped || !isBlack && Chess.isFlipped){
      if (y==1){
        if (Chess.board[x][y+2]==null && Chess.board[x][y+1]==null){
          int[] m=new int[2];
          m[0]=x; m[1]=y+2;
          p.add(m);
        }
      }
      if (y+1<8 && Chess.board[x][y+1]==null){
        int[] m=new int[2];
        m[0]=x; m[1]=y+1;
        p.add(m);
      }
      if (y+1<8 && x-1>=0 && Chess.board[x-1][y+1]!=null && Chess.board[x-1][y+1].isBlack!=isB){
        int[] m=new int[2];
        m[0]=x-1; m[1]=y+1;
        p.add(m);
      }
      if (y+1<8 && x+1<8 && Chess.board[x+1][y+1]!=null && Chess.board[x+1][y+1].isBlack!=isB){
        int[] m=new int[2];
        m[0]=x+1; m[1]=y+1;
        p.add(m);
      }
    }
    else{ //WHITE
      if (y==6){
        if (Chess.board[x][y-2]==null && Chess.board[x][y-1]==null){
          int[] m=new int[2];
          m[0]=x; m[1]=y-2;
          p.add(m);
        }
      }
      if (y-1>=0 && Chess.board[x][y-1]==null){
        int[] m=new int[2];
        m[0]=x; m[1]=y-1;
        p.add(m);
      }
      if (y-1>=0 && x-1>=0 && Chess.board[x-1][y-1]!=null && Chess.board[x-1][y-1].isBlack!=isB){
        int[] m=new int[2];
        m[0]=x-1; m[1]=y-1;
        p.add(m);
      }
      if (y-1>=0 && x+1<8 && Chess.board[x+1][y-1]!=null && Chess.board[x+1][y-1].isBlack!=isB){
        int[] m=new int[2];
        m[0]=x+1; m[1]=y-1;
        p.add(m);
      }
    }
    
    return p;
  }

  @Override
  Image getImage()
  {
    Image pawnImage;
    if (isBlack){
      pawnImage = Toolkit.getDefaultToolkit().getImage(super.getClass().getResource("Black_Pawn.png"));
    }
    else{
      pawnImage = Toolkit.getDefaultToolkit().getImage(super.getClass().getResource("White_Pawn.png"));
    }
    return pawnImage;
  }
  
  @Override
  String pieceType(){
    return "Pawn";
  }
}
