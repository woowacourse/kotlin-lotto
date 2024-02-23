package lotto.view

import lotto.model.LottoBundle
import lotto.model.LottoResult

object OutputView {
    private const val LOTTO_SIZE_MESSAGE = "%d개를 구매했습니다."
    private const val WINNING_STATISTICS_MESSAGE = "당첨 통계\n---------\n"
    private const val TOTAL_RETURN_MESSAGE = "총 수익률은 %.2f입니다."

    fun printLottoBundle(lottoBundle: LottoBundle) {
        println(LOTTO_SIZE_MESSAGE.format(lottoBundle.lottos.size))
        println(lottoBundle)
        println()
    }

    fun printResult(lottoResult: LottoResult) {
        println(WINNING_STATISTICS_MESSAGE)
        println(lottoResult)
        println(TOTAL_RETURN_MESSAGE.format(lottoResult.getProfitRate()))
    }
}
