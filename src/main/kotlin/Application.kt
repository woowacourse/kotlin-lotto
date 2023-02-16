import lotto.domain.LotteryGenerator

fun main() {
    repeat(6) {
        print(LotteryGenerator().generateLottery().numbers[it].number.toString() + ", ")
    }
}
