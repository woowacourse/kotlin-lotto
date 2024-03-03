package lotto

import lotto.controller.LotteryController
import lotto.view.InputView
import lotto.view.OutputView

fun main() {
    LotteryController(
        InputView,
        OutputView,
    ).start()
}
