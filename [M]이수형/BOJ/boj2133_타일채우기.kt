package today5

fun main() {
    var N = readLine()!!.toInt()
    var dp = Array(31) { 0 }
    //기본개념 3*2의 개수가 3개나오는데 짝수개에선 이것을 이용하여 갯수를 구할수 있다
    //그러나 특수 케이스가 나오는데 이것은 2씩 늘어날때마다 이전 케이스의 2배씩 늘어나는것
    //dp[0]을 1로해야 규칙이 완성됨
    dp[0] = 1
    dp[2] = 3
    dp[4] = 11
    if (N % 2 == 0) {
        for (i in 4..N + 1 step 2) {
            dp[i] = dp[i - 2] * dp[2]
            for (j in 0 until i - 2 step 2) {
                dp[i] += dp[j] * 2
            }
        }
    }
    println(dp[N])
}
