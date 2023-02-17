package domain.game

import domain.lotto.PurchasedLotto
import domain.lotto.WinningLotto
import domain.lotto.number.LottoNumber
import domain.money.Money
import domain.rank.Rank
import kotlin.math.floor

class LottoGame(
    private val winningLotto: WinningLotto,
    private val bonusNumber: LottoNumber,
) {
    fun matchLottos(purchasedLottos: List<PurchasedLotto>): Map<Rank, Int> {
        val matchResult = mutableMapOf<Rank, Int>()
        purchasedLottos.forEach {
            val rank = it.matchLotto(winningLotto, bonusNumber)
            val originCount = matchResult.getOrDefault(rank, 0)
            matchResult[rank] = originCount + 1
        }

        return matchResult
    }

    fun calculateIncomeRate(matchResult: Map<Rank, Int>, investMoney: Money): Double {
        val income = calculateIncome(matchResult)
        return floor((income / investMoney.amount.toDouble()) * 100) / 100
    }

    private fun calculateIncome(matchResult: Map<Rank, Int>): Long {
        var income = 0L

        matchResult.entries.forEach { (rank, count) ->
            income += (rank.winningMoney * count)
        }
        return income
    }
}
