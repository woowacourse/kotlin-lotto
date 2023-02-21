package domain

class LottoStore {
    fun buyAutoLotto(amount: Amount): List<Lotto> {
        val lottoFactory = LottoFactory(RandomNumberGenerator())
        return lottoFactory.create(getCount(amount))
    }

    fun buyManualLotto(lottos: List<String>): List<Lotto> {
        return lottos.map {
            Lotto.create(it.split(",").map { it.toInt() })
        }
    }

    private fun getCount(amount: Amount): Int = (amount.amount / LOTTO_PRICE).toInt()

    companion object {
        const val LOTTO_PRICE = 1000
    }
}
