package today5

import java.util.*

private var N = 0
private var M = 0
private var cnt = 1
private lateinit var board: Array<Array<Int>>
private var dx = arrayOf(-1, 0, 1, 0)
private var dy = arrayOf(0, 1, 0, -1)
fun main() = with(Scanner(System.`in`)) {
    N = nextInt();M = nextInt();
    val r = nextInt();
    val c = nextInt();
    val d = nextInt()
    board = Array(N) { Array(M) { nextInt() } }
    robot(r, c, d)
    println(cnt)
}

fun robot(r: Int, c: Int, d: Int) {
    //phase 1
    board[r][c] = -1

    //phase 2-a,2-b
    //파라미터로 준 d값을 변화시켜나가고싶은데 아래처럼하는것말고는 방법이없는듯?
    var d = d
    var save = d
    for (i in dx.indices) {
        d = (d + 3) % 4
        var newx = r + dx[d]
        var newy = c + dy[d]

        if (isNotWall(newx, newy) && board[newx][newy] == 0) {
            cnt++
            robot(newx, newy, d)
            return
        }
    }
    //phase 2-c,2-d
    var newd = (save + 2) % 4
    var newx = r + dx[newd]
    var newy = c + dy[newd]
    if (isNotWall(newx, newy) && board[newx][newy] != 1) {
        robot(newx, newy, save)
    }


}

fun isNotWall(x: Int, y: Int): Boolean = x in 0 until N && y in 0 until M