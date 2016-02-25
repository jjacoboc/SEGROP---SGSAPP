/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.web.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author JJ
 */
public class Criteria implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private String column;
    private String condition;
    private String value;
    private List<SelectItem> listCondition;
    private String prefijo;
    private String sufijo;
    private Boolean flagSelectOneMenu;
    private Boolean flagDateHour;
    private Boolean flagInput;
    private Boolean flagDate;
    
    
    public Criteria(){
        this.setListCondition(new Items(new ArrayList<GeneralBean>(), Items.FIRST_ITEM_SELECT, "codigo", "descripcion").getItems());
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<SelectItem> getListCondition() {
        return listCondition;
    }

    public void setListCondition(List<SelectItem> listCondition) {
        this.listCondition = listCondition;
    }

    public String getPrefijo() {
        return prefijo;
    }

    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }

    public String getSufijo() {
        return sufijo;
    }

    public void setSufijo(String sufijo) {
        this.sufijo = sufijo;
    }

    public Boolean getFlagSelectOneMenu() {
        return flagSelectOneMenu;
    }

    public void setFlagSelectOneMenu(Boolean flagSelectOneMenu) {
        this.flagSelectOneMenu = flagSelectOneMenu;
    }

    public Boolean getFlagDateHour() {
        return flagDateHour;
    }

    public void setFlagDateHour(Boolean flagDateHour) {
        this.flagDateHour = flagDateHour;
    }

    public Boolean getFlagInput() {
        return flagInput;
    }

    public void setFlagInput(Boolean flagInput) {
        this.flagInput = flagInput;
    }

    public Boolean getFlagDate() {
        return flagDate;
    }

    public void setFlagDate(Boolean flagDate) {
        this.flagDate = flagDate;
    }
}
