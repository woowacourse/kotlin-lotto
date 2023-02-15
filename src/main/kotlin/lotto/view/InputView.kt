package lotto.view

import lotto.entity.Bonus
import lotto.entity.Lotto
import lotto.entity.LottoPrice
import lotto.entity.PurchaseMoney
import lotto.entity.WinNumber

object InputView {
    private const val ERROR_MESSAGE_ONLY_NUMBER = "숫자로만 이루어져야 합니다"
    private const val ERROR_MESSAGE_SPLIT_BY_COMMA = "숫자는 콤마로 구별되어야 합니다"
    private const val ERROR_MESSAGE_WIN_NUMBER_IS_SIX = "당첨 번호는 6개여야 합니다"
    private const val ERROR_MESSAGE_DUPLICATED_NUMBER = "당첨 번호는 중복될 수 없습니다"
    private const val ERROR_MESSAGE_GREATER_THAN_LOTTO_PRICE = "로또 1장의 가격보다 큰 숫자를 입력해야 합니다"
    private const val ERROR_MESSAGE_SPLIT_ONLY_NUMBER = "구분된 입력은 숫자로만 이루어져야 합니다"
    private const val ERROR_MESSAGE_RANGE_1_TO_45 = "입력은 1에서 45 사이의 숫자여야 합니다"

    fun readPurchaseMoney(lottoPrice: LottoPrice): PurchaseMoney {
        val input = readln()
        require(input.toIntOrNull() != null) { ERROR_MESSAGE_ONLY_NUMBER }
        val purchaseMoney = PurchaseMoney(input.toInt())
        require(lottoPrice.value <= purchaseMoney.value) { ERROR_MESSAGE_GREATER_THAN_LOTTO_PRICE }
        return purchaseMoney
    }

    fun readWinNumber(): WinNumber {
        val input = readln()
        require(input.contains(",")) { ERROR_MESSAGE_SPLIT_BY_COMMA }
        val splittedInput = input.split(",").map { it.trim() }
        require(splittedInput.size == Lotto.LOTTO_COUNT) { ERROR_MESSAGE_WIN_NUMBER_IS_SIX }
        require(splittedInput.all { it.toIntOrNull() != null }) { ERROR_MESSAGE_SPLIT_ONLY_NUMBER }
        require(splittedInput.all { (Lotto.MINIMUM_LOTTO_NUMBER..Lotto.MAXIMUM_LOTTO_NUMBER).contains(it.toInt()) }) { ERROR_MESSAGE_RANGE_1_TO_45 }
        require(splittedInput.intersect(splittedInput.toSet()).size == Lotto.LOTTO_COUNT) { ERROR_MESSAGE_DUPLICATED_NUMBER }
        return WinNumber(splittedInput.map { it.toInt() })
    }

    fun readBonus(winNumber: WinNumber): Bonus {
        val input = readln()
        require(input.toIntOrNull() != null) { ERROR_MESSAGE_ONLY_NUMBER }
        require((Lotto.MINIMUM_LOTTO_NUMBER..Lotto.MAXIMUM_LOTTO_NUMBER).contains(input.toInt())) { ERROR_MESSAGE_RANGE_1_TO_45 }
        require(!winNumber.value.contains(input.toInt())) { ERROR_MESSAGE_DUPLICATED_NUMBER }
        return Bonus(input.toInt())
    }
}
