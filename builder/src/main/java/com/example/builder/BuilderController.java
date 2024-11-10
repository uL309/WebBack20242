package com.example.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;



@RestController
public class BuilderController {

    @Autowired
    private RestTemplate restTemplate;

    @Tag(name = "Index", description = "The Builder API index")
    @Operation(summary = "Get a greeting", description = "Get a greeting")
    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @Tag(name = "Builder", description = "The Builder API")
    @Operation(summary = "The BFF", description = "Frontend and selector for the Builder API")
    @GetMapping("/build")
    public ResponseDTO getBuild(@Valid @RequestBody BuilderDTO param) {
        
        UsuarioDTO user= restTemplate.getForObject("http://localhost:8080/usuarios/{id}", UsuarioDTO.class);

        //usar rabbitmq para autenticação

        if(user.getRole().equals("mei")){
            String url=UriComponentsBuilder.fromHttpUrl("http://mei/mei")
                .queryParam("icms", param.getIcms())
                .queryParam("iss", param.getIss())
                .toUriString();
                ImpostoResponse imposto = restTemplate.getForObject(url, ImpostoResponse.class);
            return new ResponseDTO(user.getName(), user.getRole(), param.getFaturamento(), imposto.getImposto());
        } else if(user.getRole().equals("simples")){
            ResponseDTO builder = new ResponseDTO(user.getName(), user.getRole(), param.getFaturamento());
            String url = UriComponentsBuilder.fromHttpUrl("http://irenda/renda")
                    .queryParam("faturamento", param.getFaturamento())
                    .queryParam("anexo", param.getAnexo())
                    .toUriString();
                    ImpostoResponse imposto = restTemplate.getForObject(url, ImpostoResponse.class);
                    builder.setImposto(imposto.getImposto());
            if (param.getIcms()==true){
                url = UriComponentsBuilder.fromHttpUrl("http://icms/simples")
                    .queryParam("faturamento", param.getFaturamento())
                    .toUriString();
                imposto = restTemplate.getForObject(url, ImpostoResponse.class);
                builder.addImposto(imposto.getImposto());
            }
            if (param.getCofins()==true){
                url = UriComponentsBuilder.fromHttpUrl("http://cofins/simples")
                    .queryParam("faturamento", param.getFaturamento())
                    .toUriString();
                imposto = restTemplate.getForObject(url, ImpostoResponse.class);
                builder.addImposto(imposto.getImposto());
            }
            if (param.getCsll()==true){
                url = UriComponentsBuilder.fromHttpUrl("http://csll/simples")
                    .queryParam("faturamento", param.getFaturamento())
                    .toUriString();
                imposto = restTemplate.getForObject(url, ImpostoResponse.class);
                builder.addImposto(imposto.getImposto());
            }
            if (param.getPis()==true){
                url = UriComponentsBuilder.fromHttpUrl("http://pis/simples")
                    .queryParam("faturamento", param.getFaturamento())
                    .toUriString();
                imposto = restTemplate.getForObject(url, ImpostoResponse.class);
                builder.addImposto(imposto.getImposto());
            }
            return new ResponseDTO(builder.getName(), builder.getRole(), builder.getFaturamento(), builder.getImposto());
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parametros inválidos");
        }
        
        
    }
    

}