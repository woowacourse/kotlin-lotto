package view

interface InputViewInterface {
    fun inputPaymentMoney(): Int
    fun inputManualLottoCount(): Int
    fun inputManualLottoNumbers(): List<Int>
    fun inputWinningLottoNumbers(): List<Int>
    fun inputBonusNumber(): Int
}
