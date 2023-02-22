package lotto.controller

import lotto.domain.LottoGenerator
import lotto.domain.ManualLottoCountValidator
import lotto.domain.YieldCalculator
import lotto.domain.model.Lotto
import lotto.domain.model.LottoMoney
import lotto.domain.model.LottoNumber
import lotto.domain.model.UserLotto
import lotto.domain.model.WinningNumbers
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
        val totalLottoCount = readInputMoney().amount / LottoMoney.MONEY_UNIT
        val manualLottoCount = readManualLottoCount()
        ManualLottoCountValidator.checkAvailable(manualLottoCount, totalLottoCount)
        val totalLotto = readManualLotto(manualLottoCount) + LottoGenerator.generate(totalLottoCount - manualLottoCount)
        OutputView.printLottoCountMessage(manualLottoCount, totalLottoCount - manualLottoCount)
        OutputView.printLottoNumbers(totalLotto)
        return totalLotto
    }

    private fun readManualLottoCount(): Int {
        OutputView.printInputManualCountPrompt()
        return InputView.readInputManualLottoCount() ?: readManualLottoCount()
    }

    private fun readManualLotto(count: Int): List<Lotto> {
        OutputView.printInputManualLottoNumbersPrompt()
        return InputView.readInputManualLotto(count) ?: readManualLotto(count)
    }

    private fun readInputMoney(): LottoMoney {
        OutputView.printInputMoneyPrompt()
        return InputView.readInputMoney() ?: readInputMoney()
    }

    private fun readWinningNumbers(): WinningNumbers {
        val winningLotto = readWinningLotto()
        val bonusNumber = readBonusNumber(winningLotto)
        return WinningNumbers(winningLotto, bonusNumber)
    }

    private fun readWinningLotto(): Lotto {
        OutputView.printInputWinningNumbersPrompt()
        return InputView.readInputWinningLotto() ?: readWinningLotto()
    }

    private fun readBonusNumber(winningLotto: Lotto): LottoNumber {
        OutputView.printInputBonusNumberPrompt()
        val bonusNumber = InputView.readInputBonusNumber() ?: readBonusNumber(winningLotto)
        WinningNumbers(winningLotto, bonusNumber)
        return bonusNumber
    }

    companion object {
        private const val ERROR_PREFIX = "[ERROR]"
    }
}
