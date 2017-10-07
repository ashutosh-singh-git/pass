package com.pass.controller;

import com.pass.model.Bookmarks;
import com.pass.model.Deck;
import com.pass.service.BookmarksService;
import com.pass.utils.CardNotFoundException;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bookmarks")
public class BookmarksController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookmarksController.class);

    private final BookmarksService service;
    private String deckId;

    @Autowired
    BookmarksController(BookmarksService service) {
        this.service = service;
    }

    @Secured({"ROLE_USER"})
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Insert Bookmarks")
    public List<Bookmarks> createBookmarks(@RequestBody @Valid List<Bookmarks> bookmarks) {
        return bookmarks.stream().map(service::create).collect(Collectors.toList());
    }

    @Secured({"ROLE_USER"})
    @RequestMapping(method = RequestMethod.GET)
    public Page<Deck> getBookmarksDetails(@RequestParam int page) {
        return service.findAll(page);
    }

    @Secured({"ROLE_USER"})
    @RequestMapping(method = RequestMethod.DELETE, value = "/{deckId}/{cardId}")
    @ApiOperation(value = "Delete Bookmarks", notes = "cardId with value 0 will result in deletion of each bookmark related to that particular deck")
    public List<Bookmarks> deleteBookMarks(@PathVariable("deckId") String deckId, @PathVariable("cardId") String cardId) {
        return service.delete(deckId, cardId);
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleBookmarksNotFound(CardNotFoundException ex) {
        LOGGER.error("Handling error with message: {}", ex.getMessage());
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", ex.getMessage());
        return response;
    }
}