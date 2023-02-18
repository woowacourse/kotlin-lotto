import domain.LottoAdministrator
import domain.LottoMachine
import domain.Seller
import model.BonusNumber
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
        printLottoResult(lotteries, winningNumber, bonusNumber)
    }

    private fun printLotto(lotteries: List<Lotto>) {
        lotteries.forEach { lotto ->
            outputView.printLotto(lotto)
        }
    }

    private fun printLottoResult(lotteries: List<Lotto>, winningNumber: Lotto, bonusNumber: BonusNumber) {
        lotteries.forEach { lotto ->
            val matchOfCount = compareLottoNumber(lotto, winningNumber)
            val isMatchBonus = checkBonusNumber(lotto, bonusNumber)
            val rank = lottoAdministrator.getRank(matchOfCount, isMatchBonus)
            lottoResult.plusRankCount(rank!!, lottoResult.result)
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

    private fun getLotto(lottoCount: Int): List<Lotto> {
        val lotteries = mutableListOf<Lotto>()

        repeat(lottoCount) {
            val lotto = lottoMachine.generateLotto()
            lotteries.add(lotto)
        }

        return lotteries
    }

    private fun getWinningNumber(): Lotto {
        outputView.printInputWinningNumbers()
        val winningNumber = inputView.inputWinningNumbers()
            .map { LottoNumber.from(it.toInt()) }
        return Lotto(winningNumber)
    }

    private fun getBonusNumber(winningNumber: Lotto): BonusNumber {
        outputView.printInputBonusNumber()
        return BonusNumber(winningNumber, LottoNumber.from(inputView.inputBonusNumber()))
    }

    private fun compareLottoNumber(lotto: Lotto, winningNumber: Lotto): Int {
        return lottoAdministrator.getMatchOfNumber(lotto, winningNumber)
    }

    private fun checkBonusNumber(lotto: Lotto, bonusNumber: BonusNumber): Boolean {
        return lottoAdministrator.isMatchBonus(lotto, bonusNumber)
    }

    private fun showLottoResult() {
        outputView.printLottoRankResult(lottoResult.result)
        outputView.printLottoProfitRate(lottoResult.getProfitRate(lottoResult.result))
    }
}
