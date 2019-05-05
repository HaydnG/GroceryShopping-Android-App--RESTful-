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
public class StoreDTO  implements Parcelable {
    
    private int ID;
    private String Name;
    private AddressDTO Address;

    public StoreDTO(int ID, String Name, AddressDTO Address) {
        this.ID = ID;
        this.Name = Name;
        this.Address = Address;
    }
    
    public StoreDTO() {
        this.ID = 0;
        this.Name = null;
        this.Address = null;
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

    public AddressDTO getAddress() {
        return Address;
    }

    public void setAddress(AddressDTO Address) {
        this.Address = Address;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.ID);
        dest.writeString(this.Name);
        dest.writeParcelable(this.Address, flags);
    }

    protected StoreDTO(Parcel in) {
        this.ID = in.readInt();
        this.Name = in.readString();
        this.Address = in.readParcelable(AddressDTO.class.getClassLoader());
    }

    public static final Creator<StoreDTO> CREATOR = new Creator<StoreDTO>() {
        @Override
        public StoreDTO createFromParcel(Parcel source) {
            return new StoreDTO(source);
        }

        @Override
        public StoreDTO[] newArray(int size) {
            return new StoreDTO[size];
        }
    };
}
