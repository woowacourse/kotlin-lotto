package lotto.domain

class LottoBunch(val value: List<Lotto>) {

    override fun toString(): String =
        value.joinToString(separator = LOTTO_BUNCH_TO_STRING_SEPARATOR) { lotto -> lotto.toString() }

    companion object {
        private const val LOTTO_BUNCH_TO_STRING_SEPARATOR = "\n"
    }
}
