package lotto.model

class SequentialLottoNumberGenerator(private val numbers: List<Int>) : LottoNumberGenerator {
    private var index = 0
    override fun generate(): Int = numbers[index++]
}
