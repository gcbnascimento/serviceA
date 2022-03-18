package com.microservicea.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
@ToString
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaResteasyEapServerCodegen", date = "2021-03-16T09:23:23.706207-03:00[America/Recife]")
public class ResponseErrorErrors {

	@Schema(required = true, description = "Código de erro específico do endpoint")
	@JsonProperty("code")
	@NotNull
	@Pattern(regexp = "[\\w\\W\\s]*")
	@Size(max = 255)
	private String code;

	@Schema(required = true, description = "Título legível por humanos do erro deste erro específico")
	@JsonProperty("title")
	@NotNull
	@Pattern(regexp = "[\\w\\W\\s]*")
	@Size(max = 255)
	private String title;

	@Schema(required = true, description = "Descrição legível por humanos deste erro específico")
	@JsonProperty("detail")
	@NotNull
	@Pattern(regexp = "[\\w\\W\\s]*")
	@Size(max = 2048)
	private String detail;

}
