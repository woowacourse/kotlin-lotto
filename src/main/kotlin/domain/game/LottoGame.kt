package domain.game

import domain.lotto.PurchasedLotto
import domain.lotto.WinningLotto
import domain.lotto.number.LottoNumber
import domain.rank.Rank
import kotlin.math.floor

class LottoGame(
    private val lottoMachine: LottoMachine
) {
    private lateinit var winningLotto: WinningLotto
    private var bonusNumber: LottoNumber? = null

    fun initWinningLottoNumbers(lottoNumbers: List<Int>, bonusNumber: Int) {
        this.bonusNumber = LottoNumber(bonusNumber)
        winningLotto = WinningLotto(lottoNumbers.map { LottoNumber(it) }, this.bonusNumber!!)
    }

    fun purchaseLottos(money: Int): List<PurchasedLotto> = lottoMachine.purchaseLottos(money)

    fun matchLottos(purchasedLottos: List<PurchasedLotto>): Map<Rank, Int> {
        val matchResult = mutableMapOf<Rank, Int>()
        purchasedLottos.forEach {
            val rank = it.matchLotto(winningLotto, bonusNumber!!)
            val originCount = matchResult.getOrDefault(rank, 0)

            matchResult[rank] = originCount + 1
        }

        return matchResult
    }

    fun calculateIncomeRate(matchResult: Map<Rank, Int>, investMoney: Int): Double {
        val income = calculateIncome(matchResult)
        return floor((income / investMoney.toDouble()) * 100) / 100
    }

    private fun calculateIncome(matchResult: Map<Rank, Int>): Long {
        var income = 0L

        matchResult.entries.forEach { (rank, count) ->
            income += (rank.winningMoney * count)
        }
        return income
    }
}
