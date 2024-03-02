package lotto.model

class Lottos(val lottos: MutableList<Lotto> = mutableListOf()) {
    fun add(lotto: Lotto) {
        lottos.add(lotto)
    }

    fun getLottos(): List<Lotto> = lottos

    fun printLottoNumbers(): List<String> {
        return lottos.map { lotto ->
            lotto.numbers.joinToString(
                separator = SEPARATOR_DELIMITER,
                prefix = PREFIX_DELIMITER,
                postfix = POSTFIX_DELIMITER,
            ) { it.number.toString() }
        }
    }

    companion object {
        private const val SEPARATOR_DELIMITER = ", "
        private const val PREFIX_DELIMITER = "["
        private const val POSTFIX_DELIMITER = "]"
    }
}
