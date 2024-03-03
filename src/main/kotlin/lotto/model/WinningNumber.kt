package lotto.model

class WinningNumber(
    private val lotto: Lotto,
    private val bonusNumber: LottoNumber,
) {
    init {
        require(!lotto.contains(bonusNumber)) {
            "당첨번호에 들어가있는걸 보너스 볼로 지정할 수 없습니다."
        }
    }

    fun matchCount(targetLotto: Lotto): Int {
        return lotto.numbers.intersect(targetLotto.numbers).size
    }

    fun matchBonusNumber(targetLotto: Lotto): Boolean {
        return targetLotto.numbers.contains(bonusNumber)
    }
}
