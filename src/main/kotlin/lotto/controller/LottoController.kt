package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoNumber
import lotto.domain.LottoResult
import lotto.domain.Lottos
import lotto.domain.WinningLotto
import lotto.view.View

class LottoController {
    private lateinit var winningLotto: WinningLotto
    private lateinit var boughtLottos: Lottos

    fun run() {
        buyLottos()
        readWinningLotto()
        showResult()
    }

    private fun buyLottos() {
        val price: Int = View.readPrice()
        val lottoCount: Int = price / Lotto.PRICE
        val lottoNumbers: List<List<Int>> = List(lottoCount) { makeRandomNumbers() }
        val lottos: Lottos = convertLottos(lottoCount, lottoNumbers)
        boughtLottos = lottos
        View.showLottoCount(boughtLottos.value.size)
        View.showLottos(lottoNumbers.map { lottoNumber: List<Int> -> lottoNumber.sorted() })
    }

    private fun convertLottos(
        lottoCount: Int,
        lottoNumbers: List<List<Int>>,
    ): Lottos =
        Lottos.buy(
            count = lottoCount,
            lottos =
                lottoNumbers
                    .map { lottoNumber: List<Int> ->
                        Lotto(lottoNumber.map { number: Int -> LottoNumber(number) }.toSet())
                    }.toSet(),
        )

    private fun readWinningLotto() {
        val lottoNumbers: List<Int> = View.readLottoNumbers()
        val lotto = Lotto(lottoNumbers.map { number: Int -> LottoNumber(number) }.toSet())
        val bonusNumber = LottoNumber(View.readBonusNumber())
        winningLotto = WinningLotto(lotto, bonusNumber)
    }

    private fun showResult() {
        val lottoResults: List<LottoResult> = boughtLottos.value.map { lotto -> LottoResult.from(winningLotto, lotto) }
        val lottoResultsDescriptions: List<String> =
            LottoResult.entries.drop(1).map { entry: LottoResult ->
                "${entry.matchCount}개 일치${getBonusBallDescription(entry)} (${entry.prizeAmount}원) - ${
                    countLottoResult(lottoResults, entry)
                }개"
            }
        View.showResult(lottoResults = lottoResultsDescriptions, profitRate = lottoResults.getProfitRate)
    }

    private fun getBonusBallDescription(entry: LottoResult): String =
        if (entry.bonusMatched == LottoResult.BonusMatched.YES) ", 보너스 볼 일치" else ""

    private fun countLottoResult(
        userLottoResults: List<LottoResult>,
        entry: LottoResult,
    ): Int =
        userLottoResults.count { lottoResult: LottoResult ->
            lottoResult == entry
        }

    private fun makeRandomNumbers(): List<Int> = LottoNumber.RANGE.shuffled().subList(0, Lotto.SIZE)

    private val List<LottoResult>.getProfitRate: Double
        get() {
            val profit: Long = this.sumOf { lottoResult: LottoResult -> lottoResult.prizeAmount.toLong() }
            val profitRate = profit / (this.size * Lotto.PRICE).toDouble()
            return profitRate
        }
}
