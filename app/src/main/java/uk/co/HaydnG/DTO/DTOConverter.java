package uk.co.HaydnG.DTO;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DTOConverter {


    public UserDTO JsonTOUserDTO(String Json){
        UserDTO User = new UserDTO();
        AddressDTO Address = new AddressDTO();

        if(Json == null){
            return null;
        }


        try {
            JSONObject Pojo = new JSONObject(Json);

            User.setID(Pojo.getInt("ID"));
            User.setFName(Pojo.getString("FName"));
            User.setLName(Pojo.getString("LName"));
            User.setAdmin(Pojo.getBoolean("admin"));
            User.setLoggedIn(Pojo.getBoolean("loggedIn"));
            User.setShowAdmins(Pojo.getBoolean("showAdmins"));
            User.setUsername(Pojo.getString("username"));

            JSONObject addressJson = Pojo.getJSONObject("address");
            Address.setID(addressJson.getInt("ID"));
            Address.setAddrline1(addressJson.getString("addrline1"));
            Address.setAddrline2(addressJson.getString("addrline2"));
            Address.setCity(addressJson.getString("city"));
            Address.setCounty(addressJson.getString("county"));
            Address.setEmail(addressJson.getString("email"));
            Address.setZipCode(addressJson.getString("zipCode"));

            User.setAddress(Address);



        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return User;
    }

    public ArrayList<ProductDTO> JsonArrayToProductDTOArray(String Json){
        ArrayList<ProductDTO> Products = new ArrayList<ProductDTO>();

        if(Json == null){
            return null;
        }

        try {
            JSONArray JArray = new JSONArray(Json);
            for(int i = 0; i < JArray.length();i++){
                JSONObject Jproduct = JArray.getJSONObject(i);
                ProductDTO product = new ProductDTO();

                product.setID(Jproduct.getInt("ID"));
                product.setDescription(Jproduct.getString("description"));
                product.setDisabled(Jproduct.getBoolean("disabled"));
                product.setLongName(Jproduct.getString("longName"));
                product.setName(Jproduct.getString("name"));
                product.setNumInCart(Jproduct.getInt("numInCart"));
                product.setPrice(Jproduct.getDouble("price"));

                JSONObject JStore = Jproduct.getJSONObject("store");
                StoreDTO store = new StoreDTO();
                store.setID(JStore.getInt("ID"));
                store.setName(JStore.getString("name"));

                product.setStore(store);

                Products.add(product);


            }




        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


        return Products;
    }

}
