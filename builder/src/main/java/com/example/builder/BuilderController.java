package com.example.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
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
        //usar rabbitmq para autenticação
        
        if (param == null) {
            return new ResponseDTO();
        } else if(param.getRole().equals("mei")){
            String url=UriComponentsBuilder.fromHttpUrl("http://localhost:8081/mei")
                .queryParam("icms", param.getIcms())
                .queryParam("iss", param.getIss())
                .toUriString();
                ImpostoResponse imposto = restTemplate.getForObject(url, ImpostoResponse.class);
            return new ResponseDTO(param.getName(), param.getRole(), param.getFaturamento(), imposto.getImposto());
        } else if(param.getRole().equals("simples")){
            ResponseDTO builder = new ResponseDTO(param.getName(), param.getRole(), param.getFaturamento());
            String url = UriComponentsBuilder.fromHttpUrl("http://192.168.0.2/simples/anexo")
                    .queryParam("faturamento", param.getFaturamento())
                    .toUriString();
                    ImpostoResponse imposto = restTemplate.getForObject(url, ImpostoResponse.class);
                    builder.setImposto(imposto.getImposto());
            if (param.getIcms()==true){
                url = UriComponentsBuilder.fromHttpUrl("http://192.168.0.2/simples/icms")
                    .queryParam("faturamento", param.getFaturamento())
                    .toUriString();
                imposto = restTemplate.getForObject(url, ImpostoResponse.class);
                builder.addImposto(imposto.getImposto());
            }
            if (param.getCofins()==true){
                url = UriComponentsBuilder.fromHttpUrl("http://192.168.0.2/simples/cofins")
                    .queryParam("faturamento", param.getFaturamento())
                    .toUriString();
                imposto = restTemplate.getForObject(url, ImpostoResponse.class);
                builder.addImposto(imposto.getImposto());
            }
            if (param.getCsll()==true){
                url = UriComponentsBuilder.fromHttpUrl("http://192.168.0.2/simples/csll")
                    .queryParam("faturamento", param.getFaturamento())
                    .toUriString();
                imposto = restTemplate.getForObject(url, ImpostoResponse.class);
                builder.addImposto(imposto.getImposto());
            }
            if (param.getPis()==true){
                url = UriComponentsBuilder.fromHttpUrl("http:////192.168.0.2/simples/pis")
                    .queryParam("faturamento", param.getFaturamento())
                    .toUriString();
                imposto = restTemplate.getForObject(url, ImpostoResponse.class);
                builder.addImposto(imposto.getImposto());
            }
            return new ResponseDTO(builder.getName(), builder.getRole(), builder.getFaturamento(), builder.getImposto());
            
        } else{
            return new ResponseDTO();
        }
        
        
    }
    

}