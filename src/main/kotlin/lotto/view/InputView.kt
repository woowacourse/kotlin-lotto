package lotto.view

import lotto.entity.InputMoney
import lotto.entity.LottoPrice

class InputView {
    fun inputMoney(lottoPrice: LottoPrice): InputMoney {
        val input = readln()

        require(input.toIntOrNull() != null) {
            "숫자로만 이루어져야 합니다"
        }

        val purchaseMoney = InputMoney(input.toInt())

        require(lottoPrice.value <= purchaseMoney.value) {
            "로또 1장의 가격보다 큰 숫자를 입력해야 합니다"
        }

        return purchaseMoney
    }
}
