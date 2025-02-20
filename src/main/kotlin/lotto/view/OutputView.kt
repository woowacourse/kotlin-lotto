package lotto.view

import lotto.domain.Lotto
import lotto.domain.LottoNumber
import lotto.domain.LottoResult
import lotto.domain.Lottos
import kotlin.math.floor

object OutputView {
    private const val MESSAGE_ENTER_PRICE = "구입금액을 입력해 주세요."
    private const val MESSAGE_ENTER_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요."
    private const val MESSAGE_ENTER_BONUS_NUMBER = "보너스 볼을 입력해 주세요."
    private const val MESSAGE_RESULT_HEADER = "당첨 통계\n---------"
    private const val ERROR_MESSAGE_ILLEGAL_STATE = "연산에 실패했습니다."
    private const val MESSAGE_BOUGHT = "개를 구매했습니다."
    private const val MESSAGE_PROFIT_RATE_LOSE = "손해라는"
    private const val MESSAGE_PROFIT_RATE_DRAW = "본전이라는"
    private const val MESSAGE_PROFIT_RATE_WIN = "이득이라는"

    fun requestPrice() {
        println(MESSAGE_ENTER_PRICE)
    }

    fun showLottoCount(lottoCount: Int) {
        println("$lottoCount$MESSAGE_BOUGHT")
    }

    fun showLottos(lottos: Lottos) {
        lottos.lottos.forEach { lotto: Lotto ->
            println(lotto.numbers.map { lottoNumber: LottoNumber -> lottoNumber.value }.sorted())
        }
    }

    fun requestWinningLotto() {
        println(MESSAGE_ENTER_WINNING_NUMBERS)
    }

    fun requestBonusNumber() {
        println(MESSAGE_ENTER_BONUS_NUMBER)
    }

    fun showResult(
        userLottoResults: List<LottoResult>,
        profitRate: Double,
    ) {
        println(MESSAGE_RESULT_HEADER)
        LottoResult.entries.drop(1).forEach { entry: LottoResult ->
            println(
                "${entry.matchCount}개 일치${getBonusBallDescription(entry)} (${entry.prizeAmount}원) - ${
                    countLottoResult(userLottoResults, entry)
                }개",
            )
        }
        println("총 수익률은 ${floor(profitRate * 100) / 100}입니다.(기준이 1이기 때문에 결과적으로 ${makeProfitRateDescription(profitRate)} 의미임)")
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

    private fun makeProfitRateDescription(profitRate: Double): String =
        when {
            profitRate < 1.0 -> MESSAGE_PROFIT_RATE_LOSE
            profitRate == 1.0 -> MESSAGE_PROFIT_RATE_DRAW
            profitRate > 1.0 -> MESSAGE_PROFIT_RATE_WIN
            else -> throw IllegalStateException(ERROR_MESSAGE_ILLEGAL_STATE)
        }
}
