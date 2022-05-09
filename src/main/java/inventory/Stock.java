package inventory;

import java.util.HashMap;
import java.util.Map;

public class Stock {
    private Map<String, Item> essentials = new HashMap<>();
    private Map<String, Item> luxury = new HashMap<>();
    private Map<String, Item> miscellaneous = new HashMap<>();

    public Stock(Map<String, Item> essentials, Map<String, Item> luxury, Map<String, Item> miscellaneous) {
        this.essentials = essentials;
        this.luxury = luxury;
        this.miscellaneous = miscellaneous;
    }

    public Map<String, Item> getEssentials() {
        return essentials;
    }

    public Map<String, Item> getLuxury() {
        return luxury;
    }

    public Map<String, Item> getMiscellaneous() {
        return miscellaneous;
    }
}
