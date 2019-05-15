package ndpproje1;

import java.util.ArrayList;

public class Laptop extends Product implements Comparable<Laptop> {

    private float lScreen;   //Screen Size
    private String lOS;   //Operating System
    private int lStorage;
    private int lRAM;
    private int lPrice;
    private static ArrayList<Laptop> laptops = new ArrayList<Laptop>();
    private ArrayList<Tweet> lTweets = new ArrayList<Tweet>();
    private float lPolarity = 0;

    public Laptop(String category, String brand, String model, float screen, String OS, int storage, int RAM, int price) {
        super(category, brand, model);
        this.lScreen = screen;
        this.lOS = OS;
        this.lStorage = storage;
        this.lRAM = RAM;
        this.lPrice = price;
    }

    public Laptop(String category, String brand, String model, float screen, String OS, int storage, int RAM, int price, ArrayList<Tweet> a) {
        super(category, brand, model);
        this.lScreen = screen;
        this.lOS = OS;
        this.lStorage = storage;
        this.lRAM = RAM;
        this.lPrice = price;
        this.lTweets = a;
        for (Tweet t : a) {
            lPolarity += t.gettValue();
        }
    }

    public float getlScreen() {
        return lScreen;
    }

    public void setlScreen(float screen) {
        this.lScreen = screen;
    }

    public String getlOS() {
        return lOS;
    }

    public void setlOS(String OS) {
        this.lOS = OS;
    }

    public int getlStorage() {
        return lStorage;
    }

    public void setlStorage(int storage) {
        this.lStorage = storage;
    }

    public int getlRAM() {
        return lRAM;
    }

    public void setlRAM(int RAM) {
        this.lRAM = RAM;
    }

    public int getlPrice() {
        return lPrice;
    }

    public void setlPrice(int price) {
        this.lPrice = price;
    }

    public String showLaptopInfo() {
        return "--" + super.getpBrand() + " " + super.getpModel() + " " + lScreen + " inch " + lOS + " " + lStorage + " GB " + lRAM + " GB " + lPrice + " TL-- \nbaşarıyla eklendi.";
    }

    public static ArrayList<Laptop> getLaptops() {
        return laptops;
    }

    public float getlPolarity() {
        return lPolarity;
    }

    @Override
    public int compareTo(Laptop o) {
        return new Float(this.lPolarity).compareTo(o.lPolarity);
    }
}
