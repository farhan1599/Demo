package com.digitide.psb.controller;

import java.util.Date;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitide.psb.service.AEPSBlockService;
import com.digitide.psb.service.AccountDetailsService;
import com.digitide.psb.service.AccountFreezeService;
import com.digitide.psb.service.AccountTransactionPaginationService;
import com.digitide.psb.service.BalanceEnquiryService;
import com.digitide.psb.service.CaaAccountEnqService;
import com.digitide.psb.service.ChecqueStatusEnqService;
import com.digitide.psb.service.ChequeBookDeliveryService;
import com.digitide.psb.service.ChequeBookRequestService;
import com.digitide.psb.service.CorporateCIFEnqService;
import com.digitide.psb.service.CustomerDetailsService;
import com.digitide.psb.service.DeRegisterService;
import com.digitide.psb.service.DebitCardBlockService;
import com.digitide.psb.service.EmailDetailsService;
import com.digitide.psb.service.EnableDisbaleService;
import com.digitide.psb.service.FullAccountStatementService;
import com.digitide.psb.service.GetCardDetailsService;
import com.digitide.psb.service.GreenPinService;
import com.digitide.psb.service.LienDetailsService;
import com.digitide.psb.service.LoanAccountEnqService;
import com.digitide.psb.service.LoanDeamandEnqService;
import com.digitide.psb.service.LockerdetailsService;
import com.digitide.psb.service.MiniStaments;
import com.digitide.psb.service.MobileDetailsService;
import com.digitide.psb.service.MulChequeStatusService;
import com.digitide.psb.service.NomineeDetailsService;
import com.digitide.psb.service.RMNCheking;
import com.digitide.psb.service.RepaymentSchedduleService;
import com.digitide.psb.service.RetailCIFEnqService;
import com.digitide.psb.service.RetailService;
import com.digitide.psb.service.SBAccInqService;
import com.digitide.psb.service.SetResetLimitService;
import com.digitide.psb.service.SignOnService;
import com.digitide.psb.service.SignOutService;
import com.digitide.psb.service.StopMulChecqueService;
import com.digitide.psb.service.StopSInglechkService;
import com.digitide.psb.service.TDAFDRAccEnqService;
import com.digitide.psb.service.TDSCertificateemailService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1")
public class PSB_Controller {
	private Logger logger = LoggerFactory.getLogger(PSB_Controller.class);

	@Autowired
	private SignOnService signOnService;

	@Autowired
	private SignOutService signOutService;

	@Autowired
	private RMNCheking getRMNCheking;

	@Autowired
	private MiniStaments getMiniStaments;

	@Autowired
	private AccountDetailsService accountDetailsService;

	@Autowired
	private EmailDetailsService emailDetailsService;

	@Autowired
	private MobileDetailsService mobileDetailsService;

	@Autowired
	private ChequeBookRequestService getChequeBookRequestService;

	@Autowired
	private ChequeBookDeliveryService getChequeBookDeliveryService;

	@Autowired
	private BalanceEnquiryService getBalanceEnquiryService;

	@Autowired
	private AccountFreezeService getAccountFreezeService;

	@Autowired
	private CustomerDetailsService getCustomerDetailsService;

	@Autowired
	private AccountTransactionPaginationService transactionService;

	@Autowired
	private LienDetailsService lienService;

	@Autowired
	private NomineeDetailsService nomineeService;

	@Autowired
	private FullAccountStatementService accountService;

	@Autowired
	private RetailService retailService;

	@Autowired
	private SetResetLimitService setResetLimitService;

	@Autowired
	private GetCardDetailsService getCardDetails;

	@Autowired
	private TDSCertificateemailService tdsCertificateemailService;

	@Autowired
	private EnableDisbaleService enableDisbaleService;

	@Autowired
	private StopMulChecqueService stopMulChecqueService;

	@Autowired
	private DebitCardBlockService cardblockService;

	@Autowired
	private CaaAccountEnqService caaAccountEnqService;

	@Autowired
	private LoanAccountEnqService loanAccountEnqService;

	@Autowired
	private LoanDeamandEnqService loanDeamandEnqService;

	@Autowired
	private LockerdetailsService lockerdetailsService;

	@Autowired
	private AEPSBlockService aePSBlockService;

	@Autowired
	private SBAccInqService sbAccInqService;

	@Autowired
	private TDAFDRAccEnqService tdaFDRAccEnqService;

	@Autowired
	private RetailCIFEnqService retailCIFEnqService;

	@Autowired
	private CorporateCIFEnqService corporateCIFEnqService;

	@Autowired
	private RepaymentSchedduleService repaymentSchedduleService;

	@Autowired
	private StopSInglechkService stopSInglechkService;

	@Autowired
	private MulChequeStatusService mulChequeStatusService;

	@Autowired
	private GreenPinService greenPinService;

	@Autowired
	private ChecqueStatusEnqService checqueStatusEnqService;

	@Autowired
	private DeRegisterService deRegisterService;

//	@Autowired
//	private CKYCDetailsService ckycService;

