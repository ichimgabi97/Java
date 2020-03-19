public class Food implements Item {
    private String name = "";
    private double weight = 0;
    private double value = 0;

    public void setName(String name){
        this.name = name;
    }

    public void setWeight(double weight){
        this.weight = weight;
    }

    @Override
    public  double getWeight(){
        return weight;
    }

    public void setValue(){
        this.value = getWeight() * 2;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getValue() {
        setValue();
        return value;
    }

}
