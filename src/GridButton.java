import javafx.scene.control.Button;
import javafx.scene.layout.Background;

//Ricardo Salguero and Grant Osborn
public class GridButton extends Button {

    private boolean marked = false;  //true when x
    private boolean markedCorrect = false; //true when o
    
    public GridButton(){
        super();
        setStyle("-fx-min-height: 75px; -fx-min-width: 75px;");
    }

    public boolean isMarkedCorrect() {
        return markedCorrect;
    }
    
    public boolean isMarked(){
     return marked;   
    }
    
     public boolean isClear(){
        return marked==false&&markedCorrect==false;
    }
    
    
    public void setX(){
         marked = true;
            this.setStyle("-fx-background-image: url('/x.png');" +
                    "-fx-min-height: 75px; -fx-min-width: 75px;");   
    }
    
   
    public void setCircle(){
         markedCorrect= true;
                this.setStyle("-fx-background-image: url('/o.png');" +
                        "-fx-min-height: 75px; -fx-min-width: 75px;");  
    }
    
    public void setClear(){
        markedCorrect = false;
        marked = false;
        this.setStyle("-fx-background-color: white;" +
                        "-fx-min-height: 75px; -fx-min-width: 75px;"+
                        "-fx-background-color: -fx-shadow-highlight-color," +
                        " -fx-outer-border, " +
                        "-fx-inner-border, -fx-body-color;");
        
    }
    
    

    //Will change the state of button to either an X, O, or blank
    public void changeState(){
        if(marked){
            if(markedCorrect){
                markedCorrect = false;
                marked = false;
                this.setStyle("-fx-background-color: white;" +
                        "-fx-min-height: 75px; -fx-min-width: 75px;"+
                        "-fx-background-color: -fx-shadow-highlight-color," +
                        " -fx-outer-border, " +
                        "-fx-inner-border, -fx-body-color;");
            }
            else{
                markedCorrect= true;
                this.setStyle("-fx-background-image: url('/o.png');" +
                        "-fx-min-height: 75px; -fx-min-width: 75px;");
            }
        }
        else {
            marked = true;
            this.setStyle("-fx-background-image: url('/x.png');" +
                    "-fx-min-height: 75px; -fx-min-width: 75px;");
        }
    }
}
