package domain

class RandomLottoGenerator : LottoGenerator {

    override fun generateLottos(money: Money): Lottos {
        val count = money.price / LOTTO_PRICE
        val lottos = mutableListOf<Lotto>()
        repeat(count) { lottos.add(generateLotto()) }
        return Lottos(lottos)
    }

    private fun generateLotto(): Lotto {
        val randomNumbers = (LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER).shuffled().subList(0, LOTTO_LIMIT_SIZE).sorted()
        val test = randomNumbers.map { LottoNumber.from(it) }
        return Lotto(test)
    }

    companion object {
        const val LOTTO_PRICE = 1000
        const val LOTTO_LIMIT_SIZE = 6
        const val LOTTO_MIN_NUMBER = 1
        const val LOTTO_MAX_NUMBER = 45
    }
}
