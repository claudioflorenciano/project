<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="br.com.gentleman.model.Produto" %>
<%@ page import="br.com.gentleman.model.Cliente" %>
<%@ page import="br.com.gentleman.model.Tamanho" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
        <meta charset="utf-8" />
        <title>Be a Gentleman</title>
        <link rel="stylesheet" type="text/css" href="css/produto.css" />
        <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
        <script type="text/javascript" src="js/jquery-3.2.1.js"></script>
        <script type="text/javascript" src="js/jquery.cycle.all.js"></script>
        <script type="text/javascript" src="js/jquery.elevateZoom-3.0.8.min.js"></script>
        <script type="text/javascript" src="js/jquery.elevatezoom.js"></script>
        <script type="text/javascript" src="js/script.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Anton" rel="stylesheet">
        <link rel="icon" href="img/suit.png" type="image/x-icon" />
        <link rel="shortcut icon" href="img/suit.png" type="image/x-icon" />
        <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro" rel="stylesheet"/>
    </head>
    <body>
    	<%
    		Produto produto = null;
    		List<Tamanho> tamanhos = null; 
    		produto = (Produto) session.getAttribute("produto");
    		tamanhos = (List<Tamanho>) session.getAttribute("tamanho");
    		
    		session.removeAttribute("produto");
    		session.removeAttribute("tamanho");
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
                    
<!--                        <div class="dropdownContent">
                            <a href="#">Blazer</a>
                            <a href="#">Camisa</a>
                            <a href="#">Calça</a>
                            <a href="#">Casaco</a>
                            <a href="#">Sapato</a>
                            <a href="#">Outros</a>
                            <a href="#">Bengala</a>
                            <a href="#">Cartola</a>
                            <a href="#">Charuto</a>
                            <a href="#">Chapéu</a>
                            <a href="#">Gravata</a>
                            <a href="#">Abotoador</a>

                        </div>
                    </li>
                    -->
                    <li class="liConteudo"><a class="gentleman" href="gentleman.jsp">Gentleman</a></li>
                </ul>
            </nav>
        </div>
        <%if(produto != null){ %>
        <div id="produto">
            <form name="formProdVisualiza" method="post" action="Carrinho.do">
                <label class="nome"><b><%= produto.getNomeProduto() %> - <%= produto.getMarca().getNomeMarca() %></b></label><br>
                <label class="preco">R$ <%= produto.getPrecoProduto() %></label><br>
                <select class="opcTamanho" name="tamanhoProd">
                <%for(Tamanho t: tamanhos){ %>
                    <option value="<%= t.getIdTamanho()%>"><%=t.getNumero() %></option>
                <%} %>
                </select>
                <label class="lQuant">Quantidade</label>
                <input type="number" class="quant" max="<%= produto.getQuantidadeEstoque() %>" min="1" value="0" name="quant">
                <p class="des"><b>Descrição</b></p>
                <p class="descricao"><%= produto.getDescricaoProduto() %></p>
                <input type="hidden" name="idProd" value="<%= produto.getIdProduto() %>">
                <input type="hidden" name="acao" value="adicionarCarrinho">
                <input class="bCarrinho" type="submit" value="+ Carrinho"/>
            </form>
            <div id="im">
                <img class="imgProd" id="zoom" src="<%= produto.getImagemProduto() %>" data-zoom-image="<%= produto.getImagemProduto() %>"/>
            </div>
            <script>
                $("#zoom").elevateZoom();
            </script>
            
        </div>
        <%}else{ %>
        <div id="produto">
        	<center>Desculpe, nenhum produto encontrado!</center>
        </div>
        <%} %>
    </body>
</html>