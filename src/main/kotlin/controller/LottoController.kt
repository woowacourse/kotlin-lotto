package controller

import domain.*
import view.InputView
import view.OutputView

class LottoController {
    private val inputView by lazy { InputView() }
    private val outputView by lazy { OutputView() }

    fun run() {
        val amount = askAmount()
        val numberOfLottosToBuyManually = askNumberOfLottosToBuyManually(amount)
        val lottosToBuyManually = askLottosToBuyManually(numberOfLottosToBuyManually)
        val lottos =
            lottosToBuyManually + getLotto(amount - Money(LottoStore.LOTTO_PRICE) * numberOfLottosToBuyManually)
        outputView.outputLottos(numberOfLottosToBuyManually, lottos)
        val winningLotto = WinningLotto(askWinningNumbers(), askBonusNumber())
        val result = LottoResult.of(lottos, winningLotto)
        outputView.outputResult(result)
    }

    private fun askAmount(): Money {
        outputView.outputGetAmount()

        while (true) {
            kotlin.runCatching {
                return Money(getAmount())
            }
                .onFailure { outputView.printErrorMessage(it) }
        }
    }

    private fun getAmount(): Int =
        inputView.inputAmount().let {
            var amount = it
            while (amount == null) {
                println(ERROR_INPUT_NOT_INT_TYPE)
                amount = inputView.inputAmount()
            }
            amount
        }

    private fun askNumberOfLottosToBuyManually(limitedAmount: Money): Count {
        outputView.outputGetNumberOfLottosToBuyManually()
        var numberOfLottosToBuyManually = getNumberOfLottosToBuyManually()

        while (limitedAmount < Money(LottoStore.LOTTO_PRICE) * numberOfLottosToBuyManually) {
            println(ERROR_OVER_LIMIT_AMOUNT)
            numberOfLottosToBuyManually = getNumberOfLottosToBuyManually()
        }

        return numberOfLottosToBuyManually
    }

    private fun getNumberOfLottosToBuyManually(): Count {
        val numberOfLottosToBuyManually = inputView.inputNumberOfLottosToBuyManually().let {
            var count = it
            while (count == null) {
                outputView.printMessage(ERROR_INPUT_NOT_INT_TYPE)
                count = inputView.inputNumberOfLottosToBuyManually()
            }
            count
        }

        while (true) {
            kotlin.runCatching {
                return Count(numberOfLottosToBuyManually)
            }
                .onFailure { outputView.printErrorMessage(it) }
        }
    }

    private fun askLottosToBuyManually(count: Count): List<Lotto> {
        if (count.isZero()) return listOf()
        outputView.outputGetLottosToBuyManually()
        return inputView.inputLottosToBuyManually(count)
    }

    private fun getLotto(amount: Money): List<Lotto> {
        return LottoStore.sell(amount)
    }

    private fun askWinningNumbers(): Lotto {
        outputView.outputGetWinningNumbers()
        return Lotto.create(inputView.inputWinningNumbers())
    }

    private fun askBonusNumber(): LottoNumber {
        outputView.outputGetBonusNumber()
        return LottoNumber.valueOf(inputView.inputBonusNumber())
    }

    companion object {
        private const val ERROR_INPUT_NOT_INT_TYPE = "int 타입으로 입력 바랍니다."
        private const val ERROR_OVER_LIMIT_AMOUNT = "구매하려는 로또 가격이 입력된 구입 금액을 초과합니다."
    }
}
