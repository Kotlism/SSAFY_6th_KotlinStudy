package boj.`5430_AC`

val T = readLine()!!.toInt() // 테스트 케이스 전체 수
var tc = 0 // 테스트 케이스

val deque = ArrayDeque<Int>() // 정해진 배열을 담는 deque

fun main() {
    while (tc++ < T){
        deque.clear()
        var dir = 0 // 반대쪽으로 읽는지 아닌지 확인
        val p = readLine()!!.toCharArray() // 수행할 함수 p
        val n = readLine()!!.toInt() // 배열에 들어있는 수의 개수
        val tmp = readLine()!!.drop(1).dropLast(1)
        if (tmp != "") deque.addAll(tmp.split(",").map { it.toInt() })

        var trigger = false

        for (i in p.indices){
            if (p[i] == 'R'){
                dir = (dir+1)%2
            }else {
                // 만약 덱이 다 비워져지면 에러를 나타낸다
                if (deque.isEmpty()){
                    println("error")
                    trigger = true
                    break
                }else{
                    if (dir == 0) deque.removeFirst()
                    else deque.removeLast()
                }
            }
        }

        if(!trigger) {
            if (dir == 0) println("["+deque.joinToString(",")+"]")
            else println("["+deque.reversed().joinToString(",")+"]")
        }
    }
}
