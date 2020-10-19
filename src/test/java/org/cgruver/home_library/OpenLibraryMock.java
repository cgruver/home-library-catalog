package org.cgruver.home_library;

import java.util.Map;

import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class OpenLibraryMock implements QuarkusTestResourceLifecycleManager {

    private WireMockServer wireMockServer;

    @Override
    public Map<String, String> start() {
        wireMockServer = new WireMockServer();
        wireMockServer.start();

        stubFor(get(urlEqualTo("/api/books?bibkeys=9780062225740&format=json&jscmd=data"))
                .willReturn(aResponse().withHeader("Content-Type", "application/json").withBody(
                        "{\"9780062225740\": {\"publishers\": [{\"name\": \"Harper\"}], \"identifiers\": {\"isbn_13\": [\"9780062225740\"], \"amazon\": [\"006222574X\"], \"isbn_10\": [\"006222574X\"], \"openlibrary\": [\"OL27566628M\"]}, \"title\": \"Pyramids\", \"url\": \"https://openlibrary.org/books/OL27566628M/Pyramids\", \"notes\": \"Source title: Pyramids (Discworld)\", \"number_of_pages\": 368, \"cover\": {\"small\": \"https://covers.openlibrary.org/b/id/9021696-S.jpg\", \"large\": \"https://covers.openlibrary.org/b/id/9021696-L.jpg\", \"medium\": \"https://covers.openlibrary.org/b/id/9021696-M.jpg\"}, \"subjects\": [{\"url\": \"https://openlibrary.org/subjects/fiction,_humorous\", \"name\": \"Fiction, humorous\"}, {\"url\": \"https://openlibrary.org/subjects/fiction,_fantasy,_general\", \"name\": \"Fiction, fantasy, general\"}, {\"url\": \"https://openlibrary.org/subjects/discworld_(imaginary_place),_fiction\", \"name\": \"Discworld (imaginary place), fiction\"}], \"publish_date\": \"Apr 30, 2013\", \"key\": \"/books/OL27566628M\", \"authors\": [{\"url\": \"https://openlibrary.org/authors/OL25712A/Terry_Pratchett\", \"name\": \"Terry Pratchett\"}]}}")));

        return null;
    }

    @Override
    public void stop() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }
}
