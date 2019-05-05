package uk.co.HaydnG.RESTful.Services;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.uk.R;

import uk.co.HaydnG.Activity.Template.ActivityTemplate;
import uk.co.HaydnG.DTO.ProductDTO;
import uk.co.HaydnG.DTO.UserDTO;
import uk.co.HaydnG.RESTful.ServiceController;
import uk.co.HaydnG.RESTful.UrlReaderCallback;
import uk.co.HaydnG.ViewAdapter.ProductViewAdapter;
import uk.co.HaydnG.ViewHolder.ProductViewHolder;

public class CountCartProdService extends UrlReaderCallback {

    private ProductViewAdapter viewHolder;
    private UserDTO User;
    private View view;
    private ProductDTO product;
    private int position;

    public static final int INCREMENT_CART = 1;
    public static final int DECREMENT_CART = 0;

    public CountCartProdService(ProductViewAdapter viewHolder,View view, ProductDTO prod, int position){
        this.viewHolder = viewHolder;
        this.User = viewHolder.activity.getUser();
        this.view = view;
        this.product = prod;
        this.position = position;

    }

    public CountCartProdService GetCount(){
        ServiceController SC = new ServiceController(this, viewHolder.activity);

        SC.execute("Cart/Get/"+product.getID(),SC.AUTH, SC.GET,this.User.getUsername(),this.User.getPassword().getPassword());

        return this;
    }


    @Override
    public void onUrlReaderFinished(String result) {
        if(result != null) {


            try{
                TextView CartNumText = (TextView) view.getTag(R.id.totalProdNum);
                CartNumText.setText(result);
            }catch (NullPointerException ex){}

            if(viewHolder.getLayoutID() == R.layout.cart_product){
                product.setNumInCart(Integer.parseInt(result));
                if(Integer.parseInt(result) <=0){
                    viewHolder.Products.remove(this.position);
                    viewHolder.notifyItemRemoved(position);
                    viewHolder.notifyItemRangeChanged(position,viewHolder.Products.size());
                }else {


                    try {


                        TextView CartProdCost = (TextView) view.getTag(R.id.totalProdCost);
                        CartProdCost.setText("£" + (product.getPrice() * product.getNumInCart()));

                        viewHolder.UpdateCartFooter();

                    } catch (NullPointerException e) {
                    }
                }
            }

        }


    }
}

