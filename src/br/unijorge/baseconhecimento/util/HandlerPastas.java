package br.unijorge.baseconhecimento.util;

import java.io.File;

import javax.faces.context.FacesContext;

public class HandlerPastas {

	private static File file;
	private static File dir;

	private static String contexto = FacesContext.getCurrentInstance()
			.getExternalContext().getRealPath("/")
			+ "conteudos";

	public static void criarPastasAssunto(String assunto) {
		file = new File(contexto);
		if (!file.exists()) {
			file.mkdir();
		}

		file = new File(contexto + "/assuntos/");
		if (!file.exists()) {
			file.mkdir();
		}

		file = new File(contexto + "/assuntos/"
				+ assunto.toLowerCase().replace(" ", "_").replace("/", ""));
		if (!file.exists()) {
			file.mkdir();
		}
	}

	public static void criarPastasTopico(String assunto, String topico) {
		file = new File(contexto + "/assuntos/"
				+ assunto.toLowerCase().replace(" ", "_").replace("/", "")
				+ "/topicos/");
		if (!file.exists()) {
			file.mkdir();
		}

		file = new File(contexto + "/assuntos/"
				+ assunto.toLowerCase().replace(" ", "_").replace("/", "")
				+ "/topicos/"
				+ topico.toLowerCase().replace(" ", "_").replace("/", ""));
		if (!file.exists()) {
			file.mkdir();
		}
	}

	public static void renomearPastaAssunto(String assuntoAntigo,
			String assuntoNovo) {

		String antigo = contexto
				+ "/assuntos/"
				+ assuntoAntigo.toLowerCase().replace(" ", "_")
						.replace("/", "");

		String novo = contexto + "/assuntos/"
				+ assuntoNovo.toLowerCase().replace(" ", "_").replace("/", "");

		file = new File(antigo);
		file.renameTo(new File(novo));
	}

	public static void renomearPastaTopico(String assunto, String topicoAntigo,
			String topicoNovo) {

		String antigo = contexto + "/assuntos/"
				+ assunto.toLowerCase().replace(" ", "_").replace("/", "")
				+ "/topicos/"
				+ topicoAntigo.toLowerCase().replace(" ", "_").replace("/", "");

		String novo = contexto + "/assuntos/"
				+ assunto.toLowerCase().replace(" ", "_").replace("/", "")
				+ "/topicos/"
				+ topicoNovo.toLowerCase().replace(" ", "_").replace("/", "");

		file = new File(antigo);
		file.renameTo(new File(novo));
	}

	public static void excluirPastaAssunto(String assunto) {
		file = new File(contexto + "/assuntos/"
				+ assunto.toLowerCase().replace(" ", "_").replace("/", ""));
		if (file.exists()) {
			file.delete();
		}
	}

	public static void excluirPastaTopico(String assunto, String topico) {
		file = new File(contexto + "/assuntos/"
				+ assunto.toLowerCase().replace(" ", "_").replace("/", "")
				+ "/topicos/"
				+ topico.toLowerCase().replace(" ", "_").replace("/", ""));
		if (file.exists()) {
			file.delete();
		}
	}

	public static void moverArquivoDoTemp(String assunto, String topico,
			String nome_arquivo) {
		file = new File(contexto + "/temp/"
				+ nome_arquivo.toLowerCase().replace(" ", "_"));

		if (file.exists()) {
			dir = new File(contexto + "/assuntos/"
					+ assunto.toLowerCase().replace(" ", "_").replace("/", "")
					+ "/topicos/"
					+ topico.toLowerCase().replace(" ", "_").replace("/", ""));

			file.renameTo(new File(dir, file.getName()));

		}
	}

	public static void apagarArquivoDoTopico(String assunto, String topico,
			String nome_arquivo) {
		// Deletando o pdf
		file = new File(contexto + "/assuntos/"
				+ assunto.toLowerCase().replace(" ", "_").replace("/", "")
				+ "/topicos/"
				+ topico.toLowerCase().replace(" ", "_").replace("/", "") + "/"
				+ nome_arquivo);
		if (file.exists()) {
			file.delete();
		}
		excluirPastaTopico(assunto, topico);

	}
	
	public static void moverTopico(String assuntoAntigo, String assuntoNovo,String topico){
		//topico
		file = new File(contexto + "/assuntos/"+assuntoAntigo+"/topicos/"+topico);

		if (file.exists()) {
			//destino
			dir = new File(contexto + "/assuntos/"+assuntoNovo+"/topicos/");

			file.renameTo(new File(dir, file.getName()));

		}
	}
}
