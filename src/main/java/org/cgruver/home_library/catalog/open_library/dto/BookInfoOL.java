package org.cgruver.home_library.catalog.open_library.dto;

import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.cgruver.home_library.catalog.open_library.BookInfoDeserializer;

import lombok.Data;

@Data
@JsonDeserialize(using = BookInfoDeserializer.class)
public class BookInfoOL {

    String isbn;
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
