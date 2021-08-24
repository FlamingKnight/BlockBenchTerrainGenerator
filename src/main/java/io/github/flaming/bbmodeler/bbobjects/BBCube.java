package io.github.flaming.bbmodeler.bbobjects;

import io.github.flaming.bbmodeler.math.Vector3i;

import java.util.LinkedHashMap;
import java.util.UUID;

public class BBCube {
    private final String name;
    private boolean rescale;
    private final int[] from;
    private final int[] to;
    private int autouv;
    private int color;
    private boolean locked;
    private int[] origin = new int[3];
    private int[] uv_offset = new int[2];
    private final LinkedHashMap<String, BBFace> faces;
    private final UUID uuid;

    public BBCube(String name, boolean rescale, Vector3i from, Vector3i to,
                  int autouv, int color, boolean locked,
                  int[] origin, int[] uv_offset, LinkedHashMap<String, BBFace> faces) {
        this(name, rescale, from, to, autouv, color, locked, origin, uv_offset, faces, UUID.randomUUID());
    }

    public BBCube(String name, boolean rescale, Vector3i from, Vector3i to,
                  int autouv, int color, boolean locked,
                  int[] origin, int[] uv_offset, LinkedHashMap<String, BBFace> faces, UUID uuid) {
        this.name = name;
        this.rescale = rescale;
        this.from = new int[]{from.x, from.y, from.z};
        this.to = new int[]{to.x, to.y, to.z};
        this.autouv = autouv;
        this.color = color;
        this.locked = locked;
        this.origin = origin;
        this.uv_offset = uv_offset;
        this.faces = faces;
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public boolean isRescale() {
        return rescale;
    }

    public void setRescale(boolean rescale) {
        this.rescale = rescale;
    }

    public int[] getFrom() {
        return from;
    }

    public Vector3i getFromAsVector3i() {
        return new Vector3i(from[0], from[1], from[2]);
    }

    public void setFrom(Vector3i vector3i) {
        this.from[0] = vector3i.x;
        this.from[1] = vector3i.y;
        this.from[2] = vector3i.z;
    }

    public int[] getTo() {
        return to;
    }

    public Vector3i getToAsVector3i() {
        return new Vector3i(to[0], to[1], to[2]);
    }

    public void setTo(Vector3i vector3i) {
        this.to[0] = vector3i.x;
        this.to[1] = vector3i.y;
        this.to[2] = vector3i.z;
    }

    public int getAutouv() {
        return autouv;
    }

    public void setAutouv(int autouv) {
        this.autouv = autouv;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public int[] getOrigin() {
        return origin;
    }

    public void setOrigin(int[] origin) {
        this.origin = origin;
    }

    public int[] getUv_offset() {
        return uv_offset;
    }

    public void setUv_offset(int[] uv_offset) {
        this.uv_offset = uv_offset;
    }

    public LinkedHashMap<String, BBFace> getFaces() {
        return faces;
    }

    public UUID getUuid() {
        return uuid;
    }
}
