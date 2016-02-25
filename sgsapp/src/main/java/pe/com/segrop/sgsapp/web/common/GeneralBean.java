/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.segrop.sgsapp.web.common;

import java.io.Serializable;

/**
 *
 * @author Jonatan Jacobo
 */
public class GeneralBean implements Serializable {

    private String codigo;
    private String descripcion;
    
    public GeneralBean(){
    }
    
    public GeneralBean(String codigo, String descripcion){
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
