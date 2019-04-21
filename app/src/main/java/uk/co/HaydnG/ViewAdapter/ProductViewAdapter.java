package uk.co.HaydnG.ViewAdapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.uk.R;

import java.util.ArrayList;
import java.util.List;

import uk.co.HaydnG.DTO.ProductDTO;
import uk.co.HaydnG.ViewHolder.ProductViewHolder;

public class ProductViewAdapter extends RecyclerView.Adapter<ProductViewHolder>{



    private final List<ProductDTO> Products;
    private final Context mContext;
    private final LayoutInflater inflater;

    public ProductAdapterListener listener;

    private AppCompatActivity activity;

    public View.OnClickListener AddToCartListener;
    public View.OnClickListener RemoveFromCartListener;




    public ProductViewAdapter(List<ProductDTO> products, Context mContext, LayoutInflater inflater, ProductAdapterListener listener) {
        Products = products;
        this.mContext = mContext;
        this.inflater = inflater;
        this.activity = activity;
        this.listener = listener;
    }

    private View.OnClickListener itemClickListener;

    public void setItemClickListener(View.OnClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product, viewGroup, false);
        ProductViewHolder vh = new ProductViewHolder(mView);
        return vh;

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, final int position) {

        ProductDTO product = this.Products.get(position);

        productViewHolder.ProductName.setText(product.getName());
        productViewHolder.ProductPrice.setText(String.valueOf(product.getPrice()));
        productViewHolder.ProductNumInCart.setText(String.valueOf(product.getNumInCart()));


        productViewHolder.ProductNumInCart.setId(product.getID());

        productViewHolder.AddToCart.setTag(productViewHolder.ProductNumInCart);
        productViewHolder.AddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.AddToCartOnClick(v, position);



            }
        });

        productViewHolder.RemoveFromCart.setTag(productViewHolder.ProductNumInCart);
        productViewHolder.RemoveFromCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.RemoveFromCartOnClick(v, position);
            }
        });




    }

    public void AddItem(View v){


    }

    public interface ProductAdapterListener {

        void AddToCartOnClick(View v, int position);

        void RemoveFromCartOnClick(View v, int position);

    }

    @Override
    public int getItemCount() {
        return this.Products.size();
    }


}


