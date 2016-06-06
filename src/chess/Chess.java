package chess; 

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * File Name: Chess.java
 *   Created: May 10, 2016
 *    Author: 
 */


public class Chess extends JPanel implements MouseListener, ActionListener
{
  // Declare instance variables here...
  private Image bg;
  public static Piece[][] board;
  private Piece[][] boardFlipped;
  public static boolean isFlipped;
  private ArrayList<int[]> possible;
  private boolean drawn;
  private Piece prevPiece;
  private boolean blackTurn;
  private JButton flip;
  private JButton reset;
  private boolean whiteWins,blackWins;
  private boolean check, checkMate;
  
  // Constructor
  public Chess(int w, int h, JFrame f)
  {
    super.setOpaque(true);
    super.setPreferredSize(new Dimension(w, h));
    super.setBackground(new Color(225, 225, 225));
    super.addMouseListener(this);
    
    bg = Toolkit.getDefaultToolkit().getImage(super.getClass().getResource("Board_Image.gif"));
    board = new Piece[8][8];
    boardFlipped = new Piece[8][8];
    drawn = true;
    blackTurn = false;
    
    this.setLayout(null);
    
    flip = new JButton("Flip Board");
    flip.setBounds(550, 690, 100, 30);
    flip.addActionListener(this);
    super.add(flip);
    
    reset = new JButton("Reset");
    reset.setBounds(20, 690, 100, 30);
    reset.addActionListener(this);
    super.add(reset);
    
    this.setPieces();
  }
  
  public void setPieces(){
    for (int i=0; i<8; i++){
      for (int k=0; k<8; k++){
        board[i][k]=null;
      }
    }
    
    for (int i=0; i<8; i++){ //BLACK
      board[i][1]=new Pawn(i,1,true);
    }
    for (int i=0; i<8; i++){ //WHITE
      board[i][6]=new Pawn(i,6,false);
    }
    
    board[0][0]=new Rook(0,0,true);
    board[7][0]=new Rook(7,0,true);
    board[0][7]=new Rook(0,7,false);
    board[7][7]=new Rook(7,7,false);
    
    board[1][0]=new Knight(1,0,true);
    board[6][0]=new Knight(6,0,true);
    board[1][7]=new Knight(1,7,false);
    board[6][7]=new Knight(6,7,false);
    
    board[2][0]=new Bishop(2,0,true);
    board[5][0]=new Bishop(5,0,true);
    board[2][7]=new Bishop(2,7,false);
    board[5][7]=new Bishop(5,7,false);
    
    board[3][0]=new Queen(3,0,true);
    board[3][7]=new Queen(3,7,false);
    
    board[4][0]=new King(4,0,true);
    board[4][7]=new King(4,7,false);
  }
  
  // Perform any custom painting (if necessary) in this method
  @Override  
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    g.setColor(Color.BLACK);
    g.drawImage(bg, 0, 0, this);
    
    for (int r=0; r<8; r++){
      for (int c=0; c<8; c++){
        try{
          g.drawImage(board[r][c].getImage(),board[r][c].getXPosition(),board[r][c].getYPosition(),this);
        }
        catch(NullPointerException e){}
      }
    }
    
    try{
      g.setColor(new Color(255, 255, 0, 100));
      //System.out.println(possible);
      if (!drawn){
        for (int[] x : possible){
          //System.out.println("("+x[0]+","+x[1]+")");
          g.fillRect(x[0]*84, x[1]*84, 84, 84);
        }
      }
    }
    catch(NullPointerException e){}
    
    g.setColor(Color.black);
    g.setFont(new Font(Font.SERIF, Font.PLAIN, 30));
    
    if (blackWins){
      g.drawString("Black Wins!", 262, 712);
    }
    else if (whiteWins){
      g.drawString("White Wins!", 262, 712);
    }
    else if (blackTurn){
      g.drawString("Black Turn", 262, 712);
    }
    else{
      g.drawString("White Turn", 262, 712);
    }
    
