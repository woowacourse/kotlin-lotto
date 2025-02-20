package lotto.model

class WinningLotto(private val winningLotto: Lotto, private val bonusNumber: Int) {
    init {
        require(!winningLotto.numbers.contains(bonusNumber)) { "당첨 번호와 보너스 번호는 중복될 수 없습니다." }
    }
}
