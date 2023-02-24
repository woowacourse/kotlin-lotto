import domain.LottoMachine
import domain.Rank
import domain.Seller
import model.Count
import model.Lotto
import model.LottoNumber
import model.LottoResult
import model.Payment
import model.WinningNumber
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
        val lotteries = getLotto(lottoCount)
        printLotto(lotteries)
        val winningNumber = getWinningNumber()
        val bonusNumber = getBonusNumber(winningNumber)
        readyLottoResult(lotteries, winningNumber, bonusNumber)
        showLottoResult()
    }

    private fun printLotto(lotteries: List<Lotto>) {
        lotteries.forEach { lotto ->
            outputView.printLotto(lotto)
        }
    }

    private fun readyLottoResult(lotteries: List<Lotto>, winningNumber: Lotto, bonusNumber: WinningNumber) {
        lotteries.forEach { lotto ->
            val matchOfCount = compareLottoNumber(lotto, winningNumber)
            val isMatchBonus = checkBonusNumber(lotto, bonusNumber)
            val rank = Rank.valueOf(matchOfCount, isMatchBonus)

            calculateLottoResult(rank)
        }
    }

    private fun calculateLottoResult(rank: Rank) = lottoResult.updateRankCount(rank)

    private fun getLottoCount(): Count {
        outputView.printInputMoney()
        val payment = Payment(inputView.inputMoney())
        val count = Seller(payment).getLottoCount()
        outputView.printLottoCount(count.number)
        return count
    }

    private fun getLotto(lottoCount: Count) = List(lottoCount.number) {
        lottoMachine.generateLotto()
    }

    private fun getWinningNumber(): Lotto {
        outputView.printInputWinningNumbers()
        val winningNumber = inputView.inputWinningNumbers()
            .map { LottoNumber(it.toInt()) }
        return Lotto(winningNumber)
    }

    private fun getBonusNumber(winningNumber: Lotto): WinningNumber {
        outputView.printInputBonusNumber()
        return WinningNumber(winningNumber, LottoNumber(inputView.inputBonusNumber()))
    }

    private fun compareLottoNumber(lotto: Lotto, winningNumber: Lotto) = lotto.getMatchOfNumber(winningNumber)

    private fun checkBonusNumber(lotto: Lotto, winningNumber: WinningNumber) = lotto.isMatchBonus2(winningNumber)

    private fun showLottoResult() {
        outputView.printLottoResult(lottoResult)
        outputView.printLottoProfitRate(lottoResult.getProfitRate())
    }
}
