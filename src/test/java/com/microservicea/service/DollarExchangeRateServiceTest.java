package com.microservicea.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservicea.dto.ResponseDollarExchangeRateDto;
import com.microservicea.exception.BusinessException;
import com.microservicea.rest.OlindaServiceRest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;
import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@QuarkusTest
public class DollarExchangeRateServiceTest {

    @InjectMock
    @RestClient
    OlindaServiceRest olindaServiceRest;

    @Inject
    ObjectMapper mapper;

    @Inject
            DollarExchangeRateService dollarExchangeRateService;

    ResponseDollarExchangeRateDto mockResponseDollarExchangeRateDto =
            ResponseDollarExchangeRateDto
                    .builder()
                    .value(List.of(ResponseDollarExchangeRateDto.ExchangeRateValues
                            .builder()
                            .dataTimeQuote("16/03/2022")
                            .build()))
                    .build();



    @Test
    void findQuoteByPeriodTest() throws IOException {
        ResponseDollarExchangeRateDto responseDollarExchangeRateDto = mapper.readValue(
                new File("src/test/resource/OlindaRestRest.json"),
                ResponseDollarExchangeRateDto.class);

        when(olindaServiceRest.findQuoteByPeriod(anyString(), anyString())).thenReturn(responseDollarExchangeRateDto);


        assertThat(assertDoesNotThrow(() ->
                        dollarExchangeRateService.findQuoteByPeriod("08/09/2021").toString()),
                is((responseDollarExchangeRateDto.toString())));

    }


    @Test
    void findQuoteByPeriodBusinessExceptionTest() throws IOException {
        ResponseDollarExchangeRateDto responseDollarExchangeRateDto = mapper.readValue(
                new File("src/test/resource/OlindaRestRest.json"),
                ResponseDollarExchangeRateDto.class);

        when(olindaServiceRest.findQuoteByPeriod(anyString(), anyString())).thenReturn(responseDollarExchangeRateDto);
        assertThrows(BusinessException.class, () -> dollarExchangeRateService.findQuoteByPeriod("13/03/2022"), "Invalid date");

    }

    @Test
    void findQuoteByPeriodHolidaysBusinessExceptionTest() throws IOException {
        ResponseDollarExchangeRateDto responseDollarExchangeRateDto = mapper.readValue(
                new File("src/test/resource/OlindaRestRest.json"),
                ResponseDollarExchangeRateDto.class);

        when(olindaServiceRest.findQuoteByPeriod(anyString(), anyString())).thenReturn(responseDollarExchangeRateDto);
        assertThrows(BusinessException.class, () -> dollarExchangeRateService.findQuoteByPeriod("07/09/2021"), "Invalid date");

    }

    @Test
    void findQuoteByPeriodWeekendBusinessExceptionTest() throws IOException {
        ResponseDollarExchangeRateDto responseDollarExchangeRateDto = mapper.readValue(
                new File("src/test/resource/OlindaRestRest.json"),
                ResponseDollarExchangeRateDto.class);

        when(olindaServiceRest.findQuoteByPeriod(anyString(), anyString())).thenReturn(responseDollarExchangeRateDto);
        assertThrows(BusinessException.class, () -> dollarExchangeRateService.findQuoteByPeriod("12/03/2022"), "Invalid date");

    }


}
