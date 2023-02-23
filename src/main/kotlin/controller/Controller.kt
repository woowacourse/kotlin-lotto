package controller

import domain.Count
import domain.Lotto
import domain.LottoCount
import domain.LottoMachine
import domain.PaymentMoney
import domain.RankStatistics
import domain.WinningNumber
import inputvalidation.BonusNumberValidation
import inputvalidation.LottoValidation
import inputvalidation.ManualLottoCountValidation
import inputvalidation.PaymentMoneyValidation
import view.InputViewInterface
import view.OutputViewInterface

class Controller(
    private val inputView: InputViewInterface,
    private val outputView: OutputViewInterface,
    private val lottoMachine: LottoMachine
) {
    fun run() {
        val totalLottoCount = getPaymentMoney().getTotalLottoCount()
        val lottoCount = getLottoCount(totalLottoCount)
        outputView.printLottoCountResult(lottoCount)
        val manualLotteries = getManualLotteries(lottoCount.manualCount)
        val automaticLotteries = lottoMachine.generateLotteries(lottoCount.automaticCount)
        printAutoLotteries(automaticLotteries)
        val winningNumbers = getWinningNumbers(getWinningLotto())
        showResult(getRankStatistics(manualLotteries, automaticLotteries, winningNumbers))
    }

    private fun <T> handleInputResult(result: Result<T>): T? {
        result.onSuccess { input ->
            return input
        }.onFailure { error ->
            println(error.message)
            return null
        }.also {
            throw IllegalStateException(UNKNOWN_ERROR)
        }
    }

    private fun getPaymentMoney(): PaymentMoney {
        val input = inputView.inputPaymentMoney()
        val validationResult = PaymentMoneyValidation().checkPaymentMoney(input)
        return handleInputResult(validationResult) ?: getPaymentMoney()
    }

    private fun getLottoCount(totalLottoCount: Count): LottoCount {
        val input = inputView.inputManualLottoCount()
        val validationResult = ManualLottoCountValidation().checkManualLottoCountValidation(input, totalLottoCount)
        return handleInputResult(validationResult) ?: getLottoCount(totalLottoCount)
    }

    private fun getManualLotteries(manualLottoCount: Count): List<Lotto> {
        return List(manualLottoCount.value) {
            val input = inputView.inputManualLotto()
            val validationResult = LottoValidation().checkLottoValidation(input)
            handleInputResult(validationResult) ?: return getManualLotteries(manualLottoCount)
        }
    }

    private fun getWinningLotto(): Lotto {
        val input = inputView.inputWinningLotto()
        val validationResult = LottoValidation().checkLottoValidation(input)
        return handleInputResult(validationResult) ?: getWinningLotto()
    }

    private fun getWinningNumbers(winningLotto: Lotto): WinningNumber {
        val input = inputView.inputBonusNumber()
        val validationResult = BonusNumberValidation().checkBonusNumberValidation(input, winningLotto)
        return handleInputResult(validationResult) ?: getWinningNumbers(winningLotto)
    }

    private fun printAutoLotteries(lotteries: List<Lotto>) {
        outputView.printAutomaticLotteries(lotteries)
    }

    private fun getRankStatistics(
        manualLotteries: List<Lotto>,
        autoLotteries: List<Lotto>,
        winningNumber: WinningNumber
    ): RankStatistics {
        val rankStatistics = RankStatistics()
        manualLotteries.forEach { lotto ->
            val rank = lotto.getRank(winningNumber)
            rankStatistics.updateRankCount(rank)
        }
        autoLotteries.forEach { lotto ->
            val rank = lotto.getRank(winningNumber)
            rankStatistics.updateRankCount(rank)
        }
        return rankStatistics
    }

    private fun showResult(rankStatistics: RankStatistics) {
        outputView.printRankStatistics(rankStatistics)
        getProfitRate(rankStatistics)
    }

    private fun getProfitRate(rankStatistics: RankStatistics) {
        val profitRate = rankStatistics.getProfitRate()
        val isLoss = rankStatistics.isProfitable(profitRate)
        outputView.printProfitRate(profitRate, isLoss)
    }

    companion object {
        const val UNKNOWN_ERROR = "알수 없는 오류입니다"
    }
}
