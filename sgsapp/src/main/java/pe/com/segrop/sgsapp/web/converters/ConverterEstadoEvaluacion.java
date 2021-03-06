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
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import pe.com.segrop.sgsapp.util.JSFUtils;
import pe.com.segrop.sgsapp.web.ui.ListasSessionMB;

/**
 *
 * @author JJ
 */
@FacesConverter("converterEstadoEvaluacion")
public class ConverterEstadoEvaluacion implements Converter{

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
        String label = "PENDIENTE DE ANALISIS";
        try{
            if(value != null && !"-1".equals(value.toString())){
                HttpSession session = JSFUtils.getSession();
                ListasSessionMB listasSession = session.getAttribute("listasSessionMB")!=null?(ListasSessionMB)session.getAttribute("listasSessionMB"):new ListasSessionMB();
                lista = listasSession.getListaEstadoEvaluacion();
                for(int i=0;i<lista.size();i++){
                    SelectItem item = (SelectItem)lista.get(i);
                    if(value.toString().equals(item.getValue().toString())){
                        label = item.getLabel();
                    }
                }
            }
        }catch (Exception exception) {
            throw new ConverterException(exception);
        }
        return label;
    }
    
}
