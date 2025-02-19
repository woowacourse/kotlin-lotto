package lotto.view

import lotto.domain.Lotto
import lotto.domain.LottoResult
import lotto.domain.Lottos
import kotlin.math.floor

object OutputView {
    private const val MESSAGE_ENTER_PRICE = "구입금액을 입력해 주세요."
    private const val MESSAGE_ENTER_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요."
    private const val MESSAGE_ENTER_BONUS_NUMBER = "보너스 볼을 입력해 주세요."
    private const val MESSAGE_RESULT_HEADER = "당첨 통계\n---------"
    private const val ERROR_MESSAGE_ILLEGAL_STATE = "연산에 실패했습니다."

    fun requestPrice() {
        println(MESSAGE_ENTER_PRICE)
    }

    fun showLottoCount(lottoCount: Int) {
        println("${lottoCount}개를 구매했습니다.")
    }

    fun showLottos(lottos: Lottos) {
        lottos.lottos.forEach { lotto: Lotto ->
            println(lotto.numbers.sorted())
        }
    }

    fun requestWinningLotto() {
        println(MESSAGE_ENTER_WINNING_NUMBERS)
    }

    fun requestBonusNumber() {
        println(MESSAGE_ENTER_BONUS_NUMBER)
    }

    fun showResult(
        lottoResults: List<LottoResult>,
        profitRate: Double,
    ) {
        println(MESSAGE_RESULT_HEADER)
        println("3개 일치 (5000원) - ${lottoResults.count { lottoResult: LottoResult -> lottoResult == LottoResult.FIFTH }}개")
        println("4개 일치 (50000원) - ${lottoResults.count { lottoResult: LottoResult -> lottoResult == LottoResult.FOURTH }}개")
        println("5개 일치 (1500000원) - ${lottoResults.count { lottoResult: LottoResult -> lottoResult == LottoResult.THIRD }}개")
        println("5개 일치, 보너스 볼 일치(30000000원) - ${lottoResults.count { lottoResult: LottoResult -> lottoResult == LottoResult.SECOND }}개")
        println("6개 일치 (2000000000원) - ${lottoResults.count { lottoResult: LottoResult -> lottoResult == LottoResult.FIRST }}개")
        println("총 수익률은 ${floor(profitRate * 100) / 100}입니다.(기준이 1이기 때문에 결과적으로 ${makeProfitRateDescription(profitRate)} 의미임)")
    }

    private fun makeProfitRateDescription(profitRate: Double): String =
        when {
            profitRate < 1.0 -> "손해라는"
            profitRate == 1.0 -> "본전이라는"
            profitRate > 1.0 -> "이득이라는"
            else -> throw IllegalStateException(ERROR_MESSAGE_ILLEGAL_STATE)
        }
}