	@Operation(summary = "Sign On", description = "Handles the sign-on process for BOI")
	@PostMapping("/signon")
	public ResponseEntity<String> handleSignOn(@RequestBody String inputJson) {
		logger.info("PSB MIddleware UAT Signon API Start at: " + new Date());
		try {
			JSONObject input = new JSONObject(inputJson);
			String response = signOnService.handleSignOn(input);
			logger.info("PSB MIddleware UAT Signon API Response at: " + response);
			logger.info("PSB MIddleware UAT Signon API End at: " + new Date());
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			logger.error("Error 500: " + e.getMessage());
			logger.info("PSB MIddleware UAT Signon API End at: " + new Date());
			return ResponseEntity.status(500).body("{\"message\": \"Internal Server Error\"}");
		}
	}

	@Operation(summary = "Sign Out", description = "Handles the sign-out process for BOI")
	@PostMapping("/signout")
	public ResponseEntity<String> handleSignOut(@RequestBody String inputJson) {
		logger.info("PSB MIddleware UAT SignOut API Start at: " + new Date());
		try {
			JSONObject input = new JSONObject(inputJson);
			ResponseEntity<String> response = signOutService.handleLeadPush(input);
			logger.info("PSB MIddleware UAT SignOut API Response at: " + response);
			logger.info("PSB MIddleware UAT SignOut API End at: " + new Date());
			return response;
		} catch (Exception e) {
			logger.error("Error 500: " + e.getMessage());
			logger.info("PSB MIddleware UAT SignOut API End at: " + new Date());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
		}
	}

	@Operation(summary = "Check RMN", description = "Check RMN PSB")
	@PostMapping("/check-rmn")
	public ResponseEntity<String> getRMN(@RequestBody String inputJson) {
		try {
			logger.info("PSB MIddleware UAT Check RMN API Start at: " + new Date());
			logger.info("PSB MIddleware UAT Check RMN API Request at: " + inputJson);
			JSONObject input = new JSONObject(inputJson);
			ResponseEntity<String> response = getRMNCheking.handleLeadPush(input);
			logger.info("PSB MIddleware UAT Check RMN API Response at: " + response);
			logger.info("PSB MIddleware UAT Check RMN API End at: " + new Date());
			return response;
		} catch (Exception e) {
			logger.error("Error 500: " + e.getMessage());
			logger.info("PSB MIddleware UAT Check RMN API End at: " + new Date());
			return ResponseEntity.status(500).body("{\"message\": \"Internal Server Error\"}");
		}
	}

	@Operation(summary = "Customer Details", description = "CustomerDetails PSB")
	@PostMapping("/customer-details")
	public ResponseEntity<String> getCustomerDetails(@RequestBody String inputJson) {
		try {
			logger.info("PSB MIddleware UAT Customer Details API Start at: " + new Date());
			logger.info("PSB MIddleware UAT Customer Details API Request at: " + inputJson);
			JSONObject input = new JSONObject(inputJson);
			ResponseEntity<String> response = getCustomerDetailsService.handleLeadPush(input);
			logger.info("PSB MIddleware UAT Customer Details API Response at: " + response);
			logger.info("PSB MIddleware UAT Customer Details API End at: " + new Date());
			return response;
		} catch (Exception e) {
			logger.error("Error 500: " + e.getMessage());
			logger.info("PSB MIddleware UAT Customer Details API End at: " + new Date());
			return ResponseEntity.status(500).body("{\"message\": \"Internal Server Error\"}");
		}
	}

	@Operation(summary = "Enable/Disable Service", description = "Enables or disables a customer feature via PSB")
	@PostMapping("/enable-disable")
	public ResponseEntity<String> enableDisable(@RequestBody String inputJson) {
		try {
			logger.info("PSB Middleware UAT Enable/Disable API Start at: " + new Date());
			logger.info("PSB Middleware UAT Enable/Disable API Request: " + inputJson);

			JSONObject input = new JSONObject(inputJson);
			ResponseEntity<String> response = enableDisbaleService.handleLeadPush(input);

			logger.info("PSB Middleware UAT Enable/Disable API Response: " + response);
			logger.info("PSB Middleware UAT Enable/Disable API End at: " + new Date());

			return response;
		} catch (Exception e) {
			logger.error("Error 500: " + e.getMessage(), e);
			logger.info("PSB Middleware UAT Enable/Disable API End at: " + new Date());
			return ResponseEntity.status(500).body("{\"message\": \"Internal Server Error\"}");
		}
	}

	@Operation(summary = "Set/Reset Transaction Limit", description = "Sets or resets the transaction limit for a customer via PSB")
	@PostMapping("/set-reset-limit")
	public ResponseEntity<String> setResetLimit(@RequestBody String inputJson) {
		try {
			logger.info("PSB Middleware UAT Set/Reset Limit API Start at: " + new Date());
			logger.info("PSB Middleware UAT Set/Reset Limit API Request: " + inputJson);

			JSONObject input = new JSONObject(inputJson);
			ResponseEntity<String> response = setResetLimitService.handleLeadPush(input);

			logger.info("PSB Middleware UAT Set/Reset Limit API Response: " + response);
			logger.info("PSB Middleware UAT Set/Reset Limit API End at: " + new Date());

			return response;
		} catch (Exception e) {
			logger.error("Error 500: " + e.getMessage(), e);
			logger.info("PSB Middleware UAT Set/Reset Limit API End at: " + new Date());
			return ResponseEntity.status(500).body("{\"message\": \"Internal Server Error\"}");
		}
	}

