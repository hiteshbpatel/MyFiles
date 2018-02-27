package jio.com.jiogames;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;
import com.mancj.slideup.SlideUp;
import com.mancj.slideup.SlideUpBuilder;
import com.qhutch.bottomsheetlayout.BottomSheetLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public final static int PAGES = 5;
    // You can choose a bigger number for LOOPS, but you know, nobody will fling
    // more than 1000 times just in order to test your "infinite" ViewPager :D
    public final static int LOOPS = 1000;
    public final static int FIRST_PAGE = PAGES * LOOPS / 2;
    public MyPagerAdapter adapter;
    public ViewPager pager;

    private final SnapHelper snapperCarr=new PagerSnapHelper();

    public Button btnMore;
    Toast toast;

    public static final String ORIENTATION = "orientation";

    private RecyclerView mRecyclerView;
    private boolean mHorizontal;

    private SlideUp slideUp;
    View dim;
    private View mView;
    private View sliderView;
    public RatingBar ratingBar;

    ArrayList<String> gameNames = new ArrayList<>();
    ArrayList<String> companyNames = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager = (ViewPager) findViewById(R.id.myviewpager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mView = findViewById(R.id.content_slide_up);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar2);

        ratingBar.setRating(Float.parseFloat("3.0"));

        FragmentManager manager = getSupportFragmentManager();

        adapter = new MyPagerAdapter(this,manager);
        //adapter = new MyPagerAdapter(get);
        pager.setAdapter(adapter);

        pager.setPageTransformer(true, adapter);

        // Set current item to the middle page so we can fling to both
        // directions left and right
        pager.setCurrentItem(FIRST_PAGE);

        // Necessary or the pager will only have one extra page to show
        // make this at least however many pages you can see
        pager.setOffscreenPageLimit(3);

        pager.setClipToPadding(false);
        // Set margin for pages as a negative number, so a part of next and
        // previous pages will be showed
        //pager.setPageMargin(-390);

       //pager.setPageMargin(-50);
        final int pageMargin = (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, -135, getResources() .getDisplayMetrics());
        pager.setPageMargin(pageMargin);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //recyclerview
        mRecyclerView = findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        //mRecyclerView.setLayoutManager(layoutManager);


        //snapperCarr.attachToRecyclerView(pager);

        mRecyclerView.setHasFixedSize(true);

        if (savedInstanceState == null) {
            mHorizontal = true;
        } else {
            mHorizontal = savedInstanceState.getBoolean(ORIENTATION);
        }
        setupAdapter();


    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        Drawable mydrawable = menu.getItem(0).getIcon();
        mydrawable.mutate();
        mydrawable.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);

        Drawable mydrawable1 = menu.getItem(1).getIcon();
        mydrawable1.mutate();
        mydrawable1.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);

        Drawable mydrawable2 = menu.getItem(2).getIcon();
        mydrawable2.mutate();
        mydrawable2.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Toast.makeText(MainActivity.this, "This is Home Page", Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.nav_games) {
            Toast.makeText(MainActivity.this, "This is Games Page", Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.nav_movies) {
            Toast.makeText(MainActivity.this, "This is Movies Page", Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.nav_music) {
            Toast.makeText(MainActivity.this, "This is Music Page", Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.nav_books) {
            Toast.makeText(MainActivity.this, "This is Books Page", Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.nav_newsstand) {
            Toast.makeText(MainActivity.this, "This is NewsStand Page", Toast.LENGTH_LONG).show();
            return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(ORIENTATION, mHorizontal);
    }

    private void setupAdapter() {
        List<App> apps = getApps();

        SnapAdapter snapAdapter = new SnapAdapter();

            snapAdapter.addSnap(new Snap(Gravity.CENTER_HORIZONTAL, "Trending Games", apps));
            snapAdapter.addSnap(new Snap(Gravity.START, "Recommended for You", apps));
            snapAdapter.addSnap(new Snap(Gravity.END, "Trending Games", apps));

            mRecyclerView.setAdapter(snapAdapter);
        }


    public void setupSlide(){


            sliderView = findViewById(R.id.slideView1);

            dim = findViewById(R.id.dim);

            mView.setVisibility(View.VISIBLE);

            slideUp = new SlideUpBuilder(sliderView)
                    .withListeners(new SlideUp.Listener.Events() {
                        @Override
                        public void onSlide(float percent) {
                            dim.setAlpha(1 - (percent / 100));
                        }

                        @Override
                        public void onVisibilityChanged(int visibility) {
                            if (visibility == View.GONE){
                                //finalFab.show();
                            }
                        }
                    })
                    .withStartGravity(Gravity.BOTTOM)
                    .withLoggingEnabled(true)
                    .withGesturesEnabled(true)
                    .withStartState(SlideUp.State.HIDDEN)
                    .build();

            slideUp.show();
        }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("details.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private List<App> getApps() {

        List<App> apps = new ArrayList<>();

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray userArray = obj.getJSONArray("details");
            for (int i = 0; i < userArray.length(); i++) {

                // create a JSONObject for fetching single user data
                JSONObject userDetail = userArray.getJSONObject(i);
                // fetch email and name and store it in arraylist

                Resources resources = this.getResources();

                // get resource id by image name
                final int resourceId = resources.getIdentifier(userDetail.getString("image_name"), "drawable", this.getPackageName());

                apps.add(new App(userDetail.getString("gameName"),resourceId,userDetail.getString("companyName")));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return apps;

    }

}
