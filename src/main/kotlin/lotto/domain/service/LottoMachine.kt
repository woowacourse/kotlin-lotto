package lotto.domain.service

import lotto.domain.model.Lotto

interface LottoMachine {
    fun generateRandomLottoNumbers(): Lotto

    fun generateManualLottoNumbers(numbers: List<Int>): Lotto
}
