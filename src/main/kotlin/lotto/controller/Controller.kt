package lotto.controller

import lotto.domain.LottoGenerator
import lotto.view.InputView
import lotto.view.OutputView

class Controller {
    fun start() {
        OutputView.printInputMoneyPrompt()
        val count = InputView.readInputMoney() / 1000
        OutputView.printLottoCountMessage(count)
        OutputView.printLottoNumbers(LottoGenerator.generate(count))
    }
}