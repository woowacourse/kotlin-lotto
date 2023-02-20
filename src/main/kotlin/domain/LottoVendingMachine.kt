package domain

object LottoVendingMachine {
    private const val LOTTO_PRICE = 1000

    fun getLottoCount(money: Money): Int {
        return (money.amount / LOTTO_PRICE).toInt()
    }

    fun generateLottoBundle(lottoCount: Int, lottoGenerator: LottoGenerator): LottoBundle {
        return LottoBundle(List(lottoCount) { lottoGenerator.generate() })
    }
}
