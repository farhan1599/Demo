package com.digitide.psb.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.digitide.psb.finacleService.CustomerDetails;
import com.digitide.psb.model.SignOnRequest;
import com.digitide.psb.pojo.Middleware_Pojo;
import com.digitide.psb.repo.SignOnRequestRepository;

@Service
public class CustomerDetailsService {
	 private Logger logger = LoggerFactory.getLogger(CustomerDetailsService.class);
    @Autowired
    private SignOnRequestRepository sessionRepository;
    
    @Autowired
    private CustomerDetails getCustomerDetails;
    
    
    
    

    private static final String ENTITY_ID = Middleware_Pojo.getExpectedEntityId();
    private static final int MAX_REQ_REF_LENGTH = 10;
    private static final String SUCCESS_RESULT = "S";
    private static final String FAILURE_RESULT = "F";
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    String apiId = "";
    public ResponseEntity<String> handleLeadPush(JSONObject inputJson){
        try {
            // Extract JSON fields
            JSONObject header = inputJson.getJSONObject("digitideCRMPSB:ReqCustomerDetails").getJSONObject("header");
            JSONObject request = inputJson.getJSONObject("digitideCRMPSB:ReqCustomerDetails").getJSONObject("request");

            String tStmp = header.optString("tStmp");
            String org = header.optString("org");
        apiId = header.optString("apiId");
            String sessionId = header.optString("sessionId");
            String channelId = header.optString("channelId");
            String rqstRefNo = request.optString("rqstRefNo");
            String encryptedentityId = request.optString("entityId");
            String accessToken = request.optString("accessToken");
            String encryptedAccountNumber = request.optString("accountNumber");
            
            String entityId = Encryption_Decryption.decrypt(encryptedentityId, Middleware_Pojo.getKey(), Middleware_Pojo.getIvkey());

            String accountNumber = Encryption_Decryption.decrypt(encryptedAccountNumber, Middleware_Pojo.getKey(), Middleware_Pojo.getIvkey());

            
            
            
            // Validate fields
            Optional<String> validationError = validateFields(tStmp, org, apiId, sessionId, channelId, rqstRefNo, entityId, accessToken);
            if (validationError.isPresent()) {
            	 return 
                         ResponseEntity
         		        .status(HttpStatus.NO_CONTENT)
         		        .body(createErrorResponse(validationError.get(), rqstRefNo, sessionId));
            }

            // Check session and access token in DB
            Optional<SignOnRequest> sessionOpt = sessionRepository.findBySessionId(sessionId);
            
            SignOnRequest session = sessionOpt.get();
            if (!session.getAccessToken().equals(accessToken)) {
            	 return ResponseEntity
         		        .status(HttpStatus.UNAUTHORIZED)
         		        .body(createErrorResponse("UNAUTH00001", rqstRefNo, sessionId));
            }
         // Check access token expiration
            LocalDateTime tokenGeneratedAt = session.getCreatedAt(); // Ensure this field exists in your entity
            LocalDateTime currentTime = LocalDateTime.now();

            Duration tokenAge = Duration.between(tokenGeneratedAt, currentTime);
            if (tokenAge.toMinutes() > 10) {
            	return ResponseEntity
            	        .status(HttpStatus.UNAUTHORIZED)
            	        .body(createErrorResponse("UNAUTH00003", rqstRefNo, sessionId));
            }

            // Check entity ID
            if (!ENTITY_ID.equals(entityId)) {
            	return ResponseEntity
            	        .status(HttpStatus.UNAUTHORIZED)
            	        .body(createErrorResponse("UNAUTH00002", rqstRefNo, sessionId));
            }
           
           
          
       String customerDetailsData = getCustomerDetails.getCustomerDetailsData(accountNumber);

            if (customerDetailsData.startsWith("W")) {
                String[] accounts = customerDetailsData.split("\\~");
            	
                // Handle error case here if needed
                String errorResponse = createErrorResponseFinacle(accounts[0], rqstRefNo, sessionId,accounts[1]);
                return ResponseEntity
            	        .status(HttpStatus.OK)
            	        .body(errorResponse);
            }
            
            
            if (customerDetailsData != null && !customerDetailsData.isEmpty()) {
                String[] accounts = customerDetailsData.split("\\~");
                JSONObject response = new JSONObject();
                JSONObject header1 = createHeader(sessionId, false); 

                JSONObject responseBody = new JSONObject();
                responseBody.put("rqstRefNo", rqstRefNo);
                responseBody.put("responseCode", "200");
                responseBody.put("result", SUCCESS_RESULT);
                responseBody.put("AccountName", Encryption_Decryption.encrypt(accounts[0], Middleware_Pojo.getKey(), Middleware_Pojo.getIvkey()));
                responseBody.put("RegisteredMobileNumber", Encryption_Decryption.encrypt(accounts[1], Middleware_Pojo.getKey(), Middleware_Pojo.getIvkey()));
                responseBody.put("DOB", Encryption_Decryption.encrypt(accounts[2], Middleware_Pojo.getKey(), Middleware_Pojo.getIvkey()));
                responseBody.put("ZoneCode", Encryption_Decryption.encrypt(accounts[3], Middleware_Pojo.getKey(), Middleware_Pojo.getIvkey()));
                responseBody.put("ZoneName", Encryption_Decryption.encrypt(accounts[4], Middleware_Pojo.getKey(), Middleware_Pojo.getIvkey()));
                responseBody.put("BranchCode", Encryption_Decryption.encrypt(accounts[5], Middleware_Pojo.getKey(), Middleware_Pojo.getIvkey()));
                responseBody.put("BranchName", Encryption_Decryption.encrypt(accounts[6], Middleware_Pojo.getKey(), Middleware_Pojo.getIvkey()));
                responseBody.put("AccountBalance", Encryption_Decryption.encrypt(accounts[7], Middleware_Pojo.getKey(), Middleware_Pojo.getIvkey()));
                responseBody.put("AccountStatus", Encryption_Decryption.encrypt(accounts[8], Middleware_Pojo.getKey(), Middleware_Pojo.getIvkey()));
                responseBody.put("AccountModeOfOperation", Encryption_Decryption.encrypt(accounts[9], Middleware_Pojo.getKey(), Middleware_Pojo.getIvkey()));
                responseBody.put("AccountIFSC", Encryption_Decryption.encrypt(accounts[10], Middleware_Pojo.getKey(), Middleware_Pojo.getIvkey()));
                responseBody.put("AccountChequebookAllowed", Encryption_Decryption.encrypt(accounts[11], Middleware_Pojo.getKey(), Middleware_Pojo.getIvkey()));
                responseBody.put("PendingChequebookRequest", Encryption_Decryption.encrypt(accounts[12], Middleware_Pojo.getKey(), Middleware_Pojo.getIvkey()));
                responseBody.put("Last30DaysChkBookAppiedFlg", Encryption_Decryption.encrypt(accounts[13], Middleware_Pojo.getKey(), Middleware_Pojo.getIvkey()));
                responseBody.put("schmType", Encryption_Decryption.encrypt(accounts[14], Middleware_Pojo.getKey(), Middleware_Pojo.getIvkey()));
                responseBody.put("schmCode", Encryption_Decryption.encrypt(accounts[15], Middleware_Pojo.getKey(), Middleware_Pojo.getIvkey()));               
                
                response.put("digitideCRMPSB:ResCustomerDetails", new JSONObject().put("header", header1).put("response", responseBody));
                return ResponseEntity.ok(response.toString());
            }else {
            	return ResponseEntity
              	        .status(HttpStatus.OK)
              	        .body(createErrorResponse("NULVLE00007", rqstRefNo, sessionId));
            }
            

        } catch (Exception e) {
            logger.error("Error: "+e.getMessage());
            return ResponseEntity
            	    .status(HttpStatus.INTERNAL_SERVER_ERROR)
            	    .body(createErrorResponse("TCHERR00001", null, null));
        }
    }

