<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="br.com.gentleman.model.Carrinho" %>
<%@ page import="br.com.gentleman.model.Venda" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
        <meta charset="utf-8"/>
        <link rel="stylesheet" href="css/cupom.css"/>
    </head>
    <body>
    	<% 
    		List<Carrinho> carrinhos = null;
    		Venda venda = null;
    		
    		if(session.getAttribute("venda") == null || session.getAttribute("carrinho") == null){
    			response.sendRedirect("login.jsp");
    		}
    		
    		carrinhos = (List<Carrinho>) session.getAttribute("carrinho");
    		venda = (Venda) session.getAttribute("venda");
    		
    		session.invalidate();
    	%>
        <div id="voltar">
            <img class="voltar" src="img/back.png"/><a class="linkVoltar" href="index.jsp">Voltar para página inicial</a>
        </div>
        <div id="cupom">
            <p class="tGentle">Gentleman</p>
            <hr class="divisao1"/>
            <p class="data"><%= venda.getDataVenda() %></p>
            <p class="cupName">CUPOM FISCAL</p>
            <p class="qtd">QTD.</p>
            <p class="prod">Produto</p>
            <hr class="divisao2">
            <%for(Carrinho c: carrinhos){ %>
            	<p class="quant"><%= c.getQuantidade() %></p>
            	<p class="nomeProd"><%= c.getProduto().getNomeProduto() + " - " + c.getProduto().getMarca().getNomeMarca() %></p>
            <%} %>
            <hr class="divisao3"/>
            <p class="nomeCliente"><%= venda.getCliente().getNomeCliente() + " " + venda.getCliente().getSobrenomeCliente() + "." %></p>
            <p class="tt">Total R$ <%= venda.getValorTotal() %></p>
        </div>
    </body>>
</html>