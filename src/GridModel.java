import javafx.scene.control.Button;
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
    
    public boolean isClearAt(int x,int y){
        GridButton button;
        button = (GridButton) this.getChildren().get(y*4+x);
        return button.isClear(); 
    }
    
    public Button getButtonAt(int x, int y){
        GridButton button;
        button = (GridButton) this.getChildren().get(y*4+x);
        return button;
        
    }
    
    public void startOver(){
        for(int i=0;i<gridAnswers.length;i++){
           GridButton button;
           button = (GridButton) this.getChildren().get(i);
           button.setClear();
        }
        
    }
    
    //return true if no errors
    public boolean clearErrors(){
        boolean result = true;
        for(int i=0;i<gridAnswers.length;i++){
           GridButton button;
           button = (GridButton) this.getChildren().get(i);
           
           
           if(button.isMarkedCorrect()&&gridAnswers[i]==false){
               button.setClear();
               result=false;
           }
           else if(button.isMarked()&&button.isMarkedCorrect()==false&&gridAnswers[i]==true){
               button.setClear();
               result=false;
           }
           else if(button.isMarkedCorrect()&&gridAnswers[i]==true){
               button.setCircle();  
           }
        }
        return result;
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
            if(checkColumn(x,y)){
             button.setClear();   
            }
        }
    }
    //true when no violations
    public boolean checkViolations(int x, int y){
        return checkColumn(x,y)&&checkRow(x,y);
        
    }
    //return true if no o in column other than at x, y
    private  boolean checkColumn(int x , int y){
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
