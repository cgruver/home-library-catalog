package org.cgruver.home_library.catalog.open_library.dto;

import java.util.List;
import lombok.Data;

@Data
public class IdentifiersOL {
    List<String> isbn13 = null;
    List<String> amazon = null;
    List<String> isbn10 = null;
    List<String> openlibrary = null;
}