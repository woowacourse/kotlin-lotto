package lotto.view

import lotto.model.Lotto
import lotto.model.LottoCount
import lotto.model.LottoNumber
import lotto.model.LottoPurchaseAmount

class InputView {
    fun readLottoPurchaseAmount(): LottoPurchaseAmount {
        val money = readln().toIntOrException()
        return LottoPurchaseAmount(money)
    }

    fun readManualLottoPurchaseCount(): LottoCount {
        val count = readln().toIntOrException()
        return LottoCount(count)
    }

    fun readManualLotto(): List<LottoNumber> =
        readln()
            .split(LOTTO_NUMBER_DELIMITER)
            .map { number -> number.toIntOrException() }
            .map { number -> LottoNumber(number) }

    fun readWinningLottoNumbersOfLastWeek(): Lotto {
        val lottoNumbersOfLastWeek =
            readln()
                .split(LOTTO_NUMBER_DELIMITER)
                .map { number -> number.toIntOrException() }
                .map { number -> LottoNumber(number) }
        return Lotto(lottoNumbersOfLastWeek)
    }

    fun readBonusNumber(): LottoNumber {
        val bonusNumber = readln().toIntOrException()
        return LottoNumber(bonusNumber)
    }

    private fun String.toIntOrException(): Int = this.trim().toIntOrNull() ?: throw IllegalArgumentException(ERROR_NOT_A_NUMBER)

    companion object {
        private const val ERROR_NOT_A_NUMBER = "숫자만 입력 가능합니다."
        private const val LOTTO_NUMBER_DELIMITER = ","
    }
}
