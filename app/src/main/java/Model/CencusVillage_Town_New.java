package Model;

import java.io.Serializable;

/**
 * Created by kuush on 7/12/2016.
 */
public class CencusVillage_Town_New implements Serializable {

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    private String Code;
    private String Name;
}
