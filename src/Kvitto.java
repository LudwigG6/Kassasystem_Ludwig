import java.util.ArrayList;
import java.util.List;

public class Kvitto {
    private ArrayList<KvittoRad> kvittoRader;

    public Kvitto(){
        kvittoRader = new ArrayList<>();
    
    }

    public void laggTillRad(KvittoRad rad){
        kvittoRader.add(rad);
    }

    public double getTotalPris(){
        double totalPris = 0;
        for(KvittoRad rad : kvittoRader){
            totalPris += rad.getTotalPris();
        }
        return totalPris;
    }
    public ArrayList<KvittoRad> getKvittoRader() {
        return kvittoRader;
    }

    
}
