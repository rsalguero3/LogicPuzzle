/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicpuzzle;

/**
 *
 * @author grant osborn
 */
public class Hint {
    
    private final int x,y,gridNum; // grid num is witch grid the hint is in 0 for center 1 for bottom 2 for right
    private final String msg;
    
    public Hint(int x,int y,int gridNum, String msg){
        this.x=x;
        this.y=y;
        this.msg=msg; 
        this.gridNum=gridNum;
        
    }
    
    
    public int getX(){return x;}
    public int getY(){return y;}
    public int getGridNum(){return gridNum;}
    public String getMsg(){return msg;}
}
