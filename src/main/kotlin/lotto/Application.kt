package lotto

import lotto.controller.LottoController
import lotto.view.InputView
import lotto.view.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()

    LottoController(inputView, outputView).run()
}