	@Operation(summary = "Balance Enquiry", description = "Balance Enquiry PSB")
	@PostMapping("/balance-enquiry")
	public ResponseEntity<String> getBalanceEnquiry(@RequestBody String inputJson) {
		try {
			logger.info("PSB MIddleware UAT Balance Enquiry API Start at: " + new Date());
			logger.info("PSB MIddleware UAT Balance Enquiry API Request at: " + inputJson);
			JSONObject input = new JSONObject(inputJson);
			ResponseEntity<String> response = getBalanceEnquiryService.handleLeadPush(input);
			logger.info("PSB MIddleware UAT Balance Enquiry API Response at: " + response);
			logger.info("PSB MIddleware UAT Balance Enquiry API End at: " + new Date());
			return response;
		} catch (Exception e) {
			logger.error("Error 500: " + e.getMessage());
			logger.info("PSB MIddleware UAT Balance Enquiry API End at: " + new Date());
			return ResponseEntity.status(500).body("{\"message\": \"Internal Server Error\"}");
		}
	}

	@Operation(summary = "Mini Staments", description = "Mini Staments PSB")
	@PostMapping("/mini-statement")
	public ResponseEntity<String> getMiniStaments(@RequestBody String inputJson) {
		try {
			logger.info("PSB MIddleware UAT Mini Staments API Start at: " + new Date());
			logger.info("PSB MIddleware UAT Mini Staments API Request at: " + inputJson);
			JSONObject input = new JSONObject(inputJson);
			ResponseEntity<String> response = getMiniStaments.handleLeadPush(input);
			logger.info("PSB MIddleware UAT Mini Staments API Response at: " + response);
			logger.info("PSB MIddleware UAT Mini Staments API End at: " + new Date());
			return response;
		} catch (Exception e) {
			logger.error("Error 500: " + e.getMessage());
			logger.info("PSB MIddleware UAT Mini Staments API End at: " + new Date());
			return ResponseEntity.status(500).body("{\"message\": \"Internal Server Error\"}");
		}
	}

	@Operation(summary = "Account Freeze", description = "Account Freeze PSB")
	@PostMapping("/account-freeze")
	public ResponseEntity<String> getAccountFreeze(@RequestBody String inputJson) {
		try {
			logger.info("PSB MIddleware UAT Account Freeze API Start at: " + new Date());
			logger.info("PSB MIddleware UAT Account Freeze API Request at: " + inputJson);
			JSONObject input = new JSONObject(inputJson);
			ResponseEntity<String> response = getAccountFreezeService.handleLeadPush(input);
			logger.info("PSB MIddleware UAT Account Freeze API Response at: " + response);
			logger.info("PSB MIddleware UAT Account Freeze API End at: " + new Date());
			return response;
		} catch (Exception e) {
			logger.error("Error 500: " + e.getMessage());
			logger.info("PSB MIddleware UAT Account Freeze API End at: " + new Date());
			return ResponseEntity.status(500).body("{\"message\": \"Internal Server Error\"}");
		}
	}

	@Operation(summary = "Cheque Book Request", description = "Cheque Book Request PSB")
	@PostMapping("/cheque-apply")
	public ResponseEntity<String> getChequeBookRequest(@RequestBody String inputJson) {
		try {
			logger.info("PSB MIddleware UAT Cheque Book Request API Start at: " + new Date());
			logger.info("PSB MIddleware UAT Cheque Book Request API Request at: " + inputJson);
			JSONObject input = new JSONObject(inputJson);
			ResponseEntity<String> response = getChequeBookRequestService.handleLeadPush(input);
			logger.info("PSB MIddleware UAT Cheque Book Request API Response at: " + response);
			logger.info("PSB MIddleware UAT Cheque Book Request API End at: " + new Date());
			return response;
		} catch (Exception e) {
			logger.error("Error 500: " + e.getMessage());
			logger.info("PSB MIddleware UAT Cheque Book Request API End at: " + new Date());
			return ResponseEntity.status(500).body("{\"message\": \"Internal Server Error\"}");
		}
	}

	@Operation(summary = "Cheque Book Delivery", description = "Cheque Book Delivery PSB")
	@PostMapping("/cheque-delivery-status")
	public ResponseEntity<String> getChequeBookDelivery(@RequestBody String inputJson) {
		try {
			logger.info("PSB MIddleware UAT Cheque Book Delivery API Start at: " + new Date());
			logger.info("PSB MIddleware UAT Cheque Book Delivery API Request at: " + inputJson);
			JSONObject input = new JSONObject(inputJson);
			ResponseEntity<String> response = getChequeBookDeliveryService.handleLeadPush(input);
			logger.info("PSB MIddleware UAT Cheque Book Delivery API Response at: " + response);
			logger.info("PSB MIddleware UAT Cheque Book Delivery API End at: " + new Date());
			return response;
		} catch (Exception e) {
			logger.error("Error 500: " + e.getMessage());
			logger.info("PSB MIddleware UAT Cheque Book Delivery API End at: " + new Date());
			return ResponseEntity.status(500).body("{\"message\": \"Internal Server Error\"}");
		}
	}

