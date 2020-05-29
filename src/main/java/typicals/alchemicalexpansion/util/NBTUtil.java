package typicals.alchemicalexpansion.util;

public class NBTUtil {
    public enum NBT_TYPES {

        //final String[] NBT_TYPES = new String[] {"END", "BYTE", "SHORT", "INT", "LONG", "FLOAT", "DOUBLE", "BYTE[]", "STRING", "LIST", "COMPOUND", "INT[]", "LONG[]"};
        END(0),
        BYTE(1),
        SHORT(2),
        INT(3),
        LONG(4),
        FLOAT(5),
        DOUBLE(6),
        BYTE_ARRAY(7),
        STRING(8),
        LIST(9),
        COMPOUND(10),
        INT_ARRAY(11),
        LONG_ARRAY(12);

        private int id;

        private NBT_TYPES(int id) {
            this.id = id;
        }

        public int id() {
            return this.id;
        }

    }
}
