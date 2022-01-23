import java.util.*

fun main() {
    val (N, E) = readLine()!!.split(" ").map { it.toInt() }
    val edges = Array(N+1) { Array(N+1) { 0 } }
    for (i in 1..E) {
        val (a, b, c) = readLine()!!.split(" ").map { it.toInt() }
        edges[a][b] = c
        edges[b][a] = c
    }
    val (v1, v2) = readLine()!!.split(" ").map { it.toInt() }

    val d1toV1  = dijkstra(1, v1, edges)
    val d1toV2  = dijkstra(1, v2, edges)
    val dV1toV2 = dijkstra(v1, v2, edges)
    val dNtoV1  = dijkstra(N, v1, edges)
    val dNtoV2  = dijkstra(N, v2, edges)

    var distance = -1
    if (d1toV1 >= 0 && dV1toV2 >= 0 && dNtoV2 >= 0) {
        distance = d1toV1 + dV1toV2 + dNtoV2
    }

    if (d1toV2 >= 0 && dV1toV2 >= 0 && dNtoV1 >= 0) {
        distance = if (distance > 0)
            distance.coerceAtMost(d1toV2 + dV1toV2 + dNtoV1)
        else
            d1toV2 + dV1toV2 + dNtoV1
    }

    println(distance)
}

fun dijkstra(start: Int, end: Int, edges: Array<Array<Int>>): Int {
    val pq = PriorityQueue<Pair<Int, Int>>(kotlin.Comparator { o1, o2 -> o1.second - o2.second })
    val distance = Array(edges.size) { -1 }

    distance[start] = 0
    for (i in edges[start].indices) {
        if (edges[start][i] > 0) {
            distance[i] = edges[start][i]
            pq.add(Pair(i, distance[i]))
        }
    }

    while (pq.isNotEmpty()) {
        val node = pq.remove()
        val idx = node.first

        for (i in edges[idx].indices) {
            if (edges[idx][i] > 0)
                if (distance[i] == -1 || distance[i] > distance[idx] + edges[idx][i]) {
                    distance[i] = distance[idx] + edges[idx][i]
                    pq.add(Pair(i, distance[i]))
                }
        }
    }

    return distance[end]
}