package com.microservicea.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Builder
@Getter
@Setter
@ToString
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaResteasyEapServerCodegen", date = "2021-03-16T09:23:23.706207-03:00[America/Recife]")
public class ResponseError {
	@Schema(required = true, description = "")
	@JsonProperty("errors")
	@NotNull
	@Size(min=1,max=13)
  private List<ResponseErrorErrors> errors;

}
