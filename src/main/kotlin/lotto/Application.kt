package lotto

import lotto.controller.Controller
import lotto.domain.RandomLotteryGenerator
import lotto.view.InputView
import lotto.view.OutputView

fun main() {
    val controller = Controller(
        RandomLotteryGenerator(), InputView(), OutputView()
    )
    controller.run()
}
