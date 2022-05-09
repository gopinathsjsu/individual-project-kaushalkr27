package inventory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

public class FillUpStocks {
    static final String dbPath = "src/main/java/inventory/stockdb/stocks.csv";

    public FillUpStocks() {}

    public Stock fillUpInventory(){
        String line = "";
        String splitBy = ",";

        HashMap<String ,Item> essentialItemList = new HashMap<>();
        HashMap<String ,Item> luxuryItemList = new HashMap<>();
        HashMap<String ,Item> miscItemList = new HashMap<>();
        try( BufferedReader br = new BufferedReader(new FileReader(dbPath))){
            while ((line = br.readLine()) != null){
                String[] item = line.split(splitBy);

                String category = item[0].trim().toLowerCase(Locale.ROOT);
                switch (category){
                    case "essentials":
                        essentialItemList.put(
                                item[1].trim().toLowerCase(Locale.ROOT),
                                new Item(
                                        item[1].trim().toLowerCase(Locale.ROOT),
                                        Integer.parseInt(item[2].trim()),
                                        Double.parseDouble(item[3].trim())
                                )
                        );
                        break;
                    case "luxury":
                        luxuryItemList.put(
                                item[1].trim().toLowerCase(Locale.ROOT),
                                new Item(
                                        item[1].trim().toLowerCase(Locale.ROOT),
                                        Integer.parseInt(item[2].trim()),
                                        Double.parseDouble(item[3].trim())
                                )
                        );
                        break;
                    case "misc":
                        miscItemList.put(
                                item[1].trim().toLowerCase(Locale.ROOT),
                                new Item(
                                        item[1].trim().toLowerCase(Locale.ROOT),
                                        Integer.parseInt(item[2].trim()),
                                        Double.parseDouble(item[3].trim())
                                )
                        );
                        break;
                    default:
                        break;
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return new Stock(essentialItemList, luxuryItemList, miscItemList);
    }
}
