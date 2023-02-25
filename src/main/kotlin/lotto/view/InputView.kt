package lotto.view

import lotto.entity.Lotto
import lotto.entity.LottoCount
import lotto.entity.LottoNumber
import lotto.entity.LottoPrice
import lotto.entity.Lottos
import lotto.entity.PurchaseMoney
import lotto.entity.WinLotto
import lotto.misc.tryAndRerun

class InputView {

    fun getPurchaseMoney(): PurchaseMoney {
        return tryAndRerun {
            println(MESSAGE_INPUT_MONEY)
            PurchaseMoney(readInt())
        } as PurchaseMoney
    }

    fun getBonus(winNumber: Lotto): LottoNumber {
        return tryAndRerun {
            println(MESSAGE_BONUS)
            val bonus = LottoNumber(readInt())
            WinLotto(winNumber, bonus)
            bonus
        } as LottoNumber
    }

    private fun readInt(): Int {
        val input = readln()
        require(input.toIntOrNull() != null) { ERROR_MESSAGE_ONLY_NUMBER }
        return input.toInt()
    }

    fun getManualLottoCount(purchaseMoney: PurchaseMoney): LottoCount {
        return tryAndRerun {
            println(MESSAGE_INPUT_MANUAL_LOTTO_COUNT)
            LottoCount(readManualLottoCount(purchaseMoney.value))
        } as LottoCount
    }

    private fun readManualLottoCount(purchaseMoney: Int): Int {
        val input = readln()
        require(input.toIntOrNull() != null) { ERROR_MESSAGE_ONLY_NUMBER }
        if (input.toInt() != 0)
            require((purchaseMoney / (input.toInt() * LottoPrice.DEFAULT_LOTTO_PRICE) > 0)) { ERROR_MESSAGE_AVAILABLE_COUNT }
        return input.toInt()
    }

    fun getManualLottos(manualLottoCount: LottoCount): Lottos {
        println(MESSAGE_INPUT_MANUAL_LOTTO_NUMBER)
        val lottos = mutableListOf<Lotto>()
        repeat(manualLottoCount.value) {
            lottos.add(getManualLotto())
        }
        return Lottos(lottos)
    }

    private fun getManualLotto(): Lotto {
        return tryAndRerun {
            Lotto.from(readLottoNumber().map(::LottoNumber))
        } as Lotto
    }

    fun getWinNumber(): Lotto {
        return tryAndRerun {
            println(MESSAGE_WIN_NUMBER)
            Lotto.from(readLottoNumber().map(::LottoNumber))
        } as Lotto
    }

    private fun readLottoNumber(): List<Int> {
        val input = readln()
        require(input.contains(",")) { ERROR_MESSAGE_SPLIT_BY_COMMA }
        val splittedInput = input.split(",").map { it.trim() }
        require(splittedInput.all { it.toIntOrNull() != null }) { ERROR_MESSAGE_SPLIT_ONLY_NUMBER }
        return splittedInput.map { it.toInt() }
    }

    companion object {
        private const val MESSAGE_INPUT_MONEY = "구입금액을 입력해 주세요."
        private const val MESSAGE_INPUT_MANUAL_LOTTO_COUNT = "수동으로 구매할 로또 수를 입력해 주세요."
        private const val MESSAGE_INPUT_MANUAL_LOTTO_NUMBER = "수동으로 구매할 번호를 입력해 주세요."
        private const val MESSAGE_WIN_NUMBER = "지난 주 당첨 번호를 입력해 주세요."
        private const val MESSAGE_BONUS = "보너스 볼을 입력해 주세요."
        private const val ERROR_MESSAGE_ONLY_NUMBER = "숫자로만 이루어져야 합니다"
        private const val ERROR_MESSAGE_AVAILABLE_COUNT = "구매 가능한 개수여야 합니다"
        private const val ERROR_MESSAGE_SPLIT_BY_COMMA = "숫자는 콤마로 구별되어야 합니다"
        private const val ERROR_MESSAGE_SPLIT_ONLY_NUMBER = "구분된 입력은 숫자로만 이루어져야 합니다"
    }
}
