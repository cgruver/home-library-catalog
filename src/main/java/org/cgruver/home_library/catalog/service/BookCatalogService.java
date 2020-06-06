package org.cgruver.home_library.catalog.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.cgruver.home_library.catalog.client.BookCatalogException;
import org.cgruver.home_library.catalog.client.dto.AuthorDTO;
import org.cgruver.home_library.catalog.client.dto.BookInfoDTO;
import org.cgruver.home_library.catalog.mapper.BookInfoMapper;
import org.cgruver.home_library.catalog.model.BookInfo;
import org.cgruver.home_library.catalog.open_library.api.OpenLibrary;
import org.cgruver.home_library.catalog.open_library.dto.AuthorOL;
import org.cgruver.home_library.catalog.open_library.dto.BookInfoOL;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class BookCatalogService {

    @Inject
    private BookInfoMapper bookInfoMapper;

    @Inject
    @RestClient
    private OpenLibrary openLibrary;

    public BookInfoDTO getBookInfoByIsbn(String isbn) throws BookCatalogException {

        BookInfoDTO bookInfoDto = null;
        BookInfo bookInfoEntity = null;
        bookInfoEntity = BookInfo.getBookInfoByIsbn(isbn);
        if (bookInfoEntity == null) {
            BookInfoOL bookInfoOL = openLibrary.getBookInfo(isbn, "json", "data");
            bookInfoDto = new BookInfoDTO();
            ArrayList<AuthorDTO> authors = new ArrayList<AuthorDTO>();
            for (AuthorOL author : bookInfoOL.getAuthors()) {
                AuthorDTO dto = new AuthorDTO();
                dto.setName(author.getName());
                dto.setOpenLibraryUrl(author.getUrl());
                authors.add(dto);
            }
            bookInfoDto.setAuthors(authors);
            bookInfoDto.setCoverImageUrl(bookInfoOL.getCover().getSmall());
            bookInfoDto.setIsbn(bookInfoOL.getIsbn());
            bookInfoDto.setNumberOfPages(bookInfoOL.getNumberOfPages());
            bookInfoDto.setOpenLibraryUrl(bookInfoOL.getUrl());
            SimpleDateFormat dateFormatter = new SimpleDateFormat();
            try {
                bookInfoDto.setPublishDate(dateFormatter.parse(bookInfoOL.getPublishDate()));
            } catch (ParseException e) {
                throw new BookCatalogException("Failed to parse Publish Date: " + bookInfoOL.getPublishDate());
            }
            bookInfoDto.setTitle(bookInfoOL.getTitle());
            
        } else {
            bookInfoDto = bookInfoMapper.bookInfoEntityToDto(bookInfoEntity);
        }

        return bookInfoDto;
    }
}