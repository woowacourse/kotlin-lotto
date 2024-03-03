package lotto.model

class LottoStore() {
    var lottos = mutableListOf<Lotto>()
        private set

    fun generateManualLottos(manualLottoNumbers: List<String>) {
        val lotto = Lotto.lottoNumbersOf(manualLottoNumbers)
        lottos.add(lotto)
    }

    fun generateAutoLottos(autoLottoCount: Int) {
        repeat(autoLottoCount) {
            val lotto = LottoNumberGeneratorManager.generate(LOTTO_NUMBER_SIZE, MIN_NUMBER, MAX_NUMBER)
            lottos.add(lotto)
        }
    }

    fun showLottos(): List<String> {
        return lottos.map { lotto ->
            lotto.numbers.joinToString(
                separator = SEPARATOR_DELIMITER,
                prefix = PREFIX_DELIMITER,
                postfix = POSTFIX_DELIMITER,
            ) { it.number.toString() }
        }
    }

    companion object {
        private const val LOTTO_NUMBER_SIZE = 6
        private const val MIN_NUMBER = 1
        private const val MAX_NUMBER = 45
        private const val SEPARATOR_DELIMITER = ", "
        private const val PREFIX_DELIMITER = "["
        private const val POSTFIX_DELIMITER = "]"
    }
}
