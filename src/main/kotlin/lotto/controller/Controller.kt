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
        val count = readInputMoney().amount / LottoMoney.MONEY_UNIT
        readManualLottoCount(count)
        OutputView.printLottoCountMessage(count)
        val lottoNumbers = LottoGenerator.generateAuto(count)
        OutputView.printLottoNumbers(lottoNumbers)
        return lottoNumbers
    }

    private fun readManualLottoCount(totalCount: Int): Int {
        return kotlin.runCatching {
            OutputView.printInputManualCountPrompt()
            InputView.readInputManualCount(totalCount)
        }.getOrElse {
            println("[ERROR] ${it.message}")
            readManualLottoCount(totalCount)
        }
    }

    private fun readInputMoney(): LottoMoney {
        return kotlin.runCatching {
            OutputView.printInputMoneyPrompt()
            InputView.readInputMoney()
        }.getOrElse {
            println("[ERROR] ${it.message}")
            readInputMoney()
        }
    }

    private fun readWinningNumbers(): WinningNumbers {
        val winningLotto = readWinningLotto()
        val bonusNumber = readBonusNumber(winningLotto)
        return WinningNumbers(winningLotto, bonusNumber)
    }

    private fun readWinningLotto(): Lotto {
        return kotlin.runCatching {
            OutputView.printInputWinningNumbersPrompt()
            InputView.readInputWinningLotto()
        }.getOrElse {
            println("[ERROR] ${it.message}")
            readWinningLotto()
        }
    }

    private fun readBonusNumber(winningLotto: Lotto): LottoNumber {
        return kotlin.runCatching {
            OutputView.printInputBonusNumberPrompt()
            val bonusNumber = InputView.readInputBonusNumber()
            WinningNumbers(winningLotto, bonusNumber)
            bonusNumber
        }.getOrElse {
            println("[ERROR] ${it.message}")
            readBonusNumber(winningLotto)
        }
    }
}
