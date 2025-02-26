package lotto.domain

class ManualLottoGenerator(
    private val numbers: List<List<Int>>,
) : LottoGenerator {
    override fun generate(): List<Lotto> = numbers.map(::Lotto)
}

fun ManualLottoGenerator(numbers: List<Int>): ManualLottoGenerator = ManualLottoGenerator(listOf(numbers))
