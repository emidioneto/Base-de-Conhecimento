package br.unijorge.baseconhecimento.excessao;

public class BusinessExceptions extends Exception {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 4543261866362422632L;

	public BusinessExceptions(String  mensagem){
        super(mensagem);
    }
}