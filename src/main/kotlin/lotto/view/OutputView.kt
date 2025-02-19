package lotto.view

import lotto.model.LottoStore
import lotto.model.RandomLottoGenerator
import java.lang.String.format

class OutputView {
    fun printLottoCount(number: Int) {
        println(format(OUTPUT_LOTTO_COUNT_MESSAGE, number))
    }

    fun printLottoBundle(input: String) {
        val lottoBundle = LottoStore().getTickets(input, RandomLottoGenerator())
        lottoBundle.forEach { lotto ->
            println(lotto.numbers)
        }
    }

    companion object {
        private const val OUTPUT_LOTTO_COUNT_MESSAGE = "%d개를 구매했습니다."
    }
}
