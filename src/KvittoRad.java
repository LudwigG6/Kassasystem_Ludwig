public class KvittoRad {
    private Produkt produkt;
    private int antal;

    public KvittoRad(Produkt produkt, int antal) {
        this.produkt = produkt;
        this.antal = antal;
    }

    public Produkt getProdukt() {
        return produkt;
    }
    public int getAntal() {
        return antal;
    }
    public double getTotalPris(){
        return produkt.getPrice() * antal;
    }
    
    
}
