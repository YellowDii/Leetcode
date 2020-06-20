public class Item {
    private String name;
    private Integer price1;
    private Integer price2;

    public Item() {
    }

    public Item(String name, Integer price1, Integer price2) {
        this.name = name;
        this.price1 = price1;
        this.price2 = price2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice1() {
        return price1;
    }

    public void setPrice1(Integer price1) {
        this.price1 = price1;
    }

    public Integer getPrice2() {
        return price2;
    }

    public void setPrice2(Integer price2) {
        this.price2 = price2;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", price1=" + price1 +
                ", price2=" + price2 +
                '}';
    }
}
