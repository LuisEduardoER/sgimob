package br.com.fsinformatica.projeto.apresentacao;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MBeanUtils {

	public MBeanUtils() {
		
	}

	public static void adicionarMensagemErro(String erro) {
		FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				erro, erro);
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
	}

	public static void adicionarMensagemSucesso(String erro) {
		FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO,
				erro, erro);
		FacesContext.getCurrentInstance().addMessage(null, mensagem);
	}
}
