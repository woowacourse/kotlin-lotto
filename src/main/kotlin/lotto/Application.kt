package lotto

import lotto.controller.Controller

fun main() {
    Thread.setDefaultUncaughtExceptionHandler { _, e ->
        println(Controller.ERROR_PREFIX + e.message)
        e.printStackTrace()
    }
    val controller = Controller()
    controller.start()
}
