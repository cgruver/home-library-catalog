package org.cgruver.home_library.catalog.api;

import javax.inject.Inject;

import org.cgruver.home_library.catalog.client.BookCatalogException;
import org.cgruver.home_library.catalog.client.api.BookCatalogApi;
import org.cgruver.home_library.catalog.client.dto.BookInfoDTO;
import org.cgruver.home_library.catalog.service.BookCatalogService;

public class BookCatalogController implements BookCatalogApi {

    @Inject
    private BookCatalogService bookCatalogService;
    @Override
    public void saveBookInfo(BookInfoDTO bookInfo) throws BookCatalogException {
        bookCatalogService.saveBookInfo(bookInfo);
    }

    @Override
    public BookInfoDTO getBookInfo(String isbn) throws BookCatalogException {
        return bookCatalogService.getBookInfoByIsbn(isbn);
    }
    
}