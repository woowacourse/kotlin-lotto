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
        val manualLottoCount = readManualLottoCount(totalLottoCount)
        val autoLottoCount = totalLottoCount - manualLottoCount
        val manualLotto = readRepeatManualLotto(manualLottoCount)
        val totalLotto = manualLotto + LottoGenerator.generate(autoLottoCount)
        OutputView.printLottoCountMessage(manualLottoCount, autoLottoCount)
        OutputView.printLottoNumbers(totalLotto)
        return totalLotto
    }

    private fun readManualLottoCount(totalLottoCount: Int): Int {
        var countAvailable: Boolean
        var manualLottoCount: Int
        do {
            OutputView.printInputManualCountPrompt()
            manualLottoCount = InputView.readInputManualLottoCount()
            countAvailable = ManualLottoCountValidator.checkAvailable(manualLottoCount, totalLottoCount)
        } while (!countAvailable)
        return manualLottoCount
    }

    private fun readRepeatManualLotto(count: Int): List<Lotto> {
        OutputView.printInputManualLottoNumbersPrompt()
        val lottos = mutableListOf<Lotto>()
        repeat(count) {
            lottos.add(readManualLotto())
        }
        return lottos
    }

    private fun readManualLotto(): Lotto {
        return kotlin.runCatching {
            LottoGenerator.generateManual(InputView.readInputLottoNumber())
        }.getOrElse {
            println(ERROR_PREFIX + it.message)
            readManualLotto()
        }
    }

    private fun readInputMoney(): LottoMoney {
        OutputView.printInputMoneyPrompt()
        return kotlin.runCatching {
            LottoMoney(InputView.readInputMoney())
        }.getOrElse {
            println(ERROR_PREFIX + it.message)
            readInputMoney()
        }
    }

    private fun readWinningNumbers(): WinningNumbers {
        val winningLotto = readWinningLotto()
        val bonusNumber = readBonusNumber(winningLotto)
        return kotlin.runCatching {
            WinningNumbers(winningLotto, bonusNumber)
        }.getOrElse {
            println(ERROR_PREFIX + it.message)
            readWinningNumbers()
        }
    }

    private fun readWinningLotto(): Lotto {
        OutputView.printInputWinningNumbersPrompt()
        val numbers = InputView.readInputLottoNumber()
        return kotlin.runCatching {
            Lotto(numbers.map { LottoNumber.from(it) })
        }.getOrElse {
            println(ERROR_PREFIX + it.message)
            readWinningLotto()
        }
    }

    private fun readBonusNumber(winningLotto: Lotto): LottoNumber {
        OutputView.printInputBonusNumberPrompt()
        val bonusNumber = InputView.readInputBonusNumber()
        return kotlin.runCatching {
            WinningNumbers(winningLotto, LottoNumber.from(bonusNumber))
            LottoNumber.from(bonusNumber)
        }.getOrElse {
            println(ERROR_PREFIX + it.message)
            readBonusNumber(winningLotto)
        }
    }

    companion object {
        const val ERROR_PREFIX = "[ERROR] "
    }
}
