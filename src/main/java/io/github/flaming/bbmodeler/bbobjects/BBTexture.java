package io.github.flaming.bbmodeler.bbobjects;

import java.util.UUID;

public class BBTexture {
    //DO NOT DELETE OR CHANGE THESE FIELDS, they are necessary for GSON to compile into a block bench model
    private final String path;
    private final String name;
    private final String folder;
    private final String namespace;
    private final String id;
    private final boolean particle = false;
    private final String render_mode = "normal";
    private final boolean visible = true;
    private final String mode = "bitmap";
    private final boolean saved = true;
    private final UUID uuid = UUID.randomUUID();
    private final String source;

    public BBTexture(String path, String name, String folder, String namespace, String id, String source) {
        this.path = path;
        this.name = name;
        this.folder = folder;
        this.namespace = namespace;
        this.id = id;
        this.source = source;
    }
}
