import java.util.ArrayList;


public class Kvitto {
    private ArrayList<KvittoRad> kvittoRader;
    private static int nextKvittoNummer = 1;
    private int kvittoNummer;

    public Kvitto(){
        kvittoRader = new ArrayList<>();
        kvittoNummer = nextKvittoNummer;
        nextKvittoNummer++;
    }

    public void laggTillRad(KvittoRad rad){
        kvittoRader.add(rad);
    }
    public int getKvittoNummer(){
        return kvittoNummer;
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
