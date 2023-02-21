package lotto.view

import lotto.entity.Lotto
import lotto.entity.LottoNumber
import lotto.entity.PurchaseMoney
import lotto.entity.WinLotto
import lotto.misc.tryAndRerun

class InputView {

    fun readPurchaseMoney(): PurchaseMoney {
        return tryAndRerun {
            val input = readln()
            require(input.toIntOrNull() != null) { ERROR_MESSAGE_ONLY_NUMBER }
            val purchaseMoney = PurchaseMoney(input.toInt())
            purchaseMoney
        } as PurchaseMoney
    }

    fun readWinNumber(): Lotto {
        return tryAndRerun {
            val input = readln()
            require(input.contains(",")) { ERROR_MESSAGE_SPLIT_BY_COMMA }
            val splittedInput = input.split(",").map { it.trim() }
            require(splittedInput.all { it.toIntOrNull() != null }) { ERROR_MESSAGE_SPLIT_ONLY_NUMBER }
            Lotto.from(splittedInput.map { LottoNumber(it.toInt()) })
        } as Lotto
    }

    fun readBonus(winNumber: Lotto): LottoNumber {
        return tryAndRerun {
            val input = readln()
            require(input.toIntOrNull() != null) { ERROR_MESSAGE_ONLY_NUMBER }
            val bonus = LottoNumber(input.toInt())
            WinLotto(winNumber, bonus)
            bonus
        } as LottoNumber
    }

    companion object {
        private const val ERROR_MESSAGE_ONLY_NUMBER = "숫자로만 이루어져야 합니다"
        private const val ERROR_MESSAGE_SPLIT_BY_COMMA = "숫자는 콤마로 구별되어야 합니다"
        private const val ERROR_MESSAGE_SPLIT_ONLY_NUMBER = "구분된 입력은 숫자로만 이루어져야 합니다"
    }
}
