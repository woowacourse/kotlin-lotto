package lotto.model

class LottoMachine {
    fun createLotto(): Lotto {
        val lottoNumber =
            (LottoNumber.MIN_NUMBER..LottoNumber.MAX_NUMBER)
                .shuffled()
                .take(Lotto.LOTTO_NUMBERS_COUNT)
                .map { number -> LottoNumber(number) }
                .sortedBy { lottoNumber -> lottoNumber.number }
        return Lotto(lottoNumber)
    }
}
