fun main() {
    val N = readLine()!!.toInt()
    val K = readLine()!!.toInt()

    val sensors = readLine()!!.split(" ").map { it.toInt() }.distinct().sorted()

    val distance = mutableListOf<Int>()
    for (i in 1 until sensors.size) {
        distance.add(sensors[i] - sensors[i-1])
    }

    distance.sortDescending()

    var sum = 0
    for (i in K-1 until distance.size) {
        sum += distance[i]
    }

    println(sum)
}