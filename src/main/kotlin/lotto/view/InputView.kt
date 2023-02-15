package lotto.view

import lotto.entity.InputMoney
import lotto.entity.LottoPrice
import lotto.entity.WinNumber

class InputView {
    fun inputMoney(lottoPrice: LottoPrice): InputMoney {
        val input = readln()
        require(input.toIntOrNull() != null) { "숫자로만 이루어져야 합니다" }
        val purchaseMoney = InputMoney(input.toInt())
        require(lottoPrice.value <= purchaseMoney.value) { "로또 1장의 가격보다 큰 숫자를 입력해야 합니다" }
        return purchaseMoney
    }

    fun winNumber(): WinNumber {
        val input = readln()
        require(input.contains(",")) { "숫자는 콤마로 구별되어야 합니다" }
        val splittedInput = input.split(",").map { it.trim() }
        require(splittedInput.all { it.toIntOrNull() != null }) { "구분된 입력은 숫자로만 이루어져야 합니다" }
        require(splittedInput.all { (1..45).contains(it.toInt()) }) { "구분된 입력은 1에서 45 사이의 숫자여야 합니다" }
        return WinNumber(splittedInput.map { it.toInt() })
    }
}
