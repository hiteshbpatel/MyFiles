package jio.com.jiogames;


public class App {

    private int mDrawable;
    private String mName;
    private String mCompany;

    public App(String name, int drawable, String company) {
        mName = name;
        mDrawable = drawable;
        mCompany = company;
    }

    public String getCompany() {
        return mCompany;
    }

    public int getDrawable() {
        return mDrawable;
    }

    public String getName() {
        return mName;
    }
}

