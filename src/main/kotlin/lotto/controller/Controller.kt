package lotto.controller

import lotto.domain.LottoGenerator
import lotto.domain.ManualLottoCountValidator
import lotto.domain.YieldCalculator
import lotto.domain.model.Lotto
import lotto.domain.model.LottoMoney
import lotto.domain.model.LottoNumber
import lotto.domain.model.UserLotto
import lotto.domain.model.WinningNumbers
import lotto.view.LottoInputView
import lotto.view.OutputView

class Controller(private val inputView: LottoInputView, private val outputView: OutputView) {
    fun start() {
        val lottoNumbers = initializeLotto()
        val userLotto = UserLotto(lottoNumbers)
        val ranks = userLotto.calculateTotalRank(readWinningNumbers())
        outputView.printResult(ranks, YieldCalculator.calculateYield(userLotto.calculateCount(), ranks))
    }

    private fun initializeLotto(): List<Lotto> {
        val totalLottoCount: Int = readInputMoney().amount / LottoMoney.MONEY_UNIT
        val manualLottoCount = readManualLottoCount(totalLottoCount)
        val autoLottoCount = totalLottoCount - manualLottoCount
        val totalLotto = readManualLotto(manualLottoCount) + LottoGenerator.generate(autoLottoCount)
        outputView.printLottoCountMessage(manualLottoCount, autoLottoCount)
        outputView.printLottoNumbers(totalLotto)
        return totalLotto
    }

    private fun readManualLottoCount(totalLottoCount: Int): Int {
        var countAvailable: Boolean
        var manualLottoCount: Int
        do {
            manualLottoCount = inputView.readManualLottoCount()
            countAvailable = ManualLottoCountValidator.checkAvailable(manualLottoCount, totalLottoCount)
        } while (!countAvailable)
        return manualLottoCount
    }

    private fun readManualLotto(count: Int): List<Lotto> {
        var lottoAvailable: Boolean
        var inputLottosNumbers: List<List<Int>>
        do {
            inputLottosNumbers = inputView.readManualLottoNumber(count)
            lottoAvailable = checkLottosAvailable(inputLottosNumbers)
        } while (!lottoAvailable)
        return inputLottosNumbers.map { lottoNumbers -> Lotto(lottoNumbers.map { LottoNumber.from(it) }.toSet()) }
    }

    private fun checkLottosAvailable(inputNumbers: List<List<Int>>): Boolean {
        inputNumbers.forEach { if (!checkLottoAvailable(it)) return false }
        return true
    }

    private fun readInputMoney(): LottoMoney {
        var inputMoney: Int
        var moneyAvailable: Boolean
        do {
            inputMoney = inputView.readMoney()
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
        var inputNumbers: List<Int>
        var lottoAvailable: Boolean
        do {
            inputNumbers = inputView.readWinningLottoNumber()
            lottoAvailable = checkLottoAvailable(inputNumbers)
        } while (!lottoAvailable)
        return Lotto(inputNumbers.map { LottoNumber.from(it) }.toSet())
    }

    private fun readBonusNumber(winningLotto: Lotto): LottoNumber {
        var inputBonusNumber: Int
        var bonusNumberAvailable: Boolean
        do {
            inputBonusNumber = inputView.readBonusNumber()
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
