package android.os;

import android.util.ArrayMap;

import androidx.annotation.Nullable;

public class Bundle {

    ArrayMap<String, Object> mMap = new ArrayMap<>();

    public void putParcelable(@Nullable String key, @Nullable Parcelable value) {
        mMap.put(key, value);
    }

    public void putInt(@Nullable String key, int value) {
        mMap.put(key, value);
    }

    public void putString(@Nullable String key, String value) {
        mMap.put(key, value);
    }

    public void putBoolean(@Nullable String key, boolean value) {
        mMap.put(key, value);
    }
}
