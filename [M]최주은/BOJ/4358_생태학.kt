package boj.`4358_생태학`

import java.util.*

fun main() {
    val htree = TreeMap<String, Double>()
    var max = 0.0

    while(true){
        val tmp = readLine() ?: break
        htree[tmp] = htree.getOrDefault(tmp, 0.0) + 1.0
        max++
    }

    while(htree.isNotEmpty()){
        val tmp = htree.pollFirstEntry()
        val value = (tmp.value / max)*100
        println("${tmp.key} ${String.format("%.4f", value)}")
    }
}
