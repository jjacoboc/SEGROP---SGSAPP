/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.web.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import pe.com.segrop.sgsapp.util.JSFUtils;
import pe.com.segrop.sgsapp.web.common.Parameters;

/**
 *
 * @author Alejandro
 */
@ManagedBean
@SessionScoped
public class ImageLogo {

    /**
     * Creates a new instance of ImageLogo
     */
    public ImageLogo() {
    }

    public StreamedContent getImage() throws IOException, Exception {
        FacesContext context = FacesContext.getCurrentInstance();
        String fileName = "logo.png";
        FileInputStream fis = null;
        try {
            if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
                // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
                return new DefaultStreamedContent();
            } else {
                // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
                String ruc = JSFUtils.getRequestParameter("ruc");

                ResourceBundle bundle = ResourceBundle.getBundle(Parameters.getParameters());
                String filepath = bundle.getString("filePath");
                String url = filepath + ruc + "\\";

                File file = new File(url, fileName);
                
                if (file.exists()) {
                    fis = new FileInputStream(file);
                } else {
                    fileName = "imagennodisponible.png";
                    fis = new FileInputStream(new File(filepath, fileName));
                }                
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return new DefaultStreamedContent(fis, "image/png", fileName);
    }
}
