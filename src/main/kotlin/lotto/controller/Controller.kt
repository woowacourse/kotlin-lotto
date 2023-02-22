package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoGenerator
import lotto.domain.LottoMoney
import lotto.domain.LottoNumber
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
        val totalCount = readInputMoney().amount / LottoMoney.MONEY_UNIT
        val manualCount = readManualLottoCount()
        val totalLotto = readManualLotto(manualCount) + LottoGenerator.generate(totalCount - manualCount)
        OutputView.printLottoCountMessage(manualCount, totalCount - manualCount)
        OutputView.printLottoNumbers(totalLotto)
        return totalLotto
    }

    private fun readManualLottoCount(): Int {
        OutputView.printInputManualCountPrompt()
        // 수동 로또 구매 개수가 구매 가능 개수보다 많은 것은 논리적인 오류이다. 따라서 LottoGenerator에서 검사해주자~
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
