package lotto.model

object RandomLottoNumbersGenerator : LottoNumbersGenerator {
    override fun generate(numberOfLottos: Int): List<List<LottoNumber>> =
        List(numberOfLottos) {
            LottoNumber.LOTTO_NUMBER_RANGE.shuffled().take(Lotto.LOTTO_SIZE).sorted()
                .map { LottoNumber.of(it) }
        }
}
