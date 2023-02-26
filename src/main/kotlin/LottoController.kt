import domain.LottoMachine
import domain.Seller
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
) {

    fun run() {
        outputView.printInputMoney()
        val payment = Payment(inputView.inputMoney())
        val seller = Seller(payment, lottoMachine)
        outputView.printLottoCount(seller.getLottoCount().number)

        val bundleOfLotto = seller.getLotto()
        printLotto(bundleOfLotto)

        val winningNumber = getWinningNumber()
        outputView.printInputBonusNumber()
        val bonusNumber = LottoNumber(inputView.inputBonusNumber())

        val lottoResult = LottoResult(bundleOfLotto, WinningNumber(winningNumber, bonusNumber))
        // 컴파일고치기
        showLottoResult(lottoResult)
    }

    private fun printLotto(bundleOfLotto: List<Lotto>) {
        bundleOfLotto.forEach { lotto ->
            outputView.printLotto(lotto)
        }
    }

    private fun getWinningNumber(): Lotto {
        outputView.printInputWinningNumbers()
        val winningNumber = inputView.inputWinningNumbers()
            .map { LottoNumber(it.toInt()) }
        return Lotto(winningNumber)
    }

    private fun showLottoResult(lottoResult: LottoResult) {
        outputView.printLottoResult(lottoResult)
        outputView.printLottoProfitRate(lottoResult.getProfitRate())
    }
}
