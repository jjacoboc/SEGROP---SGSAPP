/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.segrop.sgsapp.web.common;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jonatan Jacobo
 */
public class Parameters {

    public final static String ACTIVO = "1";
    public final static String INACTIVO = "0";
    
    public final static String TXT_MUY_ALTA = "MUY ALTA";
    public final static String TXT_ALTA = "ALTA";
    public final static String TXT_MEDIA = "MEDIA";
    public final static String TXT_BAJA = "BAJA";
    public final static String TXT_MUY_BAJA = "MUY BAJA";

    /**
     * Devuelve la ubicación del archivo messages.properties
     * @return
     */
    public static String getMessages(){
        return "pe.com.segrop.sgsapp.resources.messages";
    }

    /**
     * Devuelve la ubicación del archivo properties
     * @return
     */
    public static String getJDBC(){
        return "pe.com.segrop.sgsapp.resources.jdbc";
    }

    /**
     * Devuelve la ubicación del archivo mail.properties
     * @return
     */
    public static String getMail(){
        return "pe.com.segrop.sgsapp.resources.mail";
    }

    /**
     * Devuelve la ubicación del archivo parameters.properties
     * @return
     */
    public static String getParameters(){
        return "pe.com.segrop.sgsapp.resources.parameters";
    }

    /**
     * Devuelve la lista SI/NO.
     * @return
     */
    public static List<GeneralBean> getListaSINO(){
        List lista = new ArrayList();
        GeneralBean bean = new GeneralBean();
        bean.setCodigo("1");
        bean.setDescripcion("SI");
        lista.add(bean);
        bean = new GeneralBean();
        bean.setCodigo("0");
        bean.setDescripcion("NO");
        lista.add(bean);
        return lista;
    }
    
    public static List<GeneralBean> getListaTipoRiesgo(){
        List lista = new ArrayList();
        GeneralBean bean = new GeneralBean();
        bean.setCodigo("1");
        bean.setDescripcion("NOVEDAD");
        lista.add(bean);
        bean = new GeneralBean();
        bean.setCodigo("2");
        bean.setDescripcion("INSPECCION PRESENCIAL");
        lista.add(bean);
        return lista;
    }
}
