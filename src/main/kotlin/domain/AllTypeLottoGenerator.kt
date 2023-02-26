package domain

class AllTypeLottoGenerator : LottoGenerator {
    override fun generateLottos(count: Int): List<Lotto> {
        return List(count) { generateLotto() }
    }

    override fun generateLottos(manualLottos: List<List<Int>>): List<Lotto> {
        return manualLottos.map { Lotto(*it.toIntArray()) }
    }

    private fun generateLotto(): Lotto {
        val randomNumbers = (LottoNumber.getMinNumber()..LottoNumber.getMaxNumber()).shuffled().take(LOTTO_SIZE).sorted()
        val lotto = randomNumbers.map { LottoNumber.from(it) }
        return Lotto(lotto)
    }

    companion object {
        private const val LOTTO_SIZE = 6
    }
}
