package org.cgruver.home_library.catalog.open_library.dto;

import java.util.List;

import lombok.Data;

/**
 * BookInfoDetailOL
 */
@Data
public class BookInfoDetailOL {

    List<PublisherOL> publishers = null;
    IdentifiersOL identifiers;
    String title;
    String url;
    String notes;
    Long numberOfPages;
    CoverOL cover;
    String publishDate;
    String key;
    List<AuthorOL> authors = null;
}