import java.util.*

fun main() {
    val (F, S, G, U, D) = readLine()!!.split(" ").map { it.toInt() }

    val count = run(S, G, F, U, D)

    println(if (count == -1) "use the stairs" else count)
}

fun run(start: Int, goal: Int, max: Int, up: Int, down: Int): Int {
    val que: Queue<Int> = LinkedList()
    val used = BooleanArray(max+1)

    que.add(start)
    que.add(0)
    used[start] = true

    while (que.isNotEmpty()) {
        var cur = que.remove()
        var count = que.remove()

        if (cur == goal)
            return count

        if (cur + up <= max && !used[cur + up]) {
            used[cur + up] = true
            que.add(cur + up)
            que.add(count+1)
        }

        if (cur - down > 0 && !used[cur - down]) {
            used[cur - down] = true
            que.add(cur - down)
            que.add(count+1)
        }
    }

    return -1
}