	@Operation(summary = "Account Details", description = "AccountDetails PSB")
	@PostMapping("/account-details")
	public ResponseEntity<String> getAccountDetails(@RequestBody String inputJson) {
		try {
			logger.info("PSB MIddleware UAT Account Details API Start at: " + new Date());
			logger.info("PSB MIddleware UAT Account Details API Request at: " + inputJson);
			JSONObject input = new JSONObject(inputJson);
			ResponseEntity<String> response = accountDetailsService.handleLeadPush(input);
			logger.info("PSB MIddleware UAT Account Details API Response at: " + response);
			logger.info("PSB MIddleware UAT Account Details API End at: " + new Date());
			return response;
		} catch (Exception e) {
			logger.error("Error 500: " + e.getMessage());
			logger.info("PSB MIddleware UAT Account Details API End at: " + new Date());
			return ResponseEntity.status(500).body("{\"message\": \"Internal Server Error\"}");
		}
	}

	@Operation(summary = "Mobile Details", description = "MobileDetails PSB")
	@PostMapping("/mobile-details")
	public ResponseEntity<String> getMobileDetails(@RequestBody String inputJson) {
		try {
			logger.info("PSB MIddleware UAT Mobile Details API Start at: " + new Date());
			logger.info("PSB MIddleware UAT Mobile Details API Request at: " + inputJson);
			JSONObject input = new JSONObject(inputJson);
			ResponseEntity<String> response = mobileDetailsService.handleLeadPush(input);
			logger.info("PSB MIddleware UAT Mobile Details API Response at: " + response);
			logger.info("PSB MIddleware UAT Mobile Details API End at: " + new Date());
			return response;
		} catch (Exception e) {
			logger.error("Error 500: " + e.getMessage());
			logger.info("PSB MIddleware UAT Mobile Details API End at: " + new Date());
			return ResponseEntity.status(500).body("{\"message\": \"Internal Server Error\"}");
		}
	}

	@Operation(summary = "Email Details", description = "EmailDetails PSB")
	@PostMapping("/email-details")
	public ResponseEntity<String> getEmailDetails(@RequestBody String inputJson) {
		try {
			logger.info("PSB MIddleware UAT Email Details API Start at: " + new Date());
			logger.info("PSB MIddleware UAT Email Details API Request at: " + inputJson);
			JSONObject input = new JSONObject(inputJson);
			ResponseEntity<String> response = emailDetailsService.handleLeadPush(input);
			logger.info("PSB MIddleware UAT Email Details API Response at: " + response);
			logger.info("PSB MIddleware UAT Email Details API End at: " + new Date());
			return response;
		} catch (Exception e) {
			logger.error("Error 500: " + e.getMessage());
			logger.info("PSB MIddleware UAT Email Details API End at: " + new Date());
			return ResponseEntity.status(500).body("{\"message\": \"Internal Server Error\"}");
		}
	}

	@Operation(summary = "Transaction Details", description = "Transaction Details PSB")
	@PostMapping("/transaction-details")
	public ResponseEntity<String> getTransactions(@RequestBody String inputJson) {
		try {
			logger.info("PSB MIddleware UAT Transaction Details API Start at: " + new Date());
			logger.info("PSB MIddleware UAT Transaction Details API Request at: " + inputJson);
			JSONObject input = new JSONObject(inputJson);
			ResponseEntity<String> response = transactionService.handleLeadPush(input);
			logger.info("PSB MIddleware UAT Transaction Details API Response at: " + response);
			logger.info("PSB MIddleware UAT Transaction Details API End at: " + new Date());
			return response;
		} catch (Exception e) {
			logger.error("Error 500: " + e.getMessage());
			logger.info("PSB MIddleware UAT Transaction Details API End at: " + new Date());
			return ResponseEntity.status(500).body("{\"message\": \"Internal Server Error\"}");
		}
	}

	@PostMapping("/nominee-details")
	public ResponseEntity<String> getNominee(@RequestBody String inputJson) {
		try {
			logger.info("PSB MIddleware UAT Nominee Details API Start at: " + new Date());
			logger.info("PSB MIddleware UAT Nominee Details API Request at: " + inputJson);
			JSONObject input = new JSONObject(inputJson);
			ResponseEntity<String> response = nomineeService.handleLeadPush(input);
			logger.info("PSB MIddleware UAT Nominee Details API Response at: " + response);
			logger.info("PSB MIddleware UAT Nominee Details API End at: " + new Date());
			return response;
		} catch (Exception e) {
			logger.error("Error 500: " + e.getMessage());
			logger.info("PSB MIddleware UAT Nominee Details API End at: " + new Date());
			return ResponseEntity.status(500).body("{\"message\": \"Internal Server Error\"}");
		}
	}

