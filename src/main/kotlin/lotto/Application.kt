package lotto

import lotto.controller.World
import lotto.view.InputView
import lotto.view.OutputView

fun main() {
    runCatching {
        val world = World(InputView(), OutputView())
        world.processLotto()
    }.onFailure {
        println("[ERROR]: " + it.message)
    }
}
