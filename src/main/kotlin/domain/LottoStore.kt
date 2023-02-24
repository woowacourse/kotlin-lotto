package domain

object LottoStore {
    const val LOTTO_PRICE = 1000

    fun sell(money: Money): List<Lotto> {
        return LottoFactory.create(Count(getCount(money)))
    }

    private fun getCount(money: Money) = money / Money(LOTTO_PRICE)
}
