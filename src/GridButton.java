import javafx.scene.control.Button;
import javafx.scene.layout.Background;

//Ricardo Salguero
public class GridButton extends Button {

    private boolean marked = false;
    private boolean markedCorrect = false;
    
    public GridButton(){
        super();
        setStyle("-fx-min-height: 75px; -fx-min-width: 75px;");
    }

    public boolean isMarkedCorrect() {
        return markedCorrect;
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
