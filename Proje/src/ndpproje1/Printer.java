package ndpproje1;

import java.util.ArrayList;

public class Printer extends Product implements Comparable<Printer> {

    private int pCapacity;   //input capacitance for A4 paper
    private int pCartridge;   //number of cartridges
    private int pPrice;
    private static ArrayList<Printer> printers = new ArrayList<Printer>();
    private ArrayList<Tweet> pTweets = new ArrayList<Tweet>();
    private float pPolarity = 0;    

    public Printer(String category, String brand, String model, int capacity, int cartridge, int price) {
        super(category, brand, model);
        this.pPrice = price;
        this.pCapacity = capacity;
        this.pCartridge = cartridge;
    }

    public Printer(String category, String brand, String model, int capacity, int cartridge, int price, ArrayList<Tweet> a) {
        super(category, brand, model);
        this.pPrice = price;
        this.pCapacity = capacity;
        this.pCartridge = cartridge;
        this.pTweets = a;
        for (Tweet t : a) {
            pPolarity += t.gettValue();
        }
    }

    public int getpPrice() {
        return pPrice;
    }

    public void setpPrice(int price) {
        this.pPrice = price;
    }

    public int getpCapacity() {
        return pCapacity;
    }

    public void setpCapacity(int capacity) {
        this.pCapacity = capacity;
    }

    public int getpCartridge() {
        return pCartridge;
    }

    public void setpCartridge(int cartridge) {
        this.pCartridge = cartridge;
    }

    public String showPrinterInfo() {
        return "--" + super.getpBrand() + " " + super.getpModel() + " " + pCapacity + " " + pCartridge + " Kartuş " + pPrice + " TL-- \nbaşarıyla eklendi.";
    }

    public static ArrayList<Printer> getPrinters() {
        return printers;
    }

    public float getpPolarity() {
        return pPolarity;
    }

    @Override
    public int compareTo(Printer o) {
        return new Float(this.pPolarity).compareTo(o.pPolarity);
    }
}
