package ir.mrahimy.conceal.data

data class Waver(
    val data: LongArray,
    val sampleRate: Long,
    val channelCount: Int,
    val frameCount: Long,
    val validBits: Int
) {
    var maxValue: Long = 1
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Waver

        if (!data.contentEquals(other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        return data.contentHashCode()
    }
}

fun LongArray.mapToUniformDouble(): DoubleArray {
    val max = absolute().max()?.toDouble() ?: 1.0
    return map {
        (it.toDouble() / max)
    }.toDoubleArray()
}

fun LongArray.maxValue() = absolute().max() ?: 1

fun DoubleArray.mapToRgbValue(): IntArray {
    return map {
        (it * 255).toInt()
    }.toIntArray()
}

fun LongArray.absolute(): LongArray {
    return map {
        if (it < 0) it * -1 else it
    }.toLongArray()
}
