package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoFactory
import lotto.domain.LottoNumber
import lotto.domain.LottoResult
import lotto.domain.ManualLottoAmount
import lotto.domain.Price
import lotto.domain.WinningLotto
import lotto.service.AutoLottoNumberGenerator
import lotto.service.LottoAmountCalculator
import lotto.util.retryWhenException
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val price: Price = getPurchasePrice()
        val lottoAmount: Int = getLottoAmount(price)
        val manualLottoAmount: ManualLottoAmount = getManualAmount(lottoAmount)
        val autoLottoAmount: Int = lottoAmount - manualLottoAmount.amount

        val manualLottos = getManualLottos(manualLottoAmount.amount)

        val autoLottos = getAutoLottos(autoLottoAmount)

        val lottos: List<Lotto> = manualLottos + autoLottos

        outputView.printLottoAmount(manualLottoAmount.amount, autoLottoAmount)
        outputView.printLottos(lottos)

        val winningNumbers: Lotto = getWinningNumbers()
        val bonusNumber: LottoNumber = getBonusNumber()
        val winningLotto = WinningLotto(winningNumbers, bonusNumber)

        val profitRate: Double = LottoResult(lottos, winningLotto).calculateProfitRate()

        outputView.printResult(LottoResult(lottos, winningLotto))
        outputView.printProfit(profitRate)
    }

    private fun getAutoLottos(amount: Int): List<Lotto> {
        val generator: AutoLottoNumberGenerator = AutoLottoNumberGenerator()
        val autoLotto = LottoFactory().generateAutoLotto(generator)
        return List(amount) { autoLotto }
    }

    private fun getManualLottos(amount: Int): List<Lotto> {
        val lottoFactory = LottoFactory()
        val manualLottos = mutableListOf<Lotto>()

        outputView.printManualLottoMessage()

        repeat(amount) {
            val numbers = inputView.inputManualLottoNumber()
            manualLottos.add(lottoFactory.generateManualLotto(numbers))
        }

        return manualLottos
    }

    private fun getLottoAmount(price: Price): Int {
        return LottoAmountCalculator(price.price).calculateAmountOfLottos()
    }

    private fun getManualAmount(lottoAmount: Int): ManualLottoAmount {
        while (true) {
            val input = inputView.inputManualLottoAmount()
            if (input != null) {
                runCatching {
                    return ManualLottoAmount(input, lottoAmount)
                }.onFailure { e ->
                    outputView.printErrorMessage(e.message)
                }
            }
        }
    }

    private fun getPurchasePrice(): Price {
        while (true) {
            val input = inputView.inputPurchasePrice()
            if (input != null) {
                runCatching {
                    return Price(input)
                }.onFailure { e ->
                    outputView.printErrorMessage(e.message)
                }
            }
        }
    }

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

    private fun getBonusNumber(): LottoNumber {
        while (true) {
            val input = inputView.inputBonusNumber()
            if (input != null) {
                runCatching {
                    return LottoNumber(input)
                }.onFailure { e ->
                    outputView.printErrorMessage(e.message)
                }
            }
        }
    }
}
