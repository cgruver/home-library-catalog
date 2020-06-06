package org.cgruver.home_library.catalog.open_library;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.cgruver.home_library.catalog.open_library.dto.BookInfoOL;

import java.io.IOException;
import java.util.Map;

public class BookInfoDeserializer extends JsonDeserializer<BookInfoOL> {

    @Override
    public BookInfoOL deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        BookInfoOL bookInfo = null; // new BookInfo();

        JsonNode node = p.getCodec().readTree(p);
        Map.Entry<String, JsonNode> nodeData = node.fields().next();
        JsonNode body = nodeData.getValue();

        bookInfo = objectMapper.treeToValue(body, BookInfoOL.class);
        bookInfo.setIsbn(nodeData.getKey());

        return bookInfo;
    }

}