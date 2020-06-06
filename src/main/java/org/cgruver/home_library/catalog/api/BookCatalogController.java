package org.cgruver.home_library.catalog.api;

import org.cgruver.home_library.catalog.client.BookCatalogException;
import org.cgruver.home_library.catalog.client.api.BookCatalogApi;
import org.cgruver.home_library.catalog.client.dto.BookInfoDTO;

public class BookCatalogController implements BookCatalogApi {

    @Override
    public BookInfoDTO saveBookInfo(BookInfoDTO bookInfo) throws BookCatalogException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BookInfoDTO getBookInfo(String isbn) throws BookCatalogException {
        // TODO Auto-generated method stub
        return null;
    }
    
}