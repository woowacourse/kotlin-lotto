import domain.LottoAdministrator
import domain.LottoMachine
import domain.Rank
import domain.Seller
import model.BonusNumber
import model.Count
import model.Lotto
import model.LottoNumber
import model.LottoResult
import model.Payment
import view.InputView
import view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val lottoMachine: LottoMachine,
    private val lottoAdministrator: LottoAdministrator,
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

    private fun readyLottoResult(lotteries: List<Lotto>, winningNumber: Lotto, bonusNumber: BonusNumber) {
        lotteries.forEach { lotto ->
            val matchOfCount = compareLottoNumber(lotto, winningNumber)
            val isMatchBonus = checkBonusNumber(lotto, bonusNumber)
            val rank = lottoAdministrator.getRank(matchOfCount, isMatchBonus)

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

    private fun getBonusNumber(winningNumber: Lotto): BonusNumber {
        outputView.printInputBonusNumber()
        return BonusNumber(winningNumber, LottoNumber(inputView.inputBonusNumber()))
    }

    private fun compareLottoNumber(lotto: Lotto, winningNumber: Lotto): Int {
        return lottoAdministrator.getMatchOfNumber(lotto, winningNumber)
    }

    private fun checkBonusNumber(lotto: Lotto, bonusNumber: BonusNumber): Boolean {
        return lottoAdministrator.isMatchBonus(lotto, bonusNumber)
    }

    private fun showLottoResult() {
        outputView.printLottoResult(lottoResult)
        outputView.printLottoProfitRate(lottoResult.getProfitRate())
    }
}