	@PostMapping("/card-details")
	public ResponseEntity<String> getCardDetails(@RequestBody String inputJson) {
		try {
			logger.info("PSB MIddleware UAT DebitCard Details API Start at: " + new Date());
			logger.info("PSB MIddleware UAT DebitCard Details API Request at: " + inputJson);
			JSONObject input = new JSONObject(inputJson);
			ResponseEntity<String> response = getCardDetails.handleLeadPush(input);
			logger.info("PSB MIddleware UAT DebitCard Details API Response at: " + response);
			logger.info("PSB MIddleware UAT DebitCard Details API End at: " + new Date());
			return response;
		} catch (Exception e) {
			logger.error("Error 500: " + e.getMessage());
			logger.info("PSB MIddleware UAT DebitCard Details API End at: " + new Date());
			return ResponseEntity.status(500).body("{\"message\": \"Internal Server Error\"}");
		}
	}

	@PostMapping("/lien-details")
	public ResponseEntity<String> getLien(@RequestBody String inputJson) {
		try {
			logger.info("PSB MIddleware UAT Lien Details API Start at: " + new Date());
			logger.info("PSB MIddleware UAT Lien Details API Request at: " + inputJson);
			JSONObject input = new JSONObject(inputJson);
			ResponseEntity<String> response = lienService.handleLeadPush(input);
			logger.info("PSB MIddleware UAT Lien Details API Response at: " + response);
			logger.info("PSB MIddleware UAT Lien Details API End at: " + new Date());
			return response;
		} catch (Exception e) {
			logger.error("Error 500: " + e.getMessage());
			logger.info("PSB MIddleware UAT v Details API End at: " + new Date());
			return ResponseEntity.status(500).body("{\"message\": \"Internal Server Error\"}");
		}
	}

	@PostMapping("/account-statemnt")
	public ResponseEntity<String> getAccountStatemnt(@RequestBody String inputJson) {
		try {
			logger.info("PSB MIddleware UAT Account Statemnt API Start at: " + new Date());
			logger.info("PSB MIddleware UAT Account Statemnt API Request at: " + inputJson);
			JSONObject input = new JSONObject(inputJson);
			ResponseEntity<String> response = accountService.handleLeadPush(input);
			logger.info("PSB MIddleware UAT Account Statemnt API Response at: " + response);
			logger.info("PSB MIddleware UAT Account Statemnt API End at: " + new Date());
			return response;
		} catch (Exception e) {
			logger.error("Error 500: " + e.getMessage());
			logger.info("PSB MIddleware UAT Account Statemnt API End at: " + new Date());
			return ResponseEntity.status(500).body("{\"message\": \"Internal Server Error\"}");
		}
	}

	@PostMapping("/retail-details")
	public ResponseEntity<String> getRetail(@RequestBody String inputJson) {
		try {
			logger.info("PSB MIddleware UAT Retail API Start at: " + new Date());
			logger.info("PSB MIddleware UAT Retail API Request at: " + inputJson);
			JSONObject input = new JSONObject(inputJson);
			ResponseEntity<String> response = retailService.handleLeadPush(input);
			logger.info("PSB MIddleware UAT Retail API Response at: " + response);
			logger.info("PSB MIddleware UAT Retail API End at: " + new Date());
			return response;
		} catch (Exception e) {
			logger.error("Error 500: " + e.getMessage());
			logger.info("PSB MIddleware UAT Retail API End at: " + new Date());
			return ResponseEntity.status(500).body("{\"message\": \"Internal Server Error\"}");
		}
	}

	@PostMapping("/debit-card-block")
	public ResponseEntity<String> getcardBlock(@RequestBody String inputJson) {
		try {
			logger.info("PSB MIddleware UAT Card Block API Start at: " + new Date());
			logger.info("PSB MIddleware UAT Card Block API Request at: " + inputJson);
			JSONObject input = new JSONObject(inputJson);
			ResponseEntity<String> response = cardblockService.handleLeadPush(input);
			logger.info("PSB MIddleware UAT Card Block API Response at: " + response);
			logger.info("PSB MIddleware UAT Card Block API End at: " + new Date());
			return response;
		} catch (Exception e) {
			logger.error("Error 500: " + e.getMessage());
			logger.info("PSB MIddleware UAT Card Blocks API End at: " + new Date());
			return ResponseEntity.status(500).body("{\"message\": \"Internal Server Error\"}");
		}
	}

	@PostMapping("/stop-mul-cheques")
	public ResponseEntity<String> stopMultipleCheque(@RequestBody String inputJson) {
		try {
			logger.info("PSB MIddleware UAT stop MultipleChequek API Start at: " + new Date());
			logger.info("PSB MIddleware UAT stop MultipleCheque API Request at: " + inputJson);
			JSONObject input = new JSONObject(inputJson);
			ResponseEntity<String> response = stopMulChecqueService.handleLeadPush(input);
			logger.info("PSB MIddleware UAT stop MultipleCheque API Response at: " + response);
			logger.info("PSB MIddleware UAT stop MultipleCheque API End at: " + new Date());
			return response;
		} catch (Exception e) {
			logger.error("Error 500: " + e.getMessage());
			logger.info("PSB MIddleware UAT stop MultipleCheque API End at: " + new Date());
			return ResponseEntity.status(500).body("{\"message\": \"Internal Server Error\"}");
		}
	}

