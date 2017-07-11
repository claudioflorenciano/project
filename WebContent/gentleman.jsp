<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="br.com.gentleman.model.Cliente" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
        <meta charset="utf-8" />
        <title>Be a Gentleman</title>
        <link rel="stylesheet" type="text/css" href="css/gentleman.css" />
        <link rel="icon" href="img/suit.png" type="image/x-icon" />
        <link rel="shortcut icon" href="img/suit.png" type="image/x-icon" />
        <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro" rel="stylesheet"/>
    </head>
    <body>
        <!-- Manu para UsuÃ¡rio -->
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
        <div id="logo">
            <div id="pos">
                <img src="img/logo%20letra%20vermelha-01.png" width="300px">
            </div>
        </div>
        <div id="divisao">
        </div>
        <div id="conteudo"></div>
        <div id="divisao">
        </div>
        <footer>
            <div id="qSomos">
                <div id="claudio">
                    <fieldset>
                        <img src="img/claudio.jpg" width="200px" height="200px"/>
                        <p class="nome">Claudio Florenciano</p>
                        <p class="funcao">Função: Programador Back-end</p>
                        <p class="contato">(21) 99398-9116</p>
                    </fieldset>
                </div>
                <div id="gustavo">
                    <fieldset>
                        <img src="img/gustavo.jpg" width="200px" height="200px"/>
                        <p class="nome">Gustavo dos Santos</p>
                        <p class="funcao">Função: Programador Front-end</p>
                        <p class="contato">(21) 99152-9190</p>
                    </fieldset>
                </div>
                <div id="danilo">
                    <fieldset>
                        <img src="img/danilo.jpg" width="200px" height="200px"/>
                        <p class="nome">Danilo Gioseffi</p>
                        <p class="funcao">Função: Gerador de Conteúdo</p>
                        <p class="contato">(21) 96755-9784</p>
                    </fieldset>
                </div>
                <div id="felipe">
                    <fieldset>
                        <img src="img/felipe.jpg" width="200px" height="200px"/>
                        <p class="nome">Felipe Veillard</p>
                        <p class="funcao">Função:  Gerador de Conteúdo</p>
                        <p class="contato">(21) 99585-3053</p>
                    </fieldset>
                </div>
            </div>
        </footer>
        <div id="resto">
        	
        	
        	
        	
        	
        	
        	
        </div>
    </body>
</html>