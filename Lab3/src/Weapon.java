public class Weapon implements Item {
    private double weight = 0;
    private double value = 0;
    private String name = "";

    public void setWeight(double weight){
        this.weight = weight;
    }

    public  void setValue(double value){
        this.value = value;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public double getWeight(){
        return weight;
    }

    @Override
    public double getValue(){
        return value;
    }

    @Override
    public String getName(){
        return name;
    }
}