    if (checkMate || whiteWins || blackWins){
      g.setColor(Color.red);
      g.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
      g.drawString("Check Mate!", 420, 712);
    }
    else if (check){
      g.setColor(Color.red);
      g.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
      g.drawString("Check!", 450, 712);
      check=false;
    }
  }

  @Override
  public void actionPerformed(ActionEvent evt) {
    if (evt.getSource() == flip){
      isFlipped=!isFlipped;
//      System.out.println(java.util.Arrays.toString(board[0]));
//      board[0]=flipArray(board[0]);
//      System.out.println(java.util.Arrays.toString(board[0]));
      Piece[][] temp = new Piece[8][8];
//      for (Piece[] x : board){
//        //System.out.println(x);
//        x = flipArray(x);
        //System.out.println(x);
//      }
      //board = flipArray(board);
      for (int r=0; r<8; r++){
        for (int c=0; c<8; c++){
          temp[r][c]=board[r][c];
        }
      }
      //System.out.println(java.util.Arrays.toString(board[0]));
      for (int r=0; r<8; r++){
        for (int c=0; c<8; c++){
          board[r][c]=temp[7-r][7-c];
          try{
            board[r][c].x=r;
            board[r][c].y=c;
          }
          catch (NullPointerException e){}
        }
      }
      //System.out.println(java.util.Arrays.toString(board[0]));
      //System.out.println("flip");
      super.repaint();
    }
    if (evt.getSource() == reset){
      this.setPieces();
      blackWins=false;
      whiteWins=false;
      super.repaint();
      blackTurn=false;
    }
  }
  
  public Piece[] flipArray(Piece[] a){
    Piece[] m = new Piece[a.length];
    for (int i=0; i<a.length; i++){
      m[i]=a[i];
    }
    //System.out.println(java.util.Arrays.toString(m));
    int left = 0;
    int right = a.length - 1;
    while (left < right) {
        Piece temp = m[left];
        m[left] = m[right];
        m[right] = temp;
        left++;
        right--;
    }
    //System.out.println(java.util.Arrays.toString(m));
    //board[0]=m;
    return m;
  }
  
  // Process GUI input in this method
  @Override  
  public void mouseClicked(MouseEvent e)
  {
    int mx = e.getX()/84; int my = e.getY()/84;
    
    //System.out.println(mx+","+my);
    
    if (e.getButton() == MouseEvent.BUTTON1)
    {
      //System.out.println(mx);
      
      int[] temp = new int[2];  //Location to move to
      temp[0]=mx; temp[1]=my;
      
      try{
        for (int[] x : possible) {
          if (Arrays.equals(x, temp)){
            if (board[mx][my]!=null){
              //System.out.println(board[mx][my].pieceType());
              if (board[mx][my].isBlack() && board[mx][my].pieceType().equals("King")) whiteWins=true;
              if (!board[mx][my].isBlack() && board[mx][my].pieceType().equals("King")) blackWins=true;
            }
            //System.out.println("go");
            temp = prevPiece.move(mx, my);
            board[temp[0]][temp[1]]=null;
            board[mx][my]=prevPiece;
            drawn=!drawn;
            blackTurn=!blackTurn;

  //          if (board[mx][my]!=null){
  //            prevPiece.move(mx, my);
  //            board[mx][my]=prevPiece;
  //          }
          }
        }
        possible.clear();
      }
      catch (NullPointerException ex){}
      
      //if (isCheckMate(blackTurn)) checkMate=true;
      if (isCheck(blackTurn) || isCheck(!blackTurn)) check=true;
      //System.out.print(isCheck(blackTurn)+"   ");System.out.println(getKing(blackTurn));
      
      try{
        if (board[mx][my]!=null && board[mx][my].isBlack()==blackTurn && !blackWins && !whiteWins){
          prevPiece = board[mx][my];
          possible = prevPiece.getPossible(prevPiece.isBlack());
          System.out.println(prevPiece.x+","+prevPiece.y);
          System.out.println(board[mx][my]);
          drawn=!drawn;
        }
      }
      catch (ArrayIndexOutOfBoundsException ex){}
    }
    
    super.repaint();
  }
  
  public boolean isCheck (boolean isB){
    //k must be a King
    //if (k!=null){
      //int[] m={k.getX(),k.getY()};
    
      for (int r=0; r<8; r++){
        for (int c=0; c<8; c++){
          try{
            if (board[r][c].isBlack()!=isB){
              for (int[] x : board[r][c].getPossible(board[r][c].isBlack())){
                //System.out.print(java.util.Arrays.toString(x));
                int[] m={getKing(isB).getX(),getKing(isB).getY()};
  //              int[] t={1,2};
  //              int[] e = {1,2};
  //              System.out.print(java.util.Arrays.equals(t,e));
                if (java.util.Arrays.equals(x,m)) return true;
              }
  //            int[] m={getKing(isB).getX(),getKing(isB).getY()};
  //            System.out.println("King: "+java.util.Arrays.toString(m));
            }
          }
          catch (NullPointerException e){}
        }
      }
    //}
    return false;
  }
  
  public boolean isCheckMate(boolean isB){
    ArrayList<int[]> s = new ArrayList<>();
    int[] k = {getKing(isB).getX(),getKing(isB).getY()};
    s.add(k);
    for (int[] u : getKing(isB).getPossible(isB)){
      s.add(u);
    }
    
    for (int r=0; r<8; r++){
        for (int c=0; c<8; c++){
          try{
            if (board[r][c].isBlack()!=isB){
              for (int[] x : board[r][c].getPossible(board[r][c].isBlack())){
                for (int i=0; i<s.size(); i++){
                  if (java.util.Arrays.equals(x,s.get(i))){
                    s.remove(i);
                    i--;
                  }
                }
                if (s.isEmpty()) return true;
              }
            }
          }
          catch (NullPointerException e){}
        }
      }
    return false;
  }
  
  public Piece getKing(boolean isBlack){
    if (isBlack){
      for (int r=0; r<8; r++){
        for (int c=0; c<8; c++){
          try{
            if (board[r][c].isBlack() && board[r][c].pieceType().equals("King")) return board[r][c];
          }
          catch(NullPointerException e){}
        }
      }
    }
    else{
      for (int r=0; r<8; r++){
        for (int c=0; c<8; c++){
          try{
            if (!board[r][c].isBlack() && board[r][c].pieceType().equals("King")) return board[r][c];
          }
          catch(NullPointerException e){}
        }
      }
    }
    return null;
  }
  
  //<editor-fold defaultstate="collapsed" desc="--This method will launch your application--">
  public static void main(String[] args)
  {
    JFrame.setDefaultLookAndFeelDecorated(false);
    JFrame fr = new JFrame("Application: Chess");
    fr.setContentPane(new Chess(672, 742, fr));
    fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    fr.setLocation(10, 10);
    fr.setResizable(false);
    fr.pack();
    fr.setVisible(true);  
  }
  //</editor-fold>  


  @Override
  public void mouseReleased(MouseEvent e)
  {}

  @Override
  public void mouseEntered(MouseEvent e)
  {}

  @Override
  public void mouseExited(MouseEvent e)
  {}

  @Override
  public void mousePressed(MouseEvent e)
  {}

}
