import domain.Count
import domain.Lotto
import domain.LottoCount
import domain.LottoMachine
import domain.LottoNumber
import domain.PaymentMoney
import domain.RankStatistics
import domain.WinningNumber
import view.InputViewInterface
import view.OutputViewInterface

class LottoGameController(
    private val inputView: InputViewInterface,
    private val outputView: OutputViewInterface,
    private val lottoMachine: LottoMachine
) {
    fun run() {
        val lottoCount = getLottoCount()
        val lotteries = getLotteries(lottoCount)
        val winningNumbers = getWinningNumber(getWinningLottoNumbers(), getBonusNumber())
        val rankStatistics = getRankStatistics(lotteries, winningNumbers)
        showRankResult(rankStatistics)
        showProfitRateResult(rankStatistics)
    }

    private fun getLottoCount(): LottoCount {
        val totalLottoCount = getPaymentMoney().getTotalLottoCount()
        val lottoCount = getLottoCount(totalLottoCount)
        outputView.printLottoCountResult(lottoCount)
        return lottoCount
    }

    private fun getLotteries(lottoCount: LottoCount): List<Lotto> {
        val manualLotteries = getManualLotteries(lottoCount.manualCount)
        val automaticLotteries = lottoMachine.generateLotteries(lottoCount.automaticCount)
        printAutoLotteries(automaticLotteries)
        return manualLotteries + automaticLotteries
    }

    private fun getPaymentMoney(): PaymentMoney {
        return PaymentMoney(inputView.inputPaymentMoney())
    }

    private fun getLottoCount(totalLottoCount: Count): LottoCount {
        return LottoCount(Count(inputView.inputManualLottoCount()), totalLottoCount)
    }

    private fun getManualLotteries(manualLottoCount: Count): List<Lotto> {
        return List(manualLottoCount.value) { getManualLotto() }
    }

    private fun getManualLotto(): Lotto {
        return Lotto(inputView.inputManualLottoNumbers().map(::LottoNumber))
    }

    private fun getWinningLottoNumbers(): List<LottoNumber> {
        return inputView.inputWinningLottoNumbers().map(::LottoNumber)
    }

    private fun getBonusNumber(): LottoNumber {
        return LottoNumber(inputView.inputBonusNumber())
    }

    private fun getWinningNumber(winningLottoNumbers: List<LottoNumber>, bonusNumber: LottoNumber): WinningNumber {
        return WinningNumber(winningLottoNumbers, bonusNumber)
    }

    private fun printAutoLotteries(lotteries: List<Lotto>) {
        outputView.printAutomaticLotteries(lotteries)
    }

    private fun getRankStatistics(lotteries: List<Lotto>, winningNumber: WinningNumber): RankStatistics {
        return RankStatistics(lotteries.map { lotto ->
            winningNumber.getRank(lotto)
        })
    }

    private fun showRankResult(rankStatistics: RankStatistics) {
        outputView.printRankStatistics(rankStatistics)
    }

    private fun showProfitRateResult(rankStatistics: RankStatistics) {
        val profitRate = rankStatistics.getProfitRate()
        val isLoss = rankStatistics.isProfitable()
        outputView.printProfitRate(profitRate, isLoss)
    }
}
