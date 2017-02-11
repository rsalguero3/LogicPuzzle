import java.util.ArrayList;
import javafx.scene.effect.Bloom;

/**
 *
 * @author Grant Osborn
 * 
 * 
 */
public class HintGen {
     private ArrayList<Hint> hints;
    
    public HintGen(){
        hints= new ArrayList<>();
        hints.add(new Hint(2,0,0,"hint 1 Mark the highlighted cell as TRUE."));
        hints.add(new Hint(1,1,0,"hint 2 Mark the highlighted cell as TRUE"));
        hints.add(new Hint(0,2,2,"hint 3 Mark the highlighted cell as FALSE."));  
     
    }   
    
    public String generate(GridModel g1,GridModel g2,GridModel g3){
        Hint myHint = getHint(g1, g2, g3);
            switch (myHint.getGridNum()) {
                case 0:
                    g1.getButtonAt(myHint.getX(),myHint.getY()).setEffect(new Bloom());
                    break;
                case 1:
                    g3.getButtonAt(myHint.getX(),myHint.getY()).setEffect(new Bloom());
                    break;
                default:
                    g2.getButtonAt(myHint.getX(),myHint.getY()).setEffect(new Bloom());
                    break;
            }
           return myHint.getMsg();   
    }
    
    
    //checks each hint in list if that space is blank display hint else check if any errors if no error check next hint in list
    public Hint getHint(GridModel g1,GridModel g2,GridModel g3){
        for(Hint h: hints){
            switch (h.getGridNum()) {
                case 0:
                    if(g1.isClearAt(h.getX(), h.getY())){
                        return h;
                    }else{
                        if(!g1.clearErrors()){
                            return new Hint(0,0,0,"you made a mistake"); 
                        }
                    }    break;
                case 1:
                    if(g3.isClearAt(h.getX(), h.getY())){
                        return h;
                    }else{
                        if(!g3.clearErrors()){
                            return new Hint(0,0,0,"you made a mistake"); 
                        }
                    }    break;
                default:
                    if(g2.isClearAt(h.getX(), h.getY())){
                        return h;
                    }else{
                        if(!g2.clearErrors()){
                            return new Hint(0,0,0,"you made a mistake"); 
                        }
                    }   break;
            }
        }
        
        return new Hint(0,0,0,"no more hints"); 
    }
      
}
