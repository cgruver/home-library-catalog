package org.cgruver.home_library.catalog.open_library.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Data
@RegisterForReflection
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdentifiersOL {

    @JsonProperty("isbn_13")
    List<String> isbn13 = null;
    List<String> amazon = null;
    @JsonProperty("isbn_10")
    List<String> isbn10 = null;
    List<String> openlibrary = null;
}