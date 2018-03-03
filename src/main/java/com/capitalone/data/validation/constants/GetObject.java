package com.capitalone.data.validation.constants;

import com.capitalone.data.validation.dao.ValidationDAO;
import com.capitalone.data.validation.dao.ValidationDAOImpl;
import com.capitalone.data.validation.service.ValidationService;
import com.capitalone.data.validation.service.ValidationServiceImpl;
import com.capitalone.data.validation.util.ReportUtil;

public final class GetObject {

	private static GetObject getObject;
	private static final ReportUtil reportUtil = new ReportUtil();
	private static final ValidationDAO validationDAO = new ValidationDAOImpl();
	private static final ValidationService validationService = new ValidationServiceImpl();

	private GetObject() {
	}

	public static GetObject getObject() {
		if (getObject == null) {
			getObject = new GetObject();
		}
		return getObject;
	}

	public ReportUtil getReportUtilObject() {
		return reportUtil;
	}

	public ValidationDAO getValidationDAOObject() {
		return validationDAO;
	}

	public ValidationService getValidationService() {
		return validationService;
	}

}
