import domain.LottoAdministrator
import domain.LottoMachine
import domain.Seller
import model.Lotto
import model.LottoResult
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
        val bonusNumber = getBonusNumber()

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
        val money = inputView.inputMoney()
        val count = Seller(money).getLottoCount()
        outputView.printLottoCount(count)
        return count
    }

    private fun getLotto(ticketCount: Int): List<Lotto> {
        val lotteries = mutableListOf<Lotto>()

        repeat(ticketCount) {
            val lotto = lottoMachine.generateLotto()
            lotteries.add(lotto)
        }

        return lotteries
    }

    private fun getWinningNumber(): List<Int> {
        outputView.printInputWinningNumbers()
        return inputView.inputWinningNumbers().map { it.toInt() }
    }

    private fun getBonusNumber(): Int {
        outputView.printInputBonusNumber()
        return inputView.inputBonusNumber()
    }

    private fun compareLottoNumber(lotto: Lotto, winningNumber: List<Int>): Int {
        return lottoAdministrator.getMatchOfNumber(lotto.lottoNumbers, winningNumber)
    }

    private fun checkBonusNumber(lotto: Lotto, bonusNumber: Int): Boolean {
        return lottoAdministrator.isMatchBonus(lotto.lottoNumbers, bonusNumber)
    }

    private fun showLottoResult() {
        outputView.printLottoResult(lottoResult.result)
        outputView.printLottoProfitRate(lottoResult.getProfitRate(lottoResult.result))
    }
}
