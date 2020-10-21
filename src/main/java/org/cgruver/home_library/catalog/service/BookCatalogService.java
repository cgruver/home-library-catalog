package org.cgruver.home_library.catalog.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.cgruver.home_library.catalog.aop.Audited;
import org.cgruver.home_library.catalog.rest.BookCatalogException;
import org.cgruver.home_library.catalog.rest.dto.AuthorDTO;
import org.cgruver.home_library.catalog.rest.dto.BookInfoDTO;
import org.cgruver.home_library.catalog.mapper.BookInfoMapper;
import org.cgruver.home_library.catalog.model.Author;
import org.cgruver.home_library.catalog.model.BookInfo;
import org.cgruver.home_library.catalog.open_library.api.OpenLibrary;
import org.cgruver.home_library.catalog.open_library.dto.AuthorOL;
import org.cgruver.home_library.catalog.open_library.dto.BookInfoDetailOL;
import org.cgruver.home_library.catalog.open_library.dto.BookInfoOL;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

@ApplicationScoped
public class BookCatalogService {

    @Inject
    private BookInfoMapper bookInfoMapper;

    @Inject
    @RestClient
    private OpenLibrary openLibrary;

    @Audited
    public BookInfoDTO getBookInfoByIsbn(String isbn) throws BookCatalogException {

        final Logger LOG = Logger.getLogger(BookCatalogService.class);
        LOG.info("getBookInfoByIsbn method invoked!");
        BookInfoDTO bookInfoDto = null;
        BookInfo bookInfoEntity = null;
        bookInfoEntity = BookInfo.getBookInfoByIsbn(isbn);
        if (bookInfoEntity == null) {
            isbn = "ISBN:" + isbn;
            BookInfoOL bookInfoOL = openLibrary.getBookInfo(isbn, "json", "data");
            BookInfoDetailOL bookInfoDetails = bookInfoOL.getDetails();
            bookInfoDto = new BookInfoDTO();
            ArrayList<AuthorDTO> authors = new ArrayList<AuthorDTO>();
            for (AuthorOL author : bookInfoDetails.getAuthors()) {
                AuthorDTO dto = new AuthorDTO();
                dto.setName(author.getName());
                dto.setOpenLibraryUrl(author.getUrl());
                authors.add(dto);
            }
            bookInfoDto.setAuthors(authors);
            bookInfoDto.setCoverImageUrl(bookInfoDetails.getCover().getSmall());
            bookInfoDto.setIsbn(bookInfoOL.getIsbn());
            bookInfoDto.setNumberOfPages(bookInfoDetails.getNumberOfPages());
            bookInfoDto.setOpenLibraryUrl(bookInfoDetails.getUrl());
            bookInfoDto.setPublishDate(bookInfoDetails.getPublishDate());
            bookInfoDto.setTitle(bookInfoDetails.getTitle());
            
        } else {
            bookInfoDto = bookInfoMapper.bookInfoEntityToDto(bookInfoEntity);
        }

        return bookInfoDto;
    }

    @Transactional
    public void saveBookInfo(BookInfoDTO dto) throws BookCatalogException {
        BookInfo bookInfoEntity = bookInfoMapper.bookInfoDtoToEntity(dto);
        List<Author> authorEntities = new ArrayList<Author>();
        for (AuthorDTO authorDto : dto.getAuthors()) {
            Author authorEntity = bookInfoMapper.authorDtoToEntity(authorDto);
            authorEntity.setBookInfo(bookInfoEntity);
            authorEntities.add(authorEntity);
        }
        bookInfoEntity.setAuthors(authorEntities);
        BookInfo.persist(bookInfoEntity);
    }
}