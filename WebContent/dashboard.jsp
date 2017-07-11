<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="br.com.gentleman.model.Produto" %>
<%@ page import="br.com.gentleman.model.Tamanho" %>
<%@ page import="br.com.gentleman.model.Departamento" %>
<%@ page import="br.com.gentleman.model.Marca" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
        <meta charset="utf-8" />
        <title>Be a Gentleman</title>
        <link rel="stylesheet" type="text/css" href="css/dashboard.css" />
        <link rel="icon" href="img/suit.png" type="image/x-icon" />
        <link rel="shortcut icon" href="img/suit.png" type="image/x-icon" />
        <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro" rel="stylesheet"/>
        <script type="text/javascript" src="js/script.js"></script>
    </head>
    <body>
    	<%
    		List<Produto> produtos  = (ArrayList<Produto>) session.getAttribute("produto");
    		List<Marca> marcas = (ArrayList<Marca>) session.getAttribute("marca");
    		List<Departamento> departamentos = (ArrayList<Departamento>) session.getAttribute("departamento");
    		List<Tamanho> tamanhos = (ArrayList<Tamanho>) session.getAttribute("tamanho");
    		
    		
    		session.removeAttribute("produto");
    		session.removeAttribute("tamanho");
    		session.removeAttribute("departamento");
    		session.removeAttribute("marca");
    	%>
        <div class="icon-bar">
            <a class="active" href="index.jsp"><img src="img/home.png"></a>
            <a onclick="acende(1)"><img src="img/altera.png"></a> 
            <a onclick="acende(2)"><img src="img/listar.png"></a> 
            <a onclick="acende(3)"><img src="img/adicionar.png"></a>
            <a onclick="acende(4)"><img src="img/apagar.png"></a> 
        </div>
        <div id="AlteraProduto">
            <table>
                <tr>
                    <th>Nome</th>
                    <th>Preço</th>
                    <th>Descrição</th>
                    <th>Tamanho</th>
                    <th>Área alteração</th>
                </tr>
                <% for(Produto p: produtos){ %>
                <tr>
                    <td><%= p.getNomeProduto() %></td>
                    <td>R$ <%= p.getPrecoProduto() %></td>
                    <td><%= p.getDescricaoProduto() %></td>
                    <td><%= p.getTamanho().getNumero() %></td>
                    <td><button onclick="exibir('areAltera', <%= p.getIdProduto() %>)">Alterar</button></td>
                </tr>
                <%} %>
            </table>
            <div id="areAltera">
                <div id="fechar" onclick="fechar()">
                    <img src="img/x.png">
                </div>
                <div id="camposAltera">
                    <form id="formAltera" action="Funcionario.do" method="post">
                        <label>Nome</label><br/><br/>
                        <input class="texto" type="text" name="nomeProdAlt" required/><br/><br/>
                        <label>Preço</label><br/><br/>
                        <input class="texto" type="number" min="0" value="0.00" step="0.01" name="precoProdAlt" required/><br/><br/>
                        <label>Descrição</label><br/><br/>
                        <textarea name="descriProdAlt" required></textarea><br/><br/>
                        <input type="hidden" name="idProdAlt" id="idProdAlt" value="">
                        <input type="hidden" name="acao" value="altProd">
                        <input class="salvar" type="submit" value="Salvar">
                    </form>
                </div>
            </div>
        </div>
        <div id="ListaProduto">
            <table>
                <tr>
                    <th>Nome</th>
                    <th>Preço</th>
                    <th>Quantidade disponível</th>
                    <th>Tamanho</th>
                </tr>
                	<% for(Produto p : produtos){ %>
                <tr>
                    <td><%= p.getNomeProduto() %></td>
                    <td>R$ <%= p.getPrecoProduto() %></td>
                    <td><%= p.getQuantidadeEstoque() %> <button onclick="exibirAdd('areAdiciona',<%= p.getIdProduto() %>)">Adicionar</button></td>
                    <td><%= p.getTamanho().getNumero() %></td>
                </tr>
                	<%} %>
                
            </table>
            <div id="areAdiciona">
                <div id="fecharAdd" onclick="fecharAdd()">
                    <img src="img/x.png">
                </div>
                <div id="camposAdiciona">
                    <form id="formAdiciona" action="Funcionario.do" method="post">
                        <label>Quantidade</label><br/><br/>
                        <input class="texto" type="number" value="0" min="1" name="quantProdAdd"/><br/><br/>
                        <input type="hidden" name="idProdAdd" id="idProdAdd" value="">
                        <input type="hidden" name="acao" value="addQuant">
                        <input class="adicionar" type="submit" value="Adicionar">
                    </form>
                </div>
            </div>
        </div>
        <div id="AdicionarProduto">
            <form id="formAdicionar" name="formAdd" action="Funcionario.do" method="POST" enctype="multipart/form-data">
                <input type="hidden" name="acao" value="addProd">
                <label>Nome</label><br/><br/>
                <input class="texto" type="text" name="prodNomeAdd" required/><br/><br/>
                <label>Descrição</label><br/><br/>
                <textarea name="prodDescriAdd" required></textarea><br/><br/>
                <label>Caminho Imagem</label><br/><br/>
                <input class="texto" type="file" name="prodImagAdd" required><br/><br/>
                <label>Quantidade</label><br/><br/>
                <input class="texto" type="number" name="prodQuantAdd" value="0" min="0" required/><br/><br/>
                <label>Departamento</label><br/><br/>
                <select name="prodDepartamentoAdd">
                <% for(Departamento d: departamentos){ %>
                    <option value="<%= d.getIdDeparamento() %>"><%= d.getNomeDepartamento() %></option>
                <% } %>
                </select><br/><br/>
                <label>Tamanho</label><br/><br/>
                <select name="prodTamanhoAdd">
                <% for(Tamanho t: tamanhos){ %>
                    <option value="<%= t.getIdTamanho() %>"><%= t.getNumero() %></option>
                <% } %>
                </select><br/><br/>
                <label class="marca">Marca</label><br/><br/>
                <select class="textoMarca" name="prodMarcaAdd">
                <%for(Marca m: marcas){ %>
                    <option value="<%= m.getIdMarca() %>"><%= m.getNomeMarca() %></option>
                <%} %>
                </select><br/><br/>
                <label>Preço</label><br/><br/>
                <input class="texto" type="number" min="0" step="0.01" value="0.00" name="prodPrecoAdd" required/><br/><br/>
                <input class="cadastrar" type="submit" value="Cadastrar"/>
            </form>
            <form id="formAddMarca" action="Funcionario.do" method="post" name="formAddMarca">
                <label>Marca</label><br/><br/>
                <input type="text" class="texto" name="nomeMarca" required/><br/><br/>
                <input type="hidden" name="acao" value="addMarca">
                <input class="cadastrar" type="submit" value="Cadastrar"/>
            </form>
        </div>
        <div id="ApagaProduto">
            <table>
                <tr>
                    <th>Nome</th>
                    <th>Preço</th>
                    <th>Quantidade disponível</th>
                    <th>Tamanho</th>
                    <th>Área Apagar</th>
                </tr>
                <% for(Produto p: produtos){ %>
                <tr>
                    <td><%= p.getNomeProduto() %></td>
                    <td>R$ <%= p.getPrecoProduto() %></td>
                    <td><%= p.getQuantidadeEstoque() %></td>
                    <td><%= p.getTamanho().getNumero() %></td>
                    <form action="Funcionario.do" method="post" name="apagaProd">
                    	<input type="hidden" name="idProdDel" value="<%= p.getIdProduto() %>">
                    	<input type="hidden" name="acao" value="delProd">
                    	<td><button onclick="this.submit();">Apagar</button></td>
                    </form>
                </tr>
                <%} %>
            </table>
        </div>
    </body>
</html>