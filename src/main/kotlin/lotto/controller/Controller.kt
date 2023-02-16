package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoGenerator
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
        val count = readInputMoney() / MONEY_UNIT
        OutputView.printLottoCountMessage(count)
        val lottoNumbers = LottoGenerator.generate(count)
        OutputView.printLottoNumbers(lottoNumbers)
        return lottoNumbers
    }

    private fun readInputMoney(): Int {
        return try {
            OutputView.printInputMoneyPrompt()
            InputView.readInputMoney()
        } catch (e: IllegalArgumentException) {
            println("[ERROR] ${e.message}")
            readInputMoney()
        }
    }

    private fun readWinningNumbers(): WinningNumbers {
        val winningLotto = readWinningLotto()
        val bonusNumber = readBonusNumber(winningLotto)
        return WinningNumbers(winningLotto, bonusNumber)
    }

    private fun readWinningLotto(): Lotto {
        return try {
            OutputView.printInputWinningNumbersPrompt()
            InputView.readInputWinningLotto()
        } catch (e: IllegalArgumentException) {
            println("[ERROR] ${e.message}")
            readWinningLotto()
        }
    }

    private fun readBonusNumber(winningLotto: Lotto): LottoNumber {
        return try {
            OutputView.printInputBonusNumberPrompt()
            val bonusNumber = InputView.readInputBonusNumber()
            WinningNumbers(winningLotto, bonusNumber)
            bonusNumber
        } catch (e: IllegalArgumentException) {
            println("[ERROR] ${e.message}")
            readBonusNumber(winningLotto)
        }
    }

    companion object{
        const val MONEY_UNIT = 1000
    }
}
