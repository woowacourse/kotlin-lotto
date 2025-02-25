package lotto.model

class LottoMachine {
    private val numbers: List<Int> = (LottoNumber.MIN_VALUE..LottoNumber.MAX_VALUE).toList()

    fun publishLottoTickets(
        lottoPurchaseInfo: LottoPurchaseInfo,
        manualNumbers: List<Int>? = null,
    ): List<Lotto> {
        if (manualNumbers == null) {
            return publishLottoByAuto(lottoPurchaseInfo.getAutoLottoQuantity())
        }
        return publishLottoByManual(manualNumbers) + publishLottoByAuto(lottoPurchaseInfo.getAutoLottoQuantity())
    }

    private fun publishLottoByManual(manualNumbers: List<Int>): List<Lotto> {
        return manualNumbers.chunked(Lotto.NUMBER_COUNT).map { numbers ->
            Lotto.from(numbers)
        }
    }

    private fun publishLottoByAuto(quantity: Int): List<Lotto> = List(quantity) { publishOneLottoByAuto() }

    private fun publishOneLottoByAuto(): Lotto {
        return Lotto.from(
            numbers.shuffled()
                .take(Lotto.NUMBER_COUNT)
                .sorted(),
        )
    }
}
