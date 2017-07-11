package br.com.gentleman.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.gentleman.controller.CtrlCarrinho;
import br.com.gentleman.controller.CtrlCliente;
import br.com.gentleman.controller.CtrlProduto;
import br.com.gentleman.model.Carrinho;
import br.com.gentleman.model.Cliente;
import br.com.gentleman.model.Produto;
import br.com.gentleman.model.Tamanho;
import br.com.gentleman.model.Venda;

/**
 * Servlet implementation class ServletCarrinho
 */
@WebServlet("/Carrinho.do")
public class ServletCarrinho extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;
	CtrlProduto ctrlProduto;
	CtrlCliente ctrlCliente;
	CtrlCarrinho ctrlCarrinho;
	List<Carrinho> carrinhos;
     
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		session = request.getSession();
		String acao = request.getParameter("acao");
		
		if(acao.equals("adicionarCarrinho")){
			Long idProd = Long.parseLong(request.getParameter("idProd"));
			int quant = Integer.parseInt(request.getParameter("quant"));
			//String tamanho = request.getParameter("tamanhoProd");
			Cliente cliente = (Cliente) session.getAttribute("cliente");
			
			if(cliente != null){
				
				ctrlProduto = new CtrlProduto();
				ctrlCliente = new CtrlCliente();
				
	
				Produto produto = ctrlProduto.buscaProduto(idProd);
				Venda venda = new Venda(ctrlCliente.logar(cliente.getEmailCliente(), cliente.getClientPass()));
				
				if(quant <= produto.getQuantidadeEstoque()){
					
					if(session.getAttribute("carrinho") != null){
						 carrinhos = (ArrayList<Carrinho>) session.getAttribute("carrinho");
					}
					
					
					
					
					Carrinho carrinho = new Carrinho(venda, produto);
					carrinho.setQuantidade(quant);
					
					
					float total = 0;
					boolean checaProd = false;
					
					for(Carrinho ca: carrinhos){
						if(ca.getProduto().getIdProduto() == carrinho.getProduto().getIdProduto()){
							if(ca.getProduto().getQuantidadeEstoque() >= (carrinho.getQuantidade() + ca.getQuantidade())){
								ca.setQuantidade(ca.getQuantidade() + carrinho.getQuantidade());
							}
							checaProd = true;
						}
					}
					
					if(checaProd != true){
						carrinhos.add(carrinho);
					}
					
					for(Carrinho c : carrinhos){
						total = total + (c.getProduto().getPrecoProduto() * c.getQuantidade());
					}
					
					session.setAttribute("total", total);
					session.setAttribute("carrinho", carrinhos);
				}else{
					//Produto não tem essa quantidade
					String erros = "Produto fora de estoque!";
					
					String[] err = erros.split("!");
					
					session.setAttribute("erros", err);
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
					dispatcher.forward(request, response);
				}
				response.sendRedirect("carrinho.jsp");
			}else{
				session.invalidate();
				response.sendRedirect("login.jsp");
				//é necessario estar logado
			}
		}
		
		if(acao.equals("pagProd")){
			String idProd = request.getParameter("idProdVisualiza");
			ctrlProduto = new CtrlProduto();
			
			try{
				Produto produto = ctrlProduto.buscaProduto(Long.parseLong(idProd));
				List<Tamanho> tamanhos = ctrlProduto.buscaTamanhos(produto.getTamanho().getDepartamento().getIdDeparamento());
				Cliente cliente = (Cliente) session.getAttribute("cliente");
				
				
				session.setAttribute("produto", produto);
				session.setAttribute("tamanho", tamanhos);
				session.setAttribute("cliente", cliente);
				
			}catch(Exception er){
				System.err.println("Erro ao buscar produto: " + er.toString());
				
				String erros = "Erro ao buscar produto!";
				
				String[] err = erros.split("!");
				
				session.setAttribute("erros", err);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
				dispatcher.forward(request, response);
			}
			response.sendRedirect("produto.jsp");
		}
		
		if(acao.equals("vest")){
			//direcionar o usuario para o vestuario.jsp com os produtos armazenados no response
			CtrlProduto ctrlProduto = new CtrlProduto();
			try{
				Cliente cliente = (Cliente) session.getAttribute("cliente");
				session.setAttribute("cliente", cliente);
				
				List<Produto> produtos = ctrlProduto.buscaVestuarioProdutos();
				
				for(Produto p: produtos){
					if(p.getQuantidadeEstoque() < 1){
						produtos.remove(p);
					}
				}
				
				session.setAttribute("produto",produtos);
				
			}catch(Exception er){
				System.err.println("Erro ao redirecionar: " + er.toString());
				
				String erros = "Erro ao buscar produtos!";
				
				String[] err = erros.split("!");
				
				session.setAttribute("erros", err);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
				dispatcher.forward(request, response);
			}
			response.sendRedirect("vestuario.jsp");
		}
		
		if(acao.equals("buscaProd")){
			CtrlProduto ctrlProduto = new CtrlProduto();
			String nome = request.getParameter("nomeProdPesquisa");
			try{
				Cliente cliente = (Cliente) session.getAttribute("cliente");
				session.setAttribute("cliente", cliente);
				
				
				
				List<Produto> produtos = ctrlProduto.buscaVestuarioProdutos(nome);
				
				for(Produto p: produtos){
					if(p.getQuantidadeEstoque() < 1){
						produtos.remove(p);
					}
				}
				
				session.setAttribute("produto",produtos);

			}catch(Exception er){
				System.err.println("Erro ao redirecionar: " + er.toString());
				
				String erros = "Erro ao buscar produto!";
				
				String[] err = erros.split("!");
				
				session.setAttribute("erros", err);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
				dispatcher.forward(request, response);
			}
			response.sendRedirect("vestuario.jsp");
		}
		
		if(acao.equals("removeCarrinho")){
			String index = request.getParameter("indexProd");
			try{
				carrinhos = (List<Carrinho>) session.getAttribute("carrinho");
				carrinhos.remove(Integer.parseInt(index));
				
				float total = 0;
				
				for(Carrinho c : carrinhos){
					total = total + (c.getProduto().getPrecoProduto() * c.getQuantidade());
				}
				
				session.setAttribute("total", total);
				session.setAttribute("carrinho", carrinhos);
			}catch(Exception er){
				System.err.println("Erro ao remover carrinho: " + er.toString());
				
				String erros = "Erro ao remover carrinho!";
				
				String[] err = erros.split("!");
				
				session.setAttribute("erros", err);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
				dispatcher.forward(request, response);
			}
			response.sendRedirect("carrinho.jsp");
			
		}
		
		
		if(acao.equals("finalizarCompra")){
			if(session.getAttribute("carrinho") != null){
				//realizar o cadastro da compra no banco
				ctrlCarrinho = new CtrlCarrinho();
				ctrlProduto = new CtrlProduto();
				carrinhos = (List<Carrinho>) session.getAttribute("carrinho");
				String cep = request.getParameter("cepVenda");
				String numero = request.getParameter("numeroVenda");
				String complemento = request.getParameter("complementoVenda");
				String valorTotal = request.getParameter("total");
				
				
				String erros = "";
				
				erros = ctrlCarrinho.validaCarrinho(cep, numero, complemento);
				
				
				if(erros.equals("")){
				
					for(Carrinho c: carrinhos){
						c.getVenda().setCep(cep);
						c.getVenda().setComplemento(complemento);
						c.getVenda().setNumero(numero);
						c.getVenda().setDataVenda(new Date());
						c.getVenda().setValorTotal(Float.parseFloat(valorTotal));
					}
					
					if(ctrlCarrinho.cadastraVenda(carrinhos.get(0).getVenda())){
						try{
							ctrlCarrinho.cadastraCarrinho(carrinhos);
							
							for(Carrinho ca: carrinhos){
								ctrlProduto.efetuaCompra(ca.getProduto(), ca.getQuantidade().longValue());
							}
							
							Venda venda = ctrlCarrinho.verificaVenda(carrinhos.get(0).getVenda());
							
							session.setAttribute("venda", venda);
							session.setAttribute("carrinho", carrinhos);
							
						}catch(Exception er){
							System.err.println("Erro ao cadastrar carrinho: " + er.toString());
							erros = "Erro ao cadastrar carrinho!";
							
							String[] err = erros.split("!");
							
							session.setAttribute("erros", err);
							
							RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
							dispatcher.forward(request, response);
						}
						response.sendRedirect("cupom.jsp");
					}else{
						//informar erro ao cadastrar venda
						erros = "Erro ao cadastrar venda!";
						
						String[] err = erros.split("!");
						
						session.setAttribute("erros", err);
						
						RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
						dispatcher.forward(request, response);
					}
				}else{
					//enviar para a pagina de erros informando os erros
					String[] err = erros.split("!");
					
					session.setAttribute("erros", err);
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
					dispatcher.forward(request, response);
				}
			}else{
				//informar que a compra não pode ser realizada pois não há produtos no carrinho
				String erros = "Carrinho vazio!";
				
				String[] err = erros.split("!");
				
				session.setAttribute("erros", err);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
}
