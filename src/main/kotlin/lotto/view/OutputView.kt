package lotto.view

import lotto.Lotto

class OutputView {
    fun inputViewPrint(message: String) {
        println(message)
    }

    fun outputPublishedLotto(lottoList: List<Lotto>) {
        println(lottoList.size.toString() + PRINT_LOTTO_COUNT)
        lottoList.forEach { lotto ->
            println(lotto.numbers)
        }
    }

    companion object {
        const val INPUT_MONEY = "구입금액을 입력해 주세요."
        const val INPUT_WINNING = "지난 주 당첨 번호를 입력해 주세요."
        const val INPUT_BONUS = "보너스 볼을 입력해 주세요."
        const val PRINT_LOTTO_COUNT = "개를 구매했습니다."
    }
}
