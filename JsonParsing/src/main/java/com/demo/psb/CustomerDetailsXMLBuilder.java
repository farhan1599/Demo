package com.conneqt.psb.XMLBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsXMLBuilder {

	public  String buildXMLRequest(String accountNumber) {
    	String requestUUID = "IVRAcct" +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy")) +
                "_" +
                System.currentTimeMillis(); // ensures uniqueness even within the same second

        String messageDateTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS"));

        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<FIXML xsi:schemaLocation=\"http://www.finacle.com/fixml executeFinacleScript.xsd\" \n" +
            "       xmlns=\"http://www.finacle.com/fixml\" \n" +
            "       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
            "    <Header>\n" +
            "        <RequestHeader>\n" +
            "            <MessageKey>\n" +
            "                <RequestUUID>" + requestUUID + "</RequestUUID>\n" +
            "                <ServiceRequestId>executeFinacleScript</ServiceRequestId>\n" +
            "                <ServiceRequestVersion>10.2</ServiceRequestVersion>\n" +
            "                <ChannelId>IVR</ChannelId>\n" +
            "                <LanguageId></LanguageId>\n" +
            "            </MessageKey>\n" +
            "            <RequestMessageInfo>\n" +
            "                <BankId></BankId>\n" +
            "                <TimeZone></TimeZone>\n" +
            "                <EntityId></EntityId>\n" +
            "                <EntityType></EntityType>\n" +
            "                <ArmCorrelationId></ArmCorrelationId>\n" +
            "                <MessageDateTime>" + messageDateTime + "</MessageDateTime>\n" +
            "            </RequestMessageInfo>\n" +
            "            <Security>\n" +
            "                <Token>\n" +
            "                    <PasswordToken>\n" +
            "                        <UserId></UserId>\n" +
            "                        <Password></Password>\n" +
            "                    </PasswordToken>\n" +
            "                </Token>\n" +
            "                <FICertToken></FICertToken>\n" +
            "                <RealUserLoginSessionId></RealUserLoginSessionId>\n" +
            "                <RealUser></RealUser>\n" +
            "                <RealUserPwd></RealUserPwd>\n" +
            "                <SSOTransferToken></SSOTransferToken>\n" +
            "            </Security>\n" +
            "        </RequestHeader>\n" +
            "    </Header>\n" +
            "    <Body>\n" +
            "        <executeFinacleScriptRequest>\n" +
            "            <ExecuteFinacleScriptInputVO>\n" +
            "                <requestId>FI_IVR_ACCT_Inq.scr</requestId>\n" +
            "            </ExecuteFinacleScriptInputVO>\n" +
            "            <executeFinacleScript_CustomData>\n" +
            "                <account>" + accountNumber + "</account>\n" +
            "            </executeFinacleScript_CustomData>\n" +
            "        </executeFinacleScriptRequest>\n" +
            "    </Body>\n" +
            "</FIXML>";
    }
}
