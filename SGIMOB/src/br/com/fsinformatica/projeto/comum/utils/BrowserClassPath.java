package br.com.fsinformatica.projeto.comum.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.StringTokenizer;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Classe responsavel por pesquisar os arquivos no classpath da aplicacao.
 * 
 * @author Leonardo Moura Leitao [leonardo.leitao@previdencia.gov.br]
 * @version 1.0,
 */
class BrowserClassPath {

	private BrowserClassPath() {}

	/**
	 * Metodo responsavel por pesquisar no classpath da aplicacao e retornar
	 * todos os arquivos que satisfazem a expressao regular fornecida. <br>
	 * 
	 * Exemplos de expressao regular suportada pelo método: <br>
	 * 
	 * 1) "teste txt" --> retorna qualquer arquivo do classpath que contenha as
	 * strings "teste" e "txt". <br>
	 * 2) "teste !txt" --> retorna qualquer arquivo do classpath que contenha a
	 * string "teste" e nao contenha a string "txt". <br>
	 * 
	 * 
	 * @param expressaoRegular
	 *            string representa a repressao regular usada para filtrar a
	 *            pesquisa.
	 * @return lista de arquivos presentes no classpath que combinam com a
	 *         expressao regular fornecida
	 */
	public static List<String> pesquisarClassPath(String expressaoRegular) {
		String classpath = System.getProperty("java.class.path");
		StringTokenizer st = new StringTokenizer(classpath, File.pathSeparator);

		List<String> arquivos = new ArrayList<String>();
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			File elementoDoClasspath = new File(token);

			if (elementoDoClasspath.isDirectory()) {
				arquivos.addAll(lerDiretorio(elementoDoClasspath,
						expressaoRegular));
			} else {
				arquivos.addAll(lerArquivo(elementoDoClasspath,
						expressaoRegular));
			}
		}

		return arquivos;
	}

	private static List<String> lerDiretorio(File diretorio,
			String expressaoRegular) {
		List<String> arquivos = new ArrayList<String>();

		if (diretorio == null || !diretorio.isDirectory()) {
			return arquivos;
		}

		for (String nome : diretorio.list()) {
			File arquivo = new File(diretorio.getAbsolutePath()
					+ File.separator + nome);
			if (arquivo.isDirectory()) {
				arquivos.addAll(lerDiretorio(arquivo, expressaoRegular));
			} else {
				if (comparador(arquivo.getAbsolutePath(), expressaoRegular)) {
					arquivos.add(arquivo.getAbsolutePath());
				}
			}
		}

		return arquivos;
	}

	private static List<String> lerArquivo(File arquivo, String expressaoRegular) {
		List<String> arquivos = new ArrayList<String>();
		try {
			if (arquivo.getName().endsWith(".jar")) {
				Enumeration<JarEntry> arquivosDoJar;
				arquivosDoJar = new JarFile(arquivo).entries();
				JarEntry arquivoDoJar = null;
				while (arquivosDoJar.hasMoreElements()) {
					arquivoDoJar = arquivosDoJar.nextElement();
					if (comparador(arquivoDoJar.getName(), expressaoRegular)) {
						arquivos.add(arquivoDoJar.getName());
					}
				}
			} else {
				arquivos.add(arquivo.getAbsolutePath());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return arquivos;
	}

	private static boolean comparador(String nome, String expressaoRegular) {
		StringTokenizer st = new StringTokenizer(expressaoRegular, " ");
		StringBuilder parteDoNome = new StringBuilder();
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			if (parteDoNome.length() > 0) {
				parteDoNome.delete(0, parteDoNome.length());
			}
			parteDoNome.append(token);
			if (parteDoNome.substring(0, 1).equals("!")) {
				if (nome.contains(parteDoNome.substring(1))) {
					return false;
				}
			} else if (!nome.contains(parteDoNome)) {
				return false;
			}
		}
		return true;
	}
}