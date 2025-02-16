package org.babyfish.jimmer.sql.example.web;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ResourceContents {

    private ResourceContents() {}

    public static String contentOf(String resource) throws URISyntaxException, IOException {
        URL url = ResourceContents.class.getClassLoader().getResource(resource);
        if (url == null) {
            throw new IllegalArgumentException("Illegal resource path: " + resource);
        }
        byte[] bytes = Files.readAllBytes(
                Paths.get(url.toURI())
        );
        return new String(bytes);
    }
}
