package victor.fruitsapp;

public class Fruit {
    private String fruit;
    private String image;
    private float price;

    public Fruit(String f, String i, float p){
        this.fruit = f;
        this.image = i;
        this.price = p;
    }

    public String getFruit(){
        return this.fruit;
    }

    public String getImage(){
        return this.image;
    }

    public float getPrice(){
        return this.price;
    }
}
