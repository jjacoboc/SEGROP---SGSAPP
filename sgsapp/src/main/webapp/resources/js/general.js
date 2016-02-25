/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
window.history.forward(1);

document.onkeydown = function(evt){
    evt = (evt) ? evt : event;
    ascci = (document.all) ? evt.keyCode : evt.which;
    if(evt && ascci == 116){
        ascci = 505;
    }
    if(evt && ascci == 505){
        return false;
    }
}
function click(evt){
    evt = (evt) ? evt : event;
    if(evt.button == 2 ){
        alert('Copyright 2014 LIMA - PERU. Todos los Derechos Reservados');
    }
}
document.onmousedown=click

function valida(xinput,tipval){
    var xkey=event.keyCode;
    if(tipval=="a-z")
        if ((xkey < 97) || (xkey > 122))
            event.returnValue = false;
}

function isNumber(inputVal) {
    var oneDecimal = false
    var digitCount = 0
    inputStr = inputVal.toString()
    for (var i = 0; i < inputStr.length; i++) {
        var oneChar = inputStr.charAt(i)
        if (i == 0 && oneChar == "-") {
            continue
        }
        if (oneChar == "." && !oneDecimal) {
            oneDecimal = true
            continue
        }
        if (oneChar < "0" || oneChar > "9") {
            return false
        } else {
            digitCount++
        }
    }
    return (digitCount > 0)
}
//********************************************************************************************//
function esTeclaCodigo(e){
    var valid = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_";
    var key = String.fromCharCode(event.keyCode);
    if (valid.indexOf("" + key) == "-1") return false;
}
//********************************************************************************************//
function esTeclaFormula(e){
    var valid = "0123456789.+-*/<>=() ";
    var key = String.fromCharCode(event.keyCode);
    if (valid.indexOf("" + key) == "-1") return false;
}
//********************************************************************************************//
function esTeclaTexto(e) {
    var valid = "abcdefghijklmñnopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZóáíúéÁÉÍÓÚ ";
    var key = String.fromCharCode(event.keyCode);
    if (valid.indexOf("" + key) == "-1") return false;
}
//********************************************************************************************//
function esTeclaAlfanumerico(e) {
    var valid = "abcdefghijklmñnopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZóáíúéÁÉÍÓÚ 0123456789_";
    var key = String.fromCharCode(event.keyCode);
    if (valid.indexOf("" + key) == "-1") return false;
}
//********************************************************************************************//
function esTeclaTodas(e) {
    var valid = "abcdefghijklmñnopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ :.+-*/=!?$%()#&0123456789_°ºóáíúéÁÉÍÓÚ@";
    var key = String.fromCharCode(event.keyCode);
    if (valid.indexOf("" + key) == "-1") return false;
}
//********************************************************************************************//
function esTeclaTodasComodin(e) {
    var valid = "abcdefghijklmñnopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ :;.,-/()#&0123456789_°ºóáíúéÁÉÍÓÚ";
    var key = String.fromCharCode(event.keyCode);
    if (valid.indexOf("" + key) == "-1") return false;
}
//********************************************************************************************//
function esTeclaNumero(e) {
    var valid = ",0123456789";
    var key = String.fromCharCode(event.keyCode);
    if (valid.indexOf("" + key) == "-1") return false;
}
//********************************************************************************************//
function esTeclaNumeroSinComa(e) {
    var valid = "0123456789";
    var key = String.fromCharCode(event.keyCode);
    if (valid.indexOf("" + key) == "-1") return false;
}
//********************************************************************************************//
function esTeclaFecha(e) {
    var valid = "/0123456789";
    var key = String.fromCharCode(event.keyCode);
    if (valid.indexOf("" + key) == "-1") return false;
}
//********************************************************************************************//
function esNada(e) {
    var valid = "";
    var key = String.fromCharCode(event.keyCode);
    if (valid.indexOf("" + key) == "-1") return false;
}
//********************************************************************************************//
function esTeclaDecimal(e) {
    var valid = "0123456789,.";
    var key = String.fromCharCode(event.keyCode);
    if (valid.indexOf("" + key) == "-1") return false;
}
//********************************************************************************************//
function esTeclaRangoNumeros(e) {
    var valid = "0123456789,-";
    var key = String.fromCharCode(event.keyCode);
    if (valid.indexOf("" + key) == "-1") return false;
}
//********************************************************************************************//
function validaEmail(value,objId){
    if(value!=''){
        if(!esEmail(value)){
            alert('El email ingresado es inválido');
            document.getElementById(objId).focus();
            return;
        }
    }
}
function esEmail(string) //string = cadena que representa al correo electronico
{//valida si la entrada es un correo electronico si es cierto devuelve true
    if (string.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1)
        return true;
    else
        return false;
}
//********************************************************************************************//
function validaFechaMayor(objId1,objId2){
    var value1 = document.getElementById(objId1).value;
    var value2 = document.getElementById(objId2).value;
    if(value1!='' && value2!=''){
        if(!compararFechaMayor(value1,value2)){
            alert('La Fecha de Fin debe ser posterior a la Fecha de Inicio');
            document.getElementById(objId2).focus();
            return;
        }
    }
}
function compararFechaMayor(strFecha,strFechaMayor){
    var fecha1 = strFecha.split("/");
    var fecha2 = strFechaMayor.split("/");

    var strFecha1 = parseFloat(fecha1[2] + fecha1[1] + fecha1[0]);
    var strFecha2 = parseFloat(fecha2[2] + fecha2[1] + fecha2[0]);
		 		 
    if (strFecha1 >= strFecha2) {
        return false;
    }
    else {
        return true;
    }
}
//********************************************************************************************//
function esFecha(day,month,year) {

    if ((day.length!=2) || (month.length!=2) || (year.length!=4)) {
        return false;
    }
    if (month>12) return false;
    if ((month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12) && day>=32){
        return false;
    }
    if ((month==4 || month==6 || month==9 || month==11) && day>=31){
        alert("El mes de "+ fcnConvertirMes(month) +" sólo tiene 30 dias!");
        return false;
    }
    if (month == 2){
        var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
        if (day>29 || (day==29 && !isleap)){
            alert("Febrero de " + year + " no tiene " + day + " dias!");
            return false;
        }
    }
    return true;
}
//********************************************************************************************//
function esVacio(cad) { //retorna true si la cadena está vacía 
    var es_vacio;
    var blanco = " \n\t" + String.fromCharCode(13); // blancos
    if ((cad == null) || (cad.length == 0)) {
        es_vacio=true
    }
    else
    {
        for(i = 0, es_vacio = true; (i < cad.length) && es_vacio; i++)
            es_vacio = blanco.indexOf(cad.charAt(i)) != - 1;
    }
    return es_vacio;
}
//********************************************************************************************//
function esNaN(cad) { //retorna true si la cadena no es un número
    var aux=cad.replace(/,/g,""); //Le quitamos todas las comas y comprobamos que sea un número
    if (isNaN(aux)) return true; //Si no es un numero sin las comas, devuelve error.
    else
    {   //Validamos el formato de las comas. Primero vemos si hay comas. Si no las hay , es número.
        if (cad.indexOf(',') == -1) return false;
        //Ahora si tiene punto decimal
        if (cad.indexOf('.') != -1)
        {
            var arregloSplit=cad.split(".");
            //Verifica que no hayan comas en la parte decimal
            if (! ( /^\d*$/.test(arregloSplit[1]) )) return true;
            aux=arregloSplit[0];
        }
        else aux=cad;
        //var regexp=/(^-?\d{1,3}$)|(^-?\d{1,3},\d{3}$)/;//|(^-?\d+,\d{3},\d{3}$)/;
        var regexp=/^-?\d{1,3}(,\d{3})*$/;
        return !regexp.test(aux);
		
    }
}
//********************************************************************************************//
function esNaI(cad)
{//Retorna true si la cadena no es un número entero.
    if ( cad.indexOf('.') != -1) return true;
    return esNaN(cad);
}
//********************************************************************************************//
function enRango(cad, nroMin, nroMax)
{  //cad puede tener coma separadora de miles, pero nroMin y nroMax no
    if (esNaN(cad) || isNaN(nroMin) || isNaN(nroMax)) return false;
    var aux=cad.replace(/,/g,""); //Le quitamos todas las comas
    return ( ( aux > nroMin ) && ( aux < nroMax ) );
}
//********************************************************************************************//
//El arreglo que necesita la función esVacio, necesita objetos creados con esta funcion
function entry(key,value){
    this.key=key;
    this.value=value;
}
//********************************************************************************************//
function validaVacio(ArrCad,textoError) { //retorna true si alguna cadena está vacía o tiene espacios en blanco
    var es_vacio;
    var error=false;
    var mensajeError="";
    if(!isNaN(ArrCad.length)){
        for (j=0;j<ArrCad.length;j++)
        {
            cad=ArrCad[j].value;
            es_vacio=esVacio(cad);
            if (es_vacio)
            {
                error=true;
                textoAux=textoError.replace(/\$0/,ArrCad[j].key);
                mensajeError+=textoAux+"\n";
            }
        }
        if(error)
            alert(mensajeError);
    }
    return(error);
}
//********************************************************************************************//
function validaNumero(ArrCad,textoError)
{ //retorna true si alguna de las cadenas no es numero.
    var no_es_numero=true;
    var error=false;
    var mensajeError="";
    if(!isNaN(ArrCad.length)){
        for (j=0;j<ArrCad.length;j++)
        {
            cad=ArrCad[j].value;
            no_es_numero=esNaN(cad);
		
            if (no_es_numero)
            {
                error=true;
                textoAux=textoError.replace(/\$0/,ArrCad[j].key);
                mensajeError+=textoAux+"\n";
            }
        }
        if(error)
            alert(mensajeError);
    }
    return(error);
}
//********************************************************************************************//
function validaNoNumero(ArrCad,textoError)
{ //retorna true si alguna de las cadenas es numero.
    var es_numero=true;
    var error=false;
    var mensajeError="";
    if(!isNaN(ArrCad.length)){
        for (j=0;j<ArrCad.length;j++)
        {
            cad=ArrCad[j].value;
            es_numero=!isNaN(cad);
		
            if (es_numero)
            {
                error=true;
                textoAux=textoError.replace(/\$0/,ArrCad[j].key);
                mensajeError+=textoAux+"\n";
            }
        }
        if(error)
            alert(mensajeError);
    }
    return(error);
}
//********************************************************************************************//
function validaEntero(ArrCad,textoError){
    var no_es_entero=true;
    var error=false;
    var mensajeError="";
    if(!isNaN(ArrCad.length)){
        for (j=0;j<ArrCad.length;j++)
        {
            cad=ArrCad[j].value;
            no_es_entero=esNaI(cad);
		
            if (no_es_entero)
            {
                error=true;
                textoAux=textoError.replace(/\$0/,ArrCad[j].key);
                mensajeError+=textoAux+"\n";
            }
        }
        if(error)
            alert(mensajeError);
    }
    return(error);
}
//********************************************************************************************//
function validaPorcentaje(ArrCad,textoError)
{ //retorna true si alguna de las cadenas no es número.
    var no_es_pctje=true;
    var error=false;
    var mensajeError="";
    if(!isNaN(ArrCad.length)){
        for (j=0;j<ArrCad.length;j++)
        {
            cad=ArrCad[j].value;
            no_es_pctje=!enRango(cad,0,100);
            if (no_es_pctje)
            {
                error=true;
                textoAux=textoError.replace(/\$0/,ArrCad[j].key);
                mensajeError+=textoAux+"\n";
            }
        }
        if(error)
            alert(mensajeError);
    }
    return(error);
}
//********************************************************************************************//
function Trim(dato) { 
    return String(dato).replace(/[\s]/g,"");
}
//********************************************************************************************//
// Recibe como parametro un obj de tipo checkbox (arreglo u objeto simple) 
// y devuelve true si alguno esta seleccionado
function estaSeleccionado(obj) { 
    check = false;
    if(isNaN(obj.length)) {
        check = obj.checked;
    }
    else {
        longitud = obj.length;
        for(i = 0; i < longitud; i++) {
            if(obj[i].checked == true) {
                check = true;
                break;
            }
        }
    }
    return check;
}
//********************************************************************************************//
function mensaje(){
    obj = document.getElementById("errores");
    if(obj.style.display=='none')
        obj.style.display='';
    else
        obj.style.display='none';
}
//********************************************************************************************//
function replace(linea, antiguo,nuevo){
    res = eval('linea.replace(/'+antiguo+'/g, \"'+nuevo+'\")');
    return res;
}
//********************************************************************************************//
function validaRangoFecha(fechaInicio,fechaFin){
    diaInicio=fechaInicio.substr(0, 2);
    mesInicio=fechaInicio.substr(3, 2);
    annoInicio=fechaInicio.substr(6, 4);

    diaFin=fechaFin.substr(0, 2);
    mesFin=fechaFin.substr(3, 2);
    annoFin=fechaFin.substr(6, 4);

    if(annoFin < annoInicio){
        return false;
    }
    if(annoFin == annoInicio){                    
        if(mesFin < mesInicio){                                                                                 
            return false;
        }
    }                            
    if(annoFin == annoInicio){                    
        if(mesFin == mesInicio){                                                                 
            if(diaFin < diaInicio){                                                                                 
                return false;    
            }
        }
    }
    return true;
}
//********************************************************************************************//
function calendario(nom_campo, formato) {
    var matParam = new Array(3);
    matParam[0] = window;
    matParam[1] = nom_campo;
    matParam[2] = formato;
    window.showModalDialog('/planillas/ventanaCalendario.htm',matParam,'dialogHeight:210px; dialogWidth:280px;center:yes; help:no; resizable:no; status:no');
}
//********************************************************************************************//
function contarCaracteres(objText,total){
    if (objText.value.length>total){
        objText.value = objText.value.substring(0,total-1);
    }
}
//********************************************************************************************//
function validaFecha(value,objId){
    if(value!=''){
        if(!esFecha(value)){
            alert('Formato de fecha incorrecto. (dd/mm/yyyy)');
            document.getElementById(objId).focus();
            return;
        }
    }
}

