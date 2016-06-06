package chess;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

/*
 * File Name: Bishop.java
 *   Created: May 10, 2016
 *    Author: 
 */

public class Bishop extends Piece
{

  public Bishop(int xx, int yy, boolean b)
  {
    super(xx, yy, b);
  }

  @Override
  ArrayList<int[]> getPossible(boolean isB)
  {
    ArrayList<int[]> p =new ArrayList<>();
    int k=y+1;
    for (int i=x+1; i<8 && k<8; i++){
      if (Chess.board[i][k]==null){
        int[] m=new int[2];
        m[0]=i; m[1]=k;
        p.add(m);
      }
      else{
        if (i<8 && k<8 && Chess.board[i][k].isBlack!=isB){
          int[] m=new int[2];
          m[0]=i; m[1]=k;
          p.add(m);
        }
        break;
      }
      k++;
    }
    
    k=y-1;
    for (int i=x-1; i>=0 && k>=0; i--){
      if (Chess.board[i][k]==null){
        int[] m=new int[2];
        m[0]=i; m[1]=k;
        p.add(m);
      }
      else{
        if (i>=0 && k>=0 && Chess.board[i][k].isBlack!=isB){
          int[] m=new int[2];
          m[0]=i; m[1]=k;
          p.add(m);
        }
        break;
      }
      k--;
    }
    
    k=y+1;
    for (int i=x-1; i>=0 && k<8; i--){
      if (Chess.board[i][k]==null){
        int[] m=new int[2];
        m[0]=i; m[1]=k;
        p.add(m);
      }
      else{
        if (i>=0 && k>=0 && Chess.board[i][k].isBlack!=isB){
          int[] m=new int[2];
          m[0]=i; m[1]=k;
          p.add(m);
        }
        break;
      }
      k++;
    }
    
    k=y-1;
    for (int i=x+1; i<8 && k>=0; i++){
      if (Chess.board[i][k]==null){
        int[] m=new int[2];
        m[0]=i; m[1]=k;
        p.add(m);
      }
      else{
        if (i<8 && k<8 && Chess.board[i][k].isBlack!=isB){
          int[] m=new int[2];
          m[0]=i; m[1]=k;
          p.add(m);
        }
        break;
      }
      k--;
    }
    
    return p;
  }

  @Override
  Image getImage()
  {
    Image bishopImage;
    if (isBlack){
      bishopImage = Toolkit.getDefaultToolkit().getImage(super.getClass().getResource("Black_Bishop.png"));
    }
    else{
      bishopImage = Toolkit.getDefaultToolkit().getImage(super.getClass().getResource("White_Bishop.png"));
    }
    return bishopImage;
  }
  
  @Override
  String pieceType(){
    return "Bishop";
  }
  
}
