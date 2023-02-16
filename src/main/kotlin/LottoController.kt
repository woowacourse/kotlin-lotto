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
        val ticketCount = getTicketCount()
        val lotteries = getLotto(ticketCount)

        lotteries.forEach { lotto ->
            outputView.printLotto(lotto)
        }
        val winningNumber = getWinningNumber()
        val bonusNumber = getBonusNumber(winningNumber)

        lotteries.forEach { lotto ->
            val matchOfCount = compareLottoNumber(lotto, winningNumber)
            print(matchOfCount)
            val isMatchBonus = checkBonusNumber(lotto, bonusNumber)
            print(isMatchBonus)
            val rank = lottoAdministrator.getRank(matchOfCount, isMatchBonus)
            lottoResult.plusRankCount(rank!!, lottoResult.result)
        }
        showLottoResult()
    }

    private fun getTicketCount(): Int {
        outputView.printInputMoney()
        val payment = Payment(inputView.inputMoney())
        val count = Seller(payment).getLottoCount()
        outputView.printLottoCount(count.number)
        return count.number
    }

    private fun getLotto(ticketCount: Int): List<Lotto> {
        val lotteries = mutableListOf<Lotto>()

        repeat(ticketCount) {
            val lotto = lottoMachine.generateLotto()
            lotteries.add(lotto)
        }

        return lotteries
    }

    private fun getWinningNumber(): Lotto {
        outputView.printInputWinningNumbers()
        return Lotto(inputView.inputWinningNumbers().map { LottoNumber(it.toInt()) })
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
        outputView.printLottoResult(lottoResult.result)
        outputView.printLottoProfitRate(lottoResult.getProfitRate(lottoResult.result))
    }
}
