package lotto.controller

import lotto.domain.model.*
import lotto.domain.service.RankCalculator
import lotto.domain.service.WinningListMaker
import lotto.view.InputView
import lotto.view.Message
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    private val amount = inputAmount()

    fun run() {
        val count = getManualCount()
        val manualLottoList = getLottoList(count)
        val autoLottoList = getAutoLotto(amount.getCount(LOTTO_PRIZE) - count)

        outputView.printPurchaseResult(manualLottoList, autoLottoList)

        val winningLotto = getWinningLotto()
        val ranks = WinningListMaker().calculateRanks(winningLotto, manualLottoList + autoLottoList)

        outputView.printResult(sortResultsByOriginalRankOrder(ranks), calculateEarningRate(ranks))
    }

    private fun getAutoLotto(count: Int): List<Lotto> =
        List(count) { Lotto.createRandom() }

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
        return List(count) { inputLotto() }
    }

    private fun inputLotto(): Lotto {
        val numbers = inputView.getManualLotto().mapNotNull { LottoNumber.valueOfOrNull(it) }
        val result = Lotto.valueOf(numbers)
        if (result is LottoCreationResult.Success) return result.lotto

        outputView.printErrorMessage(Message.errorInvalidLotto())
        return inputLotto()
    }

    private fun inputAmount(): Amount {
        val amount = Amount.valueOfOrNull(inputView.getMoney())
        if (amount != null) return amount

        outputView.printErrorMessage(Message.errorInvalidAmount())
        return inputAmount()
    }

    private fun getWinningLotto(): WinningLotto {
        val numbers = inputView.getWinningLotto().mapNotNull { LottoNumber.valueOfOrNull(it) }
        val bonusNumber = LottoNumber.valueOfOrNull(inputView.getBonusNumber())

        if (bonusNumber == null) {
            outputView.printErrorMessage(Message.errorInvalidBonusNumber())
            return getWinningLotto()
        }

        val result = WinningLotto.valueOf(numbers, bonusNumber)
        if (result is WinningLottoCreationResult.Success) return result.winningLotto

        outputView.printErrorMessage(Message.errorInvalidWinningNumbers())
        return getWinningLotto()
    }

    private fun calculateEarningRate(ranks: List<Rank>): Double {
        val totalWinnings = RankCalculator().earningMoney(ranks)
        return RankCalculator().calculateEarningRate(amount.money, totalWinnings)
    }

    private fun sortResultsByOriginalRankOrder(ranks: List<Rank>): List<Pair<Rank, Int>> =
        Rank.entries.filter { it != Rank.MISS }
            .map { rank -> rank to ranks.count { it == rank } }
            .reversed()

    companion object {
        const val LOTTO_PRIZE = 1000
    }
}
