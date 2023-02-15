package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoGenerator
import lotto.domain.WinningNumbers
import lotto.view.InputView
import lotto.view.OutputView

class Controller {
    fun start() {
        val lottoNumbers = initializeLotto()
        val winningNumbers = readBonusNumber(readWinningLotto())
    }

    private fun initializeLotto(): List<Lotto> {
        val count = readInputMoney() / 1000
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

    private fun readWinningLotto(): Lotto {
        return try {
            OutputView.printInputWinningNumbersPrompt()
            InputView.readInputWinningLotto()
        } catch (e: IllegalArgumentException) {
            println("[ERROR] ${e.message}")
            readWinningLotto()
        }
    }

    private fun readBonusNumber(winningLotto: Lotto): WinningNumbers {
        return try {
            OutputView.printInputBonusNumberPrompt()
            WinningNumbers(winningLotto, InputView.readInputBonusNumber())
        } catch (e: IllegalArgumentException) {
            println("[ERROR] ${e.message}")
            readBonusNumber(winningLotto)
        }
    }
}