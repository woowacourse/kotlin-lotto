package domain

class LottoFactory(private val numberGenerator: NumberGenerator) {

    fun create(count: Int): List<Lotto> {
        require(count in MINIMUM_COUNT..MAXIMUM_COUNT) { ERROR_CREATE_COUNT.format(count) }

        return List(count) { createLotto() }
    }

    private fun createLotto() = Lotto.create(numberGenerator.generateNumbers())

    companion object {
        private const val MINIMUM_COUNT = 1
        private const val MAXIMUM_COUNT = 100
        private const val ERROR_CREATE_COUNT =
            "한 번에 생성할 수 있는 로또 개수는 ${MINIMUM_COUNT}개 이상 ${MAXIMUM_COUNT}개 이하입니다.\n잘못된 값: %d"
    }
}
