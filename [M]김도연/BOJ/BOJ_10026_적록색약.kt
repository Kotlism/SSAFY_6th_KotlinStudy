import java.util.*

fun main() {
    val n = readLine()!!.toInt()
    val array = Array(n) { readLine()!!.toCharArray() }

    val used1 = Array(n) { BooleanArray(n) { false } }
    var sum1 = 0
    for (i in array.indices) {
        for (j in array[i].indices) {
            if (!used1[i][j]) {
                sum1 += bfs1(i, j, array, used1)
            }
        }
    }

    val used2 = Array(n) { BooleanArray(n) { false } }
    var sum2 = 0
    for (i in array.indices) {
        for (j in array[i].indices) {
            if (!used2[i][j]) {
                sum2 += bfs2(i, j, array, used2)
            }
        }
    }

    println("$sum1 $sum2")
}

private val dirX = arrayOf(-1, 1, 0, 0)
private val dirY = arrayOf(0, 0, -1, 1)

fun bfs1(x: Int, y: Int, array: Array<CharArray>, used: Array<BooleanArray>): Int {
    val queue: Queue<Loc> = LinkedList()

    queue.add(Loc(x, y, array[x][y]))
    used[x][y] = true
    while (queue.isNotEmpty()) {
        val loc = queue.remove()

        for (d in dirX.indices) {
            val nx = loc.x + dirX[d]
            val ny = loc.y + dirY[d]

            if (nx < 0 || nx >= array.size || ny < 0 || ny >= array.size)
                continue

            if (used[nx][ny])
                continue

            if (array[nx][ny] == loc.color) {
                used[nx][ny] = true
                queue.add(Loc(nx, ny, loc.color))
            }
        }
    }

    return 1
}

fun bfs2(x: Int, y: Int, array: Array<CharArray>, used: Array<BooleanArray>): Int {
    val queue: Queue<Loc> = LinkedList()

    queue.add(Loc(x, y, array[x][y]))
    used[x][y] = true
    while (queue.isNotEmpty()) {
        val loc = queue.remove()

        for (d in dirX.indices) {
            val nx = loc.x + dirX[d]
            val ny = loc.y + dirY[d]

            if (nx < 0 || nx >= array.size || ny < 0 || ny >= array.size)
                continue

            if (used[nx][ny])
                continue

            when (loc.color) {
                'R', 'G' -> {
                    if (array[nx][ny] == 'R' || array[nx][ny] == 'G') {
                        used[nx][ny] = true
                        queue.add(Loc(nx, ny, loc.color))
                    }
                }
                else -> {
                    if (array[nx][ny] == loc.color) {
                        used[nx][ny] = true
                        queue.add(Loc(nx, ny, loc.color))
                    }
                }
            }
        }
    }

    return 1
}

data class Loc(val x: Int, val y: Int, val color: Char)