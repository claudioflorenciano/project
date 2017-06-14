package util;
 
public final class ExceptionErros extends Exception{
    String erro = null;

    public ExceptionErros(String erro) {
        setMsg(erro);
    }
   
    public String getMsg(){
        return  this.erro;
    }
    
    public void setMsg(String erro){
        this.erro = erro;
    }
}
