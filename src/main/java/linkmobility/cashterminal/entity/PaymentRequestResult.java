package linkmobility.cashterminal.entity;

/**
 * 
 * @author Danail Petrov
 * Represents the result of the payment operation
 * 
 * {
  "success": true,
  "errors": [
    "string"
  ],
  "paymentId": "3fa85f64-5717-4562-b3fc-2c963f66afa6"
}
 */
public class PaymentRequestResult {

	Boolean success;
	
	String[] errors;
	
	String paymentId;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String[] getErrors() {
		return errors;
	}

	public void setErrors(String[] errors) {
		this.errors = errors;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	@Override
	public String toString() {
		
		return "PaymentRequestResult [success=" + success + ", errors=" + errors + ", paymentId=" + paymentId + "]";
	}
	
}
