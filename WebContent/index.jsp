<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="br.com.gentleman.model.Cliente" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
        <meta charset="utf-8" />
        <title>Be a Gentleman</title>
        <link rel="stylesheet" type="text/css" href="css/index.css" />
        <link rel="icon" href="img/suit.png" type="image/x-icon" />
        <link rel="shortcut icon" href="img/suit.png" type="image/x-icon" />
        <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro" rel="stylesheet"/>
        <script type="text/javascript" src="js/script.js"></script>
    </head>
    <body onload="setInterval(moveSlide, 5000)">
        <!-- Manu para Usuário -->
        <div id="menuUsu">  
        </div>
        <nav id="menuCadastro">
            <ul>
            	<% 
            	Cliente cliente;
            	if(session.getAttribute("cliente") != null){
            		cliente = (Cliente) session.getAttribute("cliente"); 
            	%>
            		<%= "<li><a class='loginLink' href='Cliente.do?acao=logout'><b> Sair de " + cliente.getNomeCliente() + "</b></a></li>" %>
            	<%	
            	}else{
            	%>
            	<%= "<li><a class='loginLink' href='login.jsp'><b>Login</b></a></li>" %>
            	<%
            	}
            	%>
                <li><a class="carrinhoLink" href="carrinho.jsp"><img class="carrinho" src="img/cart.png"/></a></li>
            </ul>
        </nav>
        <div id="campoPesquisa">
        	<form name="formBusca" id="formBusca" method="post" action="Carrinho.do">
            	<input type="text" class="pesquisa" placeholder="Pesquise aqui..." name="nomeProdPesquisa"/>
            	<input type="hidden" name="acao" value="buscaProd">
            	<img class="lupa" src="img/search.png" onclick="document.getElementById('formBusca').submit();"/>
            </form>
        </div>
        <!-- Menu links site-->
        <div id="menuConteudo">
            <nav id="navConteudo">
                <ul id="ulPrincipalConteudo">
                    <li class="liConteudo"><a class="home" href="index.jsp">Home</a></li>
                    <form action="Carrinho.do" method="post" name="formVestuario" id="formVestuario">
                    	<li class="liConteudo"><a href="#" onClick="document.getElementById('formVestuario').submit();" class="vestuario">Produtos</a></li>
                    	<input type="hidden" name="acao" value="vest">
                    </form>
                    <li class="liConteudo"><a class="gentleman" href="gentleman.jsp">Gentleman</a></li>
                </ul>
            </nav>
        </div>
        <div id="slideShow">
            <div id="slide">
                <ul>
                    <li><img id="img1" src="img/1-slide.png"/></li>
                    <li><img id="img2" src="img/2-slide.png"/></li>
                    <li><img id="img3" src="img/3-slide.png"/></li>
                </ul>   
            </div>
        </div>
        <div id="resto">
        	#SomosTodosGentleman
        </div>
    </body>
</html>