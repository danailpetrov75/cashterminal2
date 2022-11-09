package linkmobility.cashterminal.entity;

/**
 * 
 * @author Danail Petrov
 * Represents current status of the payment
 * {
  "code": 0,
  "description": "string"
   }
 */
public class PaymentStatusResult {

	String code;
	
	String description;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
}
