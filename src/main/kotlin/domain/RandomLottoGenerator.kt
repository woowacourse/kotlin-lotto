package domain

class RandomLottoGenerator : LottoGenerator {

    override fun generateLottos(money: Int): Lottos {
        val count = money / LOTTO_PRICE
        var lottos = mutableListOf<Lotto>()
        repeat(count) { lottos.add(generateLotto()) }
        return Lottos(lottos)
    }

    fun generateLotto(): Lotto {
        val lotto = mutableSetOf<Int>()
        while (lotto.size < LOTTO_LIMIT_SIZE) {
            lotto.add((LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER).random())
        }
        return Lotto(lotto.sorted())
    }

    companion object {
        const val LOTTO_PRICE = 1000
        const val LOTTO_LIMIT_SIZE = 6
        const val LOTTO_MIN_NUMBER = 1
        const val LOTTO_MAX_NUMBER = 45
    }
}
