public class Book implements Item {
    private  String name ="";
    private  int pageNumber = 0;
    private double value = 0;

    public void setName(String name){
        this.name = name;
    }

    public void setPageNumber(int pageNumber){
        this.pageNumber = pageNumber;
    }

    public void setValue(double value){
        this.value = value;
    }

    @Override
    public String getName(){
        return  name;
    }

    @Override
    public double getWeight(){
        return pageNumber / 100;
    }

    @Override
    public double getValue(){
        return value;
    }
}
