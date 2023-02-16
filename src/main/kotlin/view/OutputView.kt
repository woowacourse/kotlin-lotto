package view

import domain.Lotto
import domain.Lottos

class OutputView {
    fun outputMoneyMessage() {
        println(OUTPUT_MONEY_MESSAGE)
    }

    fun outputLottoSizeMessage(size: Int) {
        println(size.toString() + OUTPUT_LOTTO_SIZE_MESSAGE)
    }

    fun outputLottos(lottos: Lottos) {
        lottos.lottos.forEach { lotto -> outputLotto(lotto) }
        println()
    }

    fun outputLotto(lotto: Lotto) {
        println(lotto.numbers!!.joinToString(prefix = PREFIX_MARK, separator = SEPERATOR_MARK, postfix = POSTFIX_MARK))
    }

    fun outputWinningLottoMessage() {
        println(OUTPUT_WINNING_LOTTO_MESSAGE)
    }

    fun outputBonusNumberMessage() {
        println(OUTPUT_BONUS_NUMBER_MESSAGE)
    }

    companion object {
        const val OUTPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요."
        const val OUTPUT_LOTTO_SIZE_MESSAGE = "개를 구매했습니다."
        const val PREFIX_MARK = "["
        const val SEPERATOR_MARK = ", "
        const val POSTFIX_MARK = "]"
        const val OUTPUT_WINNING_LOTTO_MESSAGE = "지난 주 당첨 번호를 입력해 주세요."
        const val OUTPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요."
    }
}
