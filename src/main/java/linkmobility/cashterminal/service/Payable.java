package linkmobility.cashterminal.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.json.JSONException;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonProcessingException;
import linkmobility.cashterminal.entity.AccessTokenResult;
import linkmobility.cashterminal.entity.PaymentRequest;
import linkmobility.cashterminal.entity.PaymentRequestResult;
import linkmobility.cashterminal.entity.PaymentStatusResult;
import linkmobility.cashterminal.entity.Service;

/**
 * 
 * @author Danail Petrov
 *
 * Basic interface for payment provider operations definition
 * 
 */
public interface Payable {
	
	AccessTokenResult getAccessToken() 
			throws JsonProcessingException, RestClientException;			 

	List<Service> getAvailableService() 
			throws JsonProcessingException, JSONException, RestClientException;
	
	PaymentStatusResult getPaymentStatus(String paymentId, AccessTokenResult accessTokenResult) 
			throws JsonProcessingException, RestClientException;
	
	PaymentRequestResult processPayment(PaymentRequest paymentRequest, AccessTokenResult accessTokenResult) throws JsonProcessingException,  UnsupportedEncodingException, RestClientException;
}
