package lotto.model

object RandomLottoNumbersGenerator : LottoNumbersGenerator {
    override fun generate(count: Int): List<List<LottoNumber>> =
        List(count) {
            LottoNumber.LOTTO_NUMBER_RANGE.shuffled().take(Lotto.LOTTO_SIZE).sorted()
                .map { LottoNumber.of(it) }
        }
}
