package org.cgruver.home_library;

import org.cgruver.home_library.catalog.model.BookInfo;
import org.junit.jupiter.api.Test;

import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class BookInfoTest {

    @Test
    public void testBookInfo() {
        PanacheMock.mock(BookInfo.class);

        
    }
    
}
