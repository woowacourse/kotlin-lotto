package lotto.model

class AutoLottoLottoNumbersGenerator : LottoNumbersGenerator {
    override fun generate(): List<LottoNumber> =
        (LottoNumber.MIN_NUMBER..LottoNumber.MAX_NUMBER)
            .shuffled()
            .take(Lotto.LOTTO_NUMBERS_COUNT)
            .map { number -> LottoNumber(number) }
}
