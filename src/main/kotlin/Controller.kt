import model.* // ktlint-disable no-wildcard-imports
import model.domain.AutoLottoGenerator
import model.domain.LottoMachine
import model.domain.ManualLottoGenerator
import presentation.GameStatisticsView
import presentation.InputWinningNumbersView
import presentation.OrderLottoView

class Controller(
    private val orderLottoView: OrderLottoView,
    private val lottoMachine: LottoMachine,
    private val autoLottoGenerator: AutoLottoGenerator,
    private val manualLottoGenerator: ManualLottoGenerator,
) {

    fun run() {
        val money = Money(getMoney())
        val count = Count(money, getManualLottoCount())
        makeLotto(count)
        printPurchasedLotto(count)

        val getWinningNumbers = getWinningNumbers()
        endGame(getWinningNumbers)
    }

    private fun endGame(getWinningNumbers: WinningNumbers) {
        val lottoResult = getWinningNumbers.getLottoResult(lottoMachine.bundleOfLotto)

        val gameStatisticsView = GameStatisticsView()
        gameStatisticsView.printLottoResult(lottoResult)
        gameStatisticsView.printLottoProfitRate(lottoResult.getProfitRate())
    }

    private fun printPurchasedLotto(count: Count) {
        orderLottoView.printLottoCount(count)
        lottoMachine.bundleOfLotto.forEach { lotto ->
            orderLottoView.printAllLotto(changeLottoNumberToInt(lotto))
        }
    }

    private fun getWinningNumbers(): WinningNumbers {
        val inputWinningNumbersView = InputWinningNumbersView()
        val winningNumber = getWinningNumber(inputWinningNumbersView)
        val bonusNumber = LottoNumber(getBonusNumber(inputWinningNumbersView))

        return WinningNumbers(winningNumber, bonusNumber)
    }

    private fun getWinningNumber(inputWinningNumbersView: InputWinningNumbersView): Lotto {
        val winningNumber = inputWinningNumbersView.getWinningNumber()

        return Lotto(
            winningNumber.map { number ->
                LottoNumber(number)
            },
        )
    }

    private fun getBonusNumber(inputWinningNumbersView: InputWinningNumbersView): Int =
        inputWinningNumbersView.getBonusNumber() ?: getBonusNumber(inputWinningNumbersView)

    private fun changeLottoNumberToInt(lotto: Lotto): List<Int> = lotto.ticket.map { lottoNumber ->
        lottoNumber.value
    }

    private fun makeLotto(count: Count) {
        makeManualLotto(count)
        makeAutoLotto(count)
    }

    private fun makeManualLotto(count: Count) {
        repeat(count.manualLottoCount) {
            manualLottoGenerator.makeBundleOfManualLotto(getManualLotto())
            lottoMachine.generateLotto(manualLottoGenerator)
        }
    }

    private fun makeAutoLotto(count: Count) {
        repeat(count.autoLottoCount) {
            lottoMachine.generateLotto(autoLottoGenerator)
        }
    }

    private fun getMoney(): Int = orderLottoView.getMoney() ?: retryGetMoney()

    private fun getManualLottoCount(): Int = orderLottoView.getManualLottoCount() ?: retryGetManualLottoCount()

    private fun getManualLotto(): List<LottoNumber> {
        val manualLotto = orderLottoView.getManualLotto()

        return manualLotto.map { number ->
            LottoNumber(number)
        }
    }

    private fun retryGetMoney(): Int {
        orderLottoView.printInvalidValueError()
        return getMoney()
    }

    private fun retryGetManualLottoCount(): Int {
        orderLottoView.printInvalidValueError()
        return getManualLottoCount()
    }
}
