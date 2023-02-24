package controller

import domain.*
import view.InputView
import view.OutputView

class LottoController {

    fun run() {
        val amount = askAmount()
        val manuallyLottoCount = askManuallyLottoCountThatPurchasable(amount)
        val manuallyLottos = askManuallyLottos(manuallyLottoCount)
        val lottos = manuallyLottos + LottoStore.sell(amount - Money(LottoStore.LOTTO_PRICE) * manuallyLottoCount)
        OutputView.outputLottos(manuallyLottoCount, lottos)
        val winningLotto = askWinningLotto()
        OutputView.outputResult(LottoResult.of(lottos, winningLotto))
    }

    private fun askAmount(): Money {
        OutputView.printMessage(PURCHASE_AMOUNT_REQUEST_MESSAGE)
        return InputValueConverter.convert(InputView::readNumber, ::Money)
    }

    private fun askManuallyLottoCountThatPurchasable(limitMoney: Money): Count {
        OutputView.printMessage(MANUALLY_LOTTO_COUNT_REQUEST_MESSAGE)
        var count = InputValueConverter.convert(InputView::readNumber, ::Count)
        while (limitMoney < Money(LottoStore.LOTTO_PRICE) * count) {
            OutputView.printMessage(NOT_PURCHASABLE_MESSAGE)
            count = InputValueConverter.convert(InputView::readNumber, ::Count)
        }
        return count
    }

    private fun askManuallyLottos(count: Count): List<Lotto> {
        OutputView.printMessage(MANUALLY_LOTTO_REQUEST_MESSAGE)
        return List(count.toInt()) { InputValueConverter.convert(InputView::readNumbers, Lotto::create) }
    }

    private fun askWinningLotto(): WinningLotto {
        OutputView.printMessage(WINNING_LOTTO_REQUEST_MESSAGE)
        val winningNumbers = InputValueConverter.convert(InputView::readNumbers, Lotto::create)
        OutputView.printMessage(BONUS_NUMBER_REQUEST_MESSAGE)
        var bonusNumber = InputValueConverter.convert(InputView::readNumber, LottoNumber::valueOf)
        while (true) {
            kotlin.runCatching { return WinningLotto(winningNumbers, bonusNumber) }
                .onFailure {
                    OutputView.printErrorMessage(it)
                    bonusNumber = InputValueConverter.convert(InputView::readNumber, LottoNumber::valueOf)
                }
        }
    }

    companion object {
        private const val PURCHASE_AMOUNT_REQUEST_MESSAGE = "구입금액을 입력해 주세요."
        private const val MANUALLY_LOTTO_COUNT_REQUEST_MESSAGE = "\n수동으로 구매할 로또 수를 입력해 주세요."
        private const val NOT_PURCHASABLE_MESSAGE = "구매할 로또 금액이 구입금액을 초과할 수 없습니다."
        private const val MANUALLY_LOTTO_REQUEST_MESSAGE = "\n수동으로 구매할 번호를 입력해 주세요."
        private const val WINNING_LOTTO_REQUEST_MESSAGE = "\n지난 주 당첨 번호를 입력해 주세요."
        private const val BONUS_NUMBER_REQUEST_MESSAGE = "보너스 볼을 입력해 주세요."
    }
}
