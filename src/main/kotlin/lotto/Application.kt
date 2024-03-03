package lotto

import lotto.controller.LottoController
import lotto.view.ConsoleInputView
import lotto.view.ConsoleOutputView
import lotto.view.InputView
import lotto.view.OutputView

fun main() {
    val inputView: InputView = ConsoleInputView()
    val outputView: OutputView = ConsoleOutputView()
    val lottoController = LottoController(inputView, outputView)
    lottoController.run()
}
