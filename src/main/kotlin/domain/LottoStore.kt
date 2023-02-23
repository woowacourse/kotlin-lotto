package domain

class LottoStore {
    fun buyAutoLotto(count: Count): List<Lotto> {
        val lottoFactory = LottoFactory(RandomNumberGenerator())
        return lottoFactory.create(count)
    }

    fun buyManualLotto(lottos: List<String>): List<Lotto> {
        return lottos.map {
            Lotto.create(it.split(",").map { it.toInt() })
        }
    }

    companion object {
        const val LOTTO_PRICE = 1000
    }
}
