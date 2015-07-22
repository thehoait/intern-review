package intership.dev.contact.model;

import java.io.Serializable;

/**
 * Created by hoa on 7/21/15.
 */
public class Contact implements Serializable{
    private String mName;
    private int mAvatar;
    private String mDescription;

    public Contact() {
    }

    public Contact(String mName, int mAvatar, String mDescription) {
        this.mName = mName;
        this.mAvatar = mAvatar;
        this.mDescription = mDescription;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public int getAvatar() {
        return mAvatar;
    }

    public void setAvatar(int mAvatar) {
        this.mAvatar = mAvatar;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
