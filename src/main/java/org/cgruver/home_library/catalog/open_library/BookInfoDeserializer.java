package org.cgruver.home_library.catalog.open_library;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.cgruver.home_library.catalog.open_library.dto.BookInfoDetailOL;
import org.cgruver.home_library.catalog.open_library.dto.BookInfoOL;

import java.io.IOException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookInfoDeserializer extends JsonDeserializer<BookInfoOL> {

    @Override
    public BookInfoOL deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        BookInfoOL bookInfo = new BookInfoOL();
        String isbn = p.nextFieldName();
        JsonNode node = p.getCodec().readTree(p);
        bookInfo.setIsbn(isbn);
        bookInfo.setDetails(objectMapper.treeToValue(node.get(isbn), BookInfoDetailOL.class));

        return bookInfo;
    }
}
