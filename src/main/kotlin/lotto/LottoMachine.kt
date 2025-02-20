package lotto

class LottoMachine {
    fun createLotto(): Lotto {
        val lottoNumber =
            (LottoNumber.MIN_NUMBER..LottoNumber.MAX_NUMBER)
                .shuffled()
                .take(LOTTO_NUMBER_COUNT)
                .map { number -> LottoNumber(number) }
                .sortedBy { lottoNumber -> lottoNumber.number }
        return Lotto(lottoNumber)
    }

    companion object {
        private const val LOTTO_NUMBER_COUNT = 6
    }
}
