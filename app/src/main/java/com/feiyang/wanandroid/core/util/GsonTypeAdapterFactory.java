package com.feiyang.wanandroid.core.util;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Copyright:mjt_pad_android
 * Author: liyang <br>
 * Date:2019/3/4 2:35 PM<br>
 * Desc: <br>
 */
public class GsonTypeAdapterFactory implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        final TypeAdapter<T> adapter = gson.getDelegateAdapter(this, type);
        return new TypeAdapter<T>() {
            @Override
            public void write(JsonWriter out, T value) throws IOException {
                adapter.write(out, value);
            }

            @Override
            public T read(JsonReader in) throws IOException {

                try {
                    return adapter.read(in);
                } catch (Throwable e) {
                    consumeAll(in);
                    return null;
                }

            }

            private void consumeAll(JsonReader in) throws IOException {
                if (in.hasNext()) {
                    JsonToken peek = in.peek();
                    if (peek == JsonToken.STRING) {
                        in.nextString();
                    } else if (peek == JsonToken.BEGIN_ARRAY) {
                        in.beginArray();
                        consumeAll(in);
                        in.endArray();
                    } else if (peek == JsonToken.BEGIN_OBJECT) {
                        in.beginObject();
                        consumeAll(in);
                        in.endObject();
                    } else if (peek == JsonToken.END_ARRAY) {
                        in.endArray();
                    } else if (peek == JsonToken.END_OBJECT) {
                        in.endObject();
                    } else if (peek == JsonToken.NUMBER) {
                        in.nextString();
                    } else if (peek == JsonToken.BOOLEAN) {
                        in.nextBoolean();
                    } else if (peek == JsonToken.NAME) {
                        in.nextName();
                        consumeAll(in);
                    } else if (peek == JsonToken.NULL) {
                        in.nextNull();
                    }
                }
            }
        };
    }
}
