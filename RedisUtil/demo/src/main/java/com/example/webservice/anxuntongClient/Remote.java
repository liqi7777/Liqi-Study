package com.example.webservice.anxuntongClient;


import java.io.File;
import java.io.StringWriter;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class Remote {
	/**
	 * <p> ????????:</p>    
	 * @param args
	 * @throws Exception<br><br>
	 * @author ??????<BR>
	 * @date 2015-8-10<br>
	 * @remark <br>
	 */
	private final static byte[] key = "123456789147258369123456".getBytes();
//	public static void main(String[] args) throws Exception {
//	        
//        JaxWsDynamicClientFactory aClientFactory = JaxWsDynamicClientFactory.newInstance();
//		String wsdlUrl = "http://123.172.48.228:8001/locksys_school/webServices/remote?wsdl";
//		Client client = aClientFactory.createClient(wsdlUrl);
//		
//		String bedChamberId  = getDesEncode("6705");
//        Object[] result;
//        //????WEBSERVICE?????????????
//		result = client.invoke("remote", bedChamberId);
//		String resInfo = (String) result[0];
//		
//		System.out.println("result:" + resInfo);
//	}
	
	
	/*public static void main(String[] args) throws Exception {
	    
		JaxWsDynamicClientFactory aClientFactory = JaxWsDynamicClientFactory.newInstance();
		String wsdlUrl = "http://192.168.186.9:8080/locksys_school/webServices/remote?wsdl";
		Client client = aClientFactory.createClient(wsdlUrl);

		String bedChamberId  = getDesEncode("1");
		System.out.println(bedChamberId);
		Object[] result;
		//????WEBSERVICE?????????????
		result = client.invoke("remote", bedChamberId);
		String resInfo = (String) result[0];

		System.out.println("result:" + resInfo);
		}*/
	
	public static void main(String[] args) throws Exception {
        
        JaxWsDynamicClientFactory aClientFactory = JaxWsDynamicClientFactory.newInstance();
		String wsdlUrl = "http://125.33.125.169:9030/locksys_school/webServices/remote?wsdl";
		Client client = aClientFactory.createClient(wsdlUrl);
		
		SAXReader reader = new SAXReader();  
	    Document document = reader.read(new File("C:\\Users\\Administrator\\Desktop\\新智能硬件\\北京金隅安迅通门锁\\远程开锁.XML"));
	    String text = document.asXML();
	    Object[] result;
	    result = client.invoke("addRemotePwd", text);
	    String resInfo = (String) result[0];
	    System.out.println("result:" + resInfo);
	    String bedChamberId  = getDesEncode("7");
		System.out.println(bedChamberId);
	    	
	} 
	
	/**
     * ECB????,???IV
     * @param key ???
     * @param data ????
     * @return Base64?????????
     * @throws Exception
     */
    public static byte[] des3EncodeECB(byte[] key, byte[] data)
            throws Exception {

        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");

        cipher.init(Cipher.ENCRYPT_MODE, deskey);
        byte[] bOut = cipher.doFinal(data);

        return bOut;
    }

    /**
     * ECB????,???IV
     * @param key ???
     * @param data Base64?????????
     * @return ????
     * @throws Exception
     */
    public static byte[] ees3DecodeECB(byte[] key, byte[] data)
            throws Exception {

        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");

        cipher.init(Cipher.DECRYPT_MODE, deskey);

        byte[] bOut = cipher.doFinal(data);

        return bOut;
    }
    //??????????
	public static String getDesEncode(String str){
		String result = "";
		try {
			//??????????????????byte
			byte[] data= str.getBytes("UTF-8");
			
			//??byte????????????3DES "desede" + "/ECB/PKCS5Padding"????key????
			byte[] temp = des3EncodeECB(key,data);
			
			//???????????????BASE64??????????
			result = new BASE64Encoder().encode(temp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
