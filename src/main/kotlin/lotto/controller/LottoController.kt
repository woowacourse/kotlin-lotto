package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoNumber
import lotto.domain.LottoResult
import lotto.domain.LottoSeller
import lotto.domain.WinningLotto
import lotto.util.retryWhenException
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        // 구매할 금액을 입력받는다
        val price = getPurchasePrice()
        // 수동으로 구매할 수량을 입력받는다
        val manualLottoAmount: Int = inputView.inputManualLottoAmount()
        // 수동으로 번호를 입력받고 로또 리스트로 반환
        val manualLottoNumbers: List<List<Int>> = getManualLottoNumbers(manualLottoAmount)

        val lottoSeller = LottoSeller(price, manualLottoAmount, manualLottoNumbers)

        val lottos: List<Lotto> = lottoSeller.getLottos()
        val autoLottoAmount = lottoSeller.getAutoLottoAmount()

        outputView.printLottoAmount(manualLottoAmount, autoLottoAmount)

        outputView.printLottos(lottos)

        val winningNumbers: Lotto = getWinningNumbers()
        val bonusNumber: LottoNumber = getBonusNumber()
        val winningLotto = WinningLotto(winningNumbers, bonusNumber)

        val profitRate: Double = LottoResult(lottos, winningLotto).calculateProfitRate()

        outputView.printResult(LottoResult(lottos, winningLotto))
        outputView.printProfit(profitRate)
    }

    private fun getManualLottoNumbers(amount: Int): List<List<Int>> {
        val manualLottoNumbers = mutableListOf<List<Int>>()
        outputView.printManualLottoMessage()

        repeat(amount) {
            val numbers = inputView.inputManualLottoNumber()
            manualLottoNumbers.add(numbers)
        }
        return manualLottoNumbers
    }

    private fun getPurchasePrice(): Int =
        retryWhenException(
            action = {
                val input = inputView.inputPurchasePrice()
                input.toInt()
            },
            onError = {
                outputView.printErrorMessage(it)
            },
        )

    private fun getWinningNumbers(): Lotto =
        retryWhenException(
            action = {
                val input = inputView.inputWinningNumber()
                Lotto(input.map { number -> LottoNumber(number.toInt()) })
            },
            onError = {
                outputView.printErrorMessage(it)
            },
        )

    private fun getBonusNumber(): LottoNumber =
        retryWhenException(
            action = {
                val input = InputView.inputBonusNumber()
                LottoNumber(input.toInt())
            },
            onError = {
                outputView.printErrorMessage(it)
            },
        )
}
