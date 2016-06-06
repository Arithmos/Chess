package chess;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

/*
 * File Name: Rook.java
 *   Created: May 10, 2016
 *    Author: 
 */

public class Rook extends Piece
{

  public Rook(int xx, int yy, boolean b)
  {
    super(xx, yy, b);
  }

  @Override
  ArrayList<int[]> getPossible(boolean isB)
  {
    ArrayList<int[]> p =new ArrayList<>();
    for (int i=x-1; i>=0; i--){
      if (Chess.board[i][y]==null){
        int[] m=new int[2];
        m[0]=i; m[1]=y;
        p.add(m);
      }
      else{
        if (i>=0 && Chess.board[i][y].isBlack!=isB){
          int[] m=new int[2];
          m[0]=i; m[1]=y;
          p.add(m);
        }
        break;
      }
    }
    for (int i=x+1; i<8; i++){
      if (Chess.board[i][y]==null){
        int[] m=new int[2];
        m[0]=i; m[1]=y;
        p.add(m);
      }
      else{
        if (i<8 && Chess.board[i][y].isBlack!=isB){
          int[] m=new int[2];
          m[0]=i; m[1]=y;
          p.add(m);
        }
        break;
      }
    }
    
    for (int i=y-1; i>=0; i--){
      if (Chess.board[x][i]==null){
        int[] m=new int[2];
        m[0]=x; m[1]=i;
        p.add(m);
      }
      else{
        if (i>=0 && Chess.board[x][i].isBlack!=isB){
          int[] m=new int[2];
          m[0]=x; m[1]=i;
          p.add(m);
        }
        break;
      }
    }
    for (int i=y+1; i<8; i++){
      if (Chess.board[x][i]==null){
        int[] m=new int[2];
        m[0]=x; m[1]=i;
        p.add(m);
      }
      else{
        if (i<8 && Chess.board[x][i].isBlack!=isB){
          int[] m=new int[2];
          m[0]=x; m[1]=i;
          p.add(m);
        }
        break;
      }
    }
    
    return p;
  }

  @Override
  Image getImage()
  {
    Image rookImage;
    if (isBlack){
      rookImage = Toolkit.getDefaultToolkit().getImage(super.getClass().getResource("Black_Rook.png"));
    }
    else{
      rookImage = Toolkit.getDefaultToolkit().getImage(super.getClass().getResource("White_Rook.png"));
    }
    return rookImage;
  }
  
  @Override
  String pieceType(){
    return "Rook";
  }
}
