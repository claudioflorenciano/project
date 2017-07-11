<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
        <title>Login/Cadastro - Be a Gentleman</title>
        <link rel="stylesheet" type="text/css" href="css/login.css" />
        <link rel="icon" href="img/suit.png" type="image/x-icon" />
        <link rel="shortcut icon" href="img/suit.png" type="image/x-icon" />
        <link href="https://fonts.googleapis.com/css?family=Fjalla+One|Oswald" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro" rel="stylesheet"/>
        <script type="text/javascript" src="js/script.js"></script>
	</head>
	<body>
	<!--Topo menu cadastro/login-->
        <div id="menuUsu">
            
        </div>
        <div id="topoLogin">
            <!--  <li><a class="carrinhoURL" href="carrinho.jsp"><img class="carrinho" src="img/cart.png"/></a></li> -->
        </div>
        <a class="voltar" href="index.jsp"><img class="back" src="img/back.png"/>Voltar para pagina inicial</a>
        <div id="log-cad">
            <div id="logar">
                <form action="Cliente.do" method="post">
                    <p class="p">JÁ SOU UM CLIENTE GENTLEMAN</p><br/><br/>
                    
                    
                    <label class="lEmail">E-mail</label><br/><br/>
                    <input class="email" name="email" placeholder="seuemail@exemplo.com" type="text"/><br/><br/>
                    
                    <label class="lSenha">Senha</label><br/><br/>
                    <input class="senhaLogin" name="pass" placeholder="Digite sua senha" type="password"/><br/><br/>
                    <input type="hidden" name="acao" value="logCliente">
                    
                    <input class="subLog" type="submit" value="Logar"/>
                </form>
            </div>
            <div id="acessoFuncionario">
                <form action="Funcionario.do" method="post">
                    <p class="p">ACESSO PARA FUNCIONÁRIOS</p>
                    
                    <label class="lEmailFun">E-mail</label><br/><br/>
                    <input class="emailFun" name="emailFunc" placeholder="seuemail@exemplo.com" type="text" required/><br/><br/>
                    
                    <label class="lSenhaFun">Senha</label><br/><br/>
                    <input class="senhaFun" name="passFunc" placeholder="Digite sua senha" type="password" required/><br/><br/>
                    
                    <input type="hidden" name="acao" value="logFuncionario">
                    <input class="subFun" type="submit" value="Logar"/>
                </form>
            </div>
            <div id="cadastro">
                <form class="cadastro" action="Cliente.do" method="post">
                    <p class="p">QUERO SER CLIENTE GENTLEMAN</p><br/><br/>
                    <input type="hidden" name="acao" value="cadCliente">
                    
                    <label>Nome</label><br/><br/>
                    <input class="nome" name="nomeCli" placeholder="Seu nome" type="text" required/><br/><br/>
                    
                    <label class="lSobrenome">Sobrenome</label><br/><br/>
                    <input class="sobrenome" name="sobrenomeCli" placeholder="Seu Sobrenome" type="text" required/><br/><br/>
                    
                    <label class="lCpf">CPF</label><br/><br/>
                    <input class="cpf" name="cpfCli" placeholder="000.000.000-00" maxlength="14" onkeypress="mascara_cpf(this)" onkeydown="return no_char(event)" type="text" required/><br/><br/>
                    
                    <label class="lData">Data de Nascimento</label><br/><br/>
                    <input class="data" name="dataCli" type="date" required/><br/><br/>
                    
                    <label class="lSexo">Sexo</label><br/><br/>
                    <input class="radioMas" name="sexoCli" type="radio" value="Masculino" checked/><br/><br/>
                    <p class="pMas">Masculino</p><br/><br/>
                    <input class="radioFem" name="sexoCli" type="radio" value="Feminino"/><br/><br/>
                    <p class="pFem">Feminino</p><br/><br/>
                    
                    <label class="lCelular">Celular</label><br/><br/>
                    <input class="celularDDD" name="dddCel" placeholder="(99)" type="text" maxlength="2" required/>
                    <input class="celularNum" name="celCli" placeholder="99999-9999" onkeypress="mascara_cel(this)" maxlength="10" type="text" required/><br/><br/>
                    
                    <p class="pAcesso">SEU ACESSO GENTLEMAN</p><br/><br/>
                    
                    <label class="lEmailDois">E-mail</label><br/><br/>
                    <input class="emailCad" name="emailCli" placeholder="seuemail@exemplo.com" type="text" required/><br/><br/>
                    
                    <label class="lSenhaDois">Senha</label><br/><br/>
                    <input class="senhaUm" name="passCli" placeholder="Digite sua senha" type="password" required/><br/><br/>
                    
                    <label class="lConSenha">Confirmar Senha</label><br/><br/>
                    <input class="senhaDois" name="passCliConf" placeholder="Digite novamente" type="password" required/><br/><br/>
                    
                    <input class="subCad" type="submit" value="Cadastrar"/>
                    <img class="separaLogin" src="img/separaLogin.png"/>
                </form>
            </div>
        </div>
	</body>
</html>