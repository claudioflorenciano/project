<%@page import="java.util.ArrayList"%>
<%@page import="org.eclipse.jdt.internal.compiler.ast.ThisReference"%>
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
        <script type="text/javascript" src="js/script.js"></script>
    </head>
    <body>
        <!-- Manu para Usuário -->
        <div id="menuUsu">  
        </div>
        <nav id="menuCadastro">
            <ul>
                <% 
            	Cliente cliente = null;
                List<Carrinho> carrinhos = new ArrayList<Carrinho>();
                Float total = null;
            	if(session.getAttribute("cliente") != null){
            		cliente = (Cliente) session.getAttribute("cliente"); 
           
            		
            		carrinhos = (List<Carrinho>) session.getAttribute("carrinho");
            		total = (Float) session.getAttribute("total");
            		
            		
            		session.setAttribute("carrinho", carrinhos);
            		session.setAttribute("total", total);
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
        <%if(!carrinhos.isEmpty()){ %>
        <div id="carrinho">
            <div id="carro">
                <table>
                    <tr>
                        <th>Apaga Produto</th>
                        <th>Nome</th>
                        <th>Preço</th>
                        <th>Quantidade</th>
                        <th>Tamanho</th>
                        <th>Subtotal</th>
                    </tr>
                    <%for(Carrinho c: carrinhos){ %>
                    <tr>
                        <form action="Carrinho.do" method="post" id="formApagaCarrinho">
                            <td><button onclick="document.getElementById('formApagaCarrinho').submit();">Apagar</button></td>
                            <input type="hidden" name="acao" value="removeCarrinho">
                            <input type="hidden" name="indexProd" value="<%= carrinhos.indexOf(c) %>">
                        </form>
                        <td><%=c.getProduto().getNomeProduto() %></td>
                        <td>R$ <%= c.getProduto().getPrecoProduto() %></td>
                        <td><%= c.getQuantidade() %></td>
                        <td><%= c.getProduto().getTamanho().getNumero() %></td>
                        <td>R$ <%= (c.getProduto().getPrecoProduto() * c.getQuantidade()) %></td>
                    </tr>
                    <%} %>
                </table>
            </div>
        </div>
        <table id="ttFl">
                    <tr>
                        <th>Total Final</th>
                    </tr>
                    <tr>
                        <td>R$ <%= total %></td>
                    </tr>
            </table>
        <form id="finalizar" method="post" action="Carrinho.do">
            <fieldset>
                <label class="cep">CEP:</label>
                <input type="text" class="textoCep" name="cepVenda" maxlength="9" onkeypress="mascara_cep(this)" required>
                <label class="num">Número:</label>
                <input type="text" class="textoNum" name="numeroVenda" required>
                <label class="com">Complemento:</label>
                <input type="text" class="textoCom" name="complementoVenda" required>
                <input type="hidden" name="acao" value="finalizarCompra">
                <input type="hidden" name="total" value="<%= total %>">
            </fieldset>
            <input class="finaliza" type="submit" value="Finalizar Compra"/>
        </form>
       <%}else{ %>
       	<center><h3>Desculpe, nenhum produto no carrinho!</h3></center>
       <%} %>
    </body>
</html>