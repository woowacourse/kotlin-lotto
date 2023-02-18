package lotto

import lotto.controller.Controller

fun main() {
    val controller = Controller()
    try {
        controller.start()
    } catch (e: IllegalArgumentException) {
        println(e.message)
    }
}