    private Optional<String> validateFields(String tStmp, String org, String apiId, String sessionId, String channelId, String rqstRefNo, String entityId, String accessToken) {
        if (tStmp == null || tStmp.isEmpty()) return Optional.of("NULVLE00001");
        if (org == null || org.isEmpty()) return Optional.of("NULVLE00002");
        if (apiId == null || apiId.isEmpty()) return Optional.of("NULVLE00003");
        if (sessionId == null || sessionId.isEmpty()) return Optional.of("NULVLE00004");
        if (sessionId.length() > 20) return Optional.of("MXLHEE00004");
        if (rqstRefNo.length() > MAX_REQ_REF_LENGTH) return Optional.of("MXLHEE00005");
        if (channelId == null || channelId.isEmpty()) return Optional.of("NULVLE00005");
        if (rqstRefNo == null || rqstRefNo.isEmpty()) return Optional.of("NULVLE00006");
        if (entityId == null || entityId.isEmpty()) return Optional.of("NULVLE00009");
        if (accessToken == null || accessToken.isEmpty()) return Optional.of("NULVLE00011");
        return Optional.empty();
    }

   

    private String createErrorResponse(String errorCode, String rqstRefNo, String sessionId) {
        JSONObject response = new JSONObject();
        JSONObject header = createHeader(sessionId, true);

        JSONObject responseBody = new JSONObject();
        responseBody.put("rqstRefNo", rqstRefNo);
        responseBody.put("result", FAILURE_RESULT);

        JSONArray errors = new JSONArray();
        errors.put(new JSONObject().put("errCode", errorCode).put("errDesc", getErrorDescription(errorCode)));
        responseBody.put("errors", errors);
        responseBody.put("msg", "");

        response.put("digitideCRMPSB:ResCustomerDetails", new JSONObject().put("header", header).put("response", responseBody));
        return response.toString();
    }
    
