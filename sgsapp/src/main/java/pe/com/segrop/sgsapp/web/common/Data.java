/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.web.common;

import java.io.Serializable;

/**
 *
 * @author JJ
 */
public class Data implements Serializable{
    private String name;
    private String[] values;
    
    public Data() {
    }
    
    public Data(String name) {
	this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }
}
