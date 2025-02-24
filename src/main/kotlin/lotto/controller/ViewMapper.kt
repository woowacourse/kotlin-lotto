package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoNumber
import lotto.domain.LottoResult
import lotto.domain.ProfitCalculator
import lotto.domain.WinLotto

object ViewMapper {
    const val LOTTO_RESULT_DESCRIPTION_TEMPLATE = "%d개 일치%S (%d원) - %d개"
    const val BONUS_NUMBER_MATCHED = ", 보너스 볼 일치"

    fun mapToLottoCount(pay: Int): Int = pay / Lotto.Companion.PRICE

    fun mapToRandomLottos(pay: Int): List<Lotto> = Lotto.Companion.buyRandomLottos(pay)

    fun mapToOutput(boughtLottos: List<Lotto>): List<List<Int>> =
        boughtLottos.map { lotto: Lotto ->
            lotto.numbers.map { lottoNumber: LottoNumber -> lottoNumber.value }.sorted()
        }

    fun mapToLottoResultsDescriptions(
        boughtLottos: List<Lotto>,
        winLotto: WinLotto,
    ): List<String> {
        val lottoResults: List<LottoResult> =
            boughtLottos.map { boughtLotto -> LottoResult.from(winLotto, boughtLotto) }
        val lottoPrizeEntries: List<LottoResult> =
            LottoResult.entries
                .filterNot { result: LottoResult -> result.prizeAmount == 0 }
                .sortedBy { result: LottoResult -> result.prizeAmount }
        val lottoResultsDescriptions: List<String> = makeLottoResultDescription(lottoResults, lottoPrizeEntries)
        return lottoResultsDescriptions
    }

    fun mapToProfitRate(
        boughtLottos: List<Lotto>,
        winLotto: WinLotto,
    ): Double {
        val lottoResults: List<LottoResult> =
            boughtLottos.map { boughtLotto -> LottoResult.from(winLotto, boughtLotto) }
        return ProfitCalculator.calculateProfitRate(lottoResults)
    }

    private fun makeLottoResultDescription(
        lottoResults: List<LottoResult>,
        lottoPrizeEntry: List<LottoResult>,
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
}
