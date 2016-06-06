package chess;

import java.awt.Image;
import java.util.ArrayList;

/*
 * File Name: Piece.java
 *   Created: May 10, 2016
 *    Author: 
 */

public abstract class Piece extends Object
{
  int y, x;
  boolean isBlack;
  
  public Piece(int xx, int yy, boolean b){
    x=xx;
    y=yy;
    isBlack=b;
  }
  
  public boolean isBlack(){
    return isBlack;
  }
  
  public int getX(){
    return x;
  }
  
  public int getY(){
    return y;
  }
  
  public int[] move(int nx, int ny){  //Returns original location, before the move
    int[] m = new int[2];
    m[0]=x; m[1]=y;
    x=nx;
    y=ny;
    return m;
  }
  
  public int getXPosition(){
    return (int)(84*x+12);
  }
  
  public int getYPosition(){
    return (int)(84*y+12);
  }
  
  abstract ArrayList<int[]> getPossible(boolean isB);
  abstract Image getImage();
  abstract String pieceType();
}
