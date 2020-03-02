package dev.controller;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AdresseController {

	@RequestMapping(value = "/adresses", params = "q")
	public String getAdresses(@RequestParam("q") String query) {
		String uri = "https://api-adresse.data.gouv.fr/search/?q=" + query + "&type=housenumber&autocomplete=1";
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return rt.exchange(uri, HttpMethod.GET, entity, String.class).getBody();
	}

}
