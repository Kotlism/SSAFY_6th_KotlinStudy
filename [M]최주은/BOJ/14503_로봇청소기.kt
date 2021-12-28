package `14503_로봇청소기`

lateinit var map:Array<Array<Int>>
lateinit var visited:Array<Array<Boolean>>
var N = 0
var M = 0

val dy = arrayOf(-1, 0, 1, 0)
val dx = arrayOf(0, 1, 0, -1)
var res = 0

fun main() {
    val (a,b) = readLine()!!.split(" ").map { it.toInt() }
    N = a
    M = b

    val (r, c, d) = readLine()!!.split(" ").map { it.toInt() }
    map = Array(N) { readLine()!!.split(" ").map { it.toInt() }. toTypedArray() }
    visited = Array(N) {Array(M) { false } }
    dfs(r,c,d)
    print(res)
}

fun dfs(r: Int, c: Int, d: Int) {
    // 1. 현재위치 청소
    if(!visited[r][c]) {
        visited[r][c] = true
        res++
    }

    // 2. 현재 위치 현재 방향에서 왼쪽 방향으로 탐색
    // 왼쪽 탐색
    var cnt = 0
    var dir = d
    while(cnt < 4){
        val leftY = r + dy[(dir + 3)%4]
        val leftX = c + dx[(dir + 3)%4]

        if(leftY >= 0 || leftY < N || leftX >= 0 || leftX < M){
            if(map[leftY][leftX] == 0 && !visited[leftY][leftX]) {
                dfs(leftY, leftX, (dir+3)%4)
                return
            }
        }

        dir = (dir + 3)%4
        cnt++
    }

    // 후진 가능한지 탐색
    val ny = r + dy[(dir + 2)%4]
    val nx = c + dx[(dir + 2)%4]

    if(map[ny][nx] == 1) return
    dfs(ny, nx, dir)
}
