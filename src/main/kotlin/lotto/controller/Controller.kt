package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoGenerator
import lotto.domain.Money
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
        val lottoCount = (readInputMoney().value?.div(Money.MONEY_UNIT))!!
        val manualLotto = initializeManualLotto(lottoCount)
        val autoMaticLotto = initializeAutoMaticLotto(lottoCount - manualLotto.size)
        OutputView.printLottoCountMessage(manualLotto.size, autoMaticLotto.size)
        OutputView.printLottoNumbers(manualLotto + autoMaticLotto)
        return manualLotto + autoMaticLotto
    }

    private fun initializeManualLotto(lottoCount: Int): List<Lotto> {
        OutputView.printInputManualLottoCountPrompt()
        val manualLottoCount = InputView.readInputManualLottoCount(lottoCount)
        return InputView.readManualLottoNumbers(manualLottoCount)
    }

    private fun initializeAutoMaticLotto(count: Int): List<Lotto> {
        return LottoGenerator.generate(count)
    }

    private fun readInputMoney(): Money {
        OutputView.printInputMoneyPrompt()
        return Money(InputView.readInputMoney())
    }

    private fun readWinningNumbers(): WinningNumbers {
        OutputView.printInputWinningNumbersPrompt()
        val winningLotto = InputView.readInputWinningLotto()
        OutputView.printInputBonusNumberPrompt()
        val bonusNumber = InputView.readInputBonusNumber()
        return WinningNumbers(winningLotto, bonusNumber)
    }
}
