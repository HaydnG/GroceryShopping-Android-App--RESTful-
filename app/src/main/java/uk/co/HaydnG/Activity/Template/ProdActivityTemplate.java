package uk.co.HaydnG.Activity.Template;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import uk.co.HaydnG.DTO.ProductDTO;

public abstract class ProdActivityTemplate extends ActivityTemplate {


    public RecyclerView recyclerView;

    public abstract void loadProductList(ArrayList<ProductDTO> products);

}
