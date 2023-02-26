package lotto

import lotto.controller.Controller
import lotto.view.OutputView

fun main() {
    Thread.setDefaultUncaughtExceptionHandler { _, e ->
        println(OutputView.ERROR_PREFIX + e.message)
        e.printStackTrace()
    }
    val controller = Controller()
    controller.start()
}
