package lotto.model

class AutoLottoMachine : LottoMachine {
    override fun publishLottoTickets(lottoPurchaseInfo: LottoPurchaseInfo): List<Lotto> {
        return List(lottoPurchaseInfo.getAutoLottoQuantity()) { Lotto.from(generateRandomNumbers()) }
    }

    private fun generateRandomNumbers(): List<Int> {
        return NUMBERS.shuffled()
            .take(Lotto.NUMBER_COUNT)
            .sorted()
            .toList()
    }

    companion object {
        private val NUMBERS = (LottoNumber.MIN_VALUE..LottoNumber.MAX_VALUE)
    }
}
