package lotto.view

import lotto.domain.Lottery

class OutputView {
    fun printLotteries(lotteries: List<Lottery>) {
        println("${lotteries.size}$NUMBER_OF_LOTTERY_GUIDE")
        lotteries.forEach { lottery ->
            println(lottery.numbers.sortedBy { it.number })
        }
    }

    companion object {
        private const val NUMBER_OF_LOTTERY_GUIDE = "개를 구매했습니다."
    }
}
