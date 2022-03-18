package com.microservicea.service.impl;

import com.microservicea.dto.ResponseDollarExchangeRateDto;
import com.microservicea.exception.BusinessException;
import com.microservicea.rest.OlindaServiceRest;
import com.microservicea.service.DollarExchangeRateService;
import org.apache.http.HttpStatus;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import static com.microservicea.utils.DateUtils.businessDayCheck;
import static com.microservicea.utils.DateUtils.getFormattedDateForApi;
import static com.microservicea.utils.DateUtils.getLocalDate;
import static com.microservicea.utils.DateUtils.getVerifyAndFormattedDateLastDayForApi;

@ApplicationScoped
public class DollarExchangeRateServiceImpl implements DollarExchangeRateService {

    @Inject
    @RestClient
    OlindaServiceRest olindaServiceRest;

    @Override
    public ResponseDollarExchangeRateDto findQuoteByPeriod(String initialDate) throws BusinessException {
        LocalDate date = getLocalDate(initialDate);
        if(!businessDayCheck(date)){
            throw new BusinessException("Invalid date", HttpStatus.SC_BAD_REQUEST);
        }
        return olindaServiceRest.findQuoteByPeriod(getVerifyAndFormattedDateLastDayForApi(date.plusDays(-1L)),
                getFormattedDateForApi(date));
    }
}
