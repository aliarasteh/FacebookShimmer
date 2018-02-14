package ir.vdevelop.shimmer;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Recipe> cartList;
    private RecipeListAdapter mAdapter;
    private boolean loading = false;
    int pastVisibleItems, visibleItemCount, totalItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        cartList = new ArrayList<>();
        mAdapter = new RecipeListAdapter(this, cartList);

        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(mAdapter);

        // add lazy load action to recyclerView
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //check for scroll down
                if (dy > 0) {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisibleItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (!loading) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            fetchRecipes();
                        }
                    }
                }
            }
        });

        // loading recipes
        fetchRecipes();
    }

    private void fetchRecipes() {
        loading = true;
        mAdapter.showLoaders(5); // show shimmer loading items as many as you want

        // TODO load real data from your API
        // loading fake data with 3 second delay
        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // load fake data array from assets folder
                String fakeData = loadStringFromAsset(getApplicationContext(), "fake_data.json");

                List<Recipe> recipes = new Gson().fromJson(fakeData, new TypeToken<List<Recipe>>() {
                }.getType());
                cartList.addAll(recipes);

                mAdapter.hideLoaders();
                loading = false;
            }
        }, 3000);
    }

    public static String loadStringFromAsset(Context context, String filename) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(filename);
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

}