function validaHora(value,objId){
    if(value!=''){
        if(!esHora(value)){
            alert('Formato de hora incorrecto. (24hh:mm)');
            document.getElementById(objId).focus();
            return;
        }
    }
}

function esFecha(fecha){
    //Se verifica que la fecha sólo tenga caracteres numéricos y el caracter "/"
    for (var i = 0; i < fecha.length; i++) { 
        //var carac = fecha.substring(i,i+1);
        var carac = fecha.charAt(i);
        if ((carac < "0" || carac > "9") && carac != "/") {
            return false;
        }
    }

    //Obtenemos el dia de la fecha
    var pos1 = fecha.indexOf("/");
    aux = fecha.substring(0,pos1);
    if (aux.length != 2) return false;  //verificamos que la parte del dia tenga dos caracteres
    if(aux.charAt(0) == "0") {
        aux = aux.substr(1,1);
    }
    var dia = parseInt(aux);

    //Obtenemos el mes de la fecha
    var pos2 = fecha.indexOf("/",pos1+1);
    var aux = fecha.substring(pos1+1,pos2);
    if (aux.length != 2) return false;  //verificamos que la parte del mes tenga dos caracteres
    if(aux.charAt(0) == "0") {
        aux = aux.substr(1,1);
    }
    var mes = parseInt(aux);

    //Obtenemos el año de la fecha
    aux = fecha.substring(pos2+1,fecha.length);
    if (aux.length != 4) return false;  //verificamos que la parte del año tenga cuatro caracteres
    var anno = parseInt(aux);

    if (mes < 1 || mes > 12) return false;  //el mes debe estar entre 1 y 12
    if (dia < 1 || dia > 31) return false;  //el día debe estar entre 1 y 31
    if (anno < 1754 || anno > 9999) return false;  //el año debe estar entre 1754 y 9999

    if (mes==2 && dia>29) return false;  //valida Febrero: el día debe estar entre 1 y 29

    if ((anno%4)!=0 && dia==29 && (mes==2)) return false; //Año Bisiesto: se verifica que febrero tenga 29 días

    if ((mes==4||mes==6||mes==9||mes==11)& dia>30) return false;  //Meses de 30 dias.

    return true;
}

