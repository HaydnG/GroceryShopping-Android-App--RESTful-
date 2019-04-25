package uk.co.HaydnG.ViewHolder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.uk.R;

import java.util.ArrayList;
import java.util.List;

import uk.co.HaydnG.DTO.ProductDTO;

public class ProductViewHolder extends RecyclerView.ViewHolder  {

    public TextView ProductName;
    public TextView ProductPrice;
    public TextView ProductNumInCart;
    public TextView TotalprodPrice;

    public Button AddToCart;
    public Button RemoveFromCart;





    public ProductViewHolder(View itemView) {
        super(itemView);



        this.ProductName = itemView.findViewById(R.id.product_name);
        this.ProductPrice = itemView.findViewById(R.id.product_price);
        this.ProductNumInCart = itemView.findViewById(R.id.product_cartNum);
        this.AddToCart = itemView.findViewById(R.id.product_Addtocart);
        this.RemoveFromCart = itemView.findViewById(R.id.product_Removefromcart);

        try {

            this.TotalprodPrice = itemView.findViewById(R.id.totalprod_price);


        }catch(NullPointerException ex){ }
    }




}
