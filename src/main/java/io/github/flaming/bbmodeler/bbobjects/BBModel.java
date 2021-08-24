package io.github.flaming.bbmodeler.bbobjects;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;

public class BBModel {

    private final LinkedHashMap<String, Object> meta = new LinkedHashMap<String, Object>() {
        {
            this.put("format_version", "3.6");
            this.put("creation_time", Calendar.getInstance().getTimeInMillis());
            this.put("model_format", "bedrock");
            this.put("box_uv", false);
        }
    };

    private final String name;
    private final String geometry_name;
    private final int[] visible_box = new int[]{0, 0, 0}; //TODO: What's this?
    private final boolean layered_textures = false; //TODO: What's this?
    private final int[] resolution = new int[]{64, 64};
    private final List<BBCube> elements;
    private final ArrayList<BBLayer> outliner;
    private final ArrayList<BBTexture> textures;

    public BBModel(String name, String geometry_name,
                   List<BBCube> elements, ArrayList<BBLayer> outliner, ArrayList<BBTexture> textures) {
        this.name = name;
        this.geometry_name = geometry_name;
        this.elements = elements;
        this.outliner = outliner;
        this.textures = textures;
    }
}
