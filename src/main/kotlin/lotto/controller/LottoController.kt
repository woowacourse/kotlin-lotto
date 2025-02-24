package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoNumber
import lotto.domain.LottoResult
import lotto.domain.ProfitCalculator
import lotto.domain.WinLotto
import lotto.view.View

class LottoController {
    private lateinit var winLotto: WinLotto
    private lateinit var boughtLottos: List<Lotto>

    fun buyLotto() {
        val pay: Int = View.readPay()
        View.showLottoCount(pay / Lotto.PRICE)
        makeLotto(pay)
        readWinningLotto()
        showResult()
    }

    private fun makeLotto(pay: Int) {
        val lottos: List<Lotto> = Lotto.buyRandomLottos(pay)
        boughtLottos = lottos
        View.showLottos(
            boughtLottos.map { lotto: Lotto ->
                lotto.numbers.map { lottoNumber: LottoNumber -> lottoNumber.value }.sorted()
            },
        )
    }

    private fun readWinningLotto() {
        val lottoNumbers: List<Int> = View.readLottoNumbers()
        val lotto = Lotto(lottoNumbers.map { number: Int -> LottoNumber(number) }.toSet())
        val bonusNumber = LottoNumber(View.readBonusNumber())
        winLotto = WinLotto(lotto, bonusNumber)
    }

    private fun showResult() {
        val lottoResults: List<LottoResult> = boughtLottos.map { lotto -> LottoResult.from(winLotto, lotto) }
        val lottoPrizeEntry: List<LottoResult> =
            LottoResult.entries
                .filterNot { result: LottoResult -> result.prizeAmount == 0 }
                .sortedBy { result: LottoResult -> result.prizeAmount }
        val lottoResultsDescriptions: List<String> = makeLottoResultDescription(lottoPrizeEntry, lottoResults)
        val profitRate: Double = ProfitCalculator.calculateProfitRate(lottoResults)
        View.showResult(lottoResultsDescriptions, profitRate)
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

    private fun getBonusNumberDescription(entry: LottoResult): String =
        if (entry.bonusMatched == LottoResult.BonusMatched.YES) BONUS_NUMBER_MATCHED else ""

    private fun countLottoResult(
        userLottoResults: List<LottoResult>,
        entry: LottoResult,
    ): Int =
        userLottoResults.count { lottoResult: LottoResult ->
            lottoResult == entry
        }

    companion object {
        private const val LOTTO_RESULT_DESCRIPTION_TEMPLATE = "%d개 일치%S (%d원) - %d개"
        private const val BONUS_NUMBER_MATCHED = ", 보너스 볼 일치"
    }
}
