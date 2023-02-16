package lotto.domain

class LottoBunch(vararg lotto: Lotto) {
    val value: List<Lotto> = lotto.toList()

    override fun toString(): String =
        value.joinToString(separator = LOTTO_BUNCH_TO_STRING_SEPARATOR) { lotto -> lotto.toString() }

    companion object {
        private const val LOTTO_BUNCH_TO_STRING_SEPARATOR = "\n"
    }
}
