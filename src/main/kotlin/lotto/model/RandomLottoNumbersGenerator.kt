package lotto.model

object RandomLottoNumbersGenerator : LottoNumbersGenerator {
    override fun generate(): List<LottoNumber> =
        LottoNumber.LOTTO_NUMBER_RANGE.shuffled().take(Lotto.LOTTO_SIZE).sorted()
            .map { LottoNumber.of(it) }
}
