package logicpuzzle;

import javafx.scene.layout.GridPane;

public class GridModel extends GridPane{

    //private GridPane pane;
    //Ricardo Salguero and Grant osborn
    private boolean[] gridAnswers = {};

    public GridModel(boolean[] list){
        super();
        gridAnswers = list;
        this.setStyle("-fx-border-color: black; -fx-border-style: solid");
    }
    
    public void startOver(){
        for(int i=0;i<gridAnswers.length;i++){
           GridButton button;
           button = (GridButton) this.getChildren().get(i);
           button.setClear();
        }
        
    }
    
    
    public void clearErrors(){
        for(int i=0;i<16;i++){
           GridButton button;
           button = (GridButton) this.getChildren().get(i);
           
           if(button.isMarkedCorrect()&&gridAnswers[i]==false){
               button.setClear();
           }
           else if(button.isMarked()&&button.isMarkedCorrect()==false&&gridAnswers[i]==true){
               button.setClear();
           }
           else if(button.isMarkedCorrect()&&gridAnswers[i]==true){
               button.setCircle();  
           }
        }
    }
    
    public boolean[] getGridAnswers() {
        return gridAnswers;
    }
    
    public void setRowAndColumnToX(int x, int y){
        setRowToX(x,y);
        setColumnToX(x,y);
    }
    
    
   private void setRowToX(int x, int y){
       int index=y*4; 
       int pos=x+index;
        for(int i=0;i<4;i++){
            GridButton button;
            button = (GridButton) this.getChildren().get(index+i);
            if(index+i!=pos){
                button.setX();
            }
        }   
   }
   
   private void setColumnToX(int x, int y){
       int pos=4*y+x;
       for(int i=0;i<4;i++){
           GridButton button;
           button = (GridButton) this.getChildren().get(x);
           if(x!=pos){
               button.setX();
           }
           x+=4;
       }   
   }
   
   public void clearRowAndColumn(int x, int y){
        setColumnToClear(x);
        setRowToClear(y);
    }
    
   
    private void setColumnToClear(int x){
       for(int y=0;y<4;y++){
           GridButton button;
           button = (GridButton) this.getChildren().get(x);
           if(checkRow(x,y)){
            button.setClear();
           }
           x+=4;
       }   
   }
   
    
    private void setRowToClear(int y){
        int index=y*4; 
        for(int x=0;x<4;x++){
            GridButton button;
            button = (GridButton) this.getChildren().get(index+x);
            if(checkCollum(x,y)){
             button.setClear();   
            }
        }
    }
    //true when no violations
    public boolean checkViolations(int x, int y){
        return checkCollum(x,y)&&checkRow(x,y);
        
    }
    //return true if no o in collum other than at x, y
    private  boolean checkCollum(int x , int y){
       int index=x;
       int pos=4*y+x;
       for(int i=0;i<4;i++){
           GridButton button;
           button = (GridButton) this.getChildren().get(x);
           if(button.isMarkedCorrect()&&x!=pos){
               return false;
           }
           x+=4;
       }   
       return true;
        
    }
    
    //returns true if there is no o in that row other than at x,y
    private boolean checkRow(int x,int y){ 
        int index=y*4; 
        int pos=x+index;
        for(int i=0;i<4;i++){
            GridButton button;
            button = (GridButton) this.getChildren().get(index+i);
            if(button.isMarkedCorrect()==true&&index+i!=pos){
                return false;
            }
        }    
        return true; 
    }
}
