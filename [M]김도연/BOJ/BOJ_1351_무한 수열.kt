fun main() {
    val (N, P, Q) = readLine()!!.split(" ").map { it.toLong() }

    /*
    10^2는 Int범위를 벗어나서 배열로 만들 수 없다.
    val A = Array(N+1) { 0L }
    A[0] = 1
    */

    /*
    // 방법 1: tableDP 시간이 너무 오래걸림
    if (N == 0) {
        println(1)
        return
    }

    for (i in 1..N) {
        val p = i / P
        val q = i / Q
        A[i] = A[p] + A[q]
    }

    println(A[N])
    */

    val map = HashMap<Long, Long>()
    map[0] = 1
    println(run(N, P, Q, map))
}

fun run(N: Long, P: Long, Q: Long, map: HashMap<Long, Long>): Long {
    if (!map.containsKey(N)) {
        map[N] = run(N/P, P, Q, map) + run(N/Q, P, Q, map)
    }

    return map[N]!!
}