	@PostMapping("/tds-certificate-mail")
	public ResponseEntity<String> tdsCertificateEmail(@RequestBody String inputJson) {
		try {
			logger.info("PSB MIddleware UAT tds-certificate API Start at: " + new Date());
			logger.info("PSB MIddleware UAT tds-certificate API Request at: " + inputJson);
			JSONObject input = new JSONObject(inputJson);
			ResponseEntity<String> response = tdsCertificateemailService.handleLeadPush(input);
			logger.info("PSB MIddleware UAT tds-certificate API Response at: " + response);
			logger.info("PSB MIddleware UAT tds-certificate API End at: " + new Date());
			return response;
		} catch (Exception e) {
			logger.error("Error 500: " + e.getMessage());
			logger.info("PSB MIddleware UAT tds-certificate API End at: " + new Date());
			return ResponseEntity.status(500).body("{\"message\": \"Internal Server Error\"}");
		}
	}

	@PostMapping("/caa-accenq")
	public ResponseEntity<String> caaAccountEnq(@RequestBody String inputJson) {
		try {
			logger.info("PSB MIddleware UAT caaAccountEnq API Start at: " + new Date());
			logger.info("PSB MIddleware UAT caaAccountEnq API Request at: " + inputJson);
			JSONObject input = new JSONObject(inputJson);
			ResponseEntity<String> response = caaAccountEnqService.handleLeadPush(input);
			logger.info("PSB MIddleware UAT caaAccountEnq API Response at: " + response);
			logger.info("PSB MIddleware UAT caaAccountEnq API End at: " + new Date());
			return response;
		} catch (Exception e) {
			logger.error("Error 500: " + e.getMessage());
			logger.info("PSB MIddleware UAT caaAccountEnq API End at: " + new Date());
			return ResponseEntity.status(500).body("{\"message\": \"Internal Server Error\"}");
		}
	}

	@PostMapping("/loan-accenq")
	public ResponseEntity<String> loanAccountEnq(@RequestBody String inputJson) {
		try {
			logger.info("PSB MIddleware UAT loanAccountEnq API Start at: " + new Date());
			logger.info("PSB MIddleware UAT loanAccountEnq API Request at: " + inputJson);
			JSONObject input = new JSONObject(inputJson);
			ResponseEntity<String> response = loanAccountEnqService.handleLeadPush(input);
			logger.info("PSB MIddleware UAT loanAccountEnq API Response at: " + response);
			logger.info("PSB MIddleware UAT loanAccountEnq API End at: " + new Date());
			return response;
		} catch (Exception e) {
			logger.error("Error 500: " + e.getMessage());
			logger.info("PSB MIddleware UAT loanAccountEnq API End at: " + new Date());
			return ResponseEntity.status(500).body("{\"message\": \"Internal Server Error\"}");
		}
	}

	@PostMapping("/loan-demandenq")
	public ResponseEntity<String> loanDeamandEnq(@RequestBody String inputJson) {
		try {
			logger.info("PSB MIddleware UAT loanDeamandEnq API Start at: " + new Date());
			logger.info("PSB MIddleware UAT loanDeamandEnq API Request at: " + inputJson);
			JSONObject input = new JSONObject(inputJson);
			ResponseEntity<String> response = loanDeamandEnqService.handleLeadPush(input);
			logger.info("PSB MIddleware UAT loanDeamandEnq API Response at: " + response);
			logger.info("PSB MIddleware UAT loanDeamandEnq API End at: " + new Date());
			return response;
		} catch (Exception e) {
			logger.error("Error 500: " + e.getMessage());
			logger.info("PSB MIddleware UAT loanDeamandEnqn Enq API End at: " + new Date());
			return ResponseEntity.status(500).body("{\"message\": \"Internal Server Error\"}");
		}
	}

	@PostMapping("/aepsblock")
	public ResponseEntity<String> aepsBlock(@RequestBody String inputJson) {
		try {
			logger.info("PSB MIddleware UAT aepsBlock API Start at: " + new Date());
			logger.info("PSB MIddleware UAT aepsBlock API Request at: " + inputJson);
			JSONObject input = new JSONObject(inputJson);
			ResponseEntity<String> response = aePSBlockService.handleLeadPush(input);
			logger.info("PSB MIddleware UAT aepsBlock API Response at: " + response);
			logger.info("PSB MIddleware UAT aepsBlock API End at: " + new Date());
			return response;
		} catch (Exception e) {
			logger.error("Error 500: " + e.getMessage());
			logger.info("PSB MIddleware UAT aepsBlock Enq API End at: " + new Date());
			return ResponseEntity.status(500).body("{\"message\": \"Internal Server Error\"}");
		}
	}

	@PostMapping("/sbaccountenq")
	public ResponseEntity<String> sbAccountEnq(@RequestBody String inputJson) {
		try {
			logger.info("PSB MIddleware UAT sbAccountEnq API Start at: " + new Date());
			logger.info("PSB MIddleware UAT sbAccountEnq API Request at: " + inputJson);
			JSONObject input = new JSONObject(inputJson);
			ResponseEntity<String> response = sbAccInqService.handleLeadPush(input);
			logger.info("PSB MIddleware UAT sbAccountEnq API Response at: " + response);
			logger.info("PSB MIddleware UAT sbAccountEnq API End at: " + new Date());
			return response;
		} catch (Exception e) {
			logger.error("Error 500: " + e.getMessage());
			logger.info("PSB MIddleware UAT sbAccountEnq Enq API End at: " + new Date());
			return ResponseEntity.status(500).body("{\"message\": \"Internal Server Error\"}");
		}
	}

