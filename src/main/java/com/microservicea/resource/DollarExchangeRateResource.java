package com.microservicea.resource;

import com.microservicea.dto.ResponseDollarExchangeRateDto;
import com.microservicea.dto.ResponseError;
import com.microservicea.exception.BusinessException;
import com.microservicea.service.DollarExchangeRateService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import javax.inject.Inject;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/dollarExchangeRate")
public class DollarExchangeRateResource {

    @Inject
    DollarExchangeRateService dollarExchangeRateService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Obtém os dados de cotação da data informada e da data útil anterior.",
            description = "Método para Obter os dados de cotação da data informada e da data útil anterior.")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Conjunto de informações de contação",
                    content = @Content(schema = @Schema(implementation = ResponseDollarExchangeRateDto.class))),
            @APIResponse(responseCode = "400",
                    description = "A requisição foi malformada, omitindo atributos obrigatórios, seja no payload ou através de atributos na URL.",
                    content = @Content(schema = @Schema(implementation = ResponseError.class))),
    })
    public Response findQuoteByPeriod(
            @Pattern(regexp = "^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$")
            @Size(max = 10, min = 10)
            @DefaultValue("16/03/2022")
            @QueryParam("dataInicial")
                    String initialDate
    ) {
        try {
            return okResponse(dollarExchangeRateService.findQuoteByPeriod(initialDate));
        }catch(BusinessException e) {
            return Response.status(e.getHttpStatusCode()).entity(e.getMessage()).build();
        }
    }

    public static Response okResponse(Object entity) {
        var responseBuilder = Response.ok(entity, MediaType.APPLICATION_JSON);
        return responseBuilder.build();
    }
}