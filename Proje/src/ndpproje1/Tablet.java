package ndpproje1;

import java.util.ArrayList;

public class Tablet extends Product implements Comparable<Tablet> {

    private float tScreen;
    private int tCameraMp;
    private int tStorage;
    private int tRam;
    private int tPrice;
    private static ArrayList<Tablet> tablets = new ArrayList<Tablet>();
    private ArrayList<Tweet> tTweets = new ArrayList<Tweet>();
    private float tPolarity = 0;

    public Tablet(String category, String brand, String model, float screen, int cameraMp, int storage, int ram, int price) {
        super(category, brand, model);
        this.tScreen = screen;
        this.tCameraMp = cameraMp;
        this.tStorage = storage;
        this.tRam = ram;
        this.tPrice = price;
    }

    public Tablet(String category, String brand, String model, float screen, int cameraMp, int storage, int ram, int price, ArrayList<Tweet> a) {
        super(category, brand, model);
        this.tScreen = screen;
        this.tCameraMp = cameraMp;
        this.tStorage = storage;
        this.tRam = ram;
        this.tPrice = price;
        this.tTweets = a;
        for (Tweet t : a) {
            tPolarity += t.gettValue();
        }
    }

    public float gettScreen() {
        return tScreen;
    }

    public void settScreen(float screen) {
        this.tScreen = screen;
    }

    public int gettCameraMp() {
        return tCameraMp;
    }

    public void settCameraMp(int cameraMp) {
        this.tCameraMp = cameraMp;
    }

    public int gettStorage() {
        return tStorage;
    }

    public void settStorage(int storage) {
        this.tStorage = storage;
    }

    public int gettRam() {
        return tRam;
    }

    public void setcRam(int ram) {
        this.tRam = ram;
    }

    public int gettPrice() {
        return tPrice;
    }

    public void settPrice(int price) {
        this.tPrice = price;
    }

    public String showTabletInfo() {
        return tScreen + " " + tCameraMp + " " + tStorage + " " + tRam + " " + tPrice;
    }

    public static ArrayList<Tablet> getTablets() {
        return tablets;
    }

    public float gettPolarity() {
        return tPolarity;
    }

    @Override
    public int compareTo(Tablet o) {
        return new Float(this.tPolarity).compareTo(o.tPolarity);
    }
}
