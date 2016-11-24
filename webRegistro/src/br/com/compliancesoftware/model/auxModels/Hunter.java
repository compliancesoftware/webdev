package br.com.compliancesoftware.model.auxModels;

import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.sun.org.apache.xml.internal.security.utils.Base64;

/**
 * Classe legal.
 * @author Compliance Software (by Douglas Fernandes)
 *
 */
public class Hunter 
{
	private static String key = "cripto7acripto7b";
	private static String IV = "AAAAAAAAAAAAAAAA";
	
	public static String hunt(String texto)
	{
		try
		{
			return crip(texto);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return texto;
		}
	}
	
	public static String hunted(String texto)
	{
		try
		{
			return decrip(texto);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return texto;
		}
	}
	
	private static String crip(String toCrip) throws Exception 
	{
		Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keyspec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        encripta.init(Cipher.ENCRYPT_MODE, keyspec,new IvParameterSpec(IV.getBytes("UTF-8")));
        byte[] bCripted = encripta.doFinal(toCrip.getBytes("UTF-8"));
        return toString(bCripted);
    }
	
	private static String decrip(String toDecrip) throws Exception
	{
		byte[] bEncripted = toBytes(toDecrip);
		Cipher decripta = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keyspec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        decripta.init(Cipher.DECRYPT_MODE, keyspec,new IvParameterSpec(IV.getBytes("UTF-8")));
        return new String(decripta.doFinal(bEncripted),"UTF-8");
    }
	
	private static String encode(String texto) throws Exception
	{
		byte[] bytes = texto.getBytes("UTF-8");
		String result = new String(Base64.encode(bytes));
		return result;
	}
	
	private static String decode(String base64String) throws Exception
	{
		byte[] encoded = base64String.getBytes("UTF-8");
		String texto = new String(Base64.decode(encoded));
		return texto;
	}
	
	private static String toString(byte[] bytes) throws Exception
	{
		String texto = "";
		for(byte b : bytes)
		{
			texto += "" + (int)b + "|";
		}
		texto = texto.substring(0,texto.length()-1);
		return encode(texto);
	}
	
	private static byte[] toBytes(String texto) throws Exception
	{
		String text = decode(texto);
		StringTokenizer st = new StringTokenizer(text,"|");
		ArrayList<Integer> listaInt = new ArrayList<Integer>();
		while(st.hasMoreTokens())
		{
			listaInt.add(Integer.parseInt(st.nextToken()));
		}
		byte[] bytes = new byte[listaInt.size()];
		int inteiro;
		for(int i = 0;i < listaInt.size();i++)
		{
			inteiro = (int)listaInt.get(i);
			bytes[i] = (byte)inteiro;
		}
		return bytes;
	}
	
}
