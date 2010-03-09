package br.com.fsinformatica.projeto.comum.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Map.Entry;

/**
 * Classe utilitaria para acessar arquivo .properties
 * 
 * @author Leonardo Moura Leitao [leonardo.leitao@previdencia.gov.br]
 * @version 1.0, 17/11/2008
 */
public class PropertiesUtils {

	// ---------------------------------------------------- Variaveis de classe

	private static InputStream propertiesExterno;

	private static HashMap<String, Properties> mapProperties;

	private static HashMap<String, ResourceBundle> mapResourceBundle;

	// ------------------------------------------------- Bloco de inicializacao

	static {
		carregarProperties();
	}

	// ----------------------------------------------------------- Construtores

	/**
	 * Construtor privado
	 */
	private PropertiesUtils() {
	}

	// ------------------------------------------------------- Metodos publicos

	public static String getProperty(String key) {
		return getProperty(key, Locale.getDefault());
	}

	public static String getProperty(String key, Locale locale) {
		String valor = null;

		for (Entry<String, Properties> propertiesEntry : getMapProperties()
				.entrySet()) {

			try {
				if (propertiesEntry.getValue().getProperty(key) != null) {
					valor = propertiesEntry.getValue().getProperty(key);
				}
				
				if (valor != null
						&& propertiesEntry.getKey().endsWith(
								locale + ".properties")) {
					break;
				}
			} catch (Exception e) {
			}
		}

		if (valor == null) {
			for (ResourceBundle properties : getMapResourceBundle().values()) {

				try {
					valor = properties.getString(key);
					break;
				} catch (Exception e) {
				}
			}
		}

		return valor;
	}

	// ------------------------------------------------------- Metodos privados

	private static void setProperties(String path, InputStream in) {
		if (!getMapProperties().containsKey(path)) {

			try {
				Properties properties = new Properties();
				properties.load(in);
				getMapProperties().put(path, properties);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void setResourceBundle(String path) {
		if (!getMapResourceBundle().containsKey(path)) {

			try {
				ResourceBundle properties = ResourceBundle.getBundle(path);
				getMapResourceBundle().put(path, properties);
			} catch (Exception e) {
			}
		}
	}

	private static Map<String, Properties> getMapProperties() {
		if (mapProperties == null) {
			mapProperties = new HashMap<String, Properties>();
		}
		return mapProperties;
	}

	private static Map<String, ResourceBundle> getMapResourceBundle() {
		if (mapResourceBundle == null) {
			mapResourceBundle = new HashMap<String, ResourceBundle>();
		}
		return mapResourceBundle;
	}

	private static void carregarProperties() {

		for (String arquivoProperties : BrowserClassPath
				.pesquisarClassPath(".properties")) {

			if (arquivoProperties.length() > 11) {
				setResourceBundle(arquivoProperties.substring(0,
						arquivoProperties.length() - 11));
			}

			try {
				propertiesExterno = new FileInputStream(arquivoProperties);
				setProperties(arquivoProperties, propertiesExterno);
			} catch (Exception e) {
			}
		}
	}
}