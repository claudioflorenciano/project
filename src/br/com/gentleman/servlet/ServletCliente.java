package br.com.gentleman.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.gentleman.controller.CtrlCliente;
import br.com.gentleman.model.Carrinho;
import br.com.gentleman.model.Cliente;

/**
 * Servlet implementation class ServletCliente
 */
@WebServlet("/Cliente.do")
public class ServletCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CtrlCliente ctrlCliente;
	Cliente cliente;
    HttpSession session;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    session = request.getSession();
		String acao = request.getParameter("acao");
		
		if(acao.equals("logCliente")){
			String email = request.getParameter("email");
			String pass = request.getParameter("pass");
			ctrlCliente = new CtrlCliente();
			
			String erros = "";
			
			erros = ctrlCliente.validaLogin(email, pass);
			
			if(erros.equals("")){
				if(ctrlCliente.verificar(email, pass)){
					try{
						cliente = ctrlCliente.logar(email, pass);
						
						session.setAttribute("cliente", cliente);
						List<Carrinho> carrinhos = new ArrayList<Carrinho>();
						session.setAttribute("carrinho", carrinhos);
					}catch(Exception ex){
						System.err.println("Erro ao logar: " + ex);
						erros = "Erro ao logar!";
						
						String[] err = erros.split("!");
						
						session.setAttribute("erros", err);
						
						RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
						dispatcher.forward(request, response);
					}
					RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
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
				
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
				dispatcher.forward(request, response);
			}
			
			//criar as devidas sessoes com base no objeto retornado
		}
		
		if(acao.equals("cadCliente")){
			String nome = request.getParameter("nomeCli");
			if(!nome.equals("")){
				nome = Character.toUpperCase(nome.charAt(0)) + nome.substring(1);
			}
			String sobrenome = request.getParameter("sobrenomeCli");
			String cpf = request.getParameter("cpfCli");
			String data = request.getParameter("dataCli").replace("-"," ");
			String sexo = request.getParameter("sexoCli");
			String ddd = request.getParameter("dddCel");
			String celular = ddd + " " + request.getParameter("celCli");
			String email = request.getParameter("emailCli");
			String pass = request.getParameter("passCli");
			String pass2 = request.getParameter("passCliConf");
			
			cliente = new Cliente();
			
	        String pattern = "yyyy MM dd";
	        DateFormat df = new SimpleDateFormat(pattern);
	        Date date = null;
			try {
				date = df.parse(data);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			cliente.setNomeCliente(nome);
			cliente.setSobrenomeCliente(sobrenome);
			cliente.setCpfCliente(cpf);
			cliente.setDataNascimento(date);
			cliente.setSexoCliente(sexo);
			cliente.setCelularCliente(celular);
			cliente.setEmailCliente(email);
			cliente.setClientPass(pass);
			
			ctrlCliente = new CtrlCliente();
			
			String erros = "";
			
			erros = ctrlCliente.valida(cliente, pass2);
			
			if(erros.equals("")){
				if(!ctrlCliente.verificar(email, pass)){
					try{
						ctrlCliente.cadastrar(cliente);
					}catch(Exception ex){
						System.err.println("Erro ao cadastrar: " + ex);
						erros = "Erro ao cadastrar!";
						
						String[] err = erros.split("!");
						
						session.setAttribute("erros", err);
						
						RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
						dispatcher.forward(request, response);
					}
					response.sendRedirect("login.jsp");
				}else{
					erros = "Usuario já existente!";
					
					String[] err = erros.split("!");
					
					session.setAttribute("erros", err);
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
					dispatcher.forward(request, response);
				}
			}else{
				//mandar a variavel erros atravez de uma session de volta para login.jsp
				String[] err = erros.split("!");
				
				session.setAttribute("erros", err);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
				dispatcher.forward(request, response);
			}	
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		session = request.getSession();
		
		if(acao.equals("logout")){
			session.invalidate();
			System.out.println("Deslogado");
			response.sendRedirect("index.jsp");
		}
		
	}

}
