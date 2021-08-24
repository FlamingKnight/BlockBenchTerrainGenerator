package io.github.flaming.bbmodeler.bbobjects;

import java.util.ArrayList;
import java.util.UUID;

public class BBLayer {
    private String name;
    private int[] origin = new int[3];
    private String bedrock_binding;
    private UUID uuid;
    private boolean export;
    private boolean isOpen;
    private boolean locked;
    private boolean visibility;
    private int autouv = 0;
    private ArrayList<UUID> children;
    //TODO: Add a method to add it to the elements list
    private transient ArrayList<BBCube> transientChildren;

    public BBLayer(String name, int[] origin, String bedrock_binding, UUID uuid, boolean export, boolean isOpen, boolean locked, boolean visibility, int autouv, ArrayList<UUID> children) {
        this.name = name;
        this.origin = origin;
        this.bedrock_binding = bedrock_binding;
        this.uuid = uuid;
        this.export = export;
        this.isOpen = isOpen;
        this.locked = locked;
        this.visibility = visibility;
        this.autouv = autouv;
        this.children = children;
        this.transientChildren = new ArrayList<>();
    }

    public void addCube(BBCube bbCube) {
        transientChildren.add(bbCube);
        children.add(bbCube.getUuid());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getOrigin() {
        return origin;
    }

    public void setOrigin(int[] origin) {
        this.origin = origin;
    }

    public String getBedrock_binding() {
        return bedrock_binding;
    }

    public void setBedrock_binding(String bedrock_binding) {
        this.bedrock_binding = bedrock_binding;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public boolean isExport() {
        return export;
    }

    public void setExport(boolean export) {
        this.export = export;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public int getAutouv() {
        return autouv;
    }

    public void setAutouv(int autouv) {
        this.autouv = autouv;
    }

    public ArrayList<UUID> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<UUID> children) {
        this.children = children;
    }

    public ArrayList<BBCube> getTransientChildren() {
        return transientChildren;
    }

    public void setTransientChildren(ArrayList<BBCube> transientChildren) {
        this.transientChildren = transientChildren;
    }
}
