<?xml version="1.0" encoding="UTF-8"?>
<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE HTML>
<%@include file="../commons/include.jsp" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="pe.com.segrop.sgs.domain.SegDetAcciones"%>
<%@page import="pe.com.segrop.sgs.domain.SegDetInspreAcciones"%>
<%@page import="pe.com.segrop.sgs.domain.SegDetInspreEvaluacion"%>
<%@page import="pe.com.segrop.sgs.domain.SegDetInspreevalDetalle"%>
<%@page import="pe.com.segrop.sgs.domain.SegDetNovEvaluacion"%>
<%@page import="pe.com.segrop.sgs.domain.SegDetNovevalDetalle"%>
<%@page import="pe.com.segrop.sgs.domain.SegDetRiesgo"%>
<%@page import="pe.com.segrop.sgs.web.ui.MatrizMB"%>
<%@page import="pe.com.segrop.sgs.web.ui.MenuMB"%>

<%
    int i=0,j=0;
    SegDetRiesgo riesgo = null;
    List<SegDetRiesgo> listaRiesgo = (List<SegDetRiesgo>)session.getAttribute("listaRiesgo");
    String html = ((MatrizMB)session.getAttribute("matrizMB")).getHtml();
    html = html != null ? html : "Ningún riesgo seleccionado.";
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>SGSWEB - Matriz de Riesgos</title>
        <link href="images/favicon.ico" type="image/x-icon" rel="shortcut icon">
        <link href="images/favicon.ico" type="image/x-icon" rel="icon">
        <link type="text/css" rel="stylesheet" href="../css/mycss.css">
        <style type="text/css">
            #matrix td {
                border: 1px solid #000000;
            }
        </style>
        <script type="text/javascript" src="../js/general.js"></script>
        <!--
        <script type="text/javascript" src="../js/jquery-1.6.min.js"></script>
        <script type="text/javascript" src="js/tooltip/prototype/prototype.js"></script>
        <script type="text/javascript" src="js/tooltip/scriptaculous/scriptaculous.js"></script>
        <script type="text/javascript" src="js/tooltip/HelpBalloon.js"></script>
        <script type="text/javascript">
            HelpBalloon.Options.prototype = Object.extend(HelpBalloon.Options.prototype, {
                icon: 'images/tooltip/icon.gif',
                button: 'images/tooltip/button.png',
                balloonPrefix: 'images/tooltip/balloon-'
            });
        </script>
        -->
        <script>
            function allowDrop(ev){
                ev.preventDefault();
            }
            function drag(ev){
                ev.dataTransfer.setData("Text",ev.target.id);
            }
            function drop(ev){
                ev.preventDefault();
                var idtarget = ev.target.id;
                var data = ev.dataTransfer.getData("Text");
                var canvas = document.getElementById(data);
                
                if(idtarget !== 'tdtrash' && idtarget !== 'imgTrash'){
                    var probabilidad = idtarget.substring(2,4);
                    var impacto = idtarget.substring(4,6);
                    var idsource = canvas.id;
                    canvas.src = 'images/dot_'+getColor(probabilidad,impacto)+'.png';
                    ev.target.appendChild(canvas);

                    document.getElementById('hiddenForm:ids').value = idtarget.substring(2)+idsource.substring(8);
                    document.getElementById('hiddenForm:invisibleClickTarget').click();
                }else{
                    if(confirm("El riesgo se eliminará y pasará a estado Solucionado.\nDesea Continuar?")){
                        var trash = document.getElementById('imgTrash');
                        trash.src = 'images/trashfull.png';
                        canvas.style = 'visibility: hidden';
                        ev.target.appendChild(canvas);
                        
                        document.getElementById('hiddenForm:ids').value = canvas.id.substring(8);
                        document.getElementById('hiddenForm:invisibleClickTrash').click();
                    }
                }
            }
        </script>
    </head>
    <body>
        <f:view>
            <%@include file="../commons/header.jsp" %>
            <a4j:region id="rgn">
            <a4j:status id="bodystatus" forceId="true" onstart="Richfaces.showModalPanel('process')" onstop="Richfaces.hideModalPanel('process')"></a4j:status>
            <h:form id="matrizForm">
                <rich:panel id="pnl" header="MATRIZ DE RIESGOS" style="font-size: 13px;">
                <center>
                    <table border="0" width="100%">
                        <%--
                        <tr>
                            <td colspan="2" style="vertical-align: top;">
                                <fieldset>
                                    <legend style="font-size: 11px;font-weight: bold;">Criterios de Búsqueda</legend>
                                    <h:panelGrid columns="3" columnClasses="columnLbl, columnTxt, columnLbl">
                                        <h:outputText value="Rango Fechas: " styleClass="Etiqueta1" />
                                        <h:panelGrid columns="5" cellpadding="0" cellspacing="0">
                                            <rich:calendar id="searchFechaInicio" value="#{matrizMB.searchFechaInicio}" popup="true" datePattern="dd/MM/yyyy" 
                                                           jointPoint="top-left" direction="bottom-right" locale="es" inputSize="10" style="float: left;font-size: 11px"/>
                                            <rich:spacer width="10"/>
                                            <h:outputText value="al" style="font-weight: bold;" styleClass="Etiqueta1" />
                                            <rich:spacer width="10"/>
                                            <rich:calendar id="searchFechaFin" value="#{matrizMB.searchFechaFin}" popup="true" datePattern="dd/MM/yyyy" 
                                                           jointPoint="top-left" direction="bottom-right" locale="es" inputSize="10" style="float: left;font-size: 11px"/>
                                        </h:panelGrid>
                                        <a4j:commandButton id="btnBuscar" process="@form" value="Buscar" actionListener="#{matrizMB.buscarRiesgos}" reRender="pnl" style="font-size: 11px;height: 2em"/>
                                    </h:panelGrid>
                                </fieldset>
                            </td>
                        </tr>
                        --%>
                        <tr>
                            <td style="vertical-align: top;" width="550">
                                <fieldset>
                                    <legend style="font-size: 11px;font-weight: bold;">Matriz</legend>
                                    <table id="matrix" cellpadding="0" cellspacing="0" width="540">
                                      <tr style="background-color: #cccccc;">
                                        <th rowspan="6" scope="row" accesskey="">
                                          <p>&nbsp;P&nbsp;</p><p>R</p><p>O</p><p>B</p><p>A</p><p>B</p><p>I</p><p>L</p><p>I</p><p>D</p><p>A</p><p>D</p>
                                        </th>
                                      </tr>
                                      <%
                                        for(i=44;i<=48;i++){ //probabilidad u ocurrencia
                                      %>
                                            <tr>
                                      <%
                                            for(j=48;j<=53;j++){ //impacto
                                                if(j==48){
                                      %>
                                      <%              if(i==48){ %><th height="90" align="right" style="background-color: #cccccc;">MUY BAJA&nbsp;</th><% }%>
                                      <%              if(i==47){ %><th height="90" align="right" style="background-color: #cccccc;">BAJA&nbsp;</th><% }%>
                                      <%              if(i==46){ %><th height="90" align="right" style="background-color: #cccccc;">MEDIA&nbsp;</th><% }%>
                                      <%              if(i==45){ %><th height="90" align="right" style="background-color: #cccccc;">ALTA&nbsp;</th><% }%>
                                      <%              if(i==44){ %><th height="90" align="right" style="background-color: #cccccc;">MUY ALTA&nbsp;</th><% }%>
                                      <%
                                                }else{
                                      %>
                                                    <td style="border-color: #777777;">
                                                        <table cellpadding="0" cellspacing="0" width="90" height="90">
                                      <%
                                                            for(int m=0;m<=2;m++){  // nivel de probabilidad u ocurrencia
                                      %>
                                                                <tr>
                                      <%
                                                                    for(int n=0;n<=2;n++){  // nivel de impacto
                                      %>
                                                                        <td width="30" height="30" id="td<%=i%><%=j%><%=m%><%=n%>" ondrop="drop(event);" ondragover="allowDrop(event)" style="border-color: #dddeee;">
                                      <%
                                                                            if(listaRiesgo != null && !listaRiesgo.isEmpty()){
                                                                                for(int k=0;k<listaRiesgo.size();k++){
                                                                                    riesgo = (SegDetRiesgo)listaRiesgo.get(k);
                                                                                    if(riesgo.getNOcurrencia().intValue() == i && riesgo.getNImpacto().intValue() == j
                                                                                            && riesgo.getNNivelOcurrencia().intValue() == m && riesgo.getNNivelImpacto().intValue() == n){
                                      %>
                                                                                        <image id="myCanvas<%=riesgo.getNTipoRiesgo()%><%=riesgo.getNCodRiesgo()%>" class="<%=i%>,<%=j%>,<%=k%>" 
                                                                                               draggable="true" ondragstart="drag(event);" ondblclick="showRisk(this);"></image>
                                      <%
                                                                                    }
                                                                                }
                                                                            }
                                      %>
                                                                        </td>
                                      <%
                                                                    }
                                      %>
                                                                </tr>
                                      <%
                                                            }
                                      %>
                                                        </table>
                                                    </td>
                                      <%                                    
                                                }
                                            }
                                      %>
                                            </tr>
                                      <%
                                        }
                                      %>
                                      <tr style="background-color: #cccccc;">
                                        <th>&nbsp;</th>
                                        <th>&nbsp;</th>
                                        <th width="90">MUY BAJO</th>
                                        <th width="90">BAJO</th>
                                        <th width="90">MEDIO</th>
                                        <th width="90">ALTO</th>
                                        <th width="90">MUY ALTO</th>
                                      </tr>
                                      <tr style="background-color: #cccccc;">
                                        <th>&nbsp;</th>
                                        <th>&nbsp;</th>
                                        <th colspan="6" scope="col">I&nbsp;&nbsp;&nbsp;&nbsp;M&nbsp;&nbsp;&nbsp;&nbsp;P&nbsp;&nbsp;&nbsp;&nbsp;A&nbsp;&nbsp;&nbsp;&nbsp;C&nbsp;&nbsp;&nbsp;&nbsp;T&nbsp;&nbsp;&nbsp;&nbsp;O</th>
                                      </tr>
                                    </table>
                                </fieldset>
                            </td>
                            <td style="vertical-align: top;">
                                <fieldset>
                                    <legend style="font-size: 11px;font-weight: bold;">Leyenda</legend>
                                    <table style="border: 1px solid #000000;" cellpadding="0" cellspacing="0" width="100%">
                                        <tr style="background-color: #cccccc;">
                                            <td align="center" colspan="2" style="border: 1px solid #000000;"><b>RIESGO</b></td>
                                        </tr>
                                        <tr style="background-color: #cccccc;">
                                            <td style="border: 1px solid #000000;" align="center" width="10%"><b>NIVEL</b></td>
                                            <td style="border: 1px solid #000000;" align="center"><b>DESCRIPCIÓN</b></td>
                                        </tr>
                                        <tr>
                                            <td style="border: 1px solid #000000;" align="center">&nbsp;<img src="images/dot_red.png" />&nbsp;</td>
                                            <td style="border: 1px solid #000000;">&nbsp;Riesgo extremo; requiere acción inmediata.</td>
                                        </tr>
                                        <tr>
                                            <td style="border: 1px solid #000000;" align="center">&nbsp;<image src="images/dot_orange.png"></image>&nbsp;</td>
                                            <td style="border: 1px solid #000000;">&nbsp;Riesgo alto; necesita atención de la alta gerencia.</td>
                                        </tr>
                                        <tr>
                                            <td style="border: 1px solid #000000;" align="center">&nbsp;<image src="images/dot_yellow.png"></image>&nbsp;</td>
                                            <td style="border: 1px solid #000000;">&nbsp;Riesgo moderado; debe especificarse responsabilidad gerencial.</td>
                                        </tr>
                                        <tr>
                                            <td style="border: 1px solid #000000;" align="center">&nbsp;<image src="images/dot_green.png"></image>&nbsp;</td>
                                            <td style="border: 1px solid #000000;">&nbsp;Riesgo bajo; administrar mediante procedimientos de rutina.</td>
                                        </tr>
                                    </table>
                                </fieldset>
                                <fieldset style="height: 386px;">
                                    <legend style="font-size: 11px;font-weight: bold;">Datos del Riesgo</legend>
                                    <h:panelGrid id="pgRisk" columns="1" cellpadding="0" cellspacing="0" width="100%">
                                        <div style="height: 373px;overflow-y: auto;"><%=html%></div>
                                    </h:panelGrid>
                                </fieldset>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" style="vertical-align: top;">
                                <fieldset>
                                    <legend style="font-size: 11px;font-weight: bold;">Eliminar Riesgo de la Matriz</legend>
                                    <table id="tbltrash" border="0" cellpadding="0" cellspacing="0" width="100%">
                                        <tr>
                                            <td id="tdtrash" width="50" ondrop="drop(event);" ondragover="allowDrop(event)">
                                                <image id="imgTrash"></image>
                                            </td>
                                            <td><p style="font-size: 12px;font-weight: bold;font-style: italic;margin-left: 10px;">Los riesgos colocados serán cerrados y solucionados</p></td>
                                        </tr>
                                    </table>
                                </fieldset>
                            </td>
                        </tr>
                    </table>
                </center>
                </rich:panel>
                <script type="text/javascript">
                  var trash = document.getElementById('imgTrash');
                  trash.src = 'images/trashempty.png';
                  trash.width = 48;
                  trash.height = 48;
                  <%SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");%>
                  <%if(listaRiesgo != null && !listaRiesgo.isEmpty()){%>    
                    <%for(int k=0;k<listaRiesgo.size();k++){
                          riesgo = (SegDetRiesgo)listaRiesgo.get(k);
                          List<SegDetAcciones> segDetNovAcciones = null;
                          SegDetNovEvaluacion segDetNovEvaluacion = null;
                          List<SegDetInspreAcciones> segDetInspreAcciones = null;
                          SegDetInspreEvaluacion segDetInspreEvaluacion = null;
                          if("1".equals(riesgo.getNTipoRiesgo().toString())){
                              segDetNovAcciones = (List<SegDetAcciones>)riesgo.getSegDetAcciones();
                              segDetNovEvaluacion = (SegDetNovEvaluacion)riesgo.getSegDetNovEvaluacion();
                          }else{
                              segDetInspreAcciones = (List<SegDetInspreAcciones>)riesgo.getSegDetInspreAcciones();
                              segDetInspreEvaluacion = (SegDetInspreEvaluacion)riesgo.getSegDetInspreEvaluacion();
                          }
                    %>
                      var id = 'myCanvas'+<%=riesgo.getNTipoRiesgo()%>+<%=riesgo.getNCodRiesgo()%>;
                      var element = document.getElementById(id);
                      var aux = document.getElementById(id).className;
                      element.src = 'images/dot_'+getColor(aux.substring(0,2),aux.substring(3,5))+'.png';
                    <%}%>
                  <%}%>
                  
                  function showRisk(obj){
                      var ele = obj.parentElement;
                      var className = obj.className;
                      
                      <%
                        for(int p=44;p<=48;p++){ //probabilidad u ocurrencia
                            for(int q=49;q<=53;q++){
                                for(int m=0;m<=2;m++){
                                    for(int n=0;n<=2;n++){
                      %>
                                        var id = 'td'+<%=p%>+<%=q%>+<%=m%>+<%=n%>;
                                        if(id === ele.id){
                                            document.getElementById(id).setAttribute("style", "background-color: #93C4E5;border-color: #dddeee;");
                                        }else{
                                            document.getElementById(id).setAttribute("style", "background-color: #F3F2F2;border-color: #dddeee;");
                                        }
                      <%
                                    }
                                }
                            }
                        }
                      %>
                      var probabilidad = className.substring(0,2);
                      var impacto = className.substring(3,5);
                      var indice = className.substring(6);
                      document.getElementById('showRiskForm:probabilidad').value = probabilidad;
                      document.getElementById('showRiskForm:impacto').value = impacto;
                      document.getElementById('showRiskForm:indice').value = indice;
                      document.getElementById('showRiskForm:btnShowRisk').click();
                  }
                  
                  function getColor(i,j){
                      var color = '';
                      switch(i){
                        case '44':
                            switch(j){
                                case '49': color = "orange";break;
                                case '50': color = "orange";break;
                                case '51': color = "red";break;
                                case '52': color = "red";break;
                                case '53': color = "red";break;
                            };
                            break;
                        case '45':
                            switch(j){
                                case '49': color = "yellow";break;
                                case '50': color = "orange";break;
                                case '51': color = "orange";break;
                                case '52': color = "red";break;
                                case '53': color = "red";break;
                            };
                            break;
                        case '46':
                            switch(j){
                                case '49': color = "green";break;
                                case '50': color = "yellow";break;
                                case '51': color = "orange";break;
                                case '52': color = "red";break;
                                case '53': color = "red";break;
                            };
                            break;
                        case '47':
                            switch(j){
                                case '49': color = "green";break;
                                case '50': color = "green";break;
                                case '51': color = "yellow";break;
                                case '52': color = "orange";break;
                                case '53': color = "red";break;
                            };
                            break;
                        case '48':
                            switch(j){
                                case '49': color = "green";break;
                                case '50': color = "green";break;
                                case '51': color = "yellow";break;
                                case '52': color = "orange";break;
                                case '53': color = "orange";break;
                            };
                            break;
                    }
                    return color;
                  }
                </script>
                <script type="text/javascript">
                    function segNovedad(rowKey){
                        document.getElementById('tooltipForm:rowKey').value = rowKey;
                        document.getElementById('tooltipForm:segNovedad').click();
                    }
                    function evalNovedad(rowKey){
                        document.getElementById('tooltipForm:rowKey').value = rowKey;
                        document.getElementById('tooltipForm:evalNovedad').click();
                    }
                    function segInspeccion(rowKey){
                        document.getElementById('tooltipForm:rowKey').value = rowKey;
                        document.getElementById('tooltipForm:segInspeccion').click();
                    }
                    function evalInspeccion(rowKey){
                        document.getElementById('tooltipForm:rowKey').value = rowKey;
                        document.getElementById('tooltipForm:evalInspeccion').click();
                    }
                </script>
            </h:form>
            <h:form id="hiddenForm" style="display: none;">
                <h:inputHidden id="ids" value="#{matrizMB.datos}" />
                <a4j:commandButton process="@form" id="invisibleClickTarget" actionListener="#{matrizMB.onDrop}"/>
                <a4j:commandButton process="@form" id="invisibleClickTrash" actionListener="#{matrizMB.onDeleteRisk}"/>
            </h:form>
            <h:form id="tooltipForm" style="display: none;">
                <h:inputHidden id="rowKey" value="#{matrizMB.rowKey}" />
                <a4j:commandButton process="@form" status="bodystatus" id="segNovedad" actionListener="#{matrizMB.toSeguimientoNovedad}" action="novedad"/>
                <a4j:commandButton process="@form" status="bodystatus" id="evalNovedad" actionListener="#{matrizMB.toEvaluacionNovedad}" action="novedad"/>
                <a4j:commandButton process="@form" status="bodystatus" id="segInspeccion" actionListener="#{matrizMB.toSeguimientoInspeccion}" action="presencial"/>
                <a4j:commandButton process="@form" status="bodystatus" id="evalInspeccion" actionListener="#{matrizMB.toEvaluacionInspeccion}" action="presencial"/>
            </h:form>
            <h:form id="showRiskForm" style="display: none;">
                <h:inputHidden id="probabilidad" />
                <h:inputHidden id="impacto" />
                <h:inputHidden id="indice" value="#{matrizMB.rowKey}"/>
                <a4j:commandButton id="btnShowRisk" actionListener="#{matrizMB.showRisk}" reRender="pgRisk" />
            </h:form>
            <rich:modalPanel id="process" autosized="true" resizeable="false" moveable="false">
                <h:panelGrid columns="1" width="180" style="text-align: center;">
                    <h:outputText value="Procesando..." styleClass="Etiqueta1"/>
                    <h:graphicImage style="border: 0;" value="/pages/images/156.gif"/>
                    <h:outputText value="Por favor, espere un momento." styleClass="Etiqueta1"/>
                </h:panelGrid>
            </rich:modalPanel>
            </a4j:region>
            <%@include file="../commons/footer.jsp" %>
        </f:view>
    </body>
</html>
