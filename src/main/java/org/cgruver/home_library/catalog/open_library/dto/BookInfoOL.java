package org.cgruver.home_library.catalog.open_library.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.cgruver.home_library.catalog.open_library.BookInfoDeserializer;

import lombok.Data;

@Data
@JsonDeserialize(using = BookInfoDeserializer.class)
public class BookInfoOL {

    String isbn;
    BookInfoDetailOL details;
}
