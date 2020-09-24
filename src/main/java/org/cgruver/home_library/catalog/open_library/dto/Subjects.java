package org.cgruver.home_library.catalog.open_library.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Data
@RegisterForReflection
@JsonIgnoreProperties(ignoreUnknown = true)
public class Subjects {
    String url;
    String name;
}