	@PostMapping("/tdafdraccenq")
	public ResponseEntity<String> tdaFDRAccEnq(@RequestBody String inputJson) {
		try {
			logger.info("PSB MIddleware UAT tdaFDRAccEnq API Start at: " + new Date());
			logger.info("PSB MIddleware UAT tdaFDRAccEnq API Request at: " + inputJson);
			JSONObject input = new JSONObject(inputJson);
			ResponseEntity<String> response = tdaFDRAccEnqService.handleLeadPush(input);
			logger.info("PSB MIddleware UAT tdaFDRAccEnq API Response at: " + response);
			logger.info("PSB MIddleware UAT tdaFDRAccEnq API End at: " + new Date());
			return response;
		} catch (Exception e) {
			logger.error("Error 500: " + e.getMessage());
			logger.info("PSB MIddleware UAT tdaFDRAccEnq Enq API End at: " + new Date());
			return ResponseEntity.status(500).body("{\"message\": \"Internal Server Error\"}");
		}
	}

	@PostMapping("/corpcif")
	public ResponseEntity<String> corpCIFEnq(@RequestBody String inputJson) {
		try {
			logger.info("PSB MIddleware UAT corpCIFEnq API Start at: " + new Date());
			logger.info("PSB MIddleware UAT corpCIFEnq API Request at: " + inputJson);
			JSONObject input = new JSONObject(inputJson);
			ResponseEntity<String> response = corporateCIFEnqService.handleLeadPush(input);
			logger.info("PSB MIddleware UAT corpCIFEnq API Response at: " + response);
			logger.info("PSB MIddleware UAT corpCIFEnq API End at: " + new Date());
			return response;
		} catch (Exception e) {
			logger.error("Error 500: " + e.getMessage());
			logger.info("PSB MIddleware UAT corpCIFEnq Enq API End at: " + new Date());
			return ResponseEntity.status(500).body("{\"message\": \"Internal Server Error\"}");
		}
	}

	@PostMapping("/retailcif")
	public ResponseEntity<String> retailCIFEnq(@RequestBody String inputJson) {
		try {
			logger.info("PSB MIddleware UAT retailCIFEnq API Start at: " + new Date());
			logger.info("PSB MIddleware UAT retailCIFEnq API Request at: " + inputJson);
			JSONObject input = new JSONObject(inputJson);
			ResponseEntity<String> response = retailCIFEnqService.handleLeadPush(input);
			logger.info("PSB MIddleware UAT retailCIFEnq API Response at: " + response);
			logger.info("PSB MIddleware UAT retailCIFEnq API End at: " + new Date());
			return response;
		} catch (Exception e) {
			logger.error("Error 500: " + e.getMessage());
			logger.info("PSB MIddleware UAT retailCIFEnq Enq API End at: " + new Date());
			return ResponseEntity.status(500).body("{\"message\": \"Internal Server Error\"}");
		}
	}

	@PostMapping("/repayment-Sched")
	public ResponseEntity<String> repaymentSched(@RequestBody String inputJson) {
		try {
			logger.info("PSB MIddleware UAT repaymentSched API Start at: " + new Date());
			logger.info("PSB MIddleware UAT repaymentSched API Request at: " + inputJson);
			JSONObject input = new JSONObject(inputJson);
			ResponseEntity<String> response = repaymentSchedduleService.handleLeadPush(input);
			logger.info("PSB MIddleware UAT repaymentSched API Response at: " + response);
			logger.info("PSB MIddleware UAT repaymentSched API End at: " + new Date());
			return response;
		} catch (Exception e) {
			logger.error("Error 500: " + e.getMessage());
			logger.info("PSB MIddleware UAT repaymentSched Enq API End at: " + new Date());
			return ResponseEntity.status(500).body("{\"message\": \"Internal Server Error\"}");
		}
	}

	@PostMapping("/mulcheque-status")
	public ResponseEntity<String> mulChequeStatus(@RequestBody String inputJson) {
		try {
			logger.info("PSB MIddleware UAT mulChequeStatus API Start at: " + new Date());
			logger.info("PSB MIddleware UAT mulChequeStatus API Request at: " + inputJson);
			JSONObject input = new JSONObject(inputJson);
			ResponseEntity<String> response = mulChequeStatusService.handleLeadPush(input);
			logger.info("PSB MIddleware UAT mulChequeStatus API Response at: " + response);
			logger.info("PSB MIddleware UAT mulChequeStatus API End at: " + new Date());
			return response;
		} catch (Exception e) {
			logger.error("Error 500: " + e.getMessage());
			logger.info("PSB MIddleware UAT mulChequeStatus Enq API End at: " + new Date());
			return ResponseEntity.status(500).body("{\"message\": \"Internal Server Error\"}");
		}
	}

