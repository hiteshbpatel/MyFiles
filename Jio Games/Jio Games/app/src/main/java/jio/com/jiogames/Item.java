package jio.com.jiogames;

/**
 * Created by user on 2/22/2018.
 */

public class Item {

    private final int id;
    private final int image;

    public Item(int id, int image) {
        this.id = id;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public int getImage() {
        return image;
    }


}
