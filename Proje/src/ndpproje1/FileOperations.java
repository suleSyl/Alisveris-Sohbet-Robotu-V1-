package ndpproje1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import redis.clients.jedis.Jedis;

public class FileOperations{

    private String fFileName;

    public FileOperations(String fileName) {
        this.fFileName = fileName;
    }

    public FileOperations() {
    }

    public HashMap createHashMap() throws IOException {    // The method to create a hashmap from a text file that contains polarity values

        HashMap<String, String> senticHashMap = new HashMap<String, String>();
        String key, value;
        File file = new File("senticnet4.txt");
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();

        while (line != null) {
            String[] parsed = line.split("\t");
            key = parsed[0];
            value = parsed[2];
            line = reader.readLine();
            senticHashMap.put(key, value);
        }
        return senticHashMap;
    }

    public void createFileForTweets() throws IOException {  //  The method to create text files for extracted tweets using twitter4j

        String[] brands = new String[]{"GalaxyNote8", "iPhone8", "iPhoneX", "Zenfone4", "BoschDishwasher", "LGDishwasher",
            "SamsungDishwasher", "BoschDryer", "ElectroluxDryer", "HotpointDryer", "LGDryer", "BekoFridge", "LGFridge", "SamsungFridge",
            "AppleMacbook", "DellInspiron", "HPG6", "LenovoYoga", "ToshibaSatellite", "BrotherPrinter", "HPDeskjet", "SamsungPrinter",
            "XeroxPrinter", "AppleiPad", "AsusTablet", "GalaxyTab3", "LenovoYogaTab3", "LGTelevision", "PhilipsTV", "SamsungSmartTv", "SamsungWashingMachine", "LGWashingMachine", "HotpointWashingMachine", "BekoWashingMachine"};
        for (String s : brands) {
            File file = new File(s + ".txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file, false);
            BufferedWriter bWriter = new BufferedWriter(fileWriter);
            ArrayList<Tweet> arrayList = new Extraction("#" + s, 50).getTweets();
            for (Tweet t : arrayList) {
                bWriter.write(t.gettHashTag() + " " + t.gettComment() + "\n");
                bWriter.newLine();
            }
            bWriter.close();
        }
    }

    public void insertTweetsIntoRedis() throws FileNotFoundException, IOException {             // Adds tweets into Redis

        Jedis jedis = new Jedis("localhost");
        String[] brands = new String[]{"GalaxyNote8", "iPhone8", "iPhoneX", "Zenfone4", "BoschDishwasher", "LGDishwasher",
            "SamsungDishwasher", "BoschDryer", "ElectroluxDryer", "HotpointDryer", "LGDryer", "BekoFridge", "LGFridge", "SamsungFridge",
            "AppleMacbook", "DellInspiron", "HPG6", "LenovoYoga", "ToshibaSatellite", "BrotherPrinter", "HPDeskjet", "SamsungPrinter",
            "XeroxPrinter", "AppleiPad", "AsusTablet", "GalaxyTab3", "LenovoYogaTab3", "LGTelevision", "PhilipsTV", "SamsungSmartTv", "SamsungWashingMachine", "LGWashingMachine", "HotpointWashingMachine", "BekoWashingMachine"};

        for (String s : brands) {
            File file = new File("C:/Users/Hp/Desktop/Nesne Proje/NdpProje1/Tweets/" + s + ".txt");
            BufferedReader reader = null;
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                jedis.lpush(s, line);
                line = reader.readLine();
            }
        }
    }

