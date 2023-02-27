package lotto

import lotto.controller.Controller
import lotto.domain.RandomNumberGenerator
import lotto.view.InputView
import lotto.view.OutputView

fun main() {
    Thread.setDefaultUncaughtExceptionHandler { _, e ->
        println(OutputView.ERROR_PREFIX + e.message)
        e.printStackTrace()
    }
    val controller = Controller(InputView(), OutputView(), RandomNumberGenerator())
    controller.start()
}
