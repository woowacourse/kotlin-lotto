package lotto.view

import lotto.Lotto

object OutputView {
    fun printLottoAmount(amount: Int) {
        println("${amount}개를 구매했습니다.")
    }

    fun printLottos(lottos: List<Lotto>) {
        lottos.forEach { lotto ->
            println(lotto.lottoNums.joinToString(",", "[", "]"))
        }
    }
}
