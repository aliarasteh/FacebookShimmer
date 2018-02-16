# Shimmer
[Shimmer](https://code.facebook.com/posts/636856646421011/shimmer-for-android/) is an attractive library used to add shimmer effect to any layout you designed, just like the one used by facebook or linkedin.

<p align="center">
<img src="/splash_screen.gif?raw=true" width="300" /><img src="/lazy_load.gif?raw=true" width="300" />
</p>


# Usage
Get library via gradle
```build.gradle
dependencies {
    implementation 'com.facebook.shimmer:shimmer:0.1.0@aar'
}
```

create a `ShimmerFrameLayout` and add view or layout you want to have shimmer effect on them.

```xml
<com.facebook.shimmer.ShimmerFrameLayout
    android:id="@+id/shimmer_view_container"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_centerInParent="true"
    shimmer:duration="800">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Facebook Shimmer"
        android:textColor="@android:color/white"
        android:textSize="28sp"
        android:textStyle="bold" />
</com.facebook.shimmer.ShimmerFrameLayout>
```

After this you need to handle shimmer effect from your java code.

```java
ShimmerFrameLayout shimmerFrameLayout = (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
shimmerFrameLayout.startShimmerAnimation();
```

Clone and run project and see how you could use shimmer effect for splash or lazy load in 'RecyclerView'.


# Advanced
For more details read [documentation](https://code.facebook.com/posts/636856646421011/shimmer-for-android/) on shimmer's page.


# Author
[Ali Arasteh](https://github.com/aliarasteh)
