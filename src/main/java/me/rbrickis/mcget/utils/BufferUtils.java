package me.rbrickis.mcget.utils;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public final class BufferUtils {

    public static void writeVarInt(DataOutput out, int value) throws IOException {
        while (true) {
            if ((value & 0xFFFFFF80) == 0) {
                out.writeByte(value);
                return;
            }
            out.writeByte(value & 0x7F | 0x80);
            value >>>= 7;
        }
    }

    public static int readVarInt(DataInput in) throws IOException {
        int i = 0;
        int j = 0;
        while (true) {
            int k = in.readByte();
            i |= (k & 0x7F) << j++ * 7;
            if (j > 5)
                throw new RuntimeException("VarInt too big");
            if ((k & 0x80) != 128)
                break;
        }
        return i;
    }

    /**
     * UTF-8 string prefixed with its size in bytes as a VarInt
     *
     * @param out - Stream to write too
     * @param value  - value to write to the buffer
     */
    public static void writeString(DataOutput out, String value) throws IOException {
        byte[] data = value.getBytes(StandardCharsets.UTF_8);
        int data_length = data.length;
        writeVarInt(out, data_length);
        out.write(data);
    }


    public static String readString(DataInput in) throws IOException {
        int length = readVarInt(in);
        byte[] data = new byte[length];

        in.readFully(data, 0, data.length);

        return new String(data, StandardCharsets.UTF_8);
    }



}
