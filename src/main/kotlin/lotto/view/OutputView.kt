package lotto.view

import java.lang.String.format

class OutputView {
    fun printLottoCount(number: Int) {
        println(format(OUTPUT_LOTTO_COUNT_MESSAGE, number))
    }

    companion object {
        private const val OUTPUT_LOTTO_COUNT_MESSAGE = "%d개를 구매했습니다."
    }
}
