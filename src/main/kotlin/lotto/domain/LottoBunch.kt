package lotto.domain

class LottoBunch(vararg lotto: Lotto) {
    private var _value: List<Lotto> = lotto.toList()
    val value: List<Lotto>
        get() = _value
}
