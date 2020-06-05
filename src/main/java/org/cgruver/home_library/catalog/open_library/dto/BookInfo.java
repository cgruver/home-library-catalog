package org.cgruver.home_library.catalog.open_library.dto;

import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.cgruver.home_library.catalog.open_library.BookInfoDeserializer;

import lombok.Data;

@Data
@JsonDeserialize(contentUsing = BookInfoDeserializer.class)
public class BookInfo {

    String isbn;
    List<Publisher> publishers = null;
    Identifiers identifiers;
    String title;
    String url;
    String notes;
    Long numberOfPages;
    Cover cover;
    String publishDate;
    String key;
    List<Author> authors = null;
}
