package ndpproje1;

import java.util.ArrayList;

public class Dryer extends Product implements Comparable<Dryer> {

    private int dCapacity;   //Washing Capacity: 7 kg, 9 kg, 10 kg...   
    private String dEnergy;   //Energy Class
    private int dPrice;
    private static ArrayList<Dryer> dryers = new ArrayList<Dryer>();
    private ArrayList<Tweet> dTweets = new ArrayList<Tweet>();
    private float dPolarity = 0;
    
    public Dryer(String category, String brand, String model, int capacity, String energy, int price) {
        super(category, brand, model);
        this.dCapacity = capacity;
        this.dEnergy = energy;
        this.dPrice = price;
    }

    public Dryer(String category, String brand, String model, int capacity, String energy, int price, ArrayList<Tweet> a) {
        super(category, brand, model);
        this.dCapacity = capacity;
        this.dEnergy = energy;
        this.dPrice = price;
        this.dTweets = a;
        for (Tweet t : a) {
            dPolarity += t.gettValue();
        }
    }

    public int getdCapacity() {
        return dCapacity;
    }

    public void setdCapacity(int capacity) {
        this.dCapacity = capacity;
    }

    public String getdEnergy() {
        return dEnergy;
    }

    public void setdEnergy(String energy) {
        this.dEnergy = energy;
    }

    public int getdPrice() {
        return dPrice;
    }

    public void setdPrice(int price) {
        this.dPrice = price;
    }

    public String showDryerInfo() {
        return "--" + super.getpBrand() + " " + super.getpModel() + " " + dCapacity + " kg " + dEnergy + " Enerji Sınıfı " + dPrice + " TL-- \nbaşarıyla eklendi.";
    }

    public static ArrayList<Dryer> getDryers() {
        return dryers;
    }

    public float getdPolarity() {
        return dPolarity;
    }

    @Override
    public int compareTo(Dryer o) {
        return new Float(this.dPolarity).compareTo(o.dPolarity);
    }
}
