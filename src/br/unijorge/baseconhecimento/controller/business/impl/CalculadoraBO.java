package br.unijorge.baseconhecimento.controller.business.impl;

public class CalculadoraBO {
	// Identificar a Classe do IP
	public String classeIP(int oct1) {

		String vClasse;
		if (oct1 <= 127) {
			vClasse = "A";
		} else if (oct1 <= 191) {
			vClasse = "B";
		} else if (oct1 <= 223) {
			vClasse = "C";
		} else {
			vClasse = "D ou E";
		}

		return vClasse + "\n";
	}

	// Converte um valor Decimal para Binário
	public String convertDecParaBin(int valDecimal) {

		String resultado = Integer.toBinaryString(valDecimal);

		return normalizarBIN(resultado);

	}

	// Normalizar os valores Binários garantindo a posição de Zeros a Esquerda
	private String normalizarBIN(String numReferencia) {

		int qtd = 0;
		String valNormalizado;
		qtd = numReferencia.length();

		if (qtd == 8) {
			valNormalizado = numReferencia;
		} else if (qtd == 7) {
			valNormalizado = "0" + "" + numReferencia;
		} else if (qtd == 6) {
			valNormalizado = "00" + "" + numReferencia;
		} else if (qtd == 5) {
			valNormalizado = "000" + "" + numReferencia;
		} else if (qtd == 4) {
			valNormalizado = "0000" + "" + numReferencia;
		} else if (qtd == 3) {
			valNormalizado = "00000" + "" + numReferencia;
		} else if (qtd == 2) {
			valNormalizado = "000000" + "" + numReferencia;
		} else if (qtd == 1) {
			valNormalizado = "0000000" + "" + numReferencia;
		} else {
			valNormalizado = "00000000";
		}

		return valNormalizado;
	}

	// Converte um valor Binário para Binário
	public int convertBinParaDec(String bin) {
		int i, result = 0;

		for (i = 0; i < bin.length(); i++) {
			result <<= 1;
			if (bin.charAt(i) == '1')
				result++;
		}

		return result;
	}

	// Composição de Octetos
	private String comporOctetoBin(int refOct, int bits) {
		String octeto = "";
		int x;

		if (refOct == 1) {
			for (x = 1; x <= 8; x++) {
				if (x <= bits && x <= 8)
					octeto = octeto + "1";
				if (x > bits && x <= 8)
					octeto = octeto + "0";
			}
		}

		if (refOct == 2) {
			for (x = 9; x <= 16; x++) {
				if (x <= bits && x <= 16)
					octeto = octeto + "1";
				if (x > bits && x <= 16)
					octeto = octeto + "0";
			}
		}

		if (refOct == 3) {
			for (x = 17; x <= 24; x++) {
				if (x <= bits && x <= 24)
					octeto = octeto + "1";
				if (x > bits && x <= 24)
					octeto = octeto + "0";
			}
		}

		if (refOct == 4) {
			for (x = 25; x <= 32; x++) {
				if (x <= bits && x <= 32)
					octeto = octeto + "1";
				if (x > bits && x <= 32)
					octeto = octeto + "0";
			}
		}

		return octeto;
	}

	// Retorna o valor da Mascara em Binário
	public String mascaraBIN(int bits) {
		String oct1 = "";
		String oct2 = "";
		String oct3 = "";
		String oct4 = "";

		oct1 = comporOctetoBin(1, bits);
		oct2 = comporOctetoBin(2, bits);
		oct3 = comporOctetoBin(3, bits);
		oct4 = comporOctetoBin(4, bits);

		return oct1 + "." + oct2 + "." + oct3 + "." + oct4;
	}

	// Retorna o valor da Mascara em Decimal
	public String mascaraDEC(int bits) {
		String val1 = "";
		String val2 = "";
		String val3 = "";
		String val4 = "";

		val1 = comporOctetoBin(1, bits);
		val2 = comporOctetoBin(2, bits);
		val3 = comporOctetoBin(3, bits);
		val4 = comporOctetoBin(4, bits);

		return convertBinParaDec(val1) + "." + convertBinParaDec(val2) + "."
				+ convertBinParaDec(val3) + "." + convertBinParaDec(val4);
	}
}
