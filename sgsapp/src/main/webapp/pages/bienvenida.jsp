<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../commons/include.jsp" %>

<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
        <title>SGSWEB - Inicio</title>
        <link rel="shortcut icon" href="favicon.ico" >
        <link rel="icon" type="image/gif" href="animated_favicon1.gif" >
        <link type="text/css" rel="stylesheet" href="../css/mycss.css">
        <script type="text/javascript" src="../js/general.js"></script>
    </head>
    <body>
        <f:view>
            <%@include file="../commons/header.jsp" %>
            <h:form>
                <h:panelGrid columns="1" width="100%" style="text-align: center;">
                    <rich:spacer height="150"/>
                    <h:graphicImage style="border: 0;" value="/pages/images/sgsweb_logo.png" width="219" height="254"/>
                    <rich:spacer height="150"/>
                </h:panelGrid>
            </h:form>
            <%@include file="../commons/footer.jsp" %>
        </f:view>
    </body>
</html>
