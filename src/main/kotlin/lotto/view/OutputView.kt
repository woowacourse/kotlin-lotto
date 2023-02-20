package lotto.view

import lotto.model.Rank
import lotto.model.UserLotto

object OutputView {
    private const val INSERT_MONEY = "구입금액을 입력해 주세요."
    private const val INSERT_MANUAL_LOTTO_NUMBER = "\n수동으로 구매할 로또 수를 입력해 주세요."
    private const val INSERT_MANUAL_LOTTO = "\n수동으로 구매할 번호를 입력해 주세요."
    private const val INSERT_WINNING_NUMBER = "\n지난 주 당첨 번호를 입력해 주세요."
    private const val INSERT_BONUS_BALL = "보너스 볼을 입력해 주세요."

    private const val PURCHASE = "\n수동으로 %d장, 자동으로 %d개를 구매했습니다."
    private const val WINNING_STATISTICS = "\n당첨 통계\n---------"
    private const val MATCH_STANDARD = "%d개 일치"
    private const val MATCH_STANDARD_WITH_BONUS = ", 보너스볼 일치"
    private const val MATCH_MONEY = "(%d원)"
    private const val MATCH_COUNT = "- %d개"
    private const val EARNING_RATE = "총 수익률은 %s입니다."
    private const val LOSING_MONEY = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)"

    fun printInsertMoneyMessage() {
        println(INSERT_MONEY)
    }

    fun printInsertManualNumber() {
        println(INSERT_MANUAL_LOTTO_NUMBER)
    }

    fun printInsertManualLotto() {
        println(INSERT_MANUAL_LOTTO)
    }

    fun printPurchase(numberOfManual: Int, numberOfAuto: Int) {
        println(PURCHASE.format(numberOfManual, numberOfAuto))
    }

    fun printUserLotto(userLotto: UserLotto) {
        userLotto.lotto.forEach { lotto ->
            println("[$lotto]")
        }
    }

    fun printInsertWinningNumber() {
        println(INSERT_WINNING_NUMBER)
    }

    fun printInsertBonusNumber() {
        println(INSERT_BONUS_BALL)
    }

    fun printResult(ranks: List<Int>, rates: String) {
        println(WINNING_STATISTICS)

        val size = Rank.values().size
        Rank.values().reversed().subList(1, size).forEachIndexed { index, rank ->
            printEachRankResult(ranks.reversed().subList(1, size)[index], rank)
        }
        printEarningRate(rates)
    }

    private fun printEarningRate(rates: String) {
        print(EARNING_RATE.format(rates))
        if (rates.toDouble() < 1)
            println(LOSING_MONEY)
    }

    private fun printEachRankResult(count: Int, rank: Rank) {
        var result = MATCH_STANDARD.format(rank.countOfMatch)
        if (rank.isContainBonus) {
            result += MATCH_STANDARD_WITH_BONUS
        }
        result += MATCH_MONEY.format(rank.winningMoney)
        result += MATCH_COUNT.format(count)

        println(result)
    }
}
