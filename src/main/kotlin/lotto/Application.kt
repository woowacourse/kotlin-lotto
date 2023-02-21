package lotto

import lotto.controller.Controller

fun main() {
    Thread.setDefaultUncaughtExceptionHandler { _, e ->
        println(e.message)
    }
    val controller = Controller()
    controller.start()
}
