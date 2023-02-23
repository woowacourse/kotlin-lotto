package lotto.view

import lotto.entity.Lotto
import lotto.entity.LottoCount
import lotto.entity.LottoNumber
import lotto.entity.PurchaseMoney
import lotto.misc.tryAndRerun

class InputView(val outputView: OutputView) {
    private fun readInt(): Int {
        val input = readln()
        require(input.toIntOrNull() != null) { ERROR_MESSAGE_ONLY_NUMBER }
        return input.toInt()
    }

    private fun readIntList(): List<Int> {
        val input = readln()
        require(input.contains(",")) { ERROR_MESSAGE_SPLIT_BY_COMMA }
        val splittedInput = input.split(",").map { it.trim() }
        require(splittedInput.all { it.toIntOrNull() != null }) { ERROR_MESSAGE_SPLIT_ONLY_NUMBER }
        return splittedInput.map { it.toInt() }
    }

    fun readWinNumber(message: String): Lotto {
        return tryAndRerun {
            outputView.printMessage(message)
            Lotto(readIntList())
        }
    }

    fun readBonus(message: String): LottoNumber {
        return tryAndRerun {
            outputView.printMessage(message)
            LottoNumber.from(readInt())
        }
    }

    fun readPurchaseMoney(message: String): PurchaseMoney {
        return tryAndRerun {
            outputView.printMessage(message)
            PurchaseMoney(readInt())
        }
    }

    fun readLottoManualCount(message: String): LottoCount {
        return tryAndRerun {
            outputView.printMessage(message)
            LottoCount(readInt())
        }
    }

    fun readLotto(): Lotto {
        return tryAndRerun {
            Lotto(readIntList())
        }
    }

    companion object {
        private const val ERROR_MESSAGE_ONLY_NUMBER = "숫자로만 이루어져야 합니다"
        private const val ERROR_MESSAGE_SPLIT_BY_COMMA = "숫자는 콤마로 구별되어야 합니다"
        private const val ERROR_MESSAGE_SPLIT_ONLY_NUMBER = "구분된 입력은 숫자로만 이루어져야 합니다"
    }
}
