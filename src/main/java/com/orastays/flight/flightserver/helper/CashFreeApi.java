package com.orastays.flight.flightserver.helper;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.orastays.flight.flightserver.dao.BookingVsPaymentDAO;
import com.orastays.flight.flightserver.entity.BookingEntity;
import com.orastays.flight.flightserver.entity.BookingVsPaymentEntity;
import com.orastays.flight.flightserver.exceptions.FormExceptions;
import com.orastays.flight.flightserver.model.BookingModel;
import com.orastays.flight.flightserver.model.PaymentModel;
import com.orastays.flight.flightserver.model.cashfree.RefundModel;

@Component
@RefreshScope
public class CashFreeApi {
	private static final Logger logger = LogManager.getLogger(CashFreeApi.class);

	@Autowired
	protected RestTemplate restTemplate;

	@Autowired
	protected MessageUtil messageUtil;

	@Value("${appId}")
	private String appId;

	@Value("${secretKey}")
	private String secretKey;

	@Value("${createOrderUrl}")
	private String createOrderUrl;

	@Value("${returnUrl}")
	private String returnUrl;

	@Value("${notifyUrl}")
	private String notifyUrl;

	@Value("${initiateRefundUrl}")
	private String initiateRefundUrl;

	@Autowired
	protected BookingVsPaymentDAO bookingVsPaymentDAO;

	public PaymentModel getPaymentLink(BookingModel bm, BookingEntity be, BookingVsPaymentEntity bookingVsPaymentEntity)
			throws FormExceptions {
		if (logger.isInfoEnabled()) {
			logger.info("getPaymentLink -- Start");
		}

		Map<String, Exception> exceptions = new LinkedHashMap<>();

		// generate payment link
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("appId", appId);
		map.add("secretKey", secretKey);
		map.add("orderId", bookingVsPaymentEntity.getOrderId());
		map.add("orderAmount", bookingVsPaymentEntity.getOrderAmount());
		//map.add("orderCurrency", bm.getFormOfPayment().getCurrency());
		map.add("customerEmail", bm.getUserInfo().getCustomerEmail());
		map.add("customerName", bm.getUserInfo().getCustomerName());
		map.add("customerPhone", bm.getUserInfo().getCustomerPhone());
		map.add("returnUrl", returnUrl);
		map.add("notifyUrl", notifyUrl);

		ResponseEntity<PaymentModel> response;
		HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(map, headers);
		try {
			response = restTemplate.exchange(createOrderUrl, HttpMethod.POST, request, PaymentModel.class);
			if (response.getStatusCode() == HttpStatus.OK) {
				if (response.getBody().getStatus().equalsIgnoreCase(FlightConstant.CASHFREE_ERROR)) {
					exceptions.put(messageUtil.getBundle("cashfreecreateorder.error.code"),
							new Exception(messageUtil.getBundle("cashfreecreateorder.error.message")));
					throw new FormExceptions(exceptions);
				} else if (response.getBody().getStatus().equalsIgnoreCase(FlightConstant.CASHFREE_OK)) {
					try {
						bookingVsPaymentDAO.save(bookingVsPaymentEntity);
						return response.getBody();
					} catch (Exception e) {
						e.printStackTrace();
						exceptions.put(messageUtil.getBundle("bookingdb.error.code"),
								new Exception(messageUtil.getBundle("bookingdb.error.message")));
						throw new FormExceptions(exceptions);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			exceptions.put(messageUtil.getBundle("cashfreecreateorder.error.code"),
					new Exception(messageUtil.getBundle("cashfreecreateorder.error.message")));
			throw new FormExceptions(exceptions);
		}

		if (logger.isInfoEnabled()) {
			logger.info("getPaymentLink -- End");
		}

		return null;
	}

	/*	 
	 * This method has to be called
	 * from a service 
	 * which initiate refund
	 * passing referenceid
	 * of the successful transaction 
	*/
	public RefundModel initiateRefund(BookingEntity be, BookingVsPaymentEntity bookingVsPaymentEntity, String refundAmount, String refundNote)
			throws FormExceptions {
		if (logger.isInfoEnabled()) {
			logger.info("initiateRefund -- Start");
		}

		Map<String, Exception> exceptions = new LinkedHashMap<>();

		// generate payment link
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("appId", appId);
		map.add("secretKey", secretKey);
		map.add("referenceId", bookingVsPaymentEntity.getReferenceId());
		map.add("refundAmount", refundAmount); // method which can return calculated refund amount
		map.add("refundNote", refundNote);

		ResponseEntity<RefundModel> response;
		HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(map, headers);
		try {
			response = restTemplate.exchange(initiateRefundUrl, HttpMethod.POST, request, RefundModel.class);
			if (response.getStatusCode() == HttpStatus.OK) {
				if (response.getBody().getStatus().equalsIgnoreCase(FlightConstant.CASHFREE_ERROR)) {
					exceptions.put(messageUtil.getBundle("cashfreerefund.error.code"),
							new Exception(messageUtil.getBundle("cashfreerefund.error.message")));
					throw new FormExceptions(exceptions);
				} else if (response.getBody().getStatus().equalsIgnoreCase(FlightConstant.CASHFREE_OK)) {
					try {
						//bookingVsPaymentDAO.save(bookingVsPaymentEntity);
						
						//save refund details into db
						
						return response.getBody();
					} catch (Exception e) {
						e.printStackTrace();
						exceptions.put(messageUtil.getBundle("bookingdb.error.code"), //change error code and message
								new Exception(messageUtil.getBundle("bookingdb.error.message")));
						throw new FormExceptions(exceptions);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			exceptions.put(messageUtil.getBundle("cashfreecreateorder.error.code"),
					new Exception(messageUtil.getBundle("cashfreecreateorder.error.message")));
			throw new FormExceptions(exceptions);
		}

		if (logger.isInfoEnabled()) {
			logger.info("initiateRefund -- End");
		}

		return null;
	}

}
