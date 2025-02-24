package lotto

import lotto.controller.LottoController

fun main() {
    val lottoApplication = LottoApplication()
    lottoApplication.run()
}

class LottoApplication {
    val controller = LottoController()

    fun run() {
        controller.start()
    }
}
