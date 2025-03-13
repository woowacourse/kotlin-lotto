package lotto.controller

import lotto.domain.model.Amount
import lotto.domain.model.Lotto
import lotto.domain.model.LottoCreationResult
import lotto.domain.model.LottoNumber
import lotto.domain.model.Rank
import lotto.domain.service.RankCalculator
import lotto.domain.service.WinningListMaker
import lotto.view.InputView
import lotto.view.Message
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    private val amount = getAmount()

    fun run() {
        val count = getManualCount()
        val manualLottoList = getLottoList(count)
        val autoLottoList = getAutoLotto(amount.getCount(LOTTO_PRIZE) - count)

        outputView.printPurchaseResult(manualLottoList, autoLottoList)
        val winningLotto = getValidLotto { inputView.getWinningLotto() }
        val winningNumber = getBonusNumber({ inputView.getBonusNumber() }, winningLotto)
        val ranks = WinningListMaker(winningLotto, winningNumber).makeWinningList(manualLottoList + autoLottoList)
        outputView.printResult(ranks, calculateEarningRate(ranks))
    }

    private fun getBonusNumber(
        getNumber: () -> Int,
        winningLotto: Lotto,
    ): LottoNumber {
        val bonusNumber = LottoNumber.valueOfOrNull(getNumber())
        when {
            bonusNumber == null -> outputView.printErrorMessage(Message.errorInvalidBonusNumber())
            winningLotto.findNumber(bonusNumber) -> outputView.printErrorMessage(Message.errorWinningLottoBonusNumberDuplicated())
            else -> return bonusNumber
        }
        return getBonusNumber(getNumber, winningLotto)
    }

    private fun getAutoLotto(count: Int): List<Lotto> = List(count) { Lotto.createRandom() }

    private fun getManualCount(): Int {
        val manualCount = inputView.getManualCount()
        if (amount.getCount(LOTTO_PRIZE) < manualCount) {
            outputView.printErrorMessage(Message.errorCountExceeded())
            return getManualCount()
        }
        return manualCount
    }

    private fun getLottoList(count: Int): List<Lotto> {
        inputView.messageManualLotto()
        return List(count) { getValidLotto { inputView.getManualLotto() } }
    }

    private fun getValidLotto(getNumbers: () -> List<Int>): Lotto {
        val numbers = getNumbers().mapNotNull { LottoNumber.valueOfOrNull(it) }
        val result = Lotto.valueOf(numbers)
        return when (result) {
            is LottoCreationResult.Success -> result.lotto
            is LottoCreationResult.Failure.InvalidCount -> {
                outputView.printErrorMessage(Message.errorInvalidCount())
                getValidLotto(getNumbers)
            }
            is LottoCreationResult.Failure.DuplicatedNumbers -> {
                outputView.printErrorMessage(Message.errorDuplicatedNumbers())
                getValidLotto(getNumbers)
            }
        }
    }

    private fun getAmount(): Amount {
        val amount = Amount.valueOfOrNull(inputView.getMoney())
        if (amount != null) return amount

        outputView.printErrorMessage(Message.errorInvalidAmount())
        return getAmount()
    }

    private fun calculateEarningRate(ranks: Map<Rank, Int>): Double {
        val rankCalculator = RankCalculator()
        val totalWinnings = rankCalculator.earningMoney(ranks)
        return rankCalculator.calculateEarningRate(amount.money, totalWinnings)
    }

    companion object {
        const val LOTTO_PRIZE = 1000
    }
}
