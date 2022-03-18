package com.microservicea.resource;

import com.microservicea.dto.ResponseDollarExchangeRateDto;
import com.microservicea.exception.BusinessException;
import com.microservicea.service.DollarExchangeRateService;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static io.restassured.RestAssured.given;
import static org.mockito.Mockito.when;

@QuarkusTest
@TestHTTPEndpoint(DollarExchangeRateResource.class)
class DollarExchangeRateResourceTest {

    @InjectMock
    DollarExchangeRateService dollarExchangeRateService;



    @Test
    void findQuoteByPeriodTest() {
        when(dollarExchangeRateService.findQuoteByPeriod(Mockito.anyString()))
                .thenReturn(new ResponseDollarExchangeRateDto());

        given()
                .contentType(ContentType.JSON)
          .when().get()
          .then()
             .statusCode(200);
    }

    @Test
    void findQuoteByPeriod400Test() {
        when(dollarExchangeRateService.findQuoteByPeriod(Mockito.anyString()))
                .thenThrow(new BusinessException("Invalid date", HttpStatus.SC_BAD_REQUEST));

        given()
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                .statusCode(400);
    }

}