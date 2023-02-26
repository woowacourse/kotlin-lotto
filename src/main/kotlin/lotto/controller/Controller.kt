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
        val totalLottoCount: Int = readInputMoney().amount / LottoMoney.MONEY_UNIT
        val manualLottoCount = readManualLottoCount(totalLottoCount)
        val autoLottoCount = totalLottoCount - manualLottoCount
        val manualLotto = repeatReadManualLotto(manualLottoCount)
        val totalLotto = manualLotto + LottoGenerator.generate(autoLottoCount)
        OutputView.printLottoCountMessage(manualLottoCount, autoLottoCount)
        OutputView.printLottoNumbers(totalLotto)
        return totalLotto
    }

    private fun readManualLottoCount(totalLottoCount: Int): Int {
        var countAvailable: Boolean
        var manualLottoCount: Int
        OutputView.printInputManualCountPrompt()
        do {
            manualLottoCount = InputView.readInputManualLottoCount()
            countAvailable = ManualLottoCountValidator.checkAvailable(manualLottoCount, totalLottoCount)
        } while (!countAvailable)
        return manualLottoCount
    }

    private fun repeatReadManualLotto(count: Int): List<Lotto> {
        OutputView.printInputManualLottoNumbersPrompt()
        val lottos = mutableListOf<Lotto>()
        repeat(count) {
            lottos.add(readManualLotto())
        }
        return lottos
    }

    private fun readManualLotto(): Lotto {
        var inputNumbers: List<Int>
        var lottoAvailable: Boolean
        do {
            inputNumbers = InputView.readInputLottoNumber()
            lottoAvailable = checkLottoAvailable(inputNumbers)
        } while (!lottoAvailable)
        return Lotto(inputNumbers.map { LottoNumber.from(it) }.toSet())
    }

    private fun readInputMoney(): LottoMoney {
        OutputView.printInputMoneyPrompt()
        var inputMoney: Int
        var moneyAvailable: Boolean
        do {
            inputMoney = InputView.readInputMoney()
            moneyAvailable = LottoMoney.checkMoneyAvailable(inputMoney)
        } while (!moneyAvailable)
        return LottoMoney(inputMoney)
    }

    private fun readWinningNumbers(): WinningNumbers {
        val winningLotto = readWinningLotto()
        val bonusNumber = readBonusNumber(winningLotto)
        return WinningNumbers(winningLotto, bonusNumber)
    }

    private fun readWinningLotto(): Lotto {
        OutputView.printInputWinningNumbersPrompt()
        var inputNumbers: List<Int>
        var lottoAvailable: Boolean
        do {
            inputNumbers = InputView.readInputLottoNumber()
            lottoAvailable = checkLottoAvailable(inputNumbers)
        } while (!lottoAvailable)
        return Lotto(inputNumbers.map { LottoNumber.from(it) }.toSet())
    }

    private fun readBonusNumber(winningLotto: Lotto): LottoNumber {
        OutputView.printInputBonusNumberPrompt()
        var inputBonusNumber: Int
        var bonusNumberAvailable: Boolean
        do {
            inputBonusNumber = InputView.readInputBonusNumber()
            bonusNumberAvailable = checkBonusNumberAvailable(inputBonusNumber, winningLotto)
        } while (!bonusNumberAvailable)
        return LottoNumber.from(inputBonusNumber)
    }

    private fun checkLottoAvailable(inputNumbers: List<Int>): Boolean {
        inputNumbers.forEach {
            if (!LottoNumber.checkLottoNumberAvailable(it)) return false
        }
        if (!Lotto.checkLottoAvailable(inputNumbers.map { LottoNumber.from(it) }.toSet())) return false
        return true
    }

    private fun checkBonusNumberAvailable(inputBonusNumber: Int, winningLotto: Lotto): Boolean {
        if (!LottoNumber.checkLottoNumberAvailable(inputBonusNumber)) return false
        if (WinningNumbers.checkBonusNumberAvailable(winningLotto, inputBonusNumber)) return false
        return true
    }
}
