var a = 0;
function acende(a){
    apaga();
    switch (a) {
        case 1:
            document.getElementById("AlteraProduto").style.display= "block";
            break;
        case 2:
            document.getElementById("ListaProduto").style.display= "block";
            break;
        case 3:
            document.getElementById("AdicionarProduto").style.display= "block";
            break;
        case 4:
            document.getElementById("ApagaProduto").style.display= "block";
            break;
    }
}
function apaga(){
    document.getElementById("AlteraProduto").style.display= "none";
    document.getElementById("ListaProduto").style.display= "none";
    document.getElementById("AdicionarProduto").style.display= "none";
    document.getElementById("ApagaProduto").style.display= "none";
}
function exibir(obj, id){
    document.getElementById(obj).style.display ='block';
    document.getElementById("idProdAlt").value = id;
}
function fechar(){
    document.getElementById('areAltera').style.display = "none";
}
function exibirAdd(obj, id){
    document.getElementById(obj).style.display ='block';
    document.getElementById("idProdAdd").value = id;
}
function fecharAdd(){
    document.getElementById('areAdiciona').style.display = "none";
}

function no_char(e){
    tecla = (window.event)?event.keyCode:e.which;
    if((tecla == 9) || (tecla == 37) || (tecla == 39) || (tecla == 46) || (tecla == 48) || (tecla == 49) || (tecla == 50) || (tecla == 51) || (tecla == 52) || (tecla == 53) || (tecla == 54) || (tecla == 55) || (tecla == 56) || (tecla == 57) || (tecla == 8) || (tecla == 96) || (tecla == 97) || (tecla == 98) || (tecla == 99) || (tecla == 100) || (tecla == 101) || (tecla == 102) || (tecla == 103) || (tecla == 104) || (tecla == 105)){
       return true;
    }else{
        return false;   
    }
}

function mascara_cpf(obj){
    if(obj.value.length == 3){
        obj.value += ".";
    }
    if(obj.value.length == 7){
        obj.value += ".";
    }
    if(obj.value.length == 11){
        obj.value += "-";
    }
}

function mascara_cel(obj){
    if(obj.value.length == 5){
    	obj.value += "-";
    }
}

function mascara_cep(obj){
    if(obj.value.length == 5){
    	obj.value += "-";
    }
}

function acendeErros(){
	document.getElementById("erros").style.display="block";
}

var x = 1;

function moveSlide(){
    x++
    
    if(x > 3){
        x = 1;
    }
    
    apagaSlide();
    document.getElementById("img"+x).style.display = "block";
    
}

function apagaSlide(){
    document.getElementById("img1").style.display = "none";
    document.getElementById("img2").style.display = "none";
    document.getElementById("img3").style.display = "none";
}