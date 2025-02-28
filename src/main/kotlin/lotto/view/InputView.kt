package lotto.view

import lotto.model.Lotto
import lotto.model.LottoCount
import lotto.model.LottoNumber
import lotto.model.LottoPurchaseAmount

class InputView {
    private val outputView = OutputView()

    fun readLottoPurchaseAmount(): LottoPurchaseAmount {
        outputView.printPurchaseAmountGuide()

        val money = readIntOrRetry { readLottoPurchaseAmount() }
        return LottoPurchaseAmount(money)
    }

    fun readManualLottoPurchaseCount(): LottoCount {
        outputView.printPurchaseManualLottoCountGuide()

        val count = readIntOrRetry { readManualLottoPurchaseCount() }
        return LottoCount(count)
    }

    fun readManualLottoNumbers(): List<LottoNumber> = readLottoNumbersWithGuideMessage {}

    fun readWinningLottoNumbersOfLastWeek(): Lotto {
        val winningLottoNumbersOfLastWeek =
            readLottoNumbersWithGuideMessage { outputView.printWinningLottoNumbersOfLastWeekGuide() }
        return runCatching {
            Lotto(winningLottoNumbersOfLastWeek)
        }.getOrElse { error ->
            outputView.printErrorMessage(error.message)
            readWinningLottoNumbersOfLastWeek()
        }
    }

    fun readBonusNumber(): LottoNumber {
        outputView.printBonusNumberGuide()
        val bonusNumber = readIntOrRetry { readBonusNumber() }
        return runCatching {
            LottoNumber.from(bonusNumber)
        }.getOrElse { error ->
            outputView.printErrorMessage(error.message)
            readBonusNumber()
        }
    }

    private fun <T> readIntOrRetry(action: () -> T): Int {
        return readln().toIntOrNull() ?: run {
            outputView.printErrorMessage(ERROR_INVALID_INPUT)
            return readIntOrRetry(action)
        }
    }

    private fun readLottoNumbersWithGuideMessage(guideMessage: () -> Unit): List<LottoNumber> {
        guideMessage()
        return runCatching {
            readln()
                .split(LOTTO_NUMBER_DELIMITER)
                .map { number -> number.toIntOrNull() ?: return readLottoNumbersWithGuideMessage(guideMessage) }
                .map { number -> LottoNumber.from(number) }
        }.getOrElse { error ->
            outputView.printErrorMessage(error.message)
            readManualLottoNumbers()
        }
    }

    companion object {
        private const val ERROR_INVALID_INPUT = "유효하지 않은 입력입니다."
        private const val LOTTO_NUMBER_DELIMITER = ","
    }
}
