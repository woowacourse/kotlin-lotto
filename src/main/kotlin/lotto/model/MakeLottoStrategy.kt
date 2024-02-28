package lotto.model

sealed class MakeLottoStrategy {

    abstract fun makeLotto(): Lotto

    class MakeSortedRandomLotto(private val range: IntRange) : MakeLottoStrategy() {
        override fun makeLotto(): Lotto {
            val randomNumbers = (range).shuffled().take(LOTTO_SIZE).sorted()
            val lottoNumbers = randomNumbers.map { LottoNumber.from(it) }.toSet()
            return Lotto(lottoNumbers)
        }
    }

    companion object {
        private const val LOTTO_SIZE = 6
    }
}
