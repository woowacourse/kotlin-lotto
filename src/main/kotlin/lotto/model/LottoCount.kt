package lotto.model

data class LottoCount(val numberOfTotalLotto: Int, val numberOfManualLotto: Int) {
    init {
        require(numberOfManualLotto <= numberOfTotalLotto) { "수동으로 발행 가능한 로또 개수는 총 로또 개수를 넘을 수 없습니다." }
    }

    fun getNumberOfAutoTickets(): Int = numberOfTotalLotto - numberOfManualLotto

    companion object {
        fun from(
            numberOfTotalLotto: Int,
            numberOfManualLotto: Int,
        ): LottoCount? {
            if (numberOfManualLotto > numberOfTotalLotto) {
                return null
            }
            return LottoCount(numberOfTotalLotto, numberOfManualLotto)
        }
    }
}
