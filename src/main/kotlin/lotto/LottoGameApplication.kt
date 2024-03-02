package lotto

import lotto.controller.LottoGameController
import lotto.view.ConsoleLottoGameInputView
import lotto.view.ConsoleLottoGameOutputView

fun main() {
    LottoGameController(
        ConsoleLottoGameInputView(),
        ConsoleLottoGameOutputView(),
    ).startLottoGame()
}
