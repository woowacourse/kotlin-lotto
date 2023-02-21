package view

import domain.Lotto
import domain.LottoResult
import domain.Rank

class OutputView {
    fun outputGetAmount() {
        println(GET_AMOUNT)
    }

    fun outputGetCount() {
        println(GET_COUNT)
    }

    fun outputGetManualLottos() {
        println(GET_MANUAL_LOTTOS)
    }

    fun outputGetWinningNumbers() {
        println(GET_WINNING_NUMBERS)
    }

    fun outputGetBonusNumber() {
        println(GET_BONUS_NUMBER)
    }

    fun outputLottos(manual: List<Lotto>, auto: List<Lotto>) {
        println(NOTICE_LOTTOS.format(manual.size, auto.size))
        (manual + auto).forEach { println(it.toList()) }
        println()
    }

    fun outputResult(lottoResult: LottoResult) {
        println("\n" + NOTICE_RESULT)
        println(DOTTED_LINE)
        for (value in Rank.values().reversed()) {
            if (value == Rank.MISS) continue
            if (value.mustHaveBonus) {
                println(RESULT_HAVE_BONUS.format(value.countOfMatch, value.winningMoney, lottoResult[value]))
                continue
            }
            println(RESULT_BASIC.format(value.countOfMatch, value.winningMoney, lottoResult[value]))
        }
        println(RESULT_RATE_OF_RETURN.format(lottoResult.getRateOfReturn()))
    }

    fun outputError(e: String?) {
        println(e ?: UNKNOWN_ERROR)
    }

    companion object {
        private const val GET_AMOUNT = "구입금액을 입력해 주세요."
        private const val GET_COUNT = "수동으로 구매할 로또 수를 입력해 주세요."
        private const val GET_MANUAL_LOTTOS = "수동으로 구매할 번호를 입력해 주세요."
        private const val GET_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요."
        private const val GET_BONUS_NUMBER = "보너스 볼을 입력해 주세요."
        private const val NOTICE_LOTTOS = "수동으로 %d장, 자동으로 %d개를 구매했습니다."
        private const val NOTICE_RESULT = "당첨 통계"
        private const val DOTTED_LINE = "---------"
        private const val RESULT_HAVE_BONUS = "%d개 일치, 보너스 볼 일치 (%s)원 - %d개"
        private const val RESULT_BASIC = "%d개 일치 (%s)원 - %d개"
        private const val RESULT_RATE_OF_RETURN = "총 수익률은 %.2f입니다."
        private const val UNKNOWN_ERROR = "알 수 없는 에러입니다."
    }
}
