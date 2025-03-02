package lotto.domain

class ManualLottoGenerator(private val numbersList: NumbersList) : NumberGenerator {
    override fun generate(): List<LottoNumber> {
        return numbersList.removeFirst().map { LottoNumber.of(it) }
    }
}
