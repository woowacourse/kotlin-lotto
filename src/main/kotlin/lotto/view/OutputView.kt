package lotto.view

class OutputView {
    fun printLottoCount(lottoCount: Int) {
        println(LOTTO_COUNT_MESSAGE_FORMAT.format(lottoCount))
    }

    companion object {
        private const val LOTTO_COUNT_MESSAGE_FORMAT = "%d개를 구매했습니다."
    }
}