function esHora(hora){
    //Se verifica que la fecha sólo tenga caracteres numéricos y el caracter ":"
    for (var i = 0; i < hora.length; i++) {
        //var carac = fecha.substring(i,i+1);
        var carac = hora.charAt(i);
        if ((carac < "0" || carac > "9") && carac != ":") {
            return false;
        }
    }

    //Obtenemos las horas
    var pos1 = hora.indexOf(":");
    aux = hora.substring(0,pos1);
    if (aux.length != 2) return false;  //verificamos que la parte de la hora tenga dos caracteres
    if(aux.charAt(0) == "0") {
        aux = aux.substr(1,1);
    }
    var hor = parseInt(aux);
    
    //Obtenemos los minutos
    var aux = hora.substring(pos1+1,hora.length);
    if (aux.length != 2) return false;  //verificamos que la parte de los minutos tenga dos caracteres
    if(aux.charAt(0) == "0") {
        aux = aux.substr(1,1);
    }
    var min = parseInt(aux);
    
    if(hor<0 || hor>23) return false;   // las horas deben estar entre las 00 y 23 horas.

    if(min<0 || min>59) return false;   // los minutos deben estar entre los 00 y 59 minutos.

    return true;
}
//********************************************************************************************//
/*
function esHora(hora){
    //Se verifica que la hora sólo tenga caracteres numéricos y el caracter ":"
    var regexp=/^(2[0-3]|[0-1]\d):[0-5]\d:[0-5]\d$/;
    if(!regexp.test(hora)) return false;
    else return true;
    return true
}
*/
//********************************************************************************************//
function isValidDate(dateStr) { 
    if (dateStr.value==""){
        return true; 
    }
    var datePat = /^(\d{1,2})(\/|-)(\d{1,2})\2(\d{4})$/; // requires 4 digit year 
    var matchArray = dateStr.value.match(datePat); // is the format ok? 
    if (matchArray == null) { 
        alert(dateStr.value + " No es una fecha valida. Intentelo nuevamente.") 
        return false; 
    } 
    a = dateStr.value.split("/") 
    var day = a[0]; 
    var month = a[1]; 
    var year = a[2]; 

    if (month < 1 || month > 12) { // check month range 
        alert("El mes debe ser entre 1 y 12."); 
        return false; 
    } 
    if (day < 1 || day > 31) { 
        alert("Los días deben ser entre 1 y 31"); 
        return false; 
    } 
    if ((month==4 || month==6 || month==9 || month==11) && day==31) { 
        alert(""+month+" no tiene 31 días") 
        return false; 
    } 
    if (month == 2) { // check for february 29th 
        var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)); 
        if (day>29 || (day==29 && !isleap)) { 
            alert("Febrero " + year + " no tiene " + day + " días"); 
            return false; 
        } 
    } 
    return true; 
} 
//********************************************************************************************//
/*
  Autor		: Claudio Zarate 07/03/2001
  Descripcion	: provee una mascara en linea a los textos tipo Fecha
*/
function keyFormatoFecha(ob){
    var x=window.event.keyCode;
    keyValidaFecha();  //-- valida que solo ingresen numeros o '/'
    if(x==13){    //-- si es enter no hace nada
        window.event.keyCode = 0;
        window.event.returnValue=false;
        return;
    }
    var fecha, tecla ;
    if(x != 8 && x != 47){
        if(ob.value.length==0){			
            tecla=String.fromCharCode(window.event.keyCode);
            if(tecla <= "3")
                ob.value= tecla ;
            window.event.returnValue=0;
            return ;
        }
        if(ob.value.length==1){
            fecha=ob.value;
            tecla=String.fromCharCode(window.event.keyCode);
            if(eval(fecha) < 3)
                ob.value=fecha + tecla + "/" ;
            if(eval(fecha) == 3){
                if(tecla <= "1")
                    ob.value=fecha + tecla + "/" ;
            }
            window.event.returnValue=0;
            return ;
        }
        if(ob.value.length==3){
            fecha=ob.value;
            tecla=String.fromCharCode(window.event.keyCode);
            if(tecla <= "1")
                ob.value=fecha + tecla ;
            window.event.returnValue=0;
            return ;
        }
        if(ob.value.length==4){
            fecha=ob.value.substr(3,4);            
            tecla=String.fromCharCode(window.event.keyCode);
            if(eval(fecha) == 0)
                ob.value=ob.value + tecla + "/" ;
            if(eval(fecha) == 1){
                if(tecla <= "2")
                    ob.value=ob.value + tecla + "/" ;
            }
            window.event.returnValue=0;
            return ;
        }
        if(ob.value.length == 6){
            tecla=String.fromCharCode(window.event.keyCode);         
            if(tecla == "1" || tecla == "2")
                ob.value=ob.value + tecla ;
            window.event.returnValue=0;
            return ;
        }
    }
    if(x==47){
        if(ob.value.length<2){
            window.event.keyCode = 0;
            window.event.returnValue=false;
            return ;
        }
        if(ob.value.length>2 && ob.value.length<5){
            window.event.returnValue=0;
            return ;
        }
        if(ob.value.length>5){
            window.event.returnValue=0;
            return ;
        }
    }
}
//********************************************************************************************//
/*
  Descripcion	:	Valida en linea el ingreso de caracteres en campos tipo fecha.
*/
function keyValidaFecha(){
    if(window.event.keyCode!=13){
        var Tecla;
        Tecla = String.fromCharCode(window.event.keyCode);
        if(!((Tecla>="0"&&Tecla<="9")||(Tecla=="/"))){
            window.event.keyCode = 0;
        }
    }	
}

