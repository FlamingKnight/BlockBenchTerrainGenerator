package io.github.flaming.bbmodeler.bbobjects;

import io.github.flaming.bbmodeler.math.Vector2i;

import java.util.LinkedHashMap;

public class BBFace {
    private final int[] uv;
    private final int texture;

    public static LinkedHashMap<String, BBFace> getOffsetMap(Vector2i vector2) {
        Vector2i reduced = new Vector2i(vector2.x >> 4, vector2.y >> 4);
        return new LinkedHashMap<String, BBFace>(){
            {
                this.put("north", new BBFace(new int[]{reduced.x, reduced.y, reduced.x+2, reduced.y+2}, 0));
                this.put("east", new BBFace(new int[]{reduced.x, reduced.y, reduced.x+2, reduced.y+2}, 0));
                this.put("south", new BBFace(new int[]{reduced.x, reduced.y, reduced.x+2, reduced.y+2}, 0));
                this.put("west", new BBFace(new int[]{reduced.x, reduced.y, reduced.x+2, reduced.y+2}, 0));
                this.put("up", new BBFace(new int[]{reduced.x, reduced.y, reduced.x+2, reduced.y+2}, 0));
                this.put("down", new BBFace(new int[]{reduced.x, reduced.y, reduced.x+2, reduced.y+2}, 0));
            }
        };
    }

    public BBFace(int[] uv, int texture) {
        this.uv = uv;
        this.texture = texture;
    }
}
