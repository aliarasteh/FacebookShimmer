package ir.vdevelop.shimmer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.MyViewHolder> {
    private Context context;
    private List<Recipe> listItems;
    private boolean showLoaders = false;
    private int loaderCount;

    public RecipeListAdapter(Context context, List<Recipe> cartList) {
        this.context = context;
        this.listItems = cartList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Recipe recipe = getItem(position);
        if (recipe == null) {
            holder.listItemContainer.setVisibility(View.GONE);
            holder.shimmerContainer.setVisibility(View.VISIBLE);
            holder.shimmerContainer.startShimmerAnimation();
        } else {
            holder.listItemContainer.setVisibility(View.VISIBLE);
            holder.shimmerContainer.setVisibility(View.GONE);
            holder.shimmerContainer.stopShimmerAnimation();

            holder.name.setText(recipe.getName());
            holder.chef.setText("By " + recipe.getChef());
            holder.description.setText(recipe.getDescription());
            holder.price.setText("Price: ₹" + recipe.getPrice());
            holder.timestamp.setText(recipe.getTimestamp());

            Glide.with(context)
                    .load(recipe.getThumbnail())
                    .into(holder.thumbnail);
        }
    }

    private Recipe getItem(int position) {
        if (position < listItems.size())
            return listItems.get(position);
        else
            return null;
    }

    // recipe
    @Override
    public int getItemCount() {
        if (showLoaders) {
            return listItems.size() + loaderCount;
        } else {
            return listItems.size();
        }
    }

    public void showLoaders(int loaderCount) {
        this.loaderCount = loaderCount;
        showLoaders = true;
        notifyItemRangeChanged(listItems.size() - 1, loaderCount);
        notifyDataSetChanged();
    }

    public void hideLoaders() {
        showLoaders = false;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ViewGroup listItemContainer;
        ShimmerFrameLayout shimmerContainer;
        TextView name, description, price, chef, timestamp;
        ImageView thumbnail;

        MyViewHolder(View view) {
            super(view);
            listItemContainer = view.findViewById(R.id.list_item_container);
            shimmerContainer = view.findViewById(R.id.shimmer_view_container);
            name = view.findViewById(R.id.name);
            chef = view.findViewById(R.id.chef);
            description = view.findViewById(R.id.description);
            price = view.findViewById(R.id.price);
            thumbnail = view.findViewById(R.id.thumbnail);
            timestamp = view.findViewById(R.id.timestamp);
        }
    }
}
