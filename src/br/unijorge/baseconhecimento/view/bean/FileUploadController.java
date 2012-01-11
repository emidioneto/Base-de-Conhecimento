package br.unijorge.baseconhecimento.view.bean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;

import br.unijorge.baseconhecimento.util.FacesUtil;

@ManagedBean(name = "fileUploadController")
@RequestScoped
public class FileUploadController {
	File file;

	public void carregarArquivo(FileUploadEvent event, String nome_arquivo)
			throws FileNotFoundException, IOException {

		FacesUtil.adicionarMenssagem(FacesMessage.SEVERITY_INFO, "Sucesso",
				"O arquivo " + nome_arquivo + " foi enviado com sucesso!");

		String caminho = FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath("/")
				+ "conteudos/temp/"
				+ nome_arquivo.toLowerCase().replace(" ", "_");

		this.file = new File(FacesContext.getCurrentInstance()
				.getExternalContext().getRealPath("/")
				+ "conteudos/temp/");
		if (!file.exists()) {
			file.mkdir();
		}

		byte[] conteudo = event.getFile().getContents();

		FileOutputStream fos = new FileOutputStream(caminho);
		fos.write(conteudo);
		fos.close();

	}
}
