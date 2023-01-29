package mx.bluelight.yelpcamp.app.constants;

import lombok.Getter;

@Getter
public enum Folder {
    DOC("Documents", "/documents"),
    IMG("Images", "/images"),
    MSC("Music", "/music"),
    PUB("Public", "/public"),
    VID("Videos", "/videos");

    private final String name;
    private final String path;

    Folder(String name, String path) {
        this.name = name;
        this.path = path;
    }
}
