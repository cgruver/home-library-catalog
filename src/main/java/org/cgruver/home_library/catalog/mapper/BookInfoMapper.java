package org.cgruver.home_library.catalog.mapper;

import org.cgruver.home_library.catalog.client.dto.AuthorDTO;
import org.cgruver.home_library.catalog.client.dto.BookInfoDTO;
import org.cgruver.home_library.catalog.model.Author;
import org.cgruver.home_library.catalog.model.BookInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface BookInfoMapper {

    AuthorDTO authorEntityToDto(Author entity);

    Author authorDtoToEntity(AuthorDTO dto);

    BookInfoDTO bookInfoEntityToDto(BookInfo entity);

    BookInfo bookInfoDtoToEntity(BookInfoDTO dto);
}