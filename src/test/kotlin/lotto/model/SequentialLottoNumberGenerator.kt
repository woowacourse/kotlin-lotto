package lotto.model

class SequentialLottoNumberGenerator(private val numbers: List<List<Int>>) : LottoGenerator {
    private val index: Int = 0
    override fun generate(): Lotto = Lotto(numbers[index])
}
