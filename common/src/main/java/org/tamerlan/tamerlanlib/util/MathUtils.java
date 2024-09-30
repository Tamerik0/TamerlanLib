package org.tamerlan.tamerlanlib.util;

import org.joml.Vector2d;
import org.joml.Vector3d;
import org.joml.Vector4d;

public class MathUtils {
    public static Vector3d toVector3d(Vector4d vec){
        return new Vector3d(vec.x, vec.y, vec.z);
    }
    public static Vector2d toVector2d(Vector4d vec){
        return new Vector2d(vec.x, vec.y);
    }
    public static Vector2d toVector2d(Vector3d vec){
        return new Vector2d(vec.x, vec.y);
    }
}
