package org.cgruver.home_library.catalog.open_library;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.cgruver.home_library.catalog.open_library.dto.BookInfoOL;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class BookInfoDeserializer extends JsonDeserializer<BookInfoOL> {

    @Override
    public BookInfoOL deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        BookInfoOL bookInfo = null; // new BookInfo();
        String isbn = p.nextFieldName();
        System.out.println("ISBN: " + isbn);
        JsonNode node = p.getCodec().readTree(p);
        JsonNode body = node.get(isbn);
        //ObjectCodec codec = p.getCodec();
        //TreeNode treeNode = codec.readTree(p);
        //treeNode.fieldNames();
        //JsonNode node = p.getCodec().readTree(p);
        //Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
        //Map.Entry<String, JsonNode> nodeData = fields.next();
        //JsonNode body = nodeData.getValue();

        bookInfo = objectMapper.treeToValue(body, BookInfoOL.class);
        bookInfo.setIsbn(isbn);
        //bookInfo.setIsbn(nodeData.getKey());

        return bookInfo;
    }
}
