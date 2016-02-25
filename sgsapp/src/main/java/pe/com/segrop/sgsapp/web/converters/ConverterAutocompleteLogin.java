/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.web.converters;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import pe.com.segrop.sgsapp.dao.EmpresaDao;
import pe.com.segrop.sgsapp.domain.SegCabEmpresa;
import pe.com.segrop.sgsapp.web.common.ServiceFinder;

/**
 *
 * @author Alejandro
 */
@FacesConverter("cEmpresaLogin")
public class ConverterAutocompleteLogin implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        SegCabEmpresa result = null;
        if(value != null && value.trim().length() > 0) {
            try {
                EmpresaDao empresaDao = (EmpresaDao) ServiceFinder.findBean("EmpresaDao");
                List<SegCabEmpresa> empresas = empresaDao.obtenerListaEmpresasActivas();
                for(SegCabEmpresa empresa : empresas) {
                    if(empresa.getNCodEmpresa().toString().equals(value)) {
                        result = empresa;
                        break;
                    }
                }
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en la conversión", "Empresa no válida."));
            }
        }
        return result;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null) {
            return String.valueOf(((SegCabEmpresa) value).getNCodEmpresa().toString());
        }
        else {
            return null;
        }
    }
    
}
