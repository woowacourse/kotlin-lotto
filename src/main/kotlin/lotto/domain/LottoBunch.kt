package lotto.domain

class LottoBunch(vararg lotto: Lotto) {
    private var _lottoBunch: List<Lotto> = lotto.toList()
    val lottoBunch: List<Lotto>
        get() = _lottoBunch
}
