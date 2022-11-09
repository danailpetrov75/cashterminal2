package linkmobility.cashterminal.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.context.annotation.ScopedProxyMode;

import linkmobility.cashterminal.controller.HomeController;
import linkmobility.cashterminal.entity.AccessTokenResult;
import linkmobility.cashterminal.entity.PaymentRequest;
import linkmobility.cashterminal.entity.PaymentRequestResult;
import linkmobility.cashterminal.entity.PaymentStatusResult;
import linkmobility.cashterminal.entity.Service;

/**
 * 
 * @author Danail Petrov
 * 
 * Implements the LinkPay service provider API interface
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PaymentProcessor implements Payable {

	private final static Logger LOGGER = Logger.getLogger(HomeController.class.getName());
			
	@Value("${linkpay.subscriptionKey}")
	private String subscriptionKey;
	
	@Value("${linkpay.client_secret}")
	private String client_secret;

	@Value("${linkpay.servicesURL}")
	private String servicesURL;
	
	@Value("${linkpay.paymentURL}")
	private String paymentURL;
	
	@Value("${linkpay.accessTokenURL}")
	private String accessTokenURL;
	
	@Value("${linkpay.paymentStatusURL}")
	private String paymentStatusURL;

	RestTemplate restTemplate = new RestTemplate();

	@Override
	public List<Service> getAvailableService()
			throws JsonProcessingException, JSONException, RestClientException	{
	
		String response = restTemplate.getForObject(servicesURL, String.class);		
		
		LOGGER.info("Availble serivces service response value: \n" + response.toString());

		JSONArray jsonArray = new JSONArray(response);

		ObjectMapper objectMapper = new ObjectMapper();

		List<Service> availableServices = new ArrayList<Service>();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject json = jsonArray.getJSONObject(i);
			Service service = objectMapper.readValue(json.toString(), Service.class);
			LOGGER.info(service.toString());
			availableServices.add(service);
		}

		return availableServices;
	}

	@Override
	public PaymentStatusResult getPaymentStatus(String paymentId, AccessTokenResult accessTokenResult) 
			throws JsonProcessingException, RestClientException {	
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.TEXT_PLAIN));
		headers.setContentType(MediaType.TEXT_PLAIN);
		headers.add("X-LinkPay-Access-Token", accessTokenResult.getAccessToken());

		HttpEntity<String> request = new HttpEntity<String>(headers);	

		Map<String, String> vars = Collections.singletonMap("paymentId", paymentId.trim() );	
		
		ResponseEntity<String> responseObj  = restTemplate.exchange(paymentStatusURL, HttpMethod.GET, request, String.class, vars);
		String response = responseObj.getBody();
		
		LOGGER.info("Payment status service response value:  \n" + response);
		
		ObjectMapper objectMapper = new ObjectMapper();
		PaymentStatusResult paymentStatusResult = objectMapper.readValue(response, PaymentStatusResult.class);
		
		return paymentStatusResult;		
	}

	@Override
	public PaymentRequestResult processPayment(PaymentRequest paymentRequest, AccessTokenResult accessTokenResult)
			throws JsonProcessingException,  UnsupportedEncodingException, RestClientException {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.TEXT_PLAIN));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("X-LinkPay-Access-Token", accessTokenResult.getAccessToken());	

		ObjectMapper objectMapper = new ObjectMapper();
		String jsonRequest = objectMapper.writeValueAsString(paymentRequest);

		LOGGER.info("Process payment service request value:  \n" + jsonRequest);

		HttpEntity<String> request = new HttpEntity<String>(jsonRequest, headers);

		String response = restTemplate.postForObject(paymentURL, request, String.class);

		// Decode from UTF-16 String - returned response is not printable string and
		// creating object form json string is impossible directly
		byte[] utfString = response.getBytes("UTF-16");
		response = new String(utfString, "UTF-8");
		response = response.replaceAll("\\p{C}", "");
		response = response.replaceAll("[^\\x00-\\x7F]", "");
		response = response.replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", "");

		LOGGER.info("Process payment service response value: \n" + response);

		PaymentRequestResult paymentRequestResult = objectMapper.readValue(response, PaymentRequestResult.class);

		return paymentRequestResult;
	}

	@Override
	public AccessTokenResult getAccessToken() 
			throws JsonProcessingException, RestClientException {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);
		headers.add("client_secret", client_secret);
		headers.add("Ocp-Apim-Subscription-Key", subscriptionKey);

		HttpEntity request = new HttpEntity(headers);

		ResponseEntity<String> response = restTemplate.postForEntity(accessTokenURL, request, String.class);

		String responseBody = response.getBody();

		LOGGER.info("Access token response value: \n" + responseBody);

		ObjectMapper objectMapper = new ObjectMapper();
		AccessTokenResult accessTokenResult = objectMapper.readValue(responseBody, AccessTokenResult.class);

		return accessTokenResult;
	}
}
