package uk.co.HaydnG.ViewAdapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import uk.co.HaydnG.Activity.Template.ActivityTemplate;
import uk.co.HaydnG.DTO.ProductDTO;
import uk.co.HaydnG.RESTful.Services.CountCartProdService;
import uk.co.HaydnG.RESTful.Services.ModifyCartService;
import uk.co.HaydnG.ViewHolder.ProductViewHolder;

public class ProductViewAdapter extends RecyclerView.Adapter<ProductViewHolder>{



    private final List<ProductDTO> Products;
    private final Context mContext;
    private final LayoutInflater inflater;



    private ActivityTemplate activity;
    private int LayoutID;

    public View.OnClickListener AddToCartListener;
    public View.OnClickListener RemoveFromCartListener;




    public ProductViewAdapter(List<ProductDTO> products, ActivityTemplate activity, int LaypoutID) {
        Products = products;
        this.mContext = activity.getApplicationContext();
        this.inflater = activity.getLayoutInflater();
        this.activity = activity;

        this.LayoutID = LaypoutID;
    }

    private View.OnClickListener itemClickListener;

    public void setItemClickListener(View.OnClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(this.LayoutID, viewGroup, false);
        ProductViewHolder vh = new ProductViewHolder(mView);
        return vh;

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, final int position) {

        ProductDTO product = this.Products.get(position);


        productViewHolder.ProductName.setText(product.getName());
        productViewHolder.ProductPrice.setText("£ "+ String.valueOf(product.getPrice()));

        CountCartProdService CountProd = new CountCartProdService(activity, activity.getUser(), productViewHolder.ProductNumInCart).GetCount(product.getID());




        productViewHolder.ProductNumInCart.setId(product.getID());

        productViewHolder.AddToCart.setTag(productViewHolder.ProductNumInCart);
        productViewHolder.AddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddToCartOnClick(v, position);



            }
        });

        productViewHolder.RemoveFromCart.setTag(productViewHolder.ProductNumInCart);
        productViewHolder.RemoveFromCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RemoveFromCartOnClick(v, position);
            }
        });




    }

    public void AddItem(View v){


    }

    public void AddToCartOnClick(View v, int position) {
        ProductDTO product = this.Products.get(position);
        ModifyCartService PS = new ModifyCartService(activity, activity.getUser());

        PS.ModifyCart(product.getID(), PS.INCREMENT_CART);

        TextView CartNumText = (TextView) v.getTag();
        CountCartProdService CountCart = new CountCartProdService(activity, activity.getUser(), CartNumText).GetCount(product.getID());

        if(product.getNumInCart() == 0){
            Toast.makeText(activity, "Added " + product.getName() + " to cart! ", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(activity, "+1 " + product.getName() + " in cart!" , Toast.LENGTH_SHORT).show();
        }
    }


    public void RemoveFromCartOnClick(View v, int position) {
        ProductDTO product = this.Products.get(position);
        ModifyCartService PS = new ModifyCartService(activity, activity.getUser());

        PS.ModifyCart(product.getID(), PS.DECREMENT_CART);

        TextView CartNumText = (TextView) v.getTag();
        CountCartProdService CountCart = new CountCartProdService(activity, activity.getUser(), CartNumText).GetCount(product.getID());
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


