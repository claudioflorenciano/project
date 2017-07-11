package br.com.gentleman.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import br.com.gentleman.controller.CtrlFuncionario;
import br.com.gentleman.controller.CtrlProduto;
import br.com.gentleman.model.Departamento;
import br.com.gentleman.model.Funcionario;
import br.com.gentleman.model.Marca;
import br.com.gentleman.model.Produto;
import br.com.gentleman.model.Tamanho;
import br.com.gentleman.util.ArquivoPart;

/**
 * Servlet implementation class ServletFuncionario
 */
@WebServlet("/Funcionario.do")
@MultipartConfig(
        fileSizeThreshold   = 1024 * 1024 * 1,  // 1 MB
        maxFileSize         = 1024 * 1024 * 10, // 10 MB
        maxRequestSize      = 1024 * 1024 * 15, // 15 MB
        location            = "E:/Dados/Programação/eclipse/workspace/Gentleman/WebContent/imgDB/"
)
public class ServletFuncionario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Funcionario funcionario;
	CtrlFuncionario ctrlFuncionario;
	HttpSession session;
	CtrlProduto ctrlProduto;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String acao = request.getParameter("acao");
		session = request.getSession();
		
		
		if(acao.equals("logFuncionario")){
			String email = request.getParameter("emailFunc");
			String pass = request.getParameter("passFunc");
			ctrlFuncionario = new CtrlFuncionario();
			ctrlProduto = new CtrlProduto();
			
			String erros = "";
			
			erros = ctrlFuncionario.validaLogin(email, pass);
			
			if(erros.equals("")){
				if(ctrlFuncionario.verificar(email, pass)){
					try{
						funcionario = ctrlFuncionario.logar(email, pass);
						//enviar o usuario para a area de dashboard com o funcionario
						//armazenado na sessao
						
						List<Produto> produtos = ctrlProduto.buscaProdutos();
						List<Marca> marcas = ctrlProduto.buscaMarcas();
						List<Tamanho> tamanhos = ctrlProduto.buscaTamanhos();
						List<Departamento> departamentos = ctrlProduto.buscaDepartamentos();
						
						
						session.setAttribute("funcionario", funcionario);
						session.setAttribute("marca", marcas);
						session.setAttribute("tamanho", tamanhos);
						session.setAttribute("departamento", departamentos);
						session.setAttribute("produto", produtos);
					}catch(Exception ex){
						System.err.println("Erro ao logar: " + ex);
						erros = "Erro ao logar!";
						
						String[] err = erros.split("!");
						
						session.setAttribute("erros", err);
						
						RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
						dispatcher.forward(request, response);
					}
					RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
					rd.forward(request, response);
				}else{
					erros = "Usuario inexistente!";
					
					String[] err = erros.split("!");
					
					session.setAttribute("erros", err);
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
					dispatcher.forward(request, response);
				}
			}else{
				//mandar a variavel erros atravez de uma session de volta para login.jsp
				String[] err = erros.split("!");
				
				session.setAttribute("erros", err);
				response.sendRedirect("erro.jsp");
			}
			
		}
		
		
		if(acao.equals("addProd")){
			Part arquivo = request.getPart("prodImagAdd");
			if(arquivo != null){
				ArquivoPart  ap = new ArquivoPart();
				String erros = ctrlProduto.validaImg(arquivo);
				if(erros.equals("")){
					Departamento departamento = new Departamento();
					
					departamento.setIdDeparamento(Long.parseLong(request.getParameter("prodDepartamentoAdd")));
					
					Tamanho tamanho = new Tamanho(departamento);
					
					tamanho.setIdTamanho(Long.parseLong(request.getParameter("prodTamanhoAdd")));
					
					Marca marca = new Marca();
					
					marca.setIdMarca(Long.parseLong(request.getParameter("prodMarcaAdd")));
					
					Produto produto = new Produto(tamanho, marca);
					
					produto.setNomeProduto(request.getParameter("prodNomeAdd"));
					produto.setDescricaoProduto(request.getParameter("prodDescriAdd"));
					produto.setImagemProduto("imgDB/" + ap.getFileName(arquivo));
					produto.setQuantidadeEstoque(Integer.parseInt(request.getParameter("prodQuantAdd")));
					String preco = request.getParameter("prodPrecoAdd");
					preco.replace("," , ".");
					produto.setPrecoProduto(Float.parseFloat(preco));
					
					erros += ctrlProduto.validaProd(produto);
					if(erros.equals("")){
						try{
							ctrlProduto.cadProd(produto);
							arquivo.write(ap.getFileName(arquivo));
							System.out.println("File uploaded");
							
							
							List<Produto> produtos = ctrlProduto.buscaProdutos();
							List<Marca> marcas = ctrlProduto.buscaMarcas();
							List<Tamanho> tamanhos = ctrlProduto.buscaTamanhos();
							List<Departamento> departamentos = ctrlProduto.buscaDepartamentos();
							
							
							session.setAttribute("marca", marcas);
							session.setAttribute("tamanho", tamanhos);
							session.setAttribute("departamento", departamentos);
							session.setAttribute("produto", produtos);
						}catch(Exception er){
							System.err.println("Erro ao cadastrar produto: " + er);
							erros = "Erro ao cadastrar produto!";
							
							String[] err = erros.split("!");
							
							session.setAttribute("erros", err);
							
							RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
							dispatcher.forward(request, response);
						}
						response.sendRedirect("dashboard.jsp");
						
					}else{
						String[] err = erros.split("!");
						
						session.setAttribute("erros", err);
						
						RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
						dispatcher.forward(request, response);
					}
				}else{
					//redirecionar para a pagina de erros
					String[] err = erros.split("!");
					
					session.setAttribute("erros", err);
					response.sendRedirect("erro.jsp");
				}
			}else{
				String erros = "Formulário incompleto!";
				
				String[] err = erros.split("!");
				
				session.setAttribute("erros", err);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
				dispatcher.forward(request, response);
			}
		}
		
		if(acao.equals("addMarca")){
			String nomeMarca = request.getParameter("nomeMarca");
			if(!nomeMarca.equals("")){
				if(!ctrlProduto.getMarca(nomeMarca)){
					try{
						ctrlProduto.cadMarca(nomeMarca);
						//redirecionar para a pagina de cadastro de produtos
						
						
						List<Produto> produtos = ctrlProduto.buscaProdutos();
						List<Marca> marcas = ctrlProduto.buscaMarcas();
						List<Tamanho> tamanhos = ctrlProduto.buscaTamanhos();
						List<Departamento> departamentos = ctrlProduto.buscaDepartamentos();
						
						
						session.setAttribute("marca", marcas);
						session.setAttribute("tamanho", tamanhos);
						session.setAttribute("departamento", departamentos);
						session.setAttribute("produto", produtos);
					}catch(Exception er){
						System.err.println("Erro ao cadastrar Marca: " + er.toString());
						String erros = "Erro ao cadastrar marca!";
						
						String[] err = erros.split("!");
						
						session.setAttribute("erros", err);
						
						RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
						dispatcher.forward(request, response);
					}
					response.sendRedirect("dashboard.jsp");
				}else{
					String erros = "Marca já cadastrada!";
					
					String[] err = erros.split("!");
					
					session.setAttribute("erros", err);
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
					dispatcher.forward(request, response);
				}
			}else{
				String erros = "Formulário incompleto!";
				
				String[] err = erros.split("!");
				
				session.setAttribute("erros", err);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
				dispatcher.forward(request, response);
			}
		}
		
		if(acao.equals("delProd")){
			Long idProd = Long.parseLong(request.getParameter("idProdDel"));
			
			try{
				ctrlProduto.delProd(idProd);
				//enviar o usuario para o dashboard

				
				List<Produto> produtos = ctrlProduto.buscaProdutos();
				List<Marca> marcas = ctrlProduto.buscaMarcas();
				List<Tamanho> tamanhos = ctrlProduto.buscaTamanhos();
				List<Departamento> departamentos = ctrlProduto.buscaDepartamentos();
				
				
				session.setAttribute("marca", marcas);
				session.setAttribute("tamanho", tamanhos);
				session.setAttribute("departamento", departamentos);
				session.setAttribute("produto", produtos);
			}catch(Exception er){
				System.err.println("Erro ao deletar produto: " + er.toString());
				String erros = "Erro ao deletar produto!";
				
				String[] err = erros.split("!");
				
				session.setAttribute("erros", err);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
				dispatcher.forward(request, response);
			}
			response.sendRedirect("dashboard.jsp");
		}
		
		if(acao.equals("addQuant")){
			Long id = Long.parseLong(request.getParameter("idProdAdd"));
			Long quant = Long.parseLong(request.getParameter("quantProdAdd"));
			
			Produto produto = ctrlProduto.buscaProduto(id);
			
			try{
				ctrlProduto.addQuant(produto, quant);
				//direcionar o usuario para o dashboard

				
				List<Produto> produtos = ctrlProduto.buscaProdutos();
				List<Marca> marcas = ctrlProduto.buscaMarcas();
				List<Tamanho> tamanhos = ctrlProduto.buscaTamanhos();
				List<Departamento> departamentos = ctrlProduto.buscaDepartamentos();
				
				
				session.setAttribute("marca", marcas);
				session.setAttribute("tamanho", tamanhos);
				session.setAttribute("departamento", departamentos);
				session.setAttribute("produto", produtos);
			}catch(Exception er){
				System.err.println("Erro ao adicinoar produto: " + er.toString());
				String erros = "Erro ao adicionar produto!";
				
				String[] err = erros.split("!");
				
				session.setAttribute("erros", err);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
				dispatcher.forward(request, response);
			}
			response.sendRedirect("dashboard.jsp");
			
		}
		
		if(acao.equals("altProd")){
			System.out.println("altProd");
			Long id = Long.parseLong(request.getParameter("idProdAlt"));
			String nome = request.getParameter("nomeProdAlt");
			String descricao = request.getParameter("descriProdAlt");
			String preco = request.getParameter("precoProdAlt");
			preco.replace(",", ".");
			
			String erros = ctrlProduto.validaAlt(nome, descricao);
			
			if(erros.equals("")){
				try{
					ctrlProduto.alteraProduto(nome, descricao, Float.parseFloat(preco), id);

					
					List<Produto> produtos = ctrlProduto.buscaProdutos();
					List<Marca> marcas = ctrlProduto.buscaMarcas();
					List<Tamanho> tamanhos = ctrlProduto.buscaTamanhos();
					List<Departamento> departamentos = ctrlProduto.buscaDepartamentos();
					
					
					session.setAttribute("marca", marcas);
					session.setAttribute("tamanho", tamanhos);
					session.setAttribute("departamento", departamentos);
					session.setAttribute("produto", produtos);
				}catch(Exception er){
					System.err.println("Erro ao alterar produto: " + er.toString());
					
					erros = "Erro ao alterar produto!";
					
					String[] err = erros.split("!");
					
					session.setAttribute("erros", err);
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
					dispatcher.forward(request, response);
				}
				response.sendRedirect("dashboard.jsp");
			}else{
				//informar erros ao usuario
				String[] err = erros.split("!");
				
				session.setAttribute("erros", err);
				response.sendRedirect("erro.jsp");
			}
		}
	}

}
