package lotto.domain.service

import lotto.Constants

class LottoMachine {
    fun calculateTotalCount(purchaseAmount: Int) = purchaseAmount / Constants.LOTTO_AMOUNT
}
