package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoGenerator
import lotto.domain.UserLotto
import lotto.domain.WinningNumbers
import lotto.domain.YieldCalculator
import lotto.view.InputView
import lotto.view.OutputView

class Controller {
    fun start() {
        val lottoNumbers = initializeLotto()
        val userLotto = UserLotto(lottoNumbers)
        val ranks = userLotto.calculateTotalRank(readWinningNumbers())
        OutputView.printResult(ranks, YieldCalculator.calculateYield(userLotto.calculateCount(), ranks))
    }

    private fun initializeLotto(): List<Lotto> {
        val count = readInputMoney() / Lotto.MONEY_UNIT
        OutputView.printLottoCountMessage(count)
        val lottoNumbers = LottoGenerator.generate(count)
        OutputView.printLottoNumbers(lottoNumbers)
        return lottoNumbers
    }

    private fun readInputMoney(): Int {
        OutputView.printInputMoneyPrompt()
        return InputView.readInputMoney()
    }

    private fun readWinningNumbers(): WinningNumbers {
        OutputView.printInputWinningNumbersPrompt()
        val winningLotto = InputView.readInputWinningLotto()
        OutputView.printInputBonusNumberPrompt()
        val bonusNumber = InputView.readInputBonusNumber()
        return WinningNumbers(winningLotto, bonusNumber)
    }
}
