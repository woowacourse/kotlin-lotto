package lotto

// fun main() {
//    val lottoGameController = LottoGameController()
//    lottoGameController.start()
// }

fun main() {
    val input = readln().toInt()

    var c = 1
    repeat(input) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        println("Case #$c: ${a + b}")
        c += 1
    }
}
