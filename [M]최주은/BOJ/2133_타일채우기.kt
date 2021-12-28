val N = readLine()!!.toInt()
val d = Array(1001) { 0 }

fun main() {
    // 홀수면 무조건 짝이 맞지 않아 0이다.
    if(N%2!=0) print(0)
    else print(dp(N))
}

fun dp(n: Int) :Int {
    if(n == 0) return 1
    if(n == 1) return 0
    if(n == 2) return 3
    if(d[n] != 0) return d[n]

    var res = 3 * dp(n-2)
    for (i in 3..n){
        if (i % 2 == 0) res += 2 * dp(n - i)
    }

    d[n] = res
    return d[n]
}
