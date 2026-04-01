package com.digitide.psb.pojo;


public class Middleware_Pojo {
 // private static String MiddleWareIP = "172.19.1.39";
 // private static String MiddleWareIP = "172.19.149.42"; //UAT
  
	//private static String MiddleWareIP = "172.19.1.39"; //DC-Production-OLD
		private static String FINACLE_URL = "https://dcsvrvuatwb001.psb.org.in:45000/FISERVLET/fihttp"; //DC-Production
	//	private static String MiddleWareIP = "172.19.147.188"; //DR-Production
		//private static String MiddleWareIP = "172.19.128.39"; //DR-Production-OLD
		//private static String MiddleWareIP = "172.19.147.188"; //DR-Production
  
  private static String JWT_SECRET = "LhQ8SqHwGHMgB56WzjIfy/UK08O8DGRCfldXj1C68M+jyzc+88iXW4A2DVW0xGuoVybiJi6AYVFn6PaZkxIBlA==";
  private static String CRM_PUBLICKEY = "C:\\PSB\\Publickey.crt";
  private static int JWT_EXPIRATIONMS = 3600000;
  
  private static String JWT_TOKENTYPE = "Bearer";
  
  private static String JWT_INSTANCEURL = "https://mwivruat.psb.co.in:8443/PSB_Middleware_UAT/";
  
  private static final String key = "Ew6p5IWtss1CwJ92kXDtwhHxV85iJjmNhPyZwAjQO2A=";
  
  private static final String ivkey = "MFZ+RcGhSKUeKKtYrp/PTw==";
  
  
  private static final String EXPECTED_ENTITY_ID ="DIGITIDEUAT000001";
  private static final String EXPECTED_KADUVU_SOL ="Q2IkbEA5MDkwfDExNTI3NDE3Mjk0Mjg2ODA=";
  
  private static String DEBIT_CARD_BLOCK_URL = "http://10.30.32.21:443";
	
	 private static String FSS_PUBLICKEY = "C:\\PSB\\FSSPublicKey.pem";
  
  public static String getKey() {
    return key;
  }
  
  public static String getIvkey() {
    return ivkey;
  }
  

  
  public static String getCRM_PUBLICKEY() {
	return CRM_PUBLICKEY;
}

public static void setCRM_PUBLICKEY(String cRM_PUBLICKEY) {
	CRM_PUBLICKEY = cRM_PUBLICKEY;
}

public static String getFINACLE_URL() {
	return FINACLE_URL;
}

public static void setFINACLE_URL(String fINACLE_URL) {
	FINACLE_URL = fINACLE_URL;
}

public static String getJWT_SECRET() {
    return JWT_SECRET;
  }
  
  public static void setJWT_SECRET(String jWT_SECRET) {
    JWT_SECRET = jWT_SECRET;
  }
  
  public static int getJWT_EXPIRATIONMS() {
    return JWT_EXPIRATIONMS;
  }
  
  public static void setJWT_EXPIRATIONMS(int jWT_EXPIRATIONMS) {
    JWT_EXPIRATIONMS = jWT_EXPIRATIONMS;
  }
  
  public static String getJWT_TOKENTYPE() {
    return JWT_TOKENTYPE;
  }
  
  public static void setJWT_TOKENTYPE(String jWT_TOKENTYPE) {
    JWT_TOKENTYPE = jWT_TOKENTYPE;
  }
  
  public static String getJWT_INSTANCEURL() {
    return JWT_INSTANCEURL;
  }
  
  public static void setJWT_INSTANCEURL(String jWT_INSTANCEURL) {
    JWT_INSTANCEURL = jWT_INSTANCEURL;
  }

public static String getExpectedEntityId() {
	return EXPECTED_ENTITY_ID;
}

public static String getExpectedKaduvuSol() {
	return EXPECTED_KADUVU_SOL;
}

public static String getDEBIT_CARD_BLOCK_URL() {
	return DEBIT_CARD_BLOCK_URL;
}

public static void setDEBIT_CARD_BLOCK_URL(String dEBIT_CARD_BLOCK_URL) {
	DEBIT_CARD_BLOCK_URL = dEBIT_CARD_BLOCK_URL;
}

public static String getFSS_PUBLICKEY() {
	return FSS_PUBLICKEY;
}

public static void setFSS_PUBLICKEY(String fSS_PUBLICKEY) {
	FSS_PUBLICKEY = fSS_PUBLICKEY;
}
  
  
}