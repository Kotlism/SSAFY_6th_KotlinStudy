fun main() {
    val (N, S) = readLine()!!.split(" ").map { it.toInt() }
    val arr = readLine()!!.split(" ").map { it.toInt() }

    // dfs
    val count = run(arr, 0, N, S, 0)

    println(if (S == 0) count - 1 else count)
}

fun run(array: List<Int>, start: Int, end: Int, tSum: Int, sum: Int): Int {
    if (start == end) {
        if (tSum == sum) {
            return 1
        }
        return 0
    }

    var count = 0
    count += run(array, start+1, end, tSum, sum)
    count += run(array, start+1, end, tSum, sum + array[start])

    return count
}