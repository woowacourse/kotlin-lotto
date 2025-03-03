package lotto.model

class ManualLottoGenerator(
    private val numbers: Set<Int>,
) : LottoGenerator {
    override fun getLottoNumbers(): Lotto = Lotto(numbers.map { LottoNumber(it) }.toSet())
}
