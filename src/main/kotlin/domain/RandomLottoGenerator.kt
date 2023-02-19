package domain

class RandomLottoGenerator : LottoGenerator {

    override fun generateLottos(money: Money): List<Lotto> {
        val count = money.getLottoCount()
        return mutableListOf<Lotto>().getLottos(count)
    }

    private fun MutableList<Lotto>.getLottos(count: Int): List<Lotto> {
        repeat(count) {this.add(generateLotto())}
        return this
    }

    fun generateLotto(): Lotto {
        return Lotto((LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER).shuffled().subList(0, LOTTO_LIMIT_SIZE).map { LottoNumber.from(it) })
    }

    companion object {
        const val LOTTO_LIMIT_SIZE = 6
        const val LOTTO_MIN_NUMBER = 1
        const val LOTTO_MAX_NUMBER = 45
    }
}
