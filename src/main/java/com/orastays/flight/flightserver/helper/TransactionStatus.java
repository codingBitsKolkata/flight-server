package com.orastays.flight.flightserver.helper;

public interface TransactionStatus {
	public static final String SUCCESS = "SUCCESS";
	public static final String FAILED = "FAILED";
	public static final String PENDING = "PENDING";
	public static final String CANCELLED = "CANCELLED";
	public static final String FLAGGED = "FLAGGED";
	public static final String VALIDATION_ERROR = "-";
}
