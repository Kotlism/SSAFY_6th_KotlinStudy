import java.lang.Math.max

fun main() {
    val str1 = readLine()!!.toCharArray()
    val str2 = readLine()!!.toCharArray()

    val dp = Array(str2.size+1) { Array(str1.size+1) { 0 } }

    for (i in str2.indices) {
        for (j in str1.indices) {
            if (str2[i] == str1[j]) {
                dp[i+1][j+1] = dp[i][j] + 1
            } else {
                dp[i+1][j+1] = max(dp[i][j+1], dp[i+1][j])
            }
        }
    }

    println(dp[str2.size][str1.size])
}