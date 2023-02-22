package domain.game

import domain.lotto.PurchasedLotto
import domain.lotto.WinningLotto
import domain.lotto.number.LottoNumber
import domain.money.Money
import domain.rank.Rank
import kotlin.math.floor

class LottoGame(
    private val lottoMachine: LottoMachine
) {
    fun purchaseLottos(money: Money): List<PurchasedLotto> = lottoMachine.purchaseAutoLottos(money)

    fun matchLottos(
        purchasedLottos: List<PurchasedLotto>,
        winningLotto: WinningLotto,
        bonusNumber: LottoNumber
    ): Map<Rank, Int> {
        val matchResult = mutableMapOf<Rank, Int>()
        purchasedLottos.forEach {
            val rank = it.matchLotto(winningLotto, bonusNumber)
            val originCount = matchResult.getOrDefault(rank, 0)

            matchResult[rank] = originCount + MATCHING_POINT
        }

        return matchResult
    }

    fun calculateIncomeRate(matchResult: Map<Rank, Int>, investMoney: Money): Double {
        val income = calculateIncome(matchResult)
        return floor((income / investMoney.value.toDouble()) * 100) / 100
    }

    private fun calculateIncome(matchResult: Map<Rank, Int>): Long {
        var income = 0L

        matchResult.entries.forEach { (rank, count) ->
            income += (rank.winningMoney * count)
        }
        return income
    }

    companion object {
        private const val MATCHING_POINT = 1
    }
}
