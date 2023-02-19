package lotto

import lotto.controller.Controller
import lotto.domain.RandomLotteryNumbersGenerator
import lotto.view.InputView
import lotto.view.OutputView

fun main() {
    val controller = Controller(
        RandomLotteryNumbersGenerator(), InputView(), OutputView()
    )
    controller.run()
}
