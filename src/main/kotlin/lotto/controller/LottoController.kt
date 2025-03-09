package lotto.controller

import lotto.domain.model.Amount
import lotto.domain.model.Lotto
import lotto.domain.model.LottoCreationResult
import lotto.domain.model.LottoNumber
import lotto.domain.model.Rank
import lotto.domain.model.WinningLotto
import lotto.domain.model.WinningLottoCreationResult
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

        val winningLotto = getWinningLotto()
        val ranks = WinningListMaker(winningLotto).makeWinningList(manualLottoList + autoLottoList)
        outputView.printResult(ranks, calculateEarningRate(ranks))
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
        return List(count) { getLotto() }
    }

    private fun getLotto(): Lotto {
        val numbers = inputView.getManualLotto().mapNotNull { LottoNumber.valueOfOrNull(it) }
        val result = Lotto.valueOf(numbers)
        return when (result) {
            is LottoCreationResult.Success -> result.lotto
            is LottoCreationResult.Failure.InvalidCount -> {
                outputView.printErrorMessage(Message.errorInvalidCount())
                getLotto()
            }
            is LottoCreationResult.Failure.DuplicatedNumbers -> {
                outputView.printErrorMessage(Message.errorDuplicatedNumbers())
                getLotto()
            }
        }
    }

    private fun getAmount(): Amount {
        val amount = Amount.valueOfOrNull(inputView.getMoney())
        if (amount != null) return amount

        outputView.printErrorMessage(Message.errorInvalidAmount())
        return getAmount()
    }

    private fun getWinningLotto(): WinningLotto {
        val numbers = inputView.getWinningLotto().mapNotNull { LottoNumber.valueOfOrNull(it) }
        val bonusNumber = LottoNumber.valueOfOrNull(inputView.getBonusNumber())
        if (bonusNumber == null) {
            outputView.printErrorMessage(Message.errorInvalidBonusNumber())
            return getWinningLotto()
        }
        val result = WinningLotto.valueOf(numbers, bonusNumber)
        return when (result) {
            is WinningLottoCreationResult.Success -> result.winningLotto
            is WinningLottoCreationResult.Failure.NumberSizeError -> {
                outputView.printErrorMessage(Message.errorWinningLottoNumberSize())
                getWinningLotto()
            }
            is WinningLottoCreationResult.Failure.DuplicatedNumbers -> {
                outputView.printErrorMessage(Message.errorWinningLottoDuplicatedNumbers())
                getWinningLotto()
            }
            is WinningLottoCreationResult.Failure.BonusNumberDuplicated -> {
                outputView.printErrorMessage(Message.errorWinningLottoBonusNumberDuplicated())
                getWinningLotto()
            }
        }
    }

    private fun calculateEarningRate(ranks: Map<Rank, Int>): Double {
        val totalWinnings = RankCalculator().earningMoney(ranks)
        return RankCalculator().calculateEarningRate(amount.money, totalWinnings)
    }

    companion object {
        const val LOTTO_PRIZE = 1000
    }
}
