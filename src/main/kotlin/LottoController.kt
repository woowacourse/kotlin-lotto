import domain.LottoMachine
import domain.Rank
import domain.Seller
import model.Lotto
import model.LottoCount
import model.LottoNumber
import model.LottoResult
import model.Payment
import model.WinningLotto
import view.InputView
import view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val lottoMachine: LottoMachine,
    private val lottoResult: LottoResult,
) {

    fun run() {
        val lottoCount = getLottoCount()
        val lotteries = getLotteries(lottoCount)
        showLotteries(lotteries)
        val winningLotto = WinningLotto(getWinningNumbers(), getBonusNumber())
        updateLottoResult(lotteries, winningLotto)
        showLottoResult()
    }

    private fun updateLottoResult(lotteries: List<Lotto>, winningLotto: WinningLotto) {
        lotteries.forEach { lotto ->
            val rank = Rank.getRank(lotto, winningLotto)
            if (rank != null) lottoResult.updateLottoResult(rank)
        }
    }

    private fun getLottoCount(): LottoCount {
        outputView.printInputMoney()
        val payment = Payment(inputView.inputMoney())
        val lottoCount = Seller(payment).getLottoCount()
        outputView.printLottoCount(lottoCount.count)
        return lottoCount
    }

    private fun getLotteries(lottoCount: LottoCount): List<Lotto> {
        val lotteries = mutableListOf<Lotto>()
        repeat(lottoCount.count) { lotteries.add(lottoMachine.generateLotto()) }
        return lotteries
    }

    private fun showLotteries(lotteries: List<Lotto>) {
        lotteries.forEach { lotto ->
            outputView.printLotto(lotto)
        }
    }

    private fun getWinningNumbers(): Lotto {
        outputView.printInputWinningNumbers()
        val winningNumber = inputView.inputWinningNumbers()
            .map { LottoNumber.from(it.toInt()) }
        return Lotto(winningNumber)
    }

    private fun getBonusNumber(): LottoNumber {
        outputView.printInputBonusNumber()
        return LottoNumber.from(inputView.inputBonusNumber())
    }

    private fun showLottoResult() {
        outputView.printLottoRankResult(lottoResult.result)
        outputView.printLottoProfitRate(lottoResult.getProfitRate())
    }
}
