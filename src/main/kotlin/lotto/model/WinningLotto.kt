package lotto.model

data class WinningLotto(val lotto: Lotto, val bonus: LottoNumber) {
    init {
        require(lotto.contains(bonus).not()) { "보너스 번호인 ${bonus.value}와 당첨번호인 ${lotto}는 중복되면 안됩니다." }
    }

    fun countMatch(targetLotto: Lotto): Int = (lotto.numbers intersect targetLotto.numbers).size

    fun matchBonus(targetLotto: Lotto): Boolean = targetLotto.contains(bonus)
}
