package lotto.model

fun interface LotteriesGenerator {
    fun generate(): List<Lotto>
}

class AutoLotteriesGenerator(private val availableCount: Count) : LotteriesGenerator {
    override fun generate(): List<Lotto> =
        List(availableCount.value) {
            Lotto(*LOTTO_NUM_RANGE.shuffled().take(LOTTO_SIZE).sorted().toIntArray())
        }

    companion object {
        private const val MIN_LOTTO_NUMBER = 1
        private const val MAX_LOTTO_NUMBER = 45
        private val LOTTO_NUM_RANGE = MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER
        private const val LOTTO_SIZE = 6
    }
}

class ManualLotteriesGenerator(
    private val manualCount: Count,
    private val onReceiveNumbers: () -> List<Int>,
) : LotteriesGenerator {
    override fun generate(): List<Lotto> =
        List(manualCount.value) {
            Lotto(*onReceiveNumbers().toIntArray())
        }
}