    private String createErrorResponseFinacle(String errorCode, String rqstRefNo, String sessionId,String errordesc) {
        JSONObject response = new JSONObject();
        JSONObject header = createHeader(sessionId, true);

        JSONObject responseBody = new JSONObject();
        responseBody.put("rqstRefNo", rqstRefNo);
        responseBody.put("result", FAILURE_RESULT);

        JSONArray errors = new JSONArray();
        errors.put(new JSONObject().put("errCode", errorCode).put("errDesc", errordesc));
        responseBody.put("errors", errors);
        responseBody.put("msg", "");

        response.put("digitideCRMPSB:ResCustomerDetails", new JSONObject().put("header", header).put("response", responseBody));
        return response.toString();
    }

    private JSONObject createHeader(String sessionId,boolean isError) {
        JSONObject header = new JSONObject();
        header.put("tStmp", LocalDateTime.now().format(TIMESTAMP_FORMATTER));
        header.put("org", "DIGITIDE");
        header.put("apiId", apiId);
        if (isError) {
            header.put("SRNumber", "");
        } else {
            int number = 100_000_000 + new Random().nextInt(900_000_000);
            header.put("SRNumber", "SR" + number);
        }

        header.put("sessionId", sessionId);
        header.put("channelId", "CRM");
        return header;
    }

    private String getErrorDescription(String errorCode) {
        Map<String, String> errorMapping = new HashMap<>();
        errorMapping.put("NULVLE00001", "tStmp is Null or Empty");
        errorMapping.put("NULVLE00002", "org is Null or Empty");
        errorMapping.put("NULVLE00003", "apiId is Null or Empty");
        errorMapping.put("NULVLE00004", "sessionId is Null or Empty");
        errorMapping.put("NULVLE00005", "channelId is Null or Empty");
        errorMapping.put("NULVLE00006", "rqstRefNo is Null or Empty");
        errorMapping.put("MXLHEE00004", "sessionId max length exceeded");
        errorMapping.put("MXLHEE00005", "RequestRef max length exceeded");
        errorMapping.put("NDFREQ00001", "Invalid Session ID");
        errorMapping.put("UNAUTH00001", "Unauthorized - Invalid AcessToken");       
        errorMapping.put("UNAUTH00002", "Unauthorized - Entity ID mismatch");
        errorMapping.put("UNAUTH00003", "Unauthorized - Access token expired");
        errorMapping.put("TCHERR00001", "Technical Error - Application Error");
        errorMapping.put("NULVLE00007", "No Customer Details Found");
       

        return errorMapping.getOrDefault(errorCode, "Unknown Error");
    }
}
