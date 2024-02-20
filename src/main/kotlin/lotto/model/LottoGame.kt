package lotto.model

class LottoGame(val lottos: List<Lotto>, val winningLotto: Lotto, val bonusNumber: String) {
    init {
        require(bonusNumber.toIntOrNull() in 1..45)
        require(bonusNumber !in winningLotto.numbers)
    }
}
