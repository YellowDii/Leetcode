import java.util.List;

public class Table_name_Number {
    private List<Item> items;
    private String id;

    public Table_name_Number() {
    }

    public Table_name_Number(List<Item> items, String id) {
        this.items = items;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
