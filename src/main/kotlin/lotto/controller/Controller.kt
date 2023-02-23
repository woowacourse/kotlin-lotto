package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoGenerator
import lotto.domain.LottoNumber
import lotto.domain.Money
import lotto.domain.UserLotto
import lotto.domain.WinningNumbers
import lotto.domain.YieldCalculator
import lotto.view.InputView
import lotto.view.OutputView

class Controller {
    fun start() {
        val userLotto = initializeUserLotto(readLottoCount())
        val ranks = userLotto.calculateTotalRank(readWinningNumbers())
        OutputView.printResult(ranks, YieldCalculator.calculateYield(ranks))
    }

    private fun readLottoCount(): Int {
        return (readInputMoney().value.div(Money.MONEY_UNIT))
    }

    private fun readInputMoney(): Money {
        OutputView.printInputMoneyPrompt()
        val input = InputView.readInputMoney()
        Money.validateInputMoney(input)
        return Money(input.toInt())
    }

    private fun initializeUserLotto(count: Int): UserLotto {
        val manualLotto = makeManualLotto(count)
        val autoMaticLotto = makeAutoMaticLotto(count - manualLotto.size)
        OutputView.printLottoCountMessage(manualLotto.size, autoMaticLotto.size)
        OutputView.printLottoNumbers(manualLotto + autoMaticLotto)
        return UserLotto(manualLotto + autoMaticLotto)
    }

    private fun makeManualLotto(count: Int): List<Lotto> {
        OutputView.printInputManualLottoCountPrompt()
        val manualLottoCount = InputView.readInputManualLottoCount()
        UserLotto.validateInputManualLottoCount(count, manualLottoCount)
        OutputView.printInputManualLottoNumbersPrompt()
        val lotto = mutableListOf<Lotto>()
        for (i in 0 until manualLottoCount.toInt()) {
            lotto.add(Lotto(InputView.readManualLottoNumbers().map { LottoNumber(it.toIntOrNull()) }))
        }
        return lotto
    }

    private fun makeAutoMaticLotto(count: Int): List<Lotto> {
        return LottoGenerator.generate(count)
    }

    private fun readWinningNumbers(): WinningNumbers {
        OutputView.printInputWinningNumbersPrompt()
        val winningLotto = Lotto(InputView.readInputWinningLotto().map { LottoNumber(it.toIntOrNull()) })
        OutputView.printInputBonusNumberPrompt()
        val bonusNumber = LottoNumber(InputView.readInputBonusNumber().toIntOrNull())
        return WinningNumbers(winningLotto, bonusNumber)
    }
}
