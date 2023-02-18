import domain.LottoMachine
import domain.Rank
import domain.Seller
import model.Lotto
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
        printLotto(lotteries)
        val winningNumber = getWinningNumber()
        val winningLotto = getBonusNumber(winningNumber)
        printLottoResult(lotteries, winningLotto)
    }

    private fun printLotto(lotteries: List<Lotto>) {
        lotteries.forEach { lotto ->
            outputView.printLotto(lotto)
        }
    }

    private fun printLottoResult(lotteries: List<Lotto>, winningLotto: WinningLotto) {
        lotteries.forEach { lotto ->
            val rank = Rank.getRank(lotto, winningLotto)
            if (rank != null) {
                lottoResult.plusRankCount(rank, lottoResult.result)
            }
        }
        showLottoResult()
    }

    private fun getLottoCount(): Int {
        outputView.printInputMoney()
        val payment = Payment(inputView.inputMoney())
        val lottoCount = Seller(payment).getLottoCount()
        outputView.printLottoCount(lottoCount.count)
        return lottoCount.count
    }

    private fun getLotteries(lottoCount: Int): List<Lotto> {
        val lotteries = mutableListOf<Lotto>()
        repeat(lottoCount) { lotteries.add(lottoMachine.generateLotto()) }
        return lotteries
    }

    private fun getWinningNumber(): Lotto {
        outputView.printInputWinningNumbers()
        val winningNumber = inputView.inputWinningNumbers()
            .map { LottoNumber.from(it.toInt()) }
        return Lotto(winningNumber)
    }

    private fun getBonusNumber(winningNumber: Lotto): WinningLotto {
        outputView.printInputBonusNumber()
        return WinningLotto(winningNumber, LottoNumber.from(inputView.inputBonusNumber()))
    }

    private fun showLottoResult() {
        outputView.printLottoRankResult(lottoResult.result)
        outputView.printLottoProfitRate(lottoResult.getProfitRate(lottoResult.result))
    }
}
