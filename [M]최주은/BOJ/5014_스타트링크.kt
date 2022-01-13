package boj.`5014_스타트링크`

import java.awt.Point
import java.util.*
import kotlin.system.exitProcess

var F = 0
var G = 0
var S = 0
var U = 0
var D = 0
var visited = Array(1000001) { false }

fun main() {
    val (f, s, g, u, d) = readLine()!!.split(" ").map { it.toInt() }
    F = f
    G = g
    S = s
    U = u
    D = d

    if(S == G) {
        print(0)
        exitProcess(0)
    }

    bfs()
    print("use the stairs")
}


fun bfs() {
    val queue = LinkedList<Point>()
    queue.offer(Point(S, 0))
    visited[S] = true

    while(queue.isNotEmpty()){
        val p = queue.poll()

        if (p.x == G){
            print(p.y)
            exitProcess(0)
        }

        val up = p.x + U
        val down = p.x - D

        if(up in 1..F && !visited[up]) {
            visited[up] = true
            queue.offer(Point(up, p.y+1))
        }
        if(down in 1..F && !visited[down]){
            visited[down] = true
            queue.offer(Point(down, p.y+1))
        }
    }
}
