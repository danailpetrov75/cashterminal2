package linkmobility.cashterminal.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.core.JsonProcessingException;
import linkmobility.cashterminal.entity.AccessTokenResult;
import linkmobility.cashterminal.entity.PaymentRequest;
import linkmobility.cashterminal.entity.PaymentRequestResult;
import linkmobility.cashterminal.entity.PaymentStatusResult;
import linkmobility.cashterminal.entity.Service;
import linkmobility.cashterminal.service.PaymentProcessor;

/**
 * 
 * @author Danail Petrov
 * 
 *         Processes UI request for the application
 */
@Controller
@Validated
public class HomeController {

	private final static Logger LOGGER = Logger.getLogger(HomeController.class.getName());

	@Autowired
	PaymentProcessor paymentProcessor;

	/*
	 * Renders cash terminal home page
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(HttpServletResponse response) {

		return new ModelAndView("home");
	}

	/*
	 * Renders payment status form
	 */
	@RequestMapping(value = "/paymentstatus", method = RequestMethod.GET)
	public ModelAndView showPaymentStatus(HttpServletResponse response) {

		return new ModelAndView("paymentstatus");
	}
	/*
	 * Retrieves the payment status
	 */
	@RequestMapping(value = "/checkpaymentstatus", method = RequestMethod.GET)
	public RedirectView checkPaymentStatus(HttpServletResponse response,
			@RequestParam(value = "paymentid", required = true) @NotBlank(message = "Please enter payment Id") String paymentId,
			RedirectAttributes redirectAttributes) {
	
		RedirectView modelAndView = new RedirectView("/confirmstatus", true);
		RedirectView errorView = new RedirectView("/error", true);
		
		if (paymentId == null || paymentId.isEmpty()) {			
		
			redirectAttributes.addFlashAttribute("error", "Please enter payment Id");
			
			return errorView;
			
		} else {

			try {
				AccessTokenResult accessTokenResult = paymentProcessor.getAccessToken();
				PaymentStatusResult paymentStatusResult = paymentProcessor.getPaymentStatus(paymentId,
						accessTokenResult);
				
				redirectAttributes.addFlashAttribute("paymentStatusResult", paymentStatusResult);
				
				return modelAndView;

			} catch (JsonProcessingException | RestClientException e) {
			
				redirectAttributes.addFlashAttribute("error", e.getMessage());
				LOGGER.log(Level.ALL, "Error", e.getMessage());

				return errorView;
			}
		}
	}
	/*
	 * Renders cash terminal payment form
	 */
	@RequestMapping(value = "/terminal", method = RequestMethod.GET)
	public ModelAndView showTerminal(HttpServletResponse response) {

		try {
			// Retrieve all available services for which user can commit payment
			List<Service> availableService = paymentProcessor.getAvailableService();

			// Render the payment form
			ModelAndView modelAndView = new ModelAndView("cashterminal");
			modelAndView.addObject("availableService", availableService);
			modelAndView.addObject("payment", new PaymentRequest());

			return modelAndView;

		} catch (JsonProcessingException | JSONException | RestClientException e) {

			ModelAndView error = new ModelAndView("error");
			error.addObject("error", e.getMessage());
			LOGGER.log(Level.ALL, "Error", e.getMessage());

			return error;
		}
	}

	/*
	 * Enqeues payment data for commit
	 */
	@RequestMapping(value = "/commit", method = RequestMethod.POST)
	public RedirectView commitPayment(HttpServletResponse response,
			@Valid @ModelAttribute("payment") PaymentRequest payment, BindingResult result, ModelMap model,
			RedirectAttributes redirectAttributes) {

		RedirectView errorView = new RedirectView("/error", true);
		RedirectView redirectView = new RedirectView("/confirm", true);

		if (result.hasErrors()) {

			LOGGER.info("INPUT ERROR count: " + String.valueOf(result.getFieldErrorCount()));

			List<FieldError> feList = result.getFieldErrors();
			FieldError fePhoneNumber = result.getFieldError("phoneNumber");
			FieldError fePIN = result.getFieldError("pin");
			FieldError feAmount = result.getFieldError("amount");

			redirectAttributes.addFlashAttribute("fieldErrors", feList);
			redirectAttributes.addFlashAttribute("fePhoneNumber", fePhoneNumber);
			redirectAttributes.addFlashAttribute("fePIN", fePIN);
			redirectAttributes.addFlashAttribute("feAmount", feAmount);
			
			return redirectView;

		} else {

			try {
				AccessTokenResult accessTokenResult = paymentProcessor.getAccessToken();
				LOGGER.info("Access token value: " + accessTokenResult.getAccessToken());

				PaymentRequestResult paymentRequestResult = paymentProcessor.processPayment(payment, accessTokenResult);
				LOGGER.info("Payment request result: " + paymentRequestResult.toString());

				redirectAttributes.addFlashAttribute("payment", payment);
				redirectAttributes.addFlashAttribute("paymentRequestResult", paymentRequestResult);

				return redirectView;

			} catch (JsonProcessingException | RestClientException | UnsupportedEncodingException e) {

				LOGGER.info("ERROR : " + e.getMessage());
				redirectAttributes.addFlashAttribute("error", e.getMessage());
				LOGGER.log(Level.ALL, "Error", e.getMessage());

				return errorView;
			}
		}
	}
	/*
	 * Renders payment confirmation page
	 */
	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	public ModelAndView showConfirmationPage(HttpServletResponse response,
			@ModelAttribute("payment") PaymentRequest payment, ModelMap model) throws IOException {

		return new ModelAndView("confirmation");
	}
	/*
	 * Renders payment status confirmation page
	 */
	@RequestMapping(value = "/confirmstatus", method = RequestMethod.GET)
	public ModelAndView showPaymentStatusConfirmationPage(HttpServletResponse response,
			 ModelMap model) throws IOException {

		return new ModelAndView("confirmstatus");
	}
	/*
	 * Renders error page
	 */
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public ModelAndView showErrorPage(HttpServletResponse response, ModelMap model) throws IOException {

		return new ModelAndView("error");
	}
}
