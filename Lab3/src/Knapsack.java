import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Knapsack {
    private double capacity;
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item){
        items.add(item);
    }

    @Override
    public String toString(){
        List<String> names = new ArrayList<>();
        String list ="";
        double totalWeight = 0;
        double totalValue =0;
        for (int i = 0; i< items.size(); i++){
            names.add(items.get(i).getName());
            totalValue += items.get(i).getValue();
            totalWeight += items.get(i).getWeight();
        }
        Collections.sort(names);
        for (int i = 0; i < names.size() - 1; i++){
            list += names.get(i) + ", ";
        }
        list += names.get(names.size()-1);
        return "selected items: " + list +" (total weight=" + totalWeight + ", total value=" + totalValue +")";
    }
}
