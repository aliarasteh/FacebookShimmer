package ir.vdevelop.shimmer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.facebook.shimmer.ShimmerFrameLayout;

public class SplashActivity extends AppCompatActivity {
    Handler handler = new Handler();
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ShimmerFrameLayout shimmerFrameLayout = (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
        shimmerFrameLayout.setBaseAlpha(0.4f);
        shimmerFrameLayout.startShimmerAnimation();

        runnable = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                SplashActivity.this.finish();
            }
        };
        handler.postDelayed(runnable, 4000);
    }

    @Override
    protected void onDestroy() {
        if (runnable != null)
            handler.removeCallbacks(runnable);
        super.onDestroy();
    }
}
