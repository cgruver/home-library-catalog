package org.cgruver.home_library.catalog.open_library.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

@Data
@RegisterForReflection
public class AuthorOL {
    String url;
    String name;
}