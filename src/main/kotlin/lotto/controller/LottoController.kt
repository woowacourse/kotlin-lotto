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
    val inputView: InputView,
    val outputView: OutputView,
) {
    val amount = inputAmount()

    fun run() {
        val count = getManualCount()
        val manualLottoList = getLottoList(count)
        val autoLottoList = getAutoLotto(amount.getCount(LOTTO_PRIZE) - count)

        outputView.printPurchaseResult(manualLottoList, autoLottoList)

        val winningLotto = getWinningLotto()
        val ranks = WinningListMaker().calculateRanks(winningLotto, manualLottoList + autoLottoList)

        val sortedResults = sortResultsByOriginalRankOrder(ranks)
        val totalWinnings = RankCalculator().earningMoney(ranks)
        val earningRate = RankCalculator().calculateEarningRate(amount.money, totalWinnings)

        outputView.printResult(sortedResults, earningRate)
    }

    fun getAutoLotto(count: Int): List<Lotto> = List(count) { Lotto.createRandom() }

    fun getManualCount(): Int {
        val manualCount = inputView.getManualCount()
        return if (amount.getCount(LOTTO_PRIZE) >= manualCount) {
            manualCount
        } else {
            outputView.printErrorMessage(Message.errorCountExceeded())
            getManualCount()
        }
    }

    fun getLottoList(count: Int): List<Lotto> {
        inputView.messageManualLotto()
        return List(count) { inputLotto() }
    }

    private fun inputLotto(): Lotto {
        when (
            val result =
                Lotto.create(
                    inputView
                        .getManualLotto()
                        .mapNotNull { LottoNumber.createOrNull(it) }
                        .sortedBy { it.value },
                )
        ) {
            is LottoCreationResult.Success -> return result.lotto
            is LottoCreationResult.Failure.InvalidCount -> outputView.printErrorMessage(Message.errorInvalidLotto())
            is LottoCreationResult.Failure.DuplicatedNumbers -> outputView.printErrorMessage(Message.errorInvalidLotto())
            is LottoCreationResult.Failure.NotSorted -> outputView.printErrorMessage(Message.errorInvalidLotto())
        }
        return inputLotto()
    }

    private fun inputAmount(): Amount {
        val amount = Amount.createOrNull(inputView.getMoney())
        return amount ?: run {
            outputView.printErrorMessage(Message.errorInvalidAmount())
            inputAmount()
        }
    }

    private fun getWinningLotto(): WinningLotto {
        when (
            val result =
                WinningLotto.create(
                    inputView.getWinningLotto().mapNotNull { LottoNumber.createOrNull(it) },
                    LottoNumber.createOrNull(inputView.getBonusNumber()) ?: return getWinningLotto(),
                )
        ) {
            is WinningLottoCreationResult.Success -> return result.winningLotto
            is WinningLottoCreationResult.Failure.NumberSizeError -> outputView.printErrorMessage(Message.errorInvalidWinningNumbers())
            is WinningLottoCreationResult.Failure.BonusNumberDuplicated -> outputView.printErrorMessage(Message.errorInvalidBonusNumber())
            is WinningLottoCreationResult.Failure.DuplicatedNumbers -> outputView.printErrorMessage(Message.errorInvalidWinningNumbers())
        }
        return getWinningLotto()
    }

    private fun sortResultsByOriginalRankOrder(ranks: List<Rank>): List<Pair<Rank, Int>> =
        Rank.entries
            .filter { it != Rank.MISS }
            .map { rank -> rank to ranks.count { it == rank } }
            .reversed()

    companion object {
        const val LOTTO_PRIZE = 1000
    }
}
