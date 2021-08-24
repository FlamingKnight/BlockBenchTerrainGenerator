package io.github.flaming.bbmodeler.math;

import cn.nukkit.math.*;

public class Vector3i {

    public int x;
    public int y;
    public int z;

    public Vector3i(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3i(Vector3 vector3) {
        this.x = vector3.getFloorX();
        this.y = vector3.getFloorY();
        this.z = vector3.getFloorZ();
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getZ() {
        return this.z;
    }
}
