/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.segrop.sgsapp.web.common;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Jonatan Jacobo
 */
public class Log4jInit extends HttpServlet {

    @Override
    public void init() {
        String prefix = getServletContext().getRealPath("/");
        String file = getInitParameter("log4j-init-file");

        if (file != null) {
            PropertyConfigurator.configure(prefix + file);
        }
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) {
    }
}
