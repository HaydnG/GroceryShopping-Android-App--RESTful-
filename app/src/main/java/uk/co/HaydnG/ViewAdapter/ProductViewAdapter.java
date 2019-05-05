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

import com.example.uk.R;

import java.util.List;

import uk.co.HaydnG.Activity.Template.ProdActivityTemplate;
import uk.co.HaydnG.DTO.ProductDTO;
import uk.co.HaydnG.RESTful.Services.CountCartProdService;
import uk.co.HaydnG.RESTful.Services.ModifyCartService;
import uk.co.HaydnG.ViewHolder.ProductViewHolder;

public class ProductViewAdapter extends RecyclerView.Adapter<ProductViewHolder>{



    public final List<ProductDTO> Products;
    private final Context mContext;
    private final LayoutInflater inflater;




    public ProdActivityTemplate activity;
    private int LayoutID;


    public int getLayoutID() {
        return LayoutID;
    }

    public ProductViewAdapter(List<ProductDTO> products, ProdActivityTemplate activity, int LaypoutID) {
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

        productViewHolder.ProductNumInCart.setText(product.getNumInCart() + "");



        try{

            productViewHolder.TotalprodPrice.setText("£ " + String.valueOf(product.getPrice() * product.getNumInCart()));
        }catch (NullPointerException ex){}

        try {

            productViewHolder.AddToCart.setTag(R.id.totalProdCost, productViewHolder.TotalprodPrice);
            productViewHolder.RemoveFromCart.setTag(R.id.totalProdCost,productViewHolder.TotalprodPrice);


            productViewHolder.AddToCart.setTag(R.id.prod_price, product.getPrice());
            productViewHolder.RemoveFromCart.setTag(R.id.prod_price,product.getPrice());


            productViewHolder.AddToCart.setTag(R.id.totalProdNum,productViewHolder.ProductNumInCart);
            productViewHolder.AddToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AddToCartOnClick(v, position);
                }
            });

            productViewHolder.RemoveFromCart.setTag(R.id.totalProdNum,productViewHolder.ProductNumInCart);
            productViewHolder.RemoveFromCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RemoveFromCartOnClick(v, position);
                }
            });
        }catch (NullPointerException ex){}

        new CountCartProdService(this, productViewHolder.AddToCart, product, position).GetCount();




    }


    public void AddToCartOnClick(View v, int position) {
        try {

            ProductDTO product = this.Products.get(position);
            ModifyCartService PS = new ModifyCartService(activity, activity.getUser());

            PS.ModifyCart(product.getID(), PS.INCREMENT_CART);

            CountCartProdService CountCart = new CountCartProdService(this, v, product, position).GetCount();


            if (product.getNumInCart() == 0) {
                Toast.makeText(activity, "Added " + product.getName() + " to cart! ", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(activity, "+1 " + product.getName() + " in cart!", Toast.LENGTH_SHORT).show();
            }
        }catch (IndexOutOfBoundsException i){}
    }


    public void RemoveFromCartOnClick(View v, int position) {
        try {

            ProductDTO product = this.Products.get(position);
            ModifyCartService PS = new ModifyCartService(activity, activity.getUser());

            PS.ModifyCart(product.getID(), PS.DECREMENT_CART);

            CountCartProdService CountCart = new CountCartProdService(this, v, product, position).GetCount();
        }catch (IndexOutOfBoundsException i){}


    }

    public void UpdateCartFooter(){
        TextView products = (TextView) activity.findViewById(R.id.total_products);
        TextView productsprice = (TextView) activity.findViewById(R.id.total_cost);

        int prodcount = 0;
        double prodtotal = 0;

        for(ProductDTO prod : this.Products){
            prodcount+=prod.getNumInCart();
            prodtotal+=(prod.getPrice() * prod.getNumInCart());


        }

        products.setText(prodcount+"");
        productsprice.setText("£"+ prodtotal);


//        notifyItemRangeChanged(position,this.Products.size());
//        notifyDataSetChanged();
//        activity.recyclerView.getRecycledViewPool().clear();



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


