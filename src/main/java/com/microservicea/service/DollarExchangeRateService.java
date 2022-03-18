package com.microservicea.service;

import com.microservicea.dto.ResponseDollarExchangeRateDto;
import com.microservicea.exception.BusinessException;

public interface DollarExchangeRateService  {
    ResponseDollarExchangeRateDto findQuoteByPeriod(String data) throws BusinessException;
}
