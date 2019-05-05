/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.HaydnG.DTO;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 * @author haydn
 */
public class ProductDTO  implements Parcelable {
    
    private int ID;
    private String Name;
    private String LongName;
    private Double Price;
    private String Description;
    private StoreDTO Store;
    private String PSearch;
    private int NumInCart;
    private boolean Disabled = false;


    public ProductDTO(int ID, String Name, String LongName, Double Price, String Description, StoreDTO Store) {
        this.ID = ID;
        this.Name = Name;
        this.LongName = LongName;
        this.Price = Price;
        this.Description = Description;
        this.Store = Store;
    }
    
    public ProductDTO() {
        this.ID = 0;
        this.Name = null;
        this.LongName = null;
        this.Price = null;
        this.Description = null;
        this.Store = null;
        this.PSearch = null;
    }


    public boolean isDisabled() {
        return Disabled;
    }

    public void setDisabled(boolean Disabled) {
        this.Disabled = Disabled;
    }
    
    
    

    public int getNumInCart() {
        return NumInCart;
    }

    public void setNumInCart(int NumInCart) {
        this.NumInCart = NumInCart;
    }
    
    

    public String getPSearch() {
        return PSearch;
    }

    public void setPSearch(String PSearch) {
        this.PSearch = PSearch;
    }
    
    

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getLongName() {
        return LongName;
    }

    public void setLongName(String LongName) {
        this.LongName = LongName;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double Price) {
        this.Price = Price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public StoreDTO getStore() {
        return Store;
    }

    public void setStore(StoreDTO Store) {
        this.Store = Store;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.ID);
        dest.writeString(this.Name);
        dest.writeString(this.LongName);
        dest.writeValue(this.Price);
        dest.writeString(this.Description);
        dest.writeParcelable(this.Store, flags);
        dest.writeString(this.PSearch);
        dest.writeInt(this.NumInCart);
        dest.writeByte(this.Disabled ? (byte) 1 : (byte) 0);
    }

    protected ProductDTO(Parcel in) {
        this.ID = in.readInt();
        this.Name = in.readString();
        this.LongName = in.readString();
        this.Price = (Double) in.readValue(Double.class.getClassLoader());
        this.Description = in.readString();
        this.Store = in.readParcelable(StoreDTO.class.getClassLoader());
        this.PSearch = in.readString();
        this.NumInCart = in.readInt();
        this.Disabled = in.readByte() != 0;
    }

    public static final Creator<ProductDTO> CREATOR = new Creator<ProductDTO>() {
        @Override
        public ProductDTO createFromParcel(Parcel source) {
            return new ProductDTO(source);
        }

        @Override
        public ProductDTO[] newArray(int size) {
            return new ProductDTO[size];
        }
    };
}