    public void addProducts(HashMap hashmap) throws FileNotFoundException, IOException { // Adding predetermined products to the project

        Jedis jedis = new Jedis("localhost");
        BufferedReader reader = new BufferedReader(new FileReader("Product List.txt"));
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            String[] parsedProductInfo = line.split("\t");
            String productCategory = parsedProductInfo[1];
            ArrayList<Tweet> a = new ArrayList<Tweet>();
            List<String> list;   //10 tweets in a list
            switch (parsedProductInfo[1]) {
                case "CellPhone":
                    list = jedis.lrange(parsedProductInfo[10], 0, 9);  // A list of 10 tweets of this product being retrieved from Redis 
                    a = createTweetsArraylist(parsedProductInfo[10], list, hashmap);  //
                    CellPhone c1 = new CellPhone(productCategory, parsedProductInfo[2], parsedProductInfo[3], Float.parseFloat(parsedProductInfo[4]), Integer.parseInt(parsedProductInfo[5]), Integer.parseInt(parsedProductInfo[6]), Integer.parseInt(parsedProductInfo[7]), Integer.parseInt(parsedProductInfo[8]), Integer.parseInt(parsedProductInfo[9]), a);
                    CellPhone.getCellPhones().add(c1);
                    break;
                case "Laptop":
                    list = jedis.lrange(parsedProductInfo[9], 0, 9);
                    a = createTweetsArraylist(parsedProductInfo[9], list, hashmap);
                    Laptop l1 = new Laptop(productCategory, parsedProductInfo[2], parsedProductInfo[3], Float.parseFloat(parsedProductInfo[4]), parsedProductInfo[5], Integer.parseInt(parsedProductInfo[6]), Integer.parseInt(parsedProductInfo[7]), Integer.parseInt(parsedProductInfo[8]), a);
                    Laptop.getLaptops().add(l1);
                    break;
                case "Printer":
                    list = jedis.lrange(parsedProductInfo[7], 0, 9);
                    a = createTweetsArraylist(parsedProductInfo[7], list, hashmap);
                    Printer p1 = new Printer(productCategory, parsedProductInfo[2], parsedProductInfo[3], Integer.parseInt(parsedProductInfo[4]), Integer.parseInt(parsedProductInfo[5]), Integer.parseInt(parsedProductInfo[6]), a);
                    Printer.getPrinters().add(p1);
                    break;
                case "Tablet":
                    list = jedis.lrange(parsedProductInfo[9], 0, 9);
                    a = createTweetsArraylist(parsedProductInfo[9], list, hashmap);
                    Tablet t1 = new Tablet(productCategory, parsedProductInfo[2], parsedProductInfo[3], Float.parseFloat(parsedProductInfo[4]), Integer.parseInt(parsedProductInfo[5]), Integer.parseInt(parsedProductInfo[6]), Integer.parseInt(parsedProductInfo[7]), Integer.parseInt(parsedProductInfo[8]), a);
                    Tablet.getTablets().add(t1);
                    break;
                case "Television":
                    list = jedis.lrange(parsedProductInfo[10], 0, 9);
                    a = createTweetsArraylist(parsedProductInfo[10], list, hashmap);
                    Television tt1 = new Television(productCategory, parsedProductInfo[2], parsedProductInfo[3], Integer.parseInt(parsedProductInfo[4]), parsedProductInfo[5], Integer.parseInt(parsedProductInfo[6]), Integer.parseInt(parsedProductInfo[7]), Float.parseFloat(parsedProductInfo[8]), Integer.parseInt(parsedProductInfo[9]), a);
                    Television.getTelevisions().add(tt1);
                    break;
                case "Fridge":
                    list = jedis.lrange(parsedProductInfo[8], 0, 9);
                    a = createTweetsArraylist(parsedProductInfo[8], list, hashmap);
                    Fridge f1 = new Fridge(productCategory, parsedProductInfo[2], parsedProductInfo[3], parsedProductInfo[4], parsedProductInfo[5], Integer.parseInt(parsedProductInfo[6]), Integer.parseInt(parsedProductInfo[7]), a);
                    Fridge.getFridges().add(f1);
                    break;
                case "WashingMachine":
                    list = jedis.lrange(parsedProductInfo[7], 0, 9);
                    a = createTweetsArraylist(parsedProductInfo[7], list, hashmap);
                    WashingMachine w1 = new WashingMachine(productCategory, parsedProductInfo[2], parsedProductInfo[3], Integer.parseInt(parsedProductInfo[4]), Integer.parseInt(parsedProductInfo[5]), Integer.parseInt(parsedProductInfo[6]), a);
                    WashingMachine.getWashingMachines().add(w1);
                    break;
                case "DishWasher":
                    list = jedis.lrange(parsedProductInfo[8], 0, 9);
                    a = createTweetsArraylist(parsedProductInfo[8], list, hashmap);
                    DishWasher d1 = new DishWasher(productCategory, parsedProductInfo[2], parsedProductInfo[3], parsedProductInfo[4], parsedProductInfo[5], Integer.parseInt(parsedProductInfo[6]), Integer.parseInt(parsedProductInfo[7]), a);
                    DishWasher.getDishWashers().add(d1);
                    break;
                case "Dryer":
                    list = jedis.lrange(parsedProductInfo[7], 0, 9);
                    a = createTweetsArraylist(parsedProductInfo[7], list, hashmap);
                    Dryer dd1 = new Dryer(productCategory, parsedProductInfo[2], parsedProductInfo[3], Integer.parseInt(parsedProductInfo[4]), parsedProductInfo[5], Integer.parseInt(parsedProductInfo[6]), a);
                    Dryer.getDryers().add(dd1);
                    break;
            }
        }
        reader.close();
    }

    public ArrayList<Tweet> createTweetsArraylist(String hashTag, List<String> list, HashMap<String, String> hm) {

        float polarity = 0;
        ArrayList<Tweet> a = new ArrayList<Tweet>();
        for (String s : list) {                       // For each tweet
            String[] parsedTweet = s.split(" ");    // Parsing each tweet
            for (String word : parsedTweet) {
                try {
                    polarity += Float.parseFloat((String) hm.get(word)); // Polarity being calculated
                } catch (Exception e) {
                    polarity = polarity;
                }
            }
            Tweet t = new Tweet(hashTag, s, polarity);
            a.add(t);
            polarity = 0;
        }
        return a;      // An ArrayList to store 10 tweets that belong to each product has been created and returned 
    }

    public String getfFileName() {
        return fFileName;
    }

    public void setfFileName(String fFileName) {
        this.fFileName = fFileName;
    }
}
