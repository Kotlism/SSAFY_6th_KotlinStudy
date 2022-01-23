import java.util.*
import kotlin.math.abs

fun main() {
    val pQueue = PriorityQueue<Int>(kotlin.Comparator { o1, o2 ->
        when {
            abs(o1) == abs(o2)  -> return@Comparator o1 - o2
            else -> return@Comparator abs(o1) - abs(o2)
        }
    })

    val sb = StringBuffer()
    val n = readLine()!!.toInt()
    for (i in 1..n) {
        when (val input = readLine()!!.toInt()) {
            0 -> {
                if (pQueue.isNotEmpty())    sb.append(pQueue.remove()).append("\n")
                else                        sb.append(0).append("\n")
            }
            else -> pQueue.add(input)
        }
    }

    println(sb)
}