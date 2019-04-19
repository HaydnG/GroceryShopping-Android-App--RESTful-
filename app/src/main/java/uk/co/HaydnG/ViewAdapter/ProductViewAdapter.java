package uk.co.HaydnG.ViewAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
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

public class ProductViewAdapter extends RecyclerView.Adapter<ProductViewHolder> implements View.OnClickListener {



    private final List<ProductDTO> Products;
    private final Context mContext;
    private final LayoutInflater inflater;



    public ProductViewAdapter(List<ProductDTO> products, Context mContext, LayoutInflater inflater) {
        Products = products;
        this.mContext = mContext;
        this.inflater = inflater;
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

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int position) {

        ProductDTO product = this.Products.get(position);

        productViewHolder.ProductName.setText(product.getName());
        productViewHolder.ProductPrice.setText(String.valueOf(product.getPrice()));
        productViewHolder.ProductNumInCart.setText(String.valueOf(product.getNumInCart()));

        productViewHolder.AddToCart.setOnClickListener(this);
        productViewHolder.AddToCart.setId(product.getID());

    }

    public void AddItem(View v){


    }

    @Override
    public int getItemCount() {
        return this.Products.size();
    }

    @Override
    public void onClick(View v) {

    }
}


