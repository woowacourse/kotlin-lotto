package lotto

class LottoMachine {
    fun createLotto(): Lotto {
        val lottoNumber =
            (LottoNumber.MAX_NUMBER..LottoNumber.MAX_NUMBER)
                .shuffled()
                .take(LOTTO_NUMBER_COUNT)
                .map { number -> LottoNumber(number) }
        return Lotto(lottoNumber)
    }

    companion object {
        private val LOTTO_NUMBER_COUNT = 6
    }
}
