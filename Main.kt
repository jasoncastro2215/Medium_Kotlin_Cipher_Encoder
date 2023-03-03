import java.io.File

fun encode() {
    println("Input string:")
    val str = readln()
    println("Encoded string:")
    var encoded = ""
    for (c in str.toCharArray())
        encoded += String.format("%07d", Integer.toBinaryString(c.code).toInt())
    var currentChar = encoded[0]
    var ctr = 1
    var result = ""
    for (i in 1 until encoded.length) {
        if (encoded[i] != currentChar) {
            result += "${if (currentChar == '1') "0" else "00"} ${"0".repeat(ctr)} "
            currentChar = encoded[i]
            ctr = 0
        }
        ctr++
    }
    result += "${if (currentChar == '1') "0" else "00"} ${"0".repeat(ctr)}"
    println("$result\n")
}

fun decode() {
    try {
        println("Input encoded string:")
        val encoded = readln().trim()
        for (c in encoded.toCharArray()) {
            if (!(c == ' ' || c == '0'))
                throw Exception()
        }
        var binary = ""
        val strArr = encoded.split(" ")
        if (strArr.size % 2 != 0)
            throw Exception()
        for (i in strArr.indices step 2) {
            val strLen = strArr[i].length
            if (strLen > 2)
                throw Exception()
            binary += (if (strLen == 2) "0" else "1").repeat(strArr[i + 1].length)
        }
        if (binary.length % 7 != 0)
            throw Exception()
        println("Decoded string:")
        for (i in binary.indices step 7)
            print("${binary.substring(i, i + 7).toInt(2).toChar()}")
        println("\n")
    } catch (e: Exception) {
        println("Encoded string is not valid.\n")
    }
}

fun main() {
    println("""The encoding principle is simple. The input message consists of ASCII characters (7-bit). You need to transform the text into the sequence of 0 and 1.
The encoded output message consists of blocks of 0. A block is separated from another block by a space.

Two consecutive blocks are used to produce a series of the same value bits (only 1 or0 values):

First block: it is always 0 or 00. If it is 0, then the series contains 1, if not, it contains 0
Second block: the number of 0 in this block is the number of bits in the series
Let's take a simple example with a message which consists of only one character C. The C symbol in binary is represented as 1000011, so with this technique this gives:

0 0 (the first series consists of only a single 1);
00 0000 (the second series consists of four 0);
0 00 (the third consists of two 1)
So C is coded as: 0 0 00 0000 0 00""")
    while (true) {
        println("\nPlease input operation (encode/decode/exit):")
        when (val chosen = readln()) {
            "encode" -> encode()
            "decode" -> decode()
            "exit" -> {
                print("Bye!")
                break
            }

            else -> println("There is no '$chosen' operation\n")
        }
    }
}