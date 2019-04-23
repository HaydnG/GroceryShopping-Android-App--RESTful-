package uk.co.HaydnG.Activity.Template;

import java.util.ArrayList;

import uk.co.HaydnG.DTO.ProductDTO;

public abstract class ProdActivityTemplate extends ActivityTemplate {

    public abstract void loadProductList(ArrayList<ProductDTO> products);

}
