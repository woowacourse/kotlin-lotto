package lotto.model

class LottoFactory(
    private val randomGenerator: LottoGenerator,
    private val manualNumbers: List<List<Int>>,
) {
    fun createLottos(totalCount: Int): List<Lotto> {
        require(manualNumbers.size <= totalCount) { INVALID_DUPLICATED_MESSAGE }

        val manualLottos =
            manualNumbers.map { numbers ->
                require(numbers.size == 6) { LOTTO_COUNT_MESSAGE }
                ManualLottoGenerator(numbers).generate()
            }

        val randomLottoCount = totalCount - manualLottos.size
        val randomLottos = (1..randomLottoCount).map { randomGenerator.generate() }

        return manualLottos + randomLottos
    }

    companion object {
        const val INVALID_DUPLICATED_MESSAGE = "로또 개수와 입력된 번호 개수가 일치하지 않습니다."
        const val LOTTO_COUNT_MESSAGE = "로또 번호는 6개여야 합니다."
    }
}
