<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
        <link rel="stylesheet" type="text/css" href="css/erro.css"/>
        <script type="text/javascript" src="js/script.js"></script> 
    </head>
    <body>
    	<%
    		String[] erros = (String[]) session.getAttribute("erros");
    		
    		if(erros == null){
    			response.sendRedirect("inicio.jsp");
    		}
    		
    		session.removeAttribute("erros");
    	%>
    	<div id="aviso">
    		<p class="haverErro"><b>Desculpe, ocorreu um erro!</b></p>
    	</div>
        <div id="imgErro">
            <img src="img/IMG-20170707-WA0010.jpg" width="500px">
        </div>
        <nav>
            <ul>
                <li class="voltar"><a onclick="history.back();">Voltar para pagina anterior</a></li>
                <li class="verErro"><a href="javascript:acendeErros()">Ver erros</a></li>
            </ul>
        </nav>
        <div id="erros">
           <%for(String e: erros){ %>
           		<p class="erro"><%= e %>!</p>
           <%} %>
        </div>
    </body>
</html>