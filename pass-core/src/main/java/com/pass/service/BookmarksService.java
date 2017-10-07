package com.pass.service;

import com.pass.model.Bookmarks;
import com.pass.model.Deck;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookmarksService {

    Bookmarks create(Bookmarks bookmarks);

    Bookmarks delete(String id);

    List<Bookmarks> delete(String deckId, String cardId);
//    List<Bookmarks> findAll();

    Page<Deck> findAll(int page);

    Bookmarks findById(String id);

    Bookmarks update(Bookmarks bookmarks);

}
