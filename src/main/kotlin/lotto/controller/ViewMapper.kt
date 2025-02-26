package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoResult
import lotto.domain.ProfitCalculator
import lotto.domain.WinLotto

class ViewMapper {
    val profitCalculator = ProfitCalculator()

    fun mapToOutput(boughtLottos: List<Lotto>): List<List<Int>> = boughtLottos.map(::sorted)

    fun mapToLottoResultsDescriptions(
        boughtLottos: List<Lotto>,
        win: WinLotto,
    ): List<String> {
        val lottoResults: List<LottoResult> = boughtLottos.toResults(win)
        val lottoPrizeEntries: List<LottoResult> = LottoResult.entries.sortedPrizes()
        val lottoResultsDescriptions: List<String> = makeLottoResultDescription(lottoResults, lottoPrizeEntries)
        return lottoResultsDescriptions
    }

    fun mapToProfitRate(
        boughtLottos: List<Lotto>,
        win: WinLotto,
    ): Double {
        val lottoResults: List<LottoResult> = boughtLottos.toResults(win)
        return profitCalculator.calculateProfitRate(lottoResults)
    }

    private fun sorted(lotto: Lotto): List<Int> = lotto.toList().sorted()

    private fun List<LottoResult>.sortedPrizes(): List<LottoResult> =
        LottoResult.entries
            .filterNot { result: LottoResult -> result.prizeAmount == 0 }
            .sortedBy { result: LottoResult -> result.prizeAmount }

    private fun List<Lotto>.toResults(win: WinLotto): List<LottoResult> = map { lotto: Lotto -> LottoResult.from(win, lotto) }

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
        results: List<LottoResult>,
        entry: LottoResult,
    ): Int =
        results.count { lottoResult: LottoResult ->
            lottoResult == entry
        }

    companion object {
        const val LOTTO_RESULT_DESCRIPTION_TEMPLATE = "%d개 일치%S (%d원) - %d개"
        const val BONUS_NUMBER_MATCHED = ", 보너스 볼 일치"
    }
}
