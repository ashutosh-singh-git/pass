package com.pass.controller;

import com.pass.model.CardServe;
import com.pass.service.CardService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/cardServe")
public class CardServeController {

	private final CardService service;

	@Autowired
    CardServeController(CardService service){
		this.service = service;
	}

	@Secured("ROLE_USER")
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/{cardIndex}")
	@ApiOperation(value = "get the card processed based on diffrent logics", notes = "{id},{cardIndex}:-First time send both values \"0\",onward send the previous response value.")
	public CardServe getCardDetails(@PathVariable String id, @PathVariable int cardIndex) {
		CardServe cardServe = new CardServe();
		cardServe.setId(id);
		cardServe.setCardIndex(cardIndex);
		return service.getUserCard(cardServe);
	}
}