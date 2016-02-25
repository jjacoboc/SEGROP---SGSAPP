<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../commons/include.jsp" %>

<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
        <title>SGSWEB - Gesti�n de Lugares</title>
        <link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon">
        <link href="images/favicon.ico" rel="icon" type="image/x-icon">
        <link type="text/css" rel="stylesheet" href="../css/mycss.css">
        <script type="text/javascript" src="../js/general.js"></script>
    </head>
    <body>
        <f:view>
            <%@include file="../commons/header.jsp" %>
            <h:form id="lugarForm">
                <rich:panel header="GESTION DE LUGARES" style="font-size: 13px;">
                    <fieldset>
                        <legend style="font-size: 11px;font-weight: bold;">Criterios de B�squeda</legend>
                        <h:panelGrid columns="2" width="100%" columnClasses="columnLbl1, columnTxt1">
                            <h:outputText value="Local: " styleClass="Etiqueta1" />
                            <h:selectOneMenu value="#{lugarMB.searchLocal}" valueChangeListener="#{listasSessionMB.obtenerListaAreaByLocal}" style="float: left;font-size: 11px"> 
                                <f:selectItems value="#{listasSessionMB.listaLocalActivoByEmpresa}"/>
                                <a4j:support event="onchange" reRender="searchArea" ajaxSingle="true"/>
                            </h:selectOneMenu>

                            <h:outputText value="Area: " styleClass="Etiqueta1" />
                            <h:selectOneMenu id="searchArea" value="#{lugarMB.searchArea}" style="float: left;font-size: 11px"> 
                                <f:selectItems value="#{listasSessionMB.listaAreaActivaByLocal}"/>
                            </h:selectOneMenu>
                            
                            <h:outputText value="Nombre: " styleClass="Etiqueta1" />
                            <h:inputText value="#{lugarMB.searchDescripcion}" size="50" maxlength="100" style="float: left;font-size: 11px;text-transform: uppercase;" />
                        </h:panelGrid>
                        <h:panelGrid columns="1">
                            <h:panelGroup>
                                <a4j:commandButton id="btnBuscar" value="Buscar" actionListener="#{lugarMB.buscarLugar}" reRender="tbl" style="font-size: 11px;height: 2em"/>
                                <a4j:commandButton id="btnNuevo" value="Nuevo" actionListener="#{lugarMB.toRegistrar}" reRender="dlgMsgs, empresa, descripcion" style="font-size: 11px;height: 2em">
                                    <rich:componentControl for="dlg" attachTo="btnNuevo" operation="show" event="onclick"/>
                                </a4j:commandButton>
                            </h:panelGroup>
                        </h:panelGrid>
                    </fieldset>

                    <fieldset>
                        <legend style="font-size: 11px;font-weight: bold;">Listado de Lugares</legend>
                        <rich:messages id="tblMsgs" showSummary="true" showDetail="true"/>
                        <rich:dataTable id="tbl" var="row" value="#{lugarMB.listaLugar}" rows="10" rowKeyVar="rowkey" style="font-size: 11px;" width="100%">
                            <f:facet name="header">
                                <rich:datascroller for="tbl" maxPages="15"/>
                            </f:facet>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="LOCAL" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.id.NCodLocal}" style="font-size: 11px;font-weight: normal;">
                                    <f:converter converterId="converterLocal"/>
                                </h:outputText>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="�REA" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.id.NCodArea}" style="font-size: 11px;font-weight: normal;">
                                    <f:converter converterId="converterArea"/>
                                </h:outputText>
                            </rich:column>
                            <rich:column>
                                <f:facet name="header">
                                    <h:outputText value="LUGAR" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.VDescripcion}" style="font-size: 11px;font-weight: normal;"/>
                            </rich:column>                            
                            <rich:column style="text-align: center;">
                                <f:facet name="header">
                                    <h:outputText value="ACTIVO" style="font-size: 11px;"/>
                                </f:facet>
                                <h:outputText value="#{row.NFlgActivo}" style="font-size: 11px;font-weight: normal;">
                                    <f:converter converterId="converterSiNo"/>
                                </h:outputText>
                            </rich:column>
                            <rich:column title="" style="text-align: center;">
                                <f:facet name="header">
                                    <h:outputText value="ACCIONES" style="font-size: 11px;"/>
                                </f:facet>
                                <a4j:commandLink id="elink" actionListener="#{lugarMB.toEditar}" oncomplete="#{rich:component('dlgEdit')}.show()" reRender="gridEdit">
                                    <a4j:actionparam name="rowkey" value="#{rowkey}"/>
                                    <f:setPropertyActionListener value="#{row}" target="#{lugarMB.selectedItem}"/>
                                    <h:graphicImage style="border: 0;" url="/pages/images/pencil.png" alt="editar local" title="editar local"/>
                                </a4j:commandLink>
                                <rich:spacer width="5"/>
                                <a4j:commandLink id="dlink" rendered="#{row.NFlgActivo==1}" >
                                    <f:setPropertyActionListener value="#{row}" target="#{lugarMB.selectedItem}"/>
                                    <rich:componentControl for="cdlg" attachTo="dlink" operation="show" event="onclick"/>
                                    <h:graphicImage style="border: 0;" url="/pages/images/cross.png" alt="desactivar local" title="desactivar local"/>
                                </a4j:commandLink>
                                <a4j:commandLink id="alink" rendered="#{row.NFlgActivo==0}">
                                    <f:setPropertyActionListener value="#{row}" target="#{lugarMB.selectedItem}"/>
                                    <rich:componentControl for="cadlg" attachTo="alink" operation="show" event="onclick"/>
                                    <h:graphicImage style="border: 0;" url="/pages/images/tick.png" alt="activar local" title="activar local"/>
                                </a4j:commandLink>
                            </rich:column>
                        </rich:dataTable>
                    </fieldset>
                </rich:panel>
            </h:form>
            <rich:modalPanel id="dlg" resizeable="false" moveable="false" autosized="true" width="500">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="NUEVO LUGAR"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <h:form id="dlgForm">
                    <rich:messages id="dlgMsgs" showSummary="true" showDetail="true" errorClass="msgError" errorLabelClass="msgLabelError">
                        <f:facet name="errorMarker">
                            <h:graphicImage style="border: 0;" url="/pages/images/messagebox_critical.png"/>
                        </f:facet>
                        <f:facet name="infoMarker">
                            <h:graphicImage style="border: 0;" url="/pages/images/messagebox_info.png"/>
                        </f:facet>
                        <f:facet name="warnMarker">
                            <h:graphicImage style="border: 0;" url="/pages/images/messagebox_warning.png"/>
                        </f:facet>
                    </rich:messages>
                    <h:panelGrid columns="2" style="margin-bottom:10px">
                        <h:outputLabel for="local" value="Local: " styleClass="Etiqueta1"/>
                        <h:selectOneMenu id="local" value="#{lugarMB.local}" valueChangeListener="#{listasSessionMB.obtenerListaAreaByLocal}" style="float: left;font-size: 11px">
                            <f:selectItems value="#{listasSessionMB.listaLocalActivoByEmpresa}"/>
                            <a4j:support event="onchange" reRender="area" ajaxSingle="true"/>
                        </h:selectOneMenu>
                        
                        <h:outputLabel for="area" value="�rea: " styleClass="Etiqueta1"/>
                        <h:selectOneMenu id="area" value="#{lugarMB.area}" style="float: left;font-size: 11px">
                            <f:selectItems value="#{listasSessionMB.listaAreaActivaByLocal}"/>
                        </h:selectOneMenu>

                        <h:outputLabel for="descripcion" value="Descripci�n: " styleClass="Etiqueta1"/>
                        <h:inputText id="descripcion" value="#{lugarMB.descripcion}" size="50" maxlength="100" style="float: left;font-size: 11px;text-transform: uppercase;" />
                    </h:panelGrid>

                    <h:panelGroup>
                        <a4j:commandButton id="btnGrabar" value="Grabar" onclick="confirmDlg.show();" style="font-size: 11px;height: 2em">
                            <rich:componentControl for="confirmDlg" attachTo="btnGrabar" operation="show" event="onclick"/>
                        </a4j:commandButton>
                        <a4j:commandButton id="btnCancelar" value="Cancelar" onclick="dlg.hide();" style="font-size: 11px;height: 2em">
                            <rich:componentControl for="dlg" attachTo="btnCancelar" operation="hide" event="onclick"/>
                        </a4j:commandButton>
                    </h:panelGroup>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="confirmDlg" height="100" keepVisualState="true" resizeable="false">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="Confirmaci�n"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <h:form id="confirmDlgForm">
                    <h:outputText value="Se proceder� a registrar el lugar." style="font-size: 11px;"/><br/>
                    <h:outputText value="Desea Continuar?" style="font-size: 11px;"/><br/>
                    <hr>
                    <a4j:commandButton id="btnSi" value="SI" reRender="dlgMsgs" actionListener="#{lugarMB.registrarLugar}" oncomplete="#{lugarMB.action}" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmDlg" attachTo="btnSi" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="btnNo" value="NO" type="button" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmDlg" attachTo="btnNo" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="dlgEdit" resizeable="false" moveable="false" autosized="true" width="500">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="EDITAR LUGAR"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <h:form id="dlgEditForm">
                    <rich:messages id="dlgEditMsgs" showSummary="true" showDetail="true" errorClass="msgError" errorLabelClass="msgLabelError">
                        <f:facet name="errorMarker">
                            <h:graphicImage style="border: 0;" url="/pages/images/messagebox_critical.png"/>
                        </f:facet>
                        <f:facet name="infoMarker">
                            <h:graphicImage style="border: 0;" url="/pages/images/messagebox_info.png"/>
                        </f:facet>
                        <f:facet name="warnMarker">
                            <h:graphicImage style="border: 0;" url="/pages/images/messagebox_warning.png"/>
                        </f:facet>
                    </rich:messages>
                    <h:panelGrid id="gridEdit" columns="2" style="margin-bottom:10px">
                        <h:outputLabel for="localedit" value="Local: " styleClass="Etiqueta1"/>
                        <h:selectOneMenu id="localedit" value="#{lugarMB.selectedItem.id.NCodLocal}" valueChangeListener="#{listasSessionMB.obtenerListaAreaByLocal}" style="float: left;font-size: 11px">
                            <f:selectItems value="#{listasSessionMB.listaLocalActivoByEmpresa}"/>
                            <a4j:support event="onchange" reRender="areaedit" ajaxSingle="true"/>
                        </h:selectOneMenu>
                        
                        <h:outputLabel for="areaedit" value="�rea: " styleClass="Etiqueta1"/>
                        <h:selectOneMenu id="areaedit" value="#{lugarMB.selectedItem.id.NCodArea}" style="float: left;font-size: 11px">
                            <f:selectItems value="#{listasSessionMB.listaAreaActivaByLocal}"/>
                        </h:selectOneMenu>

                        <h:outputLabel for="descripcionedit" value="Descripci�n: " styleClass="Etiqueta1"/>
                        <h:inputText id="descripcionedit" value="#{lugarMB.selectedItem.VDescripcion}" size="50" maxlength="100" style="float: left;font-size: 11px;text-transform: uppercase;" />
                    </h:panelGrid>

                    <h:panelGroup>
                        <a4j:commandButton id="btnGrabar_" value="Grabar" onclick="confirmEditDlg.show();" style="font-size: 11px;height: 2em">
                            <rich:componentControl for="confirmEditDlg" attachTo="btnGrabar_" operation="show" event="onclick"/>
                        </a4j:commandButton>
                        <a4j:commandButton id="btnCancelar_" value="Cancelar" onclick="dlgEdit.hide();" style="font-size: 11px;height: 2em">
                            <rich:componentControl for="dlgEdit" attachTo="btnCancelar_" operation="hide" event="onclick"/>
                        </a4j:commandButton>
                    </h:panelGroup>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="confirmEditDlg" height="100" keepVisualState="true" resizeable="false">
                <f:facet name="header">
                    <h:panelGroup>
                        <h:outputText value="Confirmaci�n"></h:outputText>
                    </h:panelGroup>
                </f:facet>
                <h:form id="confirmEditDlgForm">
                    <h:outputText value="Se proceder� a editar el lugar." style="font-size: 11px;"/><br/>
                    <h:outputText value="Desea Continuar?" style="font-size: 11px;"/><br/>
                    <hr>
                    <a4j:commandButton id="btnSi_" value="SI" reRender="dlgEditMsgs, tbl" actionListener="#{lugarMB.editarLugar}" oncomplete="#{lugarMB.action}" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmEditDlg" attachTo="btnSi_" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="btnNo_" value="NO" type="button" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="confirmEditDlg" attachTo="btnNo_" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="cdlg" height="100" keepVisualState="true" resizeable="false">
                <f:facet name="header">
                    <h:outputText value="Desactivaci�n del Lugar"></h:outputText>
                </f:facet>
                <h:form id="mpdForm">
                    <h:outputText value="Se proceder� a desactivar el lugar."/><br/>
                    <h:outputText value="Desea Continuar?"/><br/>
                    <hr>
                    <a4j:commandButton id="dbtnSI" value="SI" reRender="tbl" actionListener="#{lugarMB.desactivar}" status="commonstatus" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="cdlg" attachTo="dbtnSI" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="dbtnNO" value="NO" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="cdlg" attachTo="dbtnNO" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            <rich:modalPanel id="cadlg" height="100" keepVisualState="true" resizeable="false">
                <f:facet name="header">
                    <h:outputText value="Activaci�n del Lugar"></h:outputText>
                </f:facet>
                <h:form id="mpaForm">
                    <h:outputText value="Se proceder� a activar el lugar."/><br/>
                    <h:outputText value="Desea Continuar?"/><br/>
                    <hr>
                    <a4j:commandButton id="dbtnSI" value="SI" reRender="tbl" actionListener="#{lugarMB.activar}" status="commonstatus" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="cadlg" attachTo="dbtnSI" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                    <a4j:commandButton id="dbtnNO" value="NO" style="font-size: 11px;height: 2em">
                        <rich:componentControl for="cadlg" attachTo="dbtnNO" operation="hide" event="onclick"/>
                    </a4j:commandButton>
                </h:form>
            </rich:modalPanel>
            <rich:spacer />
            <%@include file="../commons/footer.jsp" %>
        </f:view>
    </body>
</html>
