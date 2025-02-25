package lotto.domain.service

import lotto.domain.model.Lotto

interface LottoMachine {
    fun generateRandomLottoNumbers(): Lotto

    fun generateRandomLottoNumbers(numbers: List<Int>): Lotto
}
