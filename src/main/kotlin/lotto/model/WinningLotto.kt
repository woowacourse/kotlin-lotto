package lotto.model

class WinningLotto(
    val lotto: Lotto,
    private val bonusNumber: LottoNumber,
) {
    init {
        require(!lotto.contains(bonusNumber)) { "당첨 번호와 보너스 번호는 중복될 수 없습니다." }
    }

    fun contains(number: LottoNumber): Boolean = lotto.contains(number)

    fun isBonusMatch(lotto: Lotto): Boolean = lotto.contains(bonusNumber)

}
