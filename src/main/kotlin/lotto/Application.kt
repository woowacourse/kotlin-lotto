package lotto

import lotto.controller.Controller
import lotto.view.InputView
import lotto.view.OutputView

fun main() {
    val controller = Controller(
        InputView(), OutputView()
    )
    controller.run()
}
