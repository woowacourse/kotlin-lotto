package lotto

import lotto.controller.LottoController
import lotto.view.InputView

fun main() {
    val inputView = InputView()
    val controller = LottoController(inputView)
    controller.runLotto()
}