import java.util.*

fun main() {
    val n = readLine()!!.toInt()
    val map = Array(n) {IntArray(n) { 0 } }

    for (i in 1..readLine()!!.toInt()) {
        val (x, y) = readLine()!!.split(" ").map { it.toInt() - 1 }
        map[x][y] = -1
    }

    val info: Queue<Pair<Int, Char>> = LinkedList()
    for (i in 1..readLine()!!.toInt()) {
        val (t, dir) = readLine()!!.split(" ")
        info.add(Pair(t.toInt(), dir[0]))
    }

    var time = 0
    var dir = 0
    val snake: Deque<Pair<Int, Int>> = LinkedList()
    val dx = arrayOf(0, 1, 0, -1); val dy = arrayOf(1, 0, -1, 0)

    snake.add(Pair(0, 0))
    while (true) {
        time++

        val nx = snake.last.first + dx[dir]
        val ny = snake.last.second + dy[dir]

        if (nx < 0 || nx >= n || ny < 0 || ny >= n)
            break
        if (snake.contains(Pair(nx, ny)))
            break

        snake.add(Pair(nx, ny))
        if (map[nx][ny] == -1) {
            map[nx][ny] = 0
        } else {
            snake.removeFirst()
        }

        if (info.isNotEmpty() && info.peek().first == time) {
            when(info.remove().second) {
                'L' -> dir = if (dir == 0) 3 else dir - 1
                'D' -> dir = (dir + 1) % 4
            }
        }
    }

    println(time)
}