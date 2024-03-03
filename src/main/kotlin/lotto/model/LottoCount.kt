package lotto.model

data class LottoCount(val numberOfTotalLotto: Int, val numberOfManualLotto: Int) {
    init {
        require(numberOfManualLotto <= numberOfTotalLotto) {
            "수동으로 발행 가능한 로또 개수는 총 로또 개수를 넘을 수 없습니다. 현재 입력값 : $numberOfManualLotto, 총 로또 개수 : $numberOfTotalLotto"
        }
    }

    fun getNumberOfAutoTickets(): Int = numberOfTotalLotto - numberOfManualLotto
}
