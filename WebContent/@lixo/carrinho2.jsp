<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="br.com.gentleman.model.Cliente" %>
<%@ page import="br.com.gentleman.model.Carrinho" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
        <meta charset="utf-8" />
        <title>Be a Gentleman</title>
        <link rel="stylesheet" type="text/css" href="css/carrinho.css" />
        <link rel="icon" href="img/suit.png" type="image/x-icon" />
        <link rel="shortcut icon" href="img/suit.png" type="image/x-icon" />
        <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro" rel="stylesheet"/>
    </head>
    <body>
    	<%
    		List<Carrinho> carrinhos;
    		Float total = null;
    		
    		carrinhos = (List<Carrinho>) session.getAttribute("carrinho");
    		total = (Float) session.getAttribute("total");
    		
    		session.setAttribute("carrinho", carrinhos);
    		session.setAttribute("total", total);
    	%>
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
            		response.sendRedirect("login.jsp");
            	%>
            	<%= "<li><a class='loginLink' href='login.jsp'><b>Login</b></a></li>" %>
            	<%
            	}
            	%>
                <li><a class="carrinhoLink"><img class="carrinho" src="img/cart.png"/></a></li>
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
                    
                </ul>
            </nav>
        </div>
        <div id="carrinho">
            <form action"#" method="post" name="formCarrinho">
                <table>
                    <tr>
                        <th>Nome</th>
                        <th>Preço</th>
                        <th>Quantidade</th>
                        <th>Tamanho</th>
                        <th>Subtotal</th>
                    </tr>
                    <%if(carrinhos != null){ %>
                    <%for(Carrinho c: carrinhos){ %>
                    <tr>
                        <td><%= c.getProduto().getNomeProduto() %></td>
                        <td>R$ <%= c.getProduto().getPrecoProduto() %></td>
                        <td><%= c.getQuantidade() %></td>
                        <td><%= c.getProduto().getTamanho().getNumero() %></td>
                        <td>R$ <%= (c.getProduto().getPrecoProduto() * c.getQuantidade()) %></td>
                    </tr>

			                <input type="hidden" name="acao" value="finalizaCarrinho">
                    <%} %>
               </table>
           	</form>
              <table>
			  	<tr>
			  		<th>Total Final</th>
			  	</tr>
			  	<tr>
			  		<td>R$ <%= total %></td>
			  	</tr>
			  </table>
			  <input class="finaliza" type="button" value="Finalizar Compra" onclick="document.getElementById('formCarrinho').submit();"/>
                    <%}else{ %>
                    	<center><h3>Desculpe, nenhum produto encontrado no carrinho!</h3></center>
                    <%} %>
               
            
        </div>
    </body>
</html>