package view

import model.Lotto

object OutputView {
    private const val LOTTO_PURCHASED = "%d개를 구매했습니다."

    fun printPurchasedLotto(lottos: List<Lotto>) {
        println(LOTTO_PURCHASED.format(lottos.size))
        lottos.forEach {
            print(it.toString())
        }
        println()
    }
}