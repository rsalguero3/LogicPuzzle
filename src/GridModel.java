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
    
    
    public void clearErrors(){
        for(int i=0;i<16;i++){
           
           GridButton button;
           button = (GridButton) this.getChildren().get(i);
           
           if(button.isMarkedCorrect()==true&&gridAnswers[i]==false){
               button.setClear();
           }
           else if(button.isMarked()&&button.isMarkedCorrect()==false&&gridAnswers[i]==true){
               button.setClear();
           }
           else if(button.isMarkedCorrect()==true&&gridAnswers[i]==true){
               button.setCircle();  
           }
        }
    }
    
    public boolean[] getGridAnswers() {
        return gridAnswers;
    }
    
   public void setRowToX(int x, int y){
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
   
   public void setCollumToX(int x, int y){
       int index=x;
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
   
    public void setCollumToClear(int x){
       for(int i=0;i<4;i++){
           GridButton button;
           button = (GridButton) this.getChildren().get(x);
           button.setClear();
           x+=4;
       }   
   }
   
    
    public void setRowToClear(int y){
        int index=y*4; 
        for(int i=0;i<4;i++){
            GridButton button;
            button = (GridButton) this.getChildren().get(index+i);
             button.setClear();     
        }
    }
    
    public boolean checkViolations(int x, int y){
        return checkCollum(x,y)&&checkRow(x,y);
        
    }
    
    public  boolean checkCollum(int x , int y){
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
    
    
    public boolean checkRow(int x,int y){ 
        int index=y*4; 
        int pos=x+index;
        for(int i=0;i<4;i++){
            GridButton button;
            button = (GridButton) this.getChildren().get(index+i);
            if(button.isMarkedCorrect()==true&&index+i!=index+x){
                return false;
            }
        }    
        return true; 
    }
}
