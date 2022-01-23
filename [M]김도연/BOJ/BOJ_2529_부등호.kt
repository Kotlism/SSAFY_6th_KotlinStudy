import kotlin.math.*

var max = 0L
var min = 9876543210

fun main() {
    val used = BooleanArray(10)
    val k = readLine()!!.toInt()
    val inEquals = readLine()!!.split(" ")

    for (i in used.indices) {
        used[i] = true
        run(inEquals, 0, k, used, "$i")
        used[i] = false
    }

    println(if (max.toString().length != k+1) "0$max" else max)
    println(if (min.toString().length != k+1) "0$min" else min)
}

fun run(list: List<String>, start: Int, end: Int, used: BooleanArray, sNum: String) {
    if (start == end) {
        val num = sNum.toLong()
        max = max(max, num)
        min = min(min, num)
        return
    }

    if (list[start] == "<") {
        val index = sNum[sNum.lastIndex] - '0'
        for (i in index until 10) {
            if (!used[i]) {
                used[i] = true
                run(list, start+1, end, used, "$sNum$i")
                used[i] = false
            }
        }
    } else {
        val index = sNum[sNum.lastIndex] - '0'
        for (i in index downTo 0) {
            if (!used[i]) {
                used[i] = true
                run(list, start+1, end, used, "$sNum$i")
                used[i] = false
            }
        }
    }
}