//Agregado por Antonio Vega:21
function obtenerM(date1, date2){
    timeInMillis = date1.getTime() - date2.getTime();
    number = 1000 * 60 * 60 * 24.0015 * 30.43675;
    meses = timeInMillis / number;
    return parseInt(meses);
}

function obtenerF(varFecha){
    var varFechaArray = varFecha.split("/");
    date = new Date();
    date.setMonth(varFechaArray[1]-1,varFechaArray[0]);
    date.setYear(varFechaArray[2]);
    return date;
}

function redondeo2decimales(numero)
{
    var original=parseFloat(numero);
    var result=Math.round(original*1)/1 ;
    return result;
}

function mostrarConstancia(){
    document.getElementById('light').style.display='block';
    document.getElementById('fade').style.display='block';
    document.getElementById('iframeConstancia').src='jsp/constancia.jsp';
}

function imprimirIFrame(){
    var browserName=navigator.appName;
    if ( browserName.toLowerCase().indexOf("netscape")>=0 ){
        var id = "iframeConstancia";
        var iframe = document.frames ? document.frames[id] : document.getElementById(id);
        var ifWin = iframe.contentWindow || iframe;
        ifWin.printPage();
    }else{
        window.parent.iframeConstancia.focus();
        window.print();
    }
}

var chartStyle = {
    width: 650,
    legend: {display: "bottom"}
};