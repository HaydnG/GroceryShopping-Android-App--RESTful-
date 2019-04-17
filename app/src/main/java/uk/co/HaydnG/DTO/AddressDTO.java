package uk.co.HaydnG.DTO;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 * @author haydn
 */
public class AddressDTO implements Parcelable {
    
    private int ID;
    private String Email;
    private String Addrline1;
    private String Addrline2;
    private String city;
    private String County;
    private String ZipCode;

    public AddressDTO(int ID, String Email, String Addrline1, String Addrline2, String city, String County, String ZipCode) {
        this.ID = ID;
        this.Email = Email;
        this.Addrline1 = Addrline1;
        this.Addrline2 = Addrline2;
        this.city = city;
        this.County = County;
        this.ZipCode = ZipCode;
    }

    
    public AddressDTO() {
        this.ID = -1;
        this.Email = "";
        this.Addrline1 = "";
        this.Addrline2 = "";
        this.city = "";
        this.County = "";
        this.ZipCode = "";
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getAddrline1() {
        return Addrline1;
    }

    public void setAddrline1(String Addrline1) {
        this.Addrline1 = Addrline1;
    }

    public String getAddrline2() {
        return Addrline2;
    }

    public void setAddrline2(String Addrline2) {
        this.Addrline2 = Addrline2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return County;
    }

    public void setCounty(String County) {
        this.County = County;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String ZipCode) {
        this.ZipCode = ZipCode;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.ID);
        dest.writeString(this.Email);
        dest.writeString(this.Addrline1);
        dest.writeString(this.Addrline2);
        dest.writeString(this.city);
        dest.writeString(this.County);
        dest.writeString(this.ZipCode);
    }

    protected AddressDTO(Parcel in) {
        this.ID = in.readInt();
        this.Email = in.readString();
        this.Addrline1 = in.readString();
        this.Addrline2 = in.readString();
        this.city = in.readString();
        this.County = in.readString();
        this.ZipCode = in.readString();
    }

    public static final Parcelable.Creator<AddressDTO> CREATOR = new Parcelable.Creator<AddressDTO>() {
        @Override
        public AddressDTO createFromParcel(Parcel source) {
            return new AddressDTO(source);
        }

        @Override
        public AddressDTO[] newArray(int size) {
            return new AddressDTO[size];
        }
    };
}
