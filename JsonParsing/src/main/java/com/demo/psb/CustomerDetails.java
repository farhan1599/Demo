package com.digitide.psb.finacleService;

import java.io.ByteArrayInputStream;
import java.net.http.HttpClient;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.LoggerFactory;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import reactor.core.publisher.Mono;

@Service
public class CustomerDetails {
	private final static Logger logger = LoggerFactory.getLogger(CustomerDetails.class);


	private static final String FINACLE_URL = Middleware_Pojo.getFINACLE_URL();

	public String getCustomerDetailsData(String accNumber) {

		CustomerDetailsXMLBuilder xmlBuilder = new CustomerDetailsXMLBuilder();
		String requestPayload = xmlBuilder.buildXMLRequest(accNumber);
		logger.info("CustomerDetailsService API Request : " + requestPayload);
		String responseXML = fetchCustomerDetails(requestPayload);
		logger.info("CustomerDetailsService API Result : " + responseXML);
		String balance = getCustomerDetails(responseXML);
		logger.info("Customer Details API Response: "+balance);
		return balance;
	}

	private String fetchCustomerDetails(String requestXML) {
		
		try {
			 SslContext sslContext = SslContextBuilder.forClient()
		                .trustManager(InsecureTrustManagerFactory.INSTANCE)
		                .build();

		        // **Step 2: Create a WebClient with disabled SSL verification**
		        HttpClient httpClient = HttpClient.create().secure(ssl -> ssl.sslContext(sslContext));

		        WebClient client = WebClient.builder()
		                .clientConnector(new ReactorClientHttpConnector(httpClient))
		                .build();
			Mono<String> responseMono = client.post().uri(FINACLE_URL).header("Content-Type", "application/xml")
					.bodyValue(requestXML).retrieve().bodyToMono(String.class);
			String block = responseMono.block();
			
			return block; // Blocking call to get the response (convert to reactive if needed)
		} catch (Exception e) {
			logger.error("Error fetching customer details: " + e.getMessage());
			return "E|";
		}
	}

	public static String getCustomerDetails(String xmlResponse) {
		boolean isSuccess = false;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new ByteArrayInputStream(xmlResponse.getBytes()));

			NodeList statusNodes = document.getElementsByTagName("Status");
			

			for (int i = 0; i < statusNodes.getLength(); i++) {
				if (statusNodes.item(i).getTextContent().equalsIgnoreCase("SUCCESS")) {
					logger.info("Inside true");
					isSuccess = true;
					break;
				}
			}
logger.info("is success value: "+isSuccess);
			if (isSuccess) {
				String custName = getNodeValue(document, "AccountName");
				
				String RegisteredMobileNumber = getNodeValue(document, "RegisteredMobileNumber");
				String DOB = getNodeValue(document, "DOB");
				String ZoneCode = getNodeValue(document, "ZoneCode");
				String ZoneName = getNodeValue(document, "ZoneName");
				String BranchCode = getNodeValue(document, "BranchCode");
				String BranchName = getNodeValue(document, "BranchName");
				String AccountBalance = getNodeValue(document, "AccountBalance");
				String AccountStatus = getNodeValue(document, "AccountStatus");
				String AccountModeOfOperation = getNodeValue(document, "AccountModeOfOperation");
				String AccountIFSC = getNodeValue(document, "AccountIFSC");
				String AccountChequebookAllowed = getNodeValue(document, "AccountChequebookAllowed");
				String PendingChequebookRequest = getNodeValue(document, "PendingChequebookRequest");
				String Last30DaysChkBookAppiedFlg = getNodeValue(document, "Last30DaysChkBookAppiedFlg");
				String schmType = getNodeValue(document, "schmType");
				String schmCode = getNodeValue(document, "schmCode");


				return custName + "~" +
			       RegisteredMobileNumber + "~" + DOB + "~" + ZoneCode + "~" +
			       ZoneName + "~" + BranchCode + "~" + BranchName + "~" +
			       AccountBalance + "~" + AccountStatus + "~" + AccountModeOfOperation + "~" +
			       AccountIFSC + "~" + AccountChequebookAllowed + "~" +
			       PendingChequebookRequest + "~" + Last30DaysChkBookAppiedFlg + "~" +
			       schmType + "~" + schmCode;
			} else {
				String errorCode = getNodeValue(document, "ErrorCode");
				String errorDesc = getNodeValue(document, "ErrorDesc");

				return errorCode + "~" + errorDesc ;
			}

		} catch (Exception e) {
			logger.error("CustomerDetailsService API Error Occurred: " + e.getMessage());
			return "E|";
		}
	}

	private static String getNodeValue(Document document, String tagName) {
		NodeList nodes = document.getElementsByTagName(tagName);
		return (nodes.getLength() > 0) ? nodes.item(0).getTextContent() : "N/A";
	}

	
}
