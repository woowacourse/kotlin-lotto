package lotto.view

import lotto.domain.Lotto

object OutputView {

    private const val INPUT_MONEY_PROMPT = "구입금액을 입력해 주세요."
    const val LOTTO_COUNT_MESSAGE = "%d개를 구매했습니다."

    fun printInputMoneyPrompt() {
        println(INPUT_MONEY_PROMPT)
    }

    fun printLottoCountMessage(count: Int) {
        println(LOTTO_COUNT_MESSAGE.format(count))
    }

    fun printLottoNumbers(lottoNumbers: List<Lotto>) {
        lottoNumbers.forEach { lotto -> printLotto(lotto) }
    }

    private fun printLotto(lotto: Lotto) {
        println("[${lotto.numbers.joinToString(", ")}]")
    }
}