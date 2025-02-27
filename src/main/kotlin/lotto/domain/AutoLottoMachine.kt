package lotto.domain

import lotto.domain.model.Lotto
import lotto.domain.model.LottoNumber
import lotto.domain.model.LottoPurchaseInfo

class AutoLottoMachine : LottoMachine {
    override fun publishLottoTickets(lottoPurchaseInfo: LottoPurchaseInfo): List<Lotto> {
        return List(lottoPurchaseInfo.getAutoLottoQuantity()) { Lotto.from(generateRandomNumbers()) }
    }

    private fun generateRandomNumbers(): List<Int> {
        return NUMBERS.shuffled()
            .take(Lotto.NUMBER_COUNT)
            .sorted()
    }

    companion object {
        private val NUMBERS = (LottoNumber.MIN_VALUE..LottoNumber.MAX_VALUE)
    }
}
