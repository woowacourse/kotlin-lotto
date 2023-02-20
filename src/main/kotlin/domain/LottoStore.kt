package domain

class LottoStore {
    fun buyAutoLotto(amount: Amount): List<Lotto> {
        val lottoFactory = LottoFactory(RandomNumberGenerator())
        return lottoFactory.create(getCount(amount))
    }

    private fun getCount(amount: Amount): Int = (amount / LOTTO_PRICE).toInt()

    companion object {
        const val LOTTO_PRICE = 1000
    }
}
