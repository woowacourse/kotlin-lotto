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

    fun buyLotto() {
        val price: Int = View.readPrice()
        View.showLottoCount(price / Lotto.PRICE)
        boughtLottos = makeLotto(price)
        readWinningLotto()
        showResult()
    }

    private fun makeLotto(price: Int): Lottos {
        val lottoNumbers: List<List<Int>> = List(price / Lotto.PRICE) { makeRandomNumbers(Lotto.NUMBERS_SIZE) }
        View.showLottos(lottoNumbers.map { lottoNumber: List<Int> -> lottoNumber.sorted() })
        val lottos: Lottos = convertToLottos(price, lottoNumbers)
        return lottos
    }

    private fun readWinningLotto() {
        val lottoNumbers: List<Int> = View.readLottoNumbers()
        val lotto = Lotto(lottoNumbers.map { number: Int -> LottoNumber(number) }.toSet())
        val bonusNumber = LottoNumber(View.readBonusNumber())
        winningLotto = WinningLotto(lotto, bonusNumber)
    }

    private fun showResult() {
        val lottoResults: List<LottoResult> = boughtLottos.value.map { lotto -> LottoResult.from(winningLotto, lotto) }
        val lottoPrizeEntry: List<LottoResult> =
            LottoResult.entries
                .filterNot { result: LottoResult -> result.prizeAmount == 0 }
                .sortedBy { result: LottoResult -> result.prizeAmount }
        val lottoResultsDescriptions: List<String> = makeLottoResultDescription(lottoPrizeEntry, lottoResults)
        View.showResult(lottoResults = lottoResultsDescriptions, profitRate = lottoResults.profitRate)
    }

    private fun makeLottoResultDescription(
        lottoPrizeEntry: List<LottoResult>,
        lottoResults: List<LottoResult>,
    ): List<String> =
        lottoPrizeEntry.map { entry: LottoResult ->
            LOTTO_RESULT_DESCRIPTION_TEMPLATE.format(
                entry.matchCount,
                getBonusNumberDescription(entry),
                entry.prizeAmount,
                countLottoResult(lottoResults, entry),
            )
        }

    private fun convertToLottos(
        price: Int,
        lottoNumbers: List<List<Int>>,
    ): Lottos =
        Lottos.buy(
            price = price,
            lottos =
                lottoNumbers
                    .map { lottoNumber: List<Int> ->
                        Lotto(lottoNumber.map { number: Int -> LottoNumber(number) }.toSet())
                    }.toSet(),
        )

    private fun getBonusNumberDescription(entry: LottoResult): String =
        if (entry.bonusMatched == LottoResult.BonusMatched.YES) BONUS_NUMBER_MATCHED else ""

    private fun countLottoResult(
        userLottoResults: List<LottoResult>,
        entry: LottoResult,
    ): Int =
        userLottoResults.count { lottoResult: LottoResult ->
            lottoResult == entry
        }

    private fun makeRandomNumbers(size: Int): List<Int> = (LottoNumber.MIN..LottoNumber.MAX).shuffled().subList(0, size)

    private val List<LottoResult>.profitRate: Double
        get() {
            val profit: Long = this.sumOf { lottoResult: LottoResult -> lottoResult.prizeAmount.toLong() }
            val profitRate = profit / (this.size * Lotto.PRICE).toDouble()
            return profitRate
        }

    companion object {
        private const val LOTTO_RESULT_DESCRIPTION_TEMPLATE = "%d개 일치%S (%d원) - %d개"
        private const val BONUS_NUMBER_MATCHED = ", 보너스 볼 일치"
    }
}
