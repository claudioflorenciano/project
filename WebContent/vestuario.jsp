<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="br.com.gentleman.model.Produto" %>
<%@ page import="br.com.gentleman.model.Cliente" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
        <meta charset="utf-8" />
        <title>Vestuario - Be a Gentleman</title>
        <link rel="stylesheet" type="text/css" href="css/vestuario.css" />
        <link rel="icon" href="img/suit.png" type="image/x-icon" />
        <link rel="shortcut icon" href="img/suit.png" type="image/x-icon" />
        <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro" rel="stylesheet"/>
    </head>
    <body>
    	<%
    		List<Produto> produtos = null;
    		produtos  = (ArrayList<Produto>) session.getAttribute("produto");
    		Cliente cliente = null;
    		try{
    			cliente = (Cliente) session.getAttribute("cliente");
    		}catch(Exception er){

    		}
    	
    		session.removeAttribute("produto");
    	%>
        <!-- Manu para Usuário -->
        <div id="menuUsu">  
        </div>
        <nav id="menuCadastro">
            <ul>
            <%if(cliente == null){ %>
                <li><a class="loginLink" href="login.jsp"><b>Login</b></a></li>
            <%}else{ %>
            	<%= "<li><a class='loginLink' href='Cliente.do?acao=logout'><b> Sair de " + cliente.getNomeCliente() + "</b></a></li>" %>
            <%} %>
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
       <!-- <aside>
            <div class="vertical-menu">
                <a href="vestuario.html" class="active">Vestuário</a>
                <a href="blazer.html">Blazer</a>
                <a href="camisa.html">Camisa</a>
                <a href="calca.html">Calça</a>
                <a href="casaco.html">casaco</a>
                <a href="sapato.html">Sapato</a>
                <a href="outros.html">Outros</a>

                <a href="#">Bengala</a>
                <a href="#">Cartola</a>
                <a href="#">Charuto</a>
                <a href="#">Chapéu</a>
                <a href="#">Gravata</a>
                <a href="#">Abotoador</a>

            </div>
        </aside> -->
        <div id="areaProdutos">
        <%if(produtos != null){ %>
        <%if(produtos.isEmpty()){%>
        	<center>
    			<h3>Desculpe, nenhum produto encontrado!</h3>
    		</center>	
        <% }%>
        <%for(Produto p: produtos){ %>
            <div id="displayProd">
            	<div id="imgProd">
                	<img src="<%= p.getImagemProduto() %>">
                </div>
                <label><%= p.getNomeProduto() + " - " + p.getMarca().getNomeMarca() %> </label></br>
                <label class="preco"><b>R$ <%= p.getPrecoProduto() %></b></label><br>
                <form name="formCarrinho" method="post" action="Carrinho.do">
                    <input type="hidden" name="idProd" value="<%= p.getIdProduto() %>">
                	<input type="hidden" name="acao" value="adicionarCarrinho">
                	<input type="hidden" name="quant" value="1">
                	<input class="bCarrinho" type="submit" value="+ Carrinho"/>
                </form>
                <form name="formVisualisa" method="post" action="Carrinho.do">
                	<input type="hidden" name="idProdVisualiza" value="<%= p.getIdProduto() %>">
                	<input type="hidden" name="acao" value="pagProd">
                	<input class="bVisualizar" type="submit" value="Visualizar"/>
                </form>
            </div>
        <%} %>
        <%}else{ %>
        	<center>
        		<h3>Desculpe, nenhum produto encontrado!</h3>
        	</center>
        <%} %>
        </div>
    </body>
</html>