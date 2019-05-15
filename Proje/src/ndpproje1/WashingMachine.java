package ndpproje1;

import java.util.ArrayList;

public class WashingMachine extends Product implements Comparable<WashingMachine> {

    private int wCapacity;   //Washing Capacity
    private int wVolume;   //Wash Volume
    private int wPrice;
    private static ArrayList<WashingMachine> washingMachines = new ArrayList<WashingMachine>();
    private ArrayList<Tweet> wTweets = new ArrayList<Tweet>();
    private float wPolarity = 0;

    public WashingMachine(String category, String brand, String model, int price, int capacity, int volume) {
        super(category, brand, model);
        this.wPrice = price;
        this.wCapacity = capacity;
        this.wVolume = volume;
    }

    public WashingMachine(String category, String brand, String model, int price, int capacity, int volume, ArrayList<Tweet> a) {
        super(category, brand, model);
        this.wPrice = price;
        this.wCapacity = capacity;
        this.wVolume = volume;
        this.wTweets = a;
        for (Tweet t : a) {
            wPolarity += t.gettValue();
        }
    }

    public int getwPrice() {
        return wPrice;
    }

    public void setwPrice(int price) {
        this.wPrice = price;
    }

    public int getwCapacity() {
        return wCapacity;
    }

    public void setwCapacity(int capacity) {
        this.wCapacity = capacity;
    }

    public int getwVolume() {
        return wVolume;
    }

    public void setwVolume(int volume) {
        this.wVolume = volume;
    }

    public String showWashingMachineInfo() {
        return wPrice + " " + wCapacity + " " + wVolume + " ";
    }

    public static ArrayList<WashingMachine> getWashingMachines() {
        return washingMachines;
    }

    public float getwPolarity() {
        return wPolarity;
    }

    @Override
    public int compareTo(WashingMachine o) {
        return new Float(this.wPolarity).compareTo(o.wPolarity);
    }
}
