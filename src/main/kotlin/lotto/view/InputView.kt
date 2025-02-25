package lotto.view

import lotto.model.Lotto
import lotto.model.LottoCount
import lotto.model.LottoNumber
import lotto.model.LottoPurchaseAmount

class InputView {
    fun readLottoPurchaseAmount(): LottoPurchaseAmount {
        val money = readln().trim().toIntOrNull() ?: throw IllegalArgumentException(ERROR_NOT_A_NUMBER)
        return LottoPurchaseAmount(money)
    }

    fun readManualLottoPurchaseCount(): LottoCount {
        val count = readln().trim().toIntOrNull() ?: throw IllegalArgumentException(ERROR_NOT_A_NUMBER)
        return LottoCount(count)
    }

    fun readWinningLottoNumbersOfLastWeek(): Lotto {
        val lottoNumbersOfLastWeek =
            readln()
                .split(LOTTO_NUMBER_DELIMITER)
                .map { number -> number.trim().toIntOrNull() ?: throw IllegalArgumentException(ERROR_NOT_A_NUMBER) }
                .map { number -> LottoNumber(number) }
        return Lotto(lottoNumbersOfLastWeek)
    }

    fun readBonusNumber(): LottoNumber {
        val bonusNumber = readln().trim().toIntOrNull() ?: throw IllegalArgumentException(ERROR_NOT_A_NUMBER)
        return LottoNumber(bonusNumber)
    }

    companion object {
        private const val ERROR_NOT_A_NUMBER = "숫자만 입력 가능합니다."
        private const val LOTTO_NUMBER_DELIMITER = ","
    }
}
