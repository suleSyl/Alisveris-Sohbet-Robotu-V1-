package ndpproje1;

import java.util.ArrayList;

public class Television extends Product implements Comparable<Television> {

    private int tScreen;   //Screen size
    private String tImageQuality;   //4K-FULL HD-HD    
    private int tHDMIInput;   //Number of HDMI inputs
    private int tUSBInput;   //Number of USB ports
    private float tWeight;
    private int tPrice;
    private static ArrayList<Television> televisions = new ArrayList<Television>();
    private ArrayList<Tweet> tTweets = new ArrayList<Tweet>();
    private float tPolarity = 0;

    public Television(String category, String brand, String model, int screen, String imageQuality, int HDMIInput, int USBInput, float weight, int price) {
        super(category, brand, model);
        this.tScreen = screen;
        this.tImageQuality = imageQuality;
        this.tHDMIInput = HDMIInput;
        this.tUSBInput = USBInput;
        this.tWeight = weight;
        this.tPrice = price;
    }

    public Television(String category, String brand, String model, int screen, String imageQuality, int HDMIInput, int USBInput, float weight, int price, ArrayList<Tweet> a) {
        super(category, brand, model);
        this.tScreen = screen;
        this.tImageQuality = imageQuality;
        this.tHDMIInput = HDMIInput;
        this.tUSBInput = USBInput;
        this.tWeight = weight;
        this.tPrice = price;
        this.tTweets = a;
        for (Tweet t : a) {
            tPolarity += t.gettValue();
        }
    }

    public int gettScreen() {
        return tScreen;
    }

    public void settScreen(int screen) {
        this.tScreen = screen;
    }

    public String gettImageQuality() {
        return tImageQuality;
    }

    public void settImageQuality(String imageQuality) {
        this.tImageQuality = imageQuality;
    }

    public int gettHDMIInput() {
        return tHDMIInput;
    }

    public void settHDMIInput(int HDMIInput) {
        this.tHDMIInput = HDMIInput;
    }

    public int gettUSBInput() {
        return tUSBInput;
    }

    public void settUSBInput(int USBInput) {
        this.tUSBInput = USBInput;
    }

    public float gettWeight() {
        return tWeight;
    }

    public void settWeight(float weight) {
        this.tWeight = weight;
    }

    public int gettPrice() {
        return tPrice;
    }

    public void settPrice(int price) {
        this.tPrice = price;
    }

    public String showTelevisionInfo() {
        return tScreen + " " + tImageQuality + " " + tHDMIInput + " HDMI Girişi" + tUSBInput + " USB Girişi " + tWeight + " kg " + tPrice + " TL-- \nbaşarıyla eklendi.";
    }

    public static ArrayList<Television> getTelevisions() {
        return televisions;
    }

    public float gettPolarity() {
        return tPolarity;
    }

    @Override
    public int compareTo(Television o) {
        return new Float(this.tPolarity).compareTo(o.tPolarity);
    }
}
