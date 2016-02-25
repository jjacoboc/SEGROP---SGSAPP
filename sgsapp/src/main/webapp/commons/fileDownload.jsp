<%@page import="java.io.DataInputStream" %>
<%@page import="java.io.File" %>
<%@page import="java.io.FileInputStream" %>
<%@page import="java.io.FileNotFoundException" %>
<%@page import="pe.com.segrop.sgs.web.common.BaseBean"%>
<!DOCTYPE html>

<%
    String fileName = (String) BaseBean.getRequest().getAttribute("fileName");
    String filePath = (String) BaseBean.getRequest().getAttribute("filePath");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body onload="window.close();">
    <%
        try{
            session = BaseBean.getSession();
            File                f        = new File(filePath.concat(fileName));
            int                 length   = 0;
            ServletOutputStream op       = response.getOutputStream();
            ServletContext      context  = getServletConfig().getServletContext();
            String              mimetype = context.getMimeType(fileName);

            response.setContentType( (mimetype != null) ? mimetype : "application/octet-stream" );
            response.setContentLength( (int)f.length() );
            response.setHeader( "Content-Disposition", "attachment; filename=".concat(fileName) );

            DataInputStream in = new DataInputStream(new FileInputStream(f));
            byte[] bbuf = new byte[in.available()];

            while((in != null) && ((length = in.read(bbuf)) != -1)){
                op.write(bbuf,0,length);
            }

            in.close();
            op.flush();
            op.close();

        }catch(FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }
    %>
    </body>
</html>