	@PostMapping("/stop-single-cheque")
	public ResponseEntity<String> stopSInglechk(@RequestBody String inputJson) {
		try {
			logger.info("PSB MIddleware UAT stopSInglechk API Start at: " + new Date());
			logger.info("PSB MIddleware UAT stopSInglechk API Request at: " + inputJson);
			JSONObject input = new JSONObject(inputJson);
			ResponseEntity<String> response = stopSInglechkService.handleLeadPush(input);
			logger.info("PSB MIddleware UAT stopSInglechk API Response at: " + response);
			logger.info("PSB MIddleware UAT stopSInglechk API End at: " + new Date());
			return response;
		} catch (Exception e) {
			logger.error("Error 500: " + e.getMessage());
			logger.info("PSB MIddleware UAT stopSInglechk Enq API End at: " + new Date());
			return ResponseEntity.status(500).body("{\"message\": \"Internal Server Error\"}");
		}
	}

	@PostMapping("/locker")
	public ResponseEntity<String> lockerDetails(@RequestBody String inputJson) {
		try {
			logger.info("PSB MIddleware UAT lockerDetails API Start at: " + new Date());
			logger.info("PSB MIddleware UAT lockerDetails API Request at: " + inputJson);
			JSONObject input = new JSONObject(inputJson);
			ResponseEntity<String> response = lockerdetailsService.handleLeadPush(input);
			logger.info("PSB MIddleware UAT lockerDetails API Response at: " + response);
			logger.info("PSB MIddleware UAT lockerDetails API End at: " + new Date());
			return response;
		} catch (Exception e) {
			logger.error("Error 500: " + e.getMessage());
			logger.info("PSB MIddleware UAT lockerDetails Enq API End at: " + new Date());
			return ResponseEntity.status(500).body("{\"message\": \"Internal Server Error\"}");
		}
	}

	@PostMapping("/cheque-status")
	public ResponseEntity<String> chequeStatus(@RequestBody String inputJson) {
		try {
			logger.info("PSB MIddleware UAT chequeStatus API Start at: " + new Date());
			logger.info("PSB MIddleware UAT chequeStatus API Request at: " + inputJson);
			JSONObject input = new JSONObject(inputJson);
			ResponseEntity<String> response = checqueStatusEnqService.handleLeadPush(input);
			logger.info("PSB MIddleware UAT chequeStatus API Response at: " + response);
			logger.info("PSB MIddleware UAT chequeStatus API End at: " + new Date());
			return response;
		} catch (Exception e) {
			logger.error("Error 500: " + e.getMessage());
			logger.info("PSB MIddleware UAT chequeStatus Enq API End at: " + new Date());
			return ResponseEntity.status(500).body("{\"message\": \"Internal Server Error\"}");
		}
	}

	@PostMapping("/green-pin")
	public ResponseEntity<String> setGreenPin(@RequestBody String inputJson) {
		try {
			logger.info("PSB MIddleware UAT setGreenPin API Start at: " + new Date());
			logger.info("PSB MIddleware UAT setGreenPin API Request at: " + inputJson);
			JSONObject input = new JSONObject(inputJson);
			ResponseEntity<String> response = greenPinService.handleLeadPush(input);
			logger.info("PSB MIddleware UAT setGreenPin API Response at: " + response);
			logger.info("PSB MIddleware UAT setGreenPin API End at: " + new Date());
			return response;
		} catch (Exception e) {
			logger.error("Error 500: " + e.getMessage());
			logger.info("PSB MIddleware UAT setGreenPin Enq API End at: " + new Date());
			return ResponseEntity.status(500).body("{\"message\": \"Internal Server Error\"}");
		}
	}

//	@PostMapping("/ckyc-details")
//	public ResponseEntity<String> getCKYC(@RequestBody String inputJson) {
//		try {
//			logger.info("PSB MIddleware UAT ckyc Details API Start at: " + new Date());
//			logger.info("PSB MIddleware UAT ckyc Details API Request at: " + inputJson);
//			JSONObject input = new JSONObject(inputJson);
//			ResponseEntity<String> response = ckycService.CKYCDetailsService(input);
//			logger.info("PSB MIddleware UAT ckyc Details API Response at: " + response);
//			logger.info("PSB MIddleware UAT ckyc Details API End at: " + new Date());
//			return response;
//		} catch (Exception e) {
//			logger.error("Error 500: " + e.getMessage());
//			logger.info("PSB MIddleware UAT ckyc Details Enq API End at: " + new Date());
//			return ResponseEntity.status(500).body("{\"message\": \"Internal Server Error\"}");
//		}
//	}  

	@PostMapping("/de-register")
	public ResponseEntity<String> deRegister(@RequestBody String inputJson) {
		try {
			logger.info("PSB Middleware UAT DeRegister API Start at: " + new Date());
			logger.info("PSB Middleware UAT DeRegister API Request: {}", inputJson);

			JSONObject input = new JSONObject(inputJson);
			ResponseEntity<String> response = deRegisterService.handleDeRegisterRequest(input);

			logger.info("PSB Middleware UAT DeRegister API Response: {}", response);
			logger.info("PSB Middleware UAT DeRegister API End at: " + new Date());
			return response;
		} catch (Exception e) {
			logger.error("Error 500 in DeRegister API: {}", e.getMessage(), e);
			logger.info("PSB Middleware UAT DeRegister API End at: " + new Date());
			return ResponseEntity.status(500).body("{\"message\": \"Internal Server Error\"}");
		}
	}

}
