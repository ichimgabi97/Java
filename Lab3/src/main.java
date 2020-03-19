public class main {
    public static void main(String[] args){
        Knapsack knapsack = new Knapsack();
        Book book1 = new Book();
        Food food1 = new Food();
        Weapon weapon = new Weapon();

        weapon.setName("Sword");
        weapon.setValue(10);
        weapon.setWeight(5);

        book1.setName("Dragon Rising");
        book1.setPageNumber(300);
        book1.setValue(5);

        food1.setName("Cabbage");
        food1.setWeight(2);

        knapsack.addItem(weapon);
        knapsack.addItem(book1);
        knapsack.addItem(food1);

        System.out.println(knapsack);
    }
}
