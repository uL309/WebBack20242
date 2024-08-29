package com.example.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;



@RestController
public class BuilderController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/build")
    public ResponseDTO getBuild(@RequestBody BuilderDTO param) {
        //try
        double imposto;
        if (param == null) {
            return new ResponseDTO();
        } else if(param.getRole().equals("mei")){
            String url=UriComponentsBuilder.fromHttpUrl("http://192.168.0.1/mei")
                .queryParam("icms", param.getIcms())
                .queryParam("iss", param.getIss())
                .toUriString();
            imposto = restTemplate.getForObject(url, Double.class);
            return new ResponseDTO(param.getName(), param.getRole(), param.getFaturamento(), imposto);
        } else if(param.getRole().equals("simples")){
            Builder builder = new Builder(param.getName(), param.getRole(), param.getFaturamento());
            String url = UriComponentsBuilder.fromHttpUrl("http://192.168.0.2/simples/anexo")
                    .queryParam("faturamento", param.getFaturamento())
                    .toUriString();
                    imposto = restTemplate.getForObject(url, Double.class);
                    builder.addImposto(imposto);
            if (param.getIcms()==true){
                url = UriComponentsBuilder.fromHttpUrl("http://192.168.0.2/simples/icms")
                    .queryParam("faturamento", param.getFaturamento())
                    .toUriString();
                imposto = restTemplate.getForObject(url, Double.class);
                builder.addImposto(imposto);
            }
            if (param.getCofins()==true){
                url = UriComponentsBuilder.fromHttpUrl("http://192.168.0.2/simples/cofins")
                    .queryParam("faturamento", param.getFaturamento())
                    .toUriString();
                imposto = restTemplate.getForObject(url, Double.class);
                builder.addImposto(imposto);
            }
            if (param.getCsll()==true){
                url = UriComponentsBuilder.fromHttpUrl("http://192.168.0.2/simples/csll")
                    .queryParam("faturamento", param.getFaturamento())
                    .toUriString();
                imposto = restTemplate.getForObject(url, Double.class);
                builder.addImposto(imposto);
            }
            if (param.getPis()==true){
                url = UriComponentsBuilder.fromHttpUrl("http:////192.168.0.2/simples/pis")
                    .queryParam("faturamento", param.getFaturamento())
                    .toUriString();
                imposto = restTemplate.getForObject(url, Double.class);
                builder.addImposto(imposto);
            }
            return new ResponseDTO(builder.getName(), builder.getRole(), builder.getFaturamento(), builder.getImposto());
            
        } else{
            return new ResponseDTO();
        }
        
        
    }
    

}