package io.github.hhromic.uimr;

/**
 * Murmur Hash 2.0, a relatively fast hash function for architectures with efficient multiplication.
 *
 * <p>For more information, please see <a href="http://code.google.com/p/smhasher/">here</a>.</p>
 *
 * <p><strong>Example usage:</strong></p>
 *
 * <pre>
 * import io.github.hhromic.uimr.MurmurHash;
 *
 * public class MyClass {
 *     public static void main(final String[] args) {
 *         System.out.println(MurmurHash.hash32("Hello World!"));
 *         System.out.println(MurmurHash.hash64("Hello World!"));
 *     }
 * }
 * </pre>
 *
 * @author Viliam Holub
 * @since 1.0
 */
public class MurmurHash {
    /**
     * Generates 64 bits hash from byte array of the specified length and seed.
     *
     * <p>Original code taken from <a href="http://d3s.mff.cuni.cz/~holub/sw/javamurmurhash/">here</a>,
     * by Viliam Holub. See link for more information.</p>
     *
     * @param data byte array to hash.
     * @param length length of the array to hash.
     * @param seed initial seed value.
     * @return 64 bits ({@code long}) hash of the array.
     */
    public static long hash64(final byte[] data, final int length, final int seed) {
        final long m = 0xc6a4a7935bd1e995L;
        final int r = 47;

        long h = (seed&0xffffffffl)^(length*m);

        int length8 = length/8;

        for (int i=0; i<length8; i++) {
                final int i8 = i*8;
                long k =  ((long)data[i8+0]&0xff)      +(((long)data[i8+1]&0xff)<<8)
                                +(((long)data[i8+2]&0xff)<<16) +(((long)data[i8+3]&0xff)<<24)
                                +(((long)data[i8+4]&0xff)<<32) +(((long)data[i8+5]&0xff)<<40)
                                +(((long)data[i8+6]&0xff)<<48) +(((long)data[i8+7]&0xff)<<56);

                k *= m;
                k ^= k >>> r;
                k *= m;

                h ^= k;
                h *= m;
        }

        switch (length%8) {
        case 7: h ^= (long)(data[(length&~7)+6]&0xff) << 48;
        case 6: h ^= (long)(data[(length&~7)+5]&0xff) << 40;
        case 5: h ^= (long)(data[(length&~7)+4]&0xff) << 32;
        case 4: h ^= (long)(data[(length&~7)+3]&0xff) << 24;
        case 3: h ^= (long)(data[(length&~7)+2]&0xff) << 16;
        case 2: h ^= (long)(data[(length&~7)+1]&0xff) << 8;
        case 1: h ^= (long)(data[length&~7]&0xff);
                h *= m;
        };

        h ^= h >>> r;
        h *= m;
        h ^= h >>> r;

        return h;
    }

    /**
     * Generates 32 bits hash from byte array of the specified length and seed.
     *
     * <p>Original code taken from <a href="http://d3s.mff.cuni.cz/~holub/sw/javamurmurhash/">here</a>,
     * by Viliam Holub. See link for more information.</p>
     *
     * @param data byte array to hash.
     * @param length length of the array to hash.
     * @param seed initial seed value.
     * @return 32 bits ({@code int}) hash of the array.
     */
    public static int hash32(final byte[] data, int length, int seed) {
        final int m = 0x5bd1e995;
        final int r = 24;

        int h = seed^length;

        int length4 = length/4;

        for (int i=0; i<length4; i++) {
                final int i4 = i*4;
                int k = (data[i4+0]&0xff) +((data[i4+1]&0xff)<<8)
                             +((data[i4+2]&0xff)<<16) +((data[i4+3]&0xff)<<24);

                k *= m;
                k ^= k >>> r;
                k *= m;

                h *= m;
                h ^= k;
        }

        switch (length%4) {
        case 3: h ^= (data[(length&~3) +2]&0xff) << 16;
        case 2: h ^= (data[(length&~3) +1]&0xff) << 8;
        case 1: h ^= (data[length&~3]&0xff);
                h *= m;
        }

        h ^= h >>> 13;
        h *= m;
        h ^= h >>> 15;

        return h;
    }

    /**
     * Generates 64 bits hash from Java String.
     *
     * <p><b>Note:</b> Always uses the following seed: {@code 0xe17a1465}.</p>
     *
     * @param text string to hash.
     * @return 64 bits ({@code long}) hash of the string.
     */
    public static long hash64(final String text) {
        final byte[] bytes = text.getBytes();
        return hash64(bytes, bytes.length, 0xe17a1465);
    }

    /**
     * Generates 32 bits hash from Java String.
     *
     * <p><b>Note:</b> Always uses the following seed: {@code 0x9747b28c}.</p>
     *
     * @param text string to hash.
     * @return 32 bits ({@code int}) hash of the string.
     */
    public static int hash32(final String text) {
        final byte[] bytes = text.getBytes();
        return hash32(bytes, bytes.length, 0x9747b28c);
    }

    /**
     * Generates 63 bits hash from Java String.
     *
     * <p>This method uses {@link #hash64(String)}. Take note of the default seed used.</p>
     *
     * @param text string to hash.
     * @return 63 bits ({@code long} without sign) hash of the string.
     */
    public static long hash63(final String text) {
        return hash64(text) & 0x7FFFFFFFFFFFFFFFL;
    }

    /**
     * Generates 31 bits hash from Java String.
     *
     * <p>This method uses {@link #hash32(String)}. Take note of the default seed used.</p>
     *
     * @param text string to hash.
     * @return 31 bits ({@code int} without sign) hash of the string.
     */
    public static int hash31(final String text) {
        return hash32(text) & 0x7FFFFFFF;
    }
}
