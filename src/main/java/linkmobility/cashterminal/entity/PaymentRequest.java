package linkmobility.cashterminal.entity;

import java.math.BigDecimal;
import java.util.Currency;

import javax.validation.Valid;
//import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @author Danail Petrov
 * Represents payment request entity. 
 *  
 * {
	  "receiverBankId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
	  "amounth": 0,
	  "currency": "string",
	  "phoneNumber": "string",
	  "pin": "string",
	  "redirectUrl": "string",
	  "description": "string",
	  "orderId": "string"
	}
 */
public class PaymentRequest {
	
	@NotBlank(message="Receiver Bank ID cannot be empty")
	String receiverBankId;	
	
	@Positive(message="Amount value must be positive")
	BigDecimal amounth;	
	
	Currency currency;	
	
	@NotBlank(message="Please enter phone number")  
	String phoneNumber;
		
	@NotBlank(message = "Please enter your pin")
	String pin;
	
	String redirectUrl = "dummyValue";
	
	String description = "dummyValue";
	
	String orderId = "dummyValue";;	
	
	public String getReceiverBankId() {
		return receiverBankId;
	}

	public void setReceiverBankId(String receiverBankId) {
		this.receiverBankId = receiverBankId;
	}

	public BigDecimal getAmounth() {
		return amounth;
	}

	public void setAmounth(BigDecimal amounth) {
		this.amounth = amounth;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String toString() {
		
		return "receiverBankId: " + receiverBankId + " phoneNumber: " + phoneNumber + 
				"amounth: " + amounth.toString() + "currency: " + currency.toString() + 
				"pin: " + pin;
	}
}
