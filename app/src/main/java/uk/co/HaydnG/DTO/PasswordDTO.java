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
public class PasswordDTO implements Parcelable {
    
    private String Password;
    private String Password2;
    private String HashedPassword;
    private String Salt;

    public PasswordDTO(String Password, String HashedPassword, String Salt) {
        this.Password = Password;
        this.HashedPassword = HashedPassword;
        this.Salt = Salt;
    }

    public PasswordDTO() {       
        this.Password = null;
        this.HashedPassword = null;
        this.Salt = null;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getPassword2() {
        return Password2;
    }

    public void setPassword2(String Password2) {
        this.Password2 = Password2;
    }
    
    
    public String getHashedPassword() {
        return HashedPassword;
    }

    public void setHashedPassword(String HashedPassword) {
        this.HashedPassword = HashedPassword;
    }

    public String getSalt() {
        return Salt;
    }

    public void setSalt(String Salt) {
        this.Salt = Salt;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Password);
        dest.writeString(this.Password2);
        dest.writeString(this.HashedPassword);
        dest.writeString(this.Salt);
    }

    protected PasswordDTO(Parcel in) {
        this.Password = in.readString();
        this.Password2 = in.readString();
        this.HashedPassword = in.readString();
        this.Salt = in.readString();
    }

    public static final Parcelable.Creator<PasswordDTO> CREATOR = new Parcelable.Creator<PasswordDTO>() {
        @Override
        public PasswordDTO createFromParcel(Parcel source) {
            return new PasswordDTO(source);
        }

        @Override
        public PasswordDTO[] newArray(int size) {
            return new PasswordDTO[size];
        }
    };
}
