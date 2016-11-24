package br.com.compliancesoftware.model.auxModels;

/**
 * Verifica se o cpf ou cnpj de um determinado campo está de acordo com a legislação.
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
public class VerificaId 
{
	
	/**
	 * Método universal de verificação de cnpj e cpf
	 * @param codigo
	 * @return
	 */
	public static boolean verifica(String codigo)
	{
		if(codigo == null || codigo.equals(null) || codigo.equals("") || codigo.length() < 11)
			return false;
		else
		{
			boolean verifica = verificaCNPJ(codigo);
			if(!verifica)
				verifica = verificaCPF(codigo);
			return verifica;
		}
	}
//----------------------------------------------------------------------CNPJ---------------------------------------------------------------------------------
	/**
	 * Métod que informa se o cnpj de uma empresa está correto.
	 * @param cnpj = String com o cnpj no modo numérico.
	 * @return "true" ou "false" se está, ou não, correto.
	 */
	private static boolean verificaCNPJ(String codigo)
	{
		String cnpj = codigo.replace(".", "");
		cnpj = cnpj.replace("-", "");
		cnpj = cnpj.replace("/", "");
		cnpj = cnpj.trim();
		
		try
		{
			if(cnpj.length() != 14)
			{
				System.out.println("Tamanho de cnpj errado! {atual: "+cnpj.length()+" | correto: 14}");
				return false;
			}
			else
			{
				if(soNumeros(cnpj))
				{
					if(terceiroDigitoCnpjOk(cnpj))
					{
						if(cnpjOk(cnpj))
							return true;
						else
						{
							return false;
						}
					}
					else
					{
						System.out.println("Erro ao calcular o terceiro digito de cnpj.");
						return false;
					}
				}
				else
				{
					System.out.println("O cnpj não contém apenas números!{código atual: "+cnpj+"}");
					return false;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("Erro na execução do código de validação de cnpj.");
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Método que informa se a String contém apenas números.
	 * @param codigo = Código numérico em forma de String.
	 * @return "true" ou "false" se contém apenas números, ou não.
	 */
	private static boolean soNumeros(String codigo)
	{
		try
		{
			int i = 0;
			for(i = 0; i < codigo.length(); i++)
			{
				if(!Character.isDigit(codigo.charAt(i)))
					return false;
			}
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	/**
	 * Método que verifica se o terceiro dígito do cnpj está correto.
	 * @param cnpj
	 * @return "true" ou "false" se estiver, ou não, correto.
	 */
	private static boolean terceiroDigitoCnpjOk(String codigo)
	{
		try
		{
			String cnpj = codigo.substring(0,8);
			String terceiro = ""+cnpj.charAt(7);
			cnpj = cnpj.substring(0,7);
			
			int val1 = Integer.parseInt(""+cnpj.charAt(0));
			int val2 = Integer.parseInt(""+cnpj.charAt(1));
			int val3 = Integer.parseInt(""+cnpj.charAt(2));
			int val4 = Integer.parseInt(""+cnpj.charAt(3));
			int val5 = Integer.parseInt(""+cnpj.charAt(4));
			int val6 = Integer.parseInt(""+cnpj.charAt(5));
			int val7 = Integer.parseInt(""+cnpj.charAt(6));
			
			int resultado = novesFora(val1,2);
			resultado += novesFora(val2,1);
			resultado += novesFora(val3,2);
			resultado += novesFora(val4,1);
			resultado += novesFora(val5,2);
			resultado += novesFora(val6,1);
			resultado += novesFora(val7,2);
			resultado = 30 - resultado;
			if(resultado < 0)
				resultado = (-1)*resultado;
			
			int verificador = Integer.parseInt(terceiro);
			
			if(resultado == verificador)
			{
				return true;
			}	
			else
			{
				System.out.println("O terceiro digito do cnpj deveria ser "+verificador+" porém, é "+resultado);
				return false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Método noves fora.
	 * @param valor
	 * @param multiplicador
	 * @return Número abaixo de 9.
	 */
	private static int novesFora(int valor,int multiplicador)
	{
		int resultado = valor * multiplicador;
		if(resultado > 9)
			resultado -= 9;
		return resultado;
	}
	
	/**
	 * Método efetivo na verificação do cnpj.
	 * @param cnpj
	 * @return "true" ou "false" se o cnpj está, ou não, correto.
	 */
	private static boolean cnpjOk(String cnpj)
	{
		if(verifica1cnpj(cnpj))
		{
			return verifica2cnpj(cnpj);
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Primeira fase de verificação do cnpj.
	 * @param cnpj
	 * @return "true" ou "false" se está correto ou não.
	 */
	private static boolean verifica1cnpj(String cnpj)
	{
		int val1 = Integer.parseInt(""+cnpj.charAt(0));
		int val2 = Integer.parseInt(""+cnpj.charAt(1));
		int val3 = Integer.parseInt(""+cnpj.charAt(2));
		int val4 = Integer.parseInt(""+cnpj.charAt(3));
		int val5 = Integer.parseInt(""+cnpj.charAt(4));
		int val6 = Integer.parseInt(""+cnpj.charAt(5));
		int val7 = Integer.parseInt(""+cnpj.charAt(6));
		int val8 = Integer.parseInt(""+cnpj.charAt(7));
		int val9 = Integer.parseInt(""+cnpj.charAt(8));
		int val10 = Integer.parseInt(""+cnpj.charAt(9));
		int val11 = Integer.parseInt(""+cnpj.charAt(10));
		int val12 = Integer.parseInt(""+cnpj.charAt(11));
		val1 *= 6;
		val2 *= 7;
		val3 *= 8;
		val4 *= 9;
		val5 *= 2;
		val6 *= 3;
		val7 *= 4;
		val8 *= 5;
		val9 *= 6;
		val10 *= 7;
		val11 *= 8;
		val12 *= 9;
		
		int resultado = val1 + val2 + val3 + val4 + val5 + val6 + val7 + val8 + val9 + val10 + val11 + val12;
		resultado = resultado % 11;
		if(resultado > 9)
			resultado = 0;
		
		int primeiro_digito = Integer.parseInt(""+cnpj.charAt(12));
		
		if(primeiro_digito == resultado)
			return true;
		else
			return false;
	}
	
	/**
	 * Segunda fase de verificação do cnpj.
	 * @param cnpj
	 * @return "true" ou "false" se está correto ou não.
	 */
	private static boolean verifica2cnpj(String cnpj)
	{
		int val1 = Integer.parseInt(""+cnpj.charAt(0));
		int val2 = Integer.parseInt(""+cnpj.charAt(1));
		int val3 = Integer.parseInt(""+cnpj.charAt(2));
		int val4 = Integer.parseInt(""+cnpj.charAt(3));
		int val5 = Integer.parseInt(""+cnpj.charAt(4));
		int val6 = Integer.parseInt(""+cnpj.charAt(5));
		int val7 = Integer.parseInt(""+cnpj.charAt(6));
		int val8 = Integer.parseInt(""+cnpj.charAt(7));
		int val9 = Integer.parseInt(""+cnpj.charAt(8));
		int val10 = Integer.parseInt(""+cnpj.charAt(9));
		int val11 = Integer.parseInt(""+cnpj.charAt(10));
		int val12 = Integer.parseInt(""+cnpj.charAt(11));
		int val13 = Integer.parseInt(""+cnpj.charAt(12));
		val1 *= 5;
		val2 *= 6;
		val3 *= 7;
		val4 *= 8;
		val5 *= 9;
		val6 *= 2;
		val7 *= 3;
		val8 *= 4;
		val9 *= 5;
		val10 *= 6;
		val11 *= 7;
		val12 *= 8;
		val13 *= 9;
		
		int resultado = val1 + val2 + val3 + val4 + val5 + val6 + val7 + val8 + val9 + val10 + val11 + val12 +val13;
		resultado = resultado % 11;
		if(resultado > 9)
			resultado = 0;
		
		int segundo_digito = Integer.parseInt(""+cnpj.charAt(13));
		
		if(segundo_digito == resultado)
			return true;
		else
			return false;
	}

//----------------------------------------------------------------------CPF---------------------------------------------------------------------------------
	
	/**
	 * Método que informa se o cpf passado está correto ou não.
	 * @param cpf = CPF a ser validado.
	 * @return "true" ou "false" se está correto, ou não.
	 */
	private static boolean verificaCPF(String cpf)
	{
		cpf = cpf.replace(".", "");
		cpf = cpf.replace("-", "");
		cpf = cpf.trim();
		
		try
		{
			if(cpf.length() != 11)
			{
				return false;
			}
			else
			{
				if(soNumeros(cpf))
				{
					if(cpfOk(cpf))
						return true;
					else
					{
						return false;
					}
				}
				else
				{
					return false;
				}
			}
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	/**
	 * Método efetivo de avaliação do CPF.
	 * @param cpf
	 * @return "true" ou "false" se estiver correto, ou não.
	 */
	private static boolean cpfOk(String cpf)
	{
		if(verifica1cpf(cpf))
		{
			return verifica2cpf(cpf);
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Primeira fase de verificação do cpf.
	 * @param cpf
	 * @return "true" ou "false" se está correto, ou não.
	 */
	private static boolean verifica1cpf(String cpf)
	{
		int val1 = Integer.parseInt(""+cpf.charAt(0));
		int val2 = Integer.parseInt(""+cpf.charAt(1));
		int val3 = Integer.parseInt(""+cpf.charAt(2));
		int val4 = Integer.parseInt(""+cpf.charAt(3));
		int val5 = Integer.parseInt(""+cpf.charAt(4));
		int val6 = Integer.parseInt(""+cpf.charAt(5));
		int val7 = Integer.parseInt(""+cpf.charAt(6));
		int val8 = Integer.parseInt(""+cpf.charAt(7));
		int val9 = Integer.parseInt(""+cpf.charAt(8));
		
		val1 *= 1;
		val2 *= 2;
		val3 *= 3;
		val4 *= 4;
		val5 *= 5;
		val6 *= 6;
		val7 *= 7;
		val8 *= 8;
		val9 *= 9;
		
		int result = (val1 + val2 + val3 + val4 + val5 + val6 + val7 + val8 + val9) % 11;
		if(result > 9)
			result = 0;
		
		int primeiro_digito = Integer.parseInt(""+cpf.charAt(9));
		
		if(primeiro_digito == result)
			return true;
		else
			return false;
		
	}
	
	/**
	 * Segunda fase de verificação do cpf.
	 * @param cpf
	 * @return "true" ou "false" se está correto, ou não.
	 */
	private static boolean verifica2cpf(String cpf)
	{
		int val1 = Integer.parseInt(""+cpf.charAt(1));
		int val2 = Integer.parseInt(""+cpf.charAt(2));
		int val3 = Integer.parseInt(""+cpf.charAt(3));
		int val4 = Integer.parseInt(""+cpf.charAt(4));
		int val5 = Integer.parseInt(""+cpf.charAt(5));
		int val6 = Integer.parseInt(""+cpf.charAt(6));
		int val7 = Integer.parseInt(""+cpf.charAt(7));
		int val8 = Integer.parseInt(""+cpf.charAt(8));
		int val9 = Integer.parseInt(""+cpf.charAt(9));
		
		val1 *= 1;
		val2 *= 2;
		val3 *= 3;
		val4 *= 4;
		val5 *= 5;
		val6 *= 6;
		val7 *= 7;
		val8 *= 8;
		val9 *= 9;
		
		int result = (val1 + val2 + val3 + val4 + val5 + val6 + val7 + val8 + val9) % 11;
		if(result > 9)
			result = 0;
		
		int segundo_digito = Integer.parseInt(""+cpf.charAt(10));
		
		if(segundo_digito == result)
			return true;
		else
			return false;
		
	}
	
}
