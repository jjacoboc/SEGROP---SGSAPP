/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pe.com.segrop.sgsapp.web.converters;

import java.math.BigDecimal;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import pe.com.segrop.sgsapp.web.common.GeneralBean;
import pe.com.segrop.sgsapp.web.common.Parameters;

/**
 *
 * @author jonatan.jacobo
 */
public class ConverterSiNo implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try{
            return BigDecimal.valueOf(Long.valueOf(value));
        }catch (Exception exception) {
            throw new ConverterException(exception);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        List lista = null;
        String label = "";
        try{
            lista = Parameters.getListaSINO();
            for(int i=0;i<lista.size();i++){
                GeneralBean item = (GeneralBean)lista.get(i);
                if(value.toString().equals(item.getCodigo())){
                    label = item.getDescripcion();
                }
            }
            return label;
        }catch (Exception exception) {
            throw new ConverterException(exception);
        }
    }


}
