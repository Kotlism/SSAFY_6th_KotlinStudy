package today5

import java.util.*


fun main() {
    val T = readLine()!!.toInt()
    repeat(T) {
        var p = readLine()!!.split("")
        var n = readLine()!!.toInt()
        val temp = readLine()!!
        val arr = temp.substring(1, temp.length - 1).split(",")
        val q = ArrayDeque<Int>()
        arr.forEach { if (it != "") q.add(it.toInt()) }
        println(change(q, p))

    }
}

fun change(q: Deque<Int>, p: List<String>): String {
    var check = true
    p.forEach {
        if (it == "R") {
            check = !check
        } else if (it == "D") {
            if (q.isEmpty()) {
                return "error"
            }
            if (check) {
                q.pollFirst()
            } else {
                q.pollLast()
            }
        }
    }

    var sb = StringBuilder("[")
    while (q.isNotEmpty()) {
        sb.append(
            if (check) {
                q.pollFirst()
            } else {
                q.pollLast()
            }
        )
        if (q.isNotEmpty()) {
            sb.append(",")
        }
    }
    sb.append("]")
    return sb.toString()
}