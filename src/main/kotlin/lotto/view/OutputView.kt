package lotto.view

import lotto.LottoResult

object OutputView {
    fun printLottoAmount(amount: Int) {
        println("${amount}개를 구매했습니다.")
    }

    fun printLottos(lottos: LottoResult) {
        lottos.lottos.forEach { lotto ->
            println(lotto.lottoNums.joinToString(",", "[", "]"))
        }
    }
}
