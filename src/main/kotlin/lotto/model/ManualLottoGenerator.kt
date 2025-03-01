package lotto.model

class ManualLottoGenerator(private val manualNumbersList: List<List<Int>>) : LottoGenerator {
    override fun generate(count: Int): List<Lotto> {
        require(manualNumbersList.size == count) { "로또 개수와 입력된 번호 개수가 일치하지 않습니다." }

        return manualNumbersList.map { numbers ->
            Lotto.from(numbers) ?: error("잘못된 로또 번호입니다.")
        }
    }
}
