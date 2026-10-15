import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;
import java.awt.event.*;



public class CashRegister implements ActionListener {

    
    JFrame frame;
    JTextArea receipt;
    JButton kaffeButton;
    JButton nalleButton;
    JButton muggButton;
    JButton chipsButton;
    JButton vaniljYoghurtButton;
    JButton daimButton;
    JTextArea inputProductName;
    JTextArea inputCount;
    JButton addToReceiptButton;
    JButton payButton;
    Kvitto kvitto;
    Produkt valdProdukt;
    Produkt kaffe;
    Produkt nalle;
    Produkt mugg;
    Produkt chips;
    Produkt vaniljYoghurt;
    Produkt daim;


    public CashRegister(){
        frame = new JFrame("IOT24 POS");


        createReceiptArea();
        createQuickButtonsArea();
        createAddArea();

        frame.getContentPane().setBackground(Color.BLACK);
        frame.setSize(1000,700); 

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(null);
    
        frame.setVisible(true);        
            

    }

    private void createAddArea(){
        inputProductName = new JTextArea();
        inputProductName.setEditable(false);
        inputProductName.setBounds(20,570,300,30);
        inputProductName.setFont(new Font("Arial Black", Font.BOLD, 18));
        frame.add(inputProductName);

        JLabel label = new JLabel("Antal");
        label.setBounds(340,545,300,30);
        label.setForeground(Color.WHITE);
        frame.add(label);

        inputCount = new JTextArea();
        inputCount.setBounds(330,570,50,30);
        inputCount.setFont(new Font("Arial Black", Font.BOLD, 18));
        frame.add(inputCount);


        addToReceiptButton = new JButton("Add");
        addToReceiptButton.addActionListener(this);
        addToReceiptButton.setBounds(400,560,70,50);
        addToReceiptButton.setFont(new Font("Arial Black", Font.PLAIN, 14));
        frame.add(addToReceiptButton);


        payButton = new JButton("Pay");
        payButton.addActionListener(this);
        payButton.setBounds(480,560,70,50);
        payButton.setFont(new Font("Arial Black", Font.PLAIN, 14));
        frame.add(payButton);


    }

    private void createQuickButtonsArea() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.setBackground(Color.green);
        panel.setPreferredSize(new Dimension(600, 600));

        kaffeButton = new JButton("Kaffe");
        kaffeButton.addActionListener(this);
        panel.add(kaffeButton);

        nalleButton = new JButton("Nalle");
        nalleButton.addActionListener(this);
        panel.add(nalleButton);

        muggButton = new JButton("Mugg");
        muggButton.addActionListener(this);
        panel.add(muggButton);

        chipsButton = new JButton("Chips");
        chipsButton.addActionListener(this);
        panel.add(chipsButton);

        vaniljYoghurtButton = new JButton("Yoghurt");
        vaniljYoghurtButton.addActionListener(this);
        panel.add(vaniljYoghurtButton);
        

        daimButton = new JButton("Daim");
        daimButton.addActionListener(this);
        panel.add(daimButton);




        panel.setBounds(0, 0, 600, 550);

        frame.add(panel);
    }

    private void createReceiptArea() {
        receipt = new JTextArea();
        receipt.setSize(400,400); 
        receipt.setLineWrap(true);
        receipt.setEditable(false);
        receipt.setVisible(true);
        receipt.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        JScrollPane scroll = new JScrollPane (receipt);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(600, 0, 500, 700);

        frame.add(scroll);    
    }

    public void run(){
        kvitto = new Kvitto();
        kaffe = new Produkt("Kaffe", 25.0, 12.0);
        nalle = new Produkt("Nalle", 50.0, 12.0);
        mugg = new Produkt("Mugg", 30.0, 12.0);
        chips = new Produkt("Chips", 20.0, 12.0);
        vaniljYoghurt = new Produkt("Vanilj Yoghurt", 15.0, 12.0);
        daim = new Produkt("Daim", 10.0, 12.0);
        uppdateraKvitto();

        
        

    }  
    private void uppdateraKvitto(){
        receipt.setText("");

        receipt.append("                  Ludwig Kassasystem\n");
        receipt.append("--------------------------------------------------\n");
        receipt.append("Kvitto nummer: " + kvitto.getKvittoNummer() + "\n");
        receipt.append("--------------------------------------------------\n"); 
        double totalMoms = 0;
        double totalNetto = 0;
        double totalBrutto = 0;
        double momsProcent = 0;
        for(KvittoRad rad : kvitto.getKvittoRader()){
            receipt.append ( rad.getProdukt().getName() + " " + rad.getAntal() + " x " + rad.getTotalPris() + "\n");
            totalMoms += rad.getMomsBelopp();
            totalNetto += rad.getNetto();
            totalBrutto += rad.getBrutto();
            momsProcent = rad.getProdukt().getMoms();
        }

        receipt.append("--------------------------------------------------\n");
        receipt.append("Total: " + kvitto.getTotalPris() + "\n");
        receipt.append(String.format("Moms: %.2f    Netto: %.2f    Brutto: %.2f%n",
            totalMoms,
            totalNetto,
            totalBrutto
        ));

        receipt.append(String.format("Moms%%: %.2f%%%n",
            momsProcent
        ));
       
    }          

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == kaffeButton) {
            valdProdukt = kaffe;
            inputProductName.setText(kaffe.getName());
        } else if (e.getSource() == nalleButton) {
            valdProdukt = nalle;
            inputProductName.setText(nalle.getName());
        } else if (e.getSource() == muggButton) {
            valdProdukt = mugg;
            inputProductName.setText(mugg.getName());
        } else if (e.getSource() == chipsButton) {
            valdProdukt = chips;
            inputProductName.setText(chips.getName());
        } else if (e.getSource() == vaniljYoghurtButton) {
            valdProdukt = vaniljYoghurt;
            inputProductName.setText(vaniljYoghurt.getName());
        } else if (e.getSource() == daimButton) {
            valdProdukt = daim;
            inputProductName.setText(daim.getName());
        } else if (e.getSource() == addToReceiptButton) {
            if (valdProdukt == null){
                System.out.println("Välj en produkt först");
                return;
            }
            String antalText = inputCount.getText();

            try{
                int antal = Integer.parseInt(antalText);
                if ( antal <= 0){
                    System.out.println("Skriv ett heltal större än 0");
                    return;
                }
                KvittoRad rad = new KvittoRad(valdProdukt, antal);
                kvitto.laggTillRad(rad);
                uppdateraKvitto();
                kvitto.getTotalPris();
                System.out.println(antal);

            } catch (NumberFormatException error){
                System.out.println("Skriv ett heltal");
            }
            
        } else if (e.getSource() == payButton){
            System.out.println("Pay fungerar");
            receipt.append("\n--------------------------------------------------\n");
            receipt.append("TACK FÖR DITT KÖP!          Sluttotal: " + kvitto.getTotalPris() + "\n");
            receipt.append("--------------------------------------------------\n");
            Timer timer = new Timer(3000, event -> {
                kvitto = new Kvitto();
                valdProdukt = null;
                inputProductName.setText("");
                inputCount.setText("");

                uppdateraKvitto();
            });

            timer.setRepeats(false);
            timer.start();
            
        }
    }

    
}
