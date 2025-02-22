package lotto.model

class LottoNumbersGenerator {
    fun generateLottoNumbers(): List<LottoNumber> =
        (LottoNumber.MIN_NUMBER..LottoNumber.MAX_NUMBER)
            .shuffled()
            .take(Lotto.LOTTO_NUMBERS_COUNT)
            .map { number -> LottoNumber(number) }
}
