package lotto

import lotto.view.InputView
import lotto.view.OutputView

fun main() {
    LottoController(
        InputView,
        OutputView,
    ).run()